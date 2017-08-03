package com.dmitriy.hw.dao.impl.jdbc.module2;

import com.dmitriy.hw.dao.ProjectDao;
import com.dmitriy.hw.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl extends BaseDaoImpl<Project> implements ProjectDao {
    private static final String GET_ALL = "SELECT * FROM projects";
    private static final String FIND_BY_ID = "SELECT * FROM projects WHERE id = ?";
    private static final String CREATE = "INSERT INTO projects (name, customers_id, cost) VALUES(?, ?, ?)";
    private static final String DELETE = "DELETE FROM projects WHERE id = ?";
    private static final String DISCONNECT_DEVELOPERS = "DELETE FROM developers_has_projects WHERE projects.id = ?";
    private static final String DISCONNECT_COMPANIES = "DELETE FROM companies_has_projects WHERE companies.id = ?";
    private static final String UPDATE = "UPDATE projects SET name = ?, customers_id = ?, cost = ? WHERE id = ?";

    @Override
    public Project create(Project item) {
        List<Project> projects = getAll();
        if (projects.contains(item)) return null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setLong(2, item.getCustomerId());
            preparedStatement.setInt(3, item.getCost());
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
    public Project read(Long id) {
        Project project = null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                project = new Project(resultSet.getString("name"),
                        resultSet.getLong("customers_id"), resultSet.getInt("cost"));
                project.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return project;
    }

    @Override
    public Project update(Project item) {
        if (item.getId() == null) return null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setLong(2, item.getCustomerId());
            preparedStatement.setInt(3, item.getCost());
            preparedStatement.setLong(4, item.getId());
            preparedStatement.executeUpdate();
            int flag = preparedStatement.executeUpdate();
            return flag != 0 ? item : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        disconnectDevelopers(id);
        disconnectCompanies(id);
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
    public List<Project> getAll() {
        List<Project> projects = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Project project = new Project(resultSet.getString("name"),
                        resultSet.getLong("customers_id"), resultSet.getInt("cost"));
                project.setId(resultSet.getLong("id"));
                projects.add(project);
            }
            return projects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void disconnectDevelopers(Long projectId) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DISCONNECT_DEVELOPERS)) {
            preparedStatement.setLong(1, projectId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void disconnectCompanies(Long projectId) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DISCONNECT_COMPANIES)) {
            preparedStatement.setLong(1, projectId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
