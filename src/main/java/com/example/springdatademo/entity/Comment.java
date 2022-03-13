package com.example.springdatademo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    @ManyToOne // ManyToOne의 기본 값은 fetch = Eager (즉시 로딩) 어차피 N:1 관계 => 1만 가져오면 되니까.
    @JoinColumn(name = "post_id")
    private Post post;

    private Integer likeCount;

}
