package com.example.springdatademo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager em;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("taemin");
        account.setPassword("passwordJpa");

        Study study = new Study();
        study.setName("JPA Study");

        //양방향이라면 한 묶음으로 둘 다 정보를 가지고 있어야 함. (서로의 reference가 필요)
//        account.getStudies().add(study);
//        study.setOwner(account);
        //convenient method를 생성
        account.addStudy(study);

        em.persist(account);
    }
}
