package com.example.springdatademo.repository;

import com.example.springdatademo.entity.Comment;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository extends MyRepository{

}
