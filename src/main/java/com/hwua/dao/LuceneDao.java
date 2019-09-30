package com.hwua.dao;

import com.hwua.entity.Message;

import java.util.List;

public interface LuceneDao {
    //把集合写入到索引库
    public void createMsgIndex(List<Message> msgList) throws Exception;
    //根据输入的查询字符串通过分词后查询message
    public List<Message> searchMsg(String queryString) throws Exception;

}
