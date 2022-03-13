package com.example.springdatademo.repository;

import com.example.springdatademo.entity.Comment;
import com.example.springdatademo.entity.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
public   class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    public void  crud() {
        // Given
        Comment comment = new Comment();
        comment.setComment("태민아, 안녕?");
        comment.setLikeCount(100);
        commentRepository.save(comment);

        Comment comment2 = new Comment();
        comment2.setComment("혜영아, 안녕?");
        comment2.setLikeCount(101);

        Post pagiePost = new Post();
        pagiePost.setTitle("Paige's post");
        postRepository.save(pagiePost);

        comment2.setPost(pagiePost);
        commentRepository.save(comment2);

        // When
        List<Comment> all = commentRepository.findByCommentContains("안녕");
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount"));
//        Page<Comment> likeComments = commentRepository.findByLikeCountGreaterThanAndPost(100, pagiePost, Pageable.ofSize(1));
        Page<Comment> highLikedComments = commentRepository.findByLikeCountGreaterThanAndPost(100, pagiePost, pageRequest);

        // Then
        assertThat(all.size()).isEqualTo(2);
        assertThat(highLikedComments.getNumberOfElements()).isEqualTo(1);
        assertThat(highLikedComments).first().hasFieldOrPropertyWithValue("likeCount", 101);

        //단일 값을 받아오는 경우는 null을 가져오는 경우도 많기 때문에 Optional 처리를 하면 훨씬 관리가 편해진다.
//        Optional<Comment> byId = commentRepository.findById(100l);
//        assertThat(byId).isEmpty();
//        Comment comment = byId.orElseThrow(IllegalArgumentException::new);

        //반면 아래와 같은 List 타입은 null이 나오지 않고  비어있는 Collection이 나오기 때문에 (JPA 특성) Optional을 사용 할 필요가 없음.
//        List<Comment> comments = commentRepository.findAll();
//        assertThat(comments).isEmpty();


    }

}