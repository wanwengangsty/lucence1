package com.hwua.service;

import com.hwua.entity.Message;

import java.util.List;

public interface MsgService {
    //创建Message索引库
    public void createMsgIndex() throws Exception;
    //从索引库中去查询短消息
    public List<Message> searchMsg(String queryString) throws Exception;
}
