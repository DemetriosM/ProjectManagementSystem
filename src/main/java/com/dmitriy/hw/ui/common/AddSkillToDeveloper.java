package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Developer;
import com.dmitriy.hw.model.Skill;

public class AddSkillToDeveloper extends AbstractDeveloperAction {

    @Override
    public void execute() {
        System.out.println("Select developer.");
        Developer developer = commandLine.choose(developerDao.getAll());
        System.out.println("Select skill.");
        Skill skill = commandLine.choose(skillDao.getAll());
        developerDao.addSkill(developer.getId(), skill.getId());
    }

    @Override
    public String toString() {
        return "Add skill to developer.";
    }
}
