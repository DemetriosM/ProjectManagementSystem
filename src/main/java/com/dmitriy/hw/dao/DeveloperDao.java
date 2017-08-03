package com.dmitriy.hw.dao;

import com.dmitriy.hw.model.Developer;
import com.dmitriy.hw.model.Project;
import com.dmitriy.hw.model.Skill;

import java.util.List;

public interface DeveloperDao extends BaseDao<Developer> {
    List<Skill> getDeveloperSkills(Long developerId);
    boolean addSkill(Long developerId, Long skillId);
    boolean removeSkill(Long developerId, Long skillId);
    List<Project> getDeveloperProjects(Long developerId);
    boolean connectToProject(Long developerId, Long projectId);
    boolean disconnectFromProject(Long developerId, Long projectId);
}
