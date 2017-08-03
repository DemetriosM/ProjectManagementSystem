package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Developer;

import java.util.List;

public class DisplayDevelopersAction extends AbstractDeveloperAction{

    @Override
    public void execute() {
        List<Developer> developers = developerDao.getAll();
        for (Developer developer: developers) {
            System.out.println(developer);
        }
    }

    @Override
    public String toString() {
        return "Display all developers";
    }
}
