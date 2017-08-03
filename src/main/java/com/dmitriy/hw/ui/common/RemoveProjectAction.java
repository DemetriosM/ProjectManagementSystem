package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Project;

public class RemoveProjectAction extends AbstractProjectAction {

    @Override
    public void execute() {
        System.out.println("Select project:");
        Project project = commandLine.choose(projectDao.getAll());
        projectDao.delete(project.getId());
    }

    @Override
    public String toString() {
        return "Remove project.";
    }
}
