package com.lib4j.processor;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class processor implements
        ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            // 只在初始化“根上下文”的时候执行
            // final ApplicationContext app = event.getApplicationContext();

        } catch (Exception e) {

        }
    }

}