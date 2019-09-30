package com.hwua.entity;

import lombok.Data;

@Data
public class Message {
    private long id;
    private String title;
    private String msgcontent;
    private String msgCreateDate;
}
