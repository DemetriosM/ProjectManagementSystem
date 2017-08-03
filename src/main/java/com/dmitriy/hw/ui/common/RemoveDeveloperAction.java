package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Developer;

public class RemoveDeveloperAction extends AbstractDeveloperAction{

    @Override
    public void execute() {
        System.out.println("Select developer:");
        Developer developer = commandLine.choose(developerDao.getAll());
        developerDao.delete(developer.getId());
    }

    @Override
    public String toString() {
        return "Remove developer.";
    }
}
