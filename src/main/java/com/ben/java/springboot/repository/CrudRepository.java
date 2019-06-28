package com.ben.java.springboot.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

@NoRepositoryBean
public interface CrudRepository<T, ID extends Serializable>  extends Repository<T, ID> {

    <S extends T> S save(S entity);


    void delete(T entity);

    T findById(ID primaryKey);

    Iterable<T> findAll();

    long count();

    boolean existsById(ID primaryKey);

}