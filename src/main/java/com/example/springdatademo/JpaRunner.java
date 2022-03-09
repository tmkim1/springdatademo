package com.example.springdatademo;

import com.example.springdatademo.entity.Post;
import com.example.springdatademo.repository.PostRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager em;

    @Autowired
    PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Post post = new Post();
//        post.setTitle("Spring Data JPA");
//
//        Comment comment = new Comment();
//        comment.setComment("스터디 모집합니다.");
//        post.addComment(comment );
//
//        Comment comment1 = new Comment();
//        comment1.setComment("지역은 서울");
//        post.addComment(comment1);
//
//        Session session = em.unwrap(Session.class);
//
//        session.save(post);

//        Post post2 = session.get(Post.class, 1l);
//        System.out.println("post.getTitle() = " + post2.getTitle());

//        JPA ===>
        postRepository.findAll().forEach(System.out::println);

    }
}
