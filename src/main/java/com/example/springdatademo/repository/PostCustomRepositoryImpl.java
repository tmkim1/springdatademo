package com.example.springdatademo.repository;

import com.example.springdatademo.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PostCustomRepositoryImpl implements PostCustomRepository{

    @Autowired
    EntityManager em;

    @Override
    public List<Post> findMyPost() {
        return em.createQuery( "SELECT p FROM Post AS p", Post.class)
                 .getResultList();
    }
}
