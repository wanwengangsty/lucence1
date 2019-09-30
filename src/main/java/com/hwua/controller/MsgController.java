package com.hwua.controller;

import com.hwua.entity.Message;
import com.hwua.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MsgController {
    @Autowired
    private MsgService msgService;
    @PostMapping("/query")
    public String findMsgs(String queryStr, Model model) throws Exception{
        List<Message> msgList = msgService.searchMsg(queryStr);
        model.addAttribute("msgList",msgList);
        return "show";
    }
}
