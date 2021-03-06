package com.example.springdatademo.repository;

import lombok.NonNull;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface MyCustomRepository<T, Id extends Serializable> extends Repository<T, Id> {
    <E extends T> E save (@NonNull E entity);

    List<T> findAll();

    Long count();

    @Nullable
    <E extends T> Optional<E> findById(Id id);

}
