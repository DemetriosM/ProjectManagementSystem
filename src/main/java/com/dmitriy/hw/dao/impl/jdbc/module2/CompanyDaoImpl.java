package com.dmitriy.hw.dao.impl.jdbc.module2;

import com.dmitriy.hw.dao.CompanyDao;
import com.dmitriy.hw.dao.DeveloperDao;
import com.dmitriy.hw.model.Company;
import com.dmitriy.hw.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl extends BaseDaoImpl<Company> implements CompanyDao {

    private static final String GET_ALL = "SELECT * FROM companies";
    private static final String FIND_BY_ID = "SELECT * FROM companies WHERE id = ?";
    private static final String CREATE = "INSERT INTO companies (name, city) VALUES(?, ?)";
    private static final String DELETE = "DELETE FROM companies WHERE id = ?";
    private static final String DISCONNECT_PROJECTS = "DELETE FROM companies_has_projects WHERE companies_id = ?";
    private static final String SELECT_DEVELOPERS = "SELECT developers.id FROM developers WHERE companies_id = ?";
    private static final String UPDATE = "UPDATE companies SET name = ?, city = ? WHERE id = ?";
    private static final String COMPANY_HAS_PROJECTS =
            "SELECT projects.id, projects.name, customers_id, cost  FROM projects, companies_has_projects WHERE companies_id = ? and projects.id = projects_id";
    private static final String ADD_PROJECT = "INSERT INTO companies_has_projects (companies_id, projects_id) VALUES (?, ?)";
    private static final String REMOVE_PROJECT = "DELETE FROM companies_has_projects WHERE companies_id = ? and projects_id = ?";
    private DeveloperDao developerDao;

    public CompanyDaoImpl() {
        developerDao = new DeveloperDaoImpl();
    }

    @Override
    public Company create(Company item) {
        List<Company> companies = getAll();
        if (companies.contains(item)) return null;
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
    public Company read(Long id) {
        Company company = null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                company = new Company(resultSet.getString("name"), resultSet.getString("city"));
                company.setId(id);
                company.setProjects(getCompanyProjects(id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return company;
    }

    @Override
    public Company update(Company item) {
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
        disconnectAllProjects(id);
        deleteDevelopers(id);
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
    public List<Company> getAll() {
        List<Company> companies = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Company company = new Company(resultSet.getString("name"), resultSet.getString("city"));
                company.setId(resultSet.getLong("id"));
                company.setProjects(getCompanyProjects(company.getId()));
                companies.add(company);
            }
            return companies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Project> getCompanyProjects(Long companyId) {
        List<Project> projects = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COMPANY_HAS_PROJECTS)) {
            preparedStatement.setLong(1, companyId);
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

    @Override
    public boolean connectProject(Long companyId, Long projectId) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PROJECT)) {
            preparedStatement.setLong(1, companyId);
            preparedStatement.setLong(2, projectId);
            int flag = preparedStatement.executeUpdate();
            return flag != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean disconnectProject(Long companyId, Long projectId) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_PROJECT)) {
            preparedStatement.setLong(1, companyId);
            preparedStatement.setLong(2, projectId);
            int flag = preparedStatement.executeUpdate();
            return flag != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void disconnectAllProjects(Long companyId) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DISCONNECT_PROJECTS)) {
            preparedStatement.setLong(1, companyId);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteDevelopers(Long companyId) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVELOPERS)) {
            preparedStatement.setLong(1, companyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                developerDao.delete(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}