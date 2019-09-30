package com.hwua.mapper;

import com.hwua.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MsgMapper {
    @Select("select id,title,msgcontent,msg_create_date msgCreateDate from messages")
    public List<Message> findAllMessages() throws Exception;
}
