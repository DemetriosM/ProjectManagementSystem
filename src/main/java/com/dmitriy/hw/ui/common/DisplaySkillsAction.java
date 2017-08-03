package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Skill;

import java.util.List;

public class DisplaySkillsAction extends AbstractSkillAction {

    @Override
    public void execute() {
        List<Skill> skills = skillDao.getAll();
        for (Skill skill: skills) {
            System.out.println(skills);
        }
    }

    @Override
    public String toString() {
        return "Display all skills";
    }
}
