package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Developer;
import com.dmitriy.hw.model.Skill;

public class RemoveSkillFromDeveloper extends AbstractDeveloperAction {

    @Override
    public void execute() {
        System.out.println("Select developer.");
        Developer developer = commandLine.choose(developerDao.getAll());
        System.out.println("Select skill.");
        Skill skill = commandLine.choose(developerDao.getDeveloperSkills(developer.getId()));
        developerDao.removeSkill(developer.getId(), skill.getId());
    }

    @Override
    public String toString() {
        return "Remove skill from developer.";
    }
}
