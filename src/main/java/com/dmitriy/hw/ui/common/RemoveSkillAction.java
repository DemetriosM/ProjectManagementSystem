package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Skill;

public class RemoveSkillAction extends AbstractSkillAction {

    @Override
    public void execute() {
        System.out.println("Select customer:");
        Skill skill = commandLine.choose(skillDao.getAll());
        skillDao.delete(skill.getId());
    }

    @Override
    public String toString() {
        return "Remove skill.";
    }
}
