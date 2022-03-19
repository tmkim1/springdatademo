package com.example.springdatademo;

import com.example.springdatademo.commands.PostPublishedEvent;
import org.springframework.context.event.EventListener;

public class PostListener{

    //implements ApplicationListener<PostPublishedEvent>
    //@Override => 아래 어노테이션 (스프링 지원)으로 간단하게 리스너 등록 가능
    @EventListener
    public void onApplicationEvent(PostPublishedEvent event) {
        System.out.println("-----------");
        System.out.println(event.getPost().getTitle() + " is published!! ");
        System.out.println("-----------");
    }
}
