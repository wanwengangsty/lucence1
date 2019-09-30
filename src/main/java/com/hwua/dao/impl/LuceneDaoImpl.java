package com.hwua.dao.impl;

import com.hwua.dao.LuceneDao;
import com.hwua.entity.Message;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LuceneDaoImpl implements LuceneDao {
    @Autowired
    private IndexWriter indexWriter;/*写索引*/
    @Autowired
    private Analyzer analyzer;/*分析器*/
    @Override
    public void createMsgIndex(List<Message> msgList) throws Exception {
        List<Document> docList= new ArrayList<>();//Document文档的集合,一条记录就封装成一个Document对象
        for(Message msg:msgList){
            Document document = new Document();
            //获取每条记录中的数据
            Long id = msg.getId();
            String title = msg.getTitle();
            String msgContent = msg.getMsgcontent();
            String msgCreateDate = msg.getMsgCreateDate();
            document.add(new StringField("id",id+"", Field.Store.YES));
            document.add(new TextField("title",title,Field.Store.YES));
            document.add(new TextField("msgContent",msgContent,Field.Store.YES));
            document.add(new StringField("msgCreateDate",msgCreateDate,Field.Store.YES));
            docList.add(document);
        }
        //把所有的文档写到索引库
        indexWriter.addDocuments(docList);
        indexWriter.commit();
    }

    @Override
    public List<Message> searchMsg(String queryString) throws Exception {
        Directory directory = FSDirectory.open(new File("d:/lucene/indexDir").toPath());
        IndexReader indexReader = DirectoryReader.open(directory);//以读的方式打开索引库
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);//创建索引库查询器对象
        QueryParser queryParser = new QueryParser("msgContent",analyzer);
        Query query = queryParser.parse(queryString);
        TopDocs topDocs = indexSearcher.search(query, 20);
        System.out.println("查询结果的数量:"+topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        List<Message> msgList = new ArrayList<>();
        for(ScoreDoc scoreDoc:scoreDocs){
            int docId = scoreDoc.doc;//获取查询到的文档id
            Document document = indexSearcher.doc(docId);
            Message msg = new Message();
            msg.setId(Long.parseLong(document.get("id")));
            msg.setTitle(document.get("title"));
            msg.setMsgcontent(document.get("msgContent"));
            msg.setMsgCreateDate(document.get("msgCreateDate"));
            msgList.add(msg);
        }
        return msgList;
    }
}
