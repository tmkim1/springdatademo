package com.example.springdatademo.repository;

import com.example.springdatademo.entity.Comment;
import com.example.springdatademo.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Stream;

public interface CommentRepository extends MyRepository<Comment, Long> {
    List<Comment> findByCommentContains(String comment);

    //Pageable은 sort까지 포함하고 있음
    //아래와 같이 JPA를 이용하여 쿼리를 만들었을 때, 제대로 만든건지 확인하려면 Test 코드 실행 => 빈 등록 될 때 잘못 된 쿼리는 에러가 발생
    Page<Comment> findByLikeCountGreaterThanAndPost(Integer likeCount, Post post, Pageable pageable);
}
 