package com.dmitriy.hw.dao.impl.jdbc.module2;

import com.dmitriy.hw.dao.CustomerDao;
import com.dmitriy.hw.dao.ProjectDao;
import com.dmitriy.hw.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

    private static final String GET_ALL = "SELECT * FROM customers";
    private static final String FIND_BY_ID = "SELECT * FROM customers WHERE id = ?";
    private static final String CREATE = "INSERT INTO customers (name, city) VALUES(?, ?)";
    private static final String DELETE = "DELETE FROM customers WHERE id = ?";
    private static final String SELECT_PROJECTS = "SELECT * FROM projects WHERE customers_id = ?";
    private static final String UPDATE = "UPDATE customers SET name = ?, city = ? WHERE id = ?";
    private ProjectDao projectDao;

    public CustomerDaoImpl() {
        this.projectDao = new ProjectDaoImpl();
    }

    @Override
    public Customer create(Customer item) {
        List<Customer> customers = getAll();
        if (customers.contains(item)) return null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getCity());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            item.setId(resultSet.getLong(1));
            return item;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer read(Long id) {
        Customer customer = null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer(resultSet.getString("name"), resultSet.getString("city"));
                customer.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public Customer update(Customer item) {
        if (item.getId() == null) return null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getCity());
            preparedStatement.setLong(3, item.getId());
            preparedStatement.executeUpdate();
            int flag = preparedStatement.executeUpdate();
            return flag != 0 ? item : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        deleteProjects(id);
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            int flag = preparedStatement.executeUpdate();
            return flag != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Customer customer = new Customer(resultSet.getString("name"), resultSet.getString("city"));
                customer.setId(resultSet.getLong("id"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteProjects(Long customerId) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJECTS)) {
            preparedStatement.setLong(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                projectDao.delete(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}