package com.example.springdatademo;

import com.example.springdatademo.repository.MyRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

@SpringBootApplication
@EnableJpaRepositories(repositoryImplementationPostfix = "impl", repositoryBaseClass = MyRepositoryImpl.class) //impl은 기본값 (다른거 사용할 때 해당 어노테이션 사용
public class SpringdatademoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdatademoApplication.class, args);
    }

}
