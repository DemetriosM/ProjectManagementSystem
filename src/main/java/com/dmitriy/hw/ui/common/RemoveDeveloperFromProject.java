package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Developer;
import com.dmitriy.hw.model.Project;

public class RemoveDeveloperFromProject extends AbstractDeveloperAction {

    @Override
    public void execute() {
        System.out.println("Select developer.");
        Developer developer = commandLine.choose(developerDao.getAll());
        System.out.println("Select project.");
        Project project = commandLine.choose(developerDao.getDeveloperProjects(developer.getId()));
        developerDao.connectToProject(developer.getId(), project.getId());
    }

    @Override
    public String toString() {
        return "Remove developer from project.";
    }
}
