package com.example.springdatademo.repository;

import com.example.springdatademo.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
public   class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void  crud() {
//        Comment comment = new Comment();
//        comment.setComment("안녕");
//        commentRepository.save(comment);
//
//        List<Comment> all = commentRepository.findAll();
//        assertThat(all.size()).isEqualTo(1);

        //단일 값을 받아오는 경우는 null을 가져오는 경우도 많기 때문에 Optional 처리를 하면 훨씬 관리가 편해진다.
//        Optional<Comment> byId = commentRepository.findById(100l);
//        assertThat(byId).isEmpty();
//        Comment comment = byId.orElseThrow(IllegalArgumentException::new);

        //반면 아래와 같은 List 타입은 null이 나오지 않고  비어있는 Collection이 나오기 때문에 (JPA 특성) Optional을 사용 할 필요가 없음.
        List<Comment> comments = commentRepository.findAll();
        assertThat(comments).isEmpty();


    }

}