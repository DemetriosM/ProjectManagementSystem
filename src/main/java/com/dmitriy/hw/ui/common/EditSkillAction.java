package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Skill;

public class EditSkillAction extends AbstractSkillAction{

    @Override
    public void execute() {
        System.out.println("Select skill:");
        Skill skill = commandLine.choose(skillDao.getAll());
        System.out.println("Input new skill name:");
        skill.setLang(getValidString());
        skillDao.update(skill);
    }

    @Override
    public String toString() {
        return "Edit skill.";
    }
}
