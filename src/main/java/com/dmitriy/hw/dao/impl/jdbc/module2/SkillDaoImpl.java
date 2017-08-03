package com.dmitriy.hw.dao.impl.jdbc.module2;

import com.dmitriy.hw.dao.SkillDao;
import com.dmitriy.hw.model.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends BaseDaoImpl<Skill> implements SkillDao{

    private static final String GET_ALL = "SELECT * FROM skills";
    private static final String FIND_BY_ID = "SELECT * FROM skills WHERE id = ?";
    private static final String CREATE = "INSERT INTO skills (lang) VALUES(?)";
    private static final String DELETE = "DELETE FROM skills WHERE id = ?";
    private static final String UPDATE = "UPDATE skills SET lang = ? WHERE id = ?";

    @Override
    public Skill create(Skill item) {
        List<Skill> skills = getAll();
        if (skills.contains(item)) return null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getLang());
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
    public Skill read(Long id) {
        Skill skill = null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                skill = new Skill(resultSet.getString("lang"));
                skill.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return skill;
    }

    @Override
    public Skill update(Skill item) {
        if (item.getId() == null) return null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, item.getLang());
            preparedStatement.setLong(2, item.getId());
            preparedStatement.executeUpdate();
            int flag = preparedStatement.executeUpdate();
            return flag != 0 ? item : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
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
    public List<Skill> getAll() {
        List<Skill> skills = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Skill skill = new Skill(resultSet.getString("lang"));
                skill.setId(resultSet.getLong("id"));
                skills.add(skill);
            }
            return skills;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
