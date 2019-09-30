package com.hwua.init;

import com.hwua.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
/*当程序一加载,实现ApplicationRunner对象的run方法也会跟着执行*/
public class MsgRunner implements ApplicationRunner {
    @Autowired
    private MsgService msgService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("init......");
        msgService.createMsgIndex();//查询并创建索引库

    }
}
