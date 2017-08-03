package com.dmitriy.hw.dao.impl.jdbc.module2;

import com.dmitriy.hw.dao.DeveloperDao;
import com.dmitriy.hw.model.Developer;
import com.dmitriy.hw.model.Project;
import com.dmitriy.hw.model.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDaoImpl extends BaseDaoImpl<Developer> implements DeveloperDao {

    private static final String GET_ALL = "SELECT * FROM developers";
    private static final String FIND_BY_ID = "SELECT * FROM developers WHERE id = ?";
    private static final String CREATE = "INSERT INTO developers (surname, name, salary, companies_id) VALUES(?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM developers WHERE id = ?";
    private static final String DISCONNECT_SKILLS = "DELETE FROM developers_has_skills WHERE developers_id = ?";
    private static final String DISCONNECT_PROJECTS = "DELETE FROM developers_has_projects WHERE developers_id = ?";
    private static final String UPDATE =
            "UPDATE developers SET surname = ?, name = ?, salary = ?, companies_id = ? WHERE id = ?";
    private static final String DEVELOPER_HAS_SKILLS =
            "SELECT skills.id, lang FROM developers_has_skills, skills WHERE developers_id = ? and skills_id = skills.id;";
    private static final String ADD_SKILL = "INSERT INTO developers_has_skills (developers_id, skills_id) VALUES (?, ?)";
    private static final String REMOVE_SKILL = "DELETE FROM developers_has_skills WHERE developers_id = ? and skills_id = ?";
    private static final String DEVELOPER_HAS_PROJECTS =
            "SELECT projects.id, projects.name, customers_id, cost  FROM projects, developers_has_projects WHERE developers_id = ? and projects.id = projects_id;";
    private static final String ADD_PROJECT = "INSERT INTO developers_has_projects (developers_id, projects_id) VALUES (?, ?)";
    private static final String REMOVE_PROJECT = "DELETE FROM developers_has_projects WHERE developers_id = ? and projects_id = ?";

    @Override
    public Developer create(Developer item) {
        List<Developer> developers = getAll();
        if (developers.contains(item)) return null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getSurname());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setInt(3, item.getSalary());
            preparedStatement.setLong(4, item.getCompanyId());
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
    public Developer read(Long id) {
        Developer developer = null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                developer = new Developer(resultSet.getString("surname"), resultSet.getString("name"),
                        resultSet.getInt("salary"), resultSet.getLong("companies_id"));
                developer.setId(id);
                developer.setSkills(getDeveloperSkills(id));
                developer.setProjects(getDeveloperProjects(id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return developer;
    }

    @Override
    public Developer update(Developer item) {
        if (item.getId() == null) return null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, item.getSurname());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setInt(3, item.getSalary());
            preparedStatement.setLong(4, item.getCompanyId());
            preparedStatement.setLong(5, item.getId());
            preparedStatement.executeUpdate();
            int flag = preparedStatement.executeUpdate();
            return flag != 0 ? item : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        disconnectAllSkills(id);
        disconnectAllProjects(id);
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
    public List<Developer> getAll() {
        List<Developer> developers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Developer developer =
                        new Developer(resultSet.getString("surname"), resultSet.getString("name"),
                                resultSet.getInt("salary"), resultSet.getLong("companies_id"));
                developer.setId(resultSet.getLong("id"));
                developer.setSkills(getDeveloperSkills(developer.getId()));
                developer.setProjects(getDeveloperProjects(developer.getId()));
                developers.add(developer);
            }
            return developers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Skill> getDeveloperSkills(Long developerId) {
        List<Skill> skills = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DEVELOPER_HAS_SKILLS)) {
            preparedStatement.setLong(1, developerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Skill skill =
                        new Skill(resultSet.getString("lang"));
                skill.setId(resultSet.getLong("id"));
                skills.add(skill);
            }
            return skills;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addSkill(Long developerId, Long skillId) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_SKILL)) {
            preparedStatement.setLong(1, developerId);
            preparedStatement.setLong(2, skillId);
            int flag = preparedStatement.executeUpdate();
            return flag != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeSkill(Long developerId, Long skillId) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_SKILL)) {
            preparedStatement.setLong(1, developerId);
            preparedStatement.setLong(2, skillId);
            int flag = preparedStatement.executeUpdate();
            return flag != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Project> getDeveloperProjects(Long developerId) {
        List<Project> projects = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DEVELOPER_HAS_PROJECTS)) {
            preparedStatement.setLong(1, developerId);
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
    public boolean connectToProject(Long developerId, Long projectId) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PROJECT)) {
            preparedStatement.setLong(1, developerId);
            preparedStatement.setLong(2, projectId);
            int flag = preparedStatement.executeUpdate();
            return flag != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean disconnectFromProject(Long developerId, Long projectId) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_PROJECT)) {
            preparedStatement.setLong(1, developerId);
            preparedStatement.setLong(2, projectId);
            int flag = preparedStatement.executeUpdate();
            return flag != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void disconnectAllSkills(Long developerId) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DISCONNECT_SKILLS)) {
            preparedStatement.setLong(1, developerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void disconnectAllProjects(Long developerId) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DISCONNECT_PROJECTS)) {
            preparedStatement.setLong(1, developerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}