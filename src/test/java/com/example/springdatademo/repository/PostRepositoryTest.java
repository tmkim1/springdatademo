package com.example.springdatademo.repository;

import com.example.springdatademo.commands.PostPublishedEvent;
import com.example.springdatademo.entity.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@Import(PostRepositoryTestConfig.class)
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

//    AggregateRoot를 사용하면 아래와 같이 이벤트를 생성해서 주입 할 필요 없이 저장하는 메서드에서 바로 이벤트를 발행해주면 됨.
//    @Autowired
//    ApplicationContext applicationContext;
//
//    @Test
//    public void event() {
//        Post post = new Post();
//        post.setTitle("event");
//
//        PostPublishedEvent event = new PostPublishedEvent(post);
//        applicationContext.publishEvent(event);
//    }

    @Test
    public void customRepository() {

        //Given
        String title = "custom";
        Post post = new Post();
        post.setTitle(title);

        //When
        postRepository.save(post.publish());
        List<Post> customPosts = postRepository.findAll();

        //Then
        assertThat(customPosts.size()).isEqualTo(1);
        assertThat(customPosts.get(0).getTitle()).isEqualTo(title);
    }

    @Test
    @Rollback(value = false)
    public void crudRepository() {
        //Given
        Post post = new Post();
        post.setTitle("Hello Spring Boot Common");
        assertThat(post.getId()).isNull();

        assertThat(postRepository.contains(post)).isFalse(); //Transient

        //When
        Post newPost = postRepository.save(post);

        //Then
        assertThat(postRepository.contains(newPost)).isTrue(); //Persist
        assertThat(newPost.getId()).isNotNull();

        //When
        List<Post> posts = postRepository.findAll();

        //Then
        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts).contains(newPost);

        //When
        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));
        //Then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);

        //When
        postRepository.findByTitleContains("Spring", PageRequest.of(0, 10));
        //Then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);

        //When
        long springCnt = postRepository.countByTitleContains("Spring");
        assertThat(springCnt).isEqualTo(1);
    }
}