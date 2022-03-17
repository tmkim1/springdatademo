package com.example.springdatademo.repository;

import com.example.springdatademo.entity.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    public void customRepository() {

        //Given
        String title = "custom";
        Post post = new Post();
        post.setTitle(title);

        //When
        postRepository.save(post);
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