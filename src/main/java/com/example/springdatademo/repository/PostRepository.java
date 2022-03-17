package com.example.springdatademo.repository;

import com.example.springdatademo.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

// -- abstract는 상속을 하나만 받을 수 있지만 Interface는 다중 상속이 가능하다.
public interface PostRepository extends MyRepository<Post, Long> {
    Page<Post> findByTitleContains(String title, Pageable pageable);

    long countByTitleContains(String spring);
}
