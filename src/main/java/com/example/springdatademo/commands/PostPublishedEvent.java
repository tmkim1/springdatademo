package com.example.springdatademo.commands;

import com.example.springdatademo.entity.Post;
import org.springframework.context.ApplicationEvent;

public class PostPublishedEvent extends ApplicationEvent {

    private final Post post;

    public PostPublishedEvent(Object source) {
        super(source);
        this.post = (Post) source; //Event를 발생하는 곳을 Post라고 가정
    }

    public Post getPost() {
        return post;
    }
}
