package com.example.springdatademo.entity;

import com.example.springdatademo.commands.PostPublishedEvent;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 도메인 이벤트
 * Entity로 설정 해놓은 도메인에서 상태를 변경하는 행위에 대한 이벤트 기능을 구현
 *
 * ApplicationContext는 BeanFactory 상속, ApplicationEventPublisher를 상속 받고 있음
 *
 * AbstractAggregateRoot<Post> => 도메인 이벤트를 모와 놓는 곳, 이벤트를 비우는 곳에 대한 메서드가 존재
 *
 */


@Entity
public class Post extends AbstractAggregateRoot<Post> {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL) //@OneToMany의 기본 값은 Fetch = Lazy (지연 로딩)
    private Set<Comment> comments = new HashSet<>();

    public void addComment(Comment comment) {
        this.getComments().add(comment);
        comment.setPost(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String  getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                '}';
    }

    public Post publish() {
        //AbstractAggr egateRoot를 상속받아 registerEvent 메서드로 이벤트를 바로 주입하여 사용 가능
        //registerEvent => domainEvents.add(event);
        this.registerEvent(new PostPublishedEvent(this));
        return this;
    }
}
