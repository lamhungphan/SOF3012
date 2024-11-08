package com.fpoly.sof3012.dao;
import java.util.List;

public interface Dao<T> {
    List<T> findAll();
    T findById(String id);
    T create(T entity);
    T update(T entity);
    void deleteById(String id);
}
