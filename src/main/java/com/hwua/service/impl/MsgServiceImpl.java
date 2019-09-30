package com.hwua.service.impl;

import com.hwua.dao.LuceneDao;
import com.hwua.entity.Message;
import com.hwua.mapper.MsgMapper;
import com.hwua.service.MsgService;
import org.apache.lucene.index.IndexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    private MsgMapper msgMapper;
    @Autowired
    private LuceneDao luceneDao;

    /**
     * 把数据库中查询到的Message对象写到索引库中
     * @throws Exception
     */
    @Override
    public void createMsgIndex() throws Exception {
        List<Message> msgList = msgMapper.findAllMessages();// 从数据库中读取数据
        luceneDao.createMsgIndex(msgList);//把数据写到索引库中
    }

    @Override
    public List<Message> searchMsg(String queryString) throws Exception {
        return luceneDao.searchMsg(queryString);
    }
}
