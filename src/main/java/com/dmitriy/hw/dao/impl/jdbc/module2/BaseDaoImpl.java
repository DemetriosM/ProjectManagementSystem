package com.dmitriy.hw.dao.impl.jdbc.module2;

import com.dmitriy.hw.dao.BaseDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    private final static String URL =
            "jdbc:mysql://localhost:3306/dev?useSSL=true&serverTimezone=UTC";
    private final static String USER = "root";
    private final static String PASSWORD = "mysql123";

    final Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public abstract T create(T item);

    @Override
    public abstract T read(Long id);

    @Override
    public abstract T update(T item);

    @Override
    public abstract boolean delete(Long id);

    @Override
    public abstract List<T> getAll();
}
