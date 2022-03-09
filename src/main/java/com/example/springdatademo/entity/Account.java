package com.example.springdatademo.entity;

import com.example.springdatademo.vo.Address;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @Id @GeneratedValue
    private Long id;

    //@Column @Entity가 붙은 클래스안에 필드들은 사실상 적지 않아도 @Column이 붙어 있음
    //@Column(nullable = false, unique = true) 같은 부가기능을 사용할때만 명시하고 기본 사용 권장
    private String username;

    private String password;

    //foreign key를 가진 엔티티가 owner가 된다 + 엔티티간 서로 @OneToMany, @ManyToOne만 해준다고 해서 양 방향 관계가 되는 것이 아님,
    //2개의 단방향 관계 => 양방향임을 알려주기 위해서 mappedBy를 사용한다.
    @OneToMany(mappedBy = "owner")
    private Set<Study> studies = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Transient //컬럼으로 맵핑하고 싶지 않은 멤버 변수
    private String no;

    @Embedded
    private Address address;
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

    public Set<Study> getStudies() {
        return studies;
    }

    public void setStudies(Set<Study> studies) {
        this.studies = studies;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addStudy(Study study) {
        this.getStudies().add(study);
        study.setOwner(this);
    }

    public void removeStudy(Study study) {
        this.getStudies().remove(study);
        study.setOwner(null);
    }
}
