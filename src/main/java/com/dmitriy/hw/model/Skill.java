package com.dmitriy.hw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "skills")
public class Skill extends BaseModel{
    @Column
    private String lang;

    public Skill() {
    }

    public Skill(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        return getLang().toLowerCase().equals(skill.getLang().toLowerCase());
    }

    @Override
    public int hashCode() {
        return getLang().toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return String.format("%-7s", getLang());
    }
}
