package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.dao.SkillDao;
import com.dmitriy.hw.dao.impl.jdbc.module2.SkillDaoImpl;

public abstract class AbstractSkillAction extends ActionWithScanner {
    protected final SkillDao skillDao;

    protected AbstractSkillAction() {
        skillDao = new SkillDaoImpl();
    }
}
