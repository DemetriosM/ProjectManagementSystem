package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Project;

import java.util.List;

public class DisplayProjectsAction extends AbstractProjectAction{

    @Override
    public void execute() {
        List<Project> projects = projectDao.getAll();
        for (Project project: projects) {
            System.out.println(project);
        }
    }

    @Override
    public String toString() {
        return "Display all projects";
    }
}
