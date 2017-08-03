package com.dmitriy.hw.dao;

import java.util.List;

public interface BaseDao<T> {
    T create(T item);
    T read(Long id);
    T update(T item);
    boolean delete(Long id);
    List<T> getAll();
}
