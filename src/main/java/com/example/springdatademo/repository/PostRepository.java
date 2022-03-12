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

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByTitleContains(String title, Pageable pageable);

    long countByTitleContains(String spring);
}
