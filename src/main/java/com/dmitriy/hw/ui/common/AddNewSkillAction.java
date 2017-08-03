package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Skill;

public class AddNewSkillAction extends AbstractSkillAction {
    @Override
    public void execute() {
        System.out.println("Input skill name:");
        String skillName = getValidString();
        Skill skill = new Skill(skillName);
        skillDao.create(skill);
    }

    @Override
    public String toString() {
        return "Insert new skill.";
    }
}
