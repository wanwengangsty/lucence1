package com.hwua.config;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

@Configuration
public class LuceneConfig {
    //索引库的位置
    private static final String INDEXPATH="d:/lucene/indexDir";

    @Bean
    public Analyzer analyzer(){
        return new IKAnalyzer();
    }

    /**
     * 创建索引库directory对象
     * @return
     * @throws Exception
     */
    @Bean
    public Directory directory() throws Exception{
        File dir = new File(INDEXPATH);
        if(!dir.exists()){
            dir.mkdirs();//创建不存在的索引库目录
        }
        return FSDirectory.open(dir.toPath());//创建一个Directory对象
    }

    /**
     * 创建IndeWriter,用此对象把数据写到索引库中
     * @param directory
     * @param analyzer
     * @return
     * @throws Exception
     */
    @Bean
    public IndexWriter indexWriter(Directory directory, Analyzer analyzer) throws Exception{
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(analyzer));

        System.out.println("删除索引库");//清空索引库
        indexWriter.deleteAll();
        indexWriter.commit();
        return indexWriter;
    }

}
