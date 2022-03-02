package com.example.springdatademo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Account {

    @Id @GeneratedValue
    private Long id;

    //@Column @Entity가 붙은 클래스안에 필드들은 사실상 적지 않아도 @Column이 붙어 있음
    //@Column(nullable = false, unique = true) 같은 부가기능을 사용할때만 명시하고 기본 사용 권장
    private String username;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Transient //컬럼으로 맵핑하고 싶지 않은 멤버 변수
    private String no;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
