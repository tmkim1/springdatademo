package com.example.springdatademo.repository;

import com.example.springdatademo.entity.Post;

import java.util.List;

public interface PostCustomRepository {
    List<Post> findMyPost();
}
