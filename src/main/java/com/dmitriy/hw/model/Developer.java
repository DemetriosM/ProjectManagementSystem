package com.dmitriy.hw.model;

import java.util.List;

public class Developer extends BaseModel {
    private String surname;
    private String name;
    private Integer salary;
    private Long companyId;
    private List<Skill> skills;
    private List<Project> projects;

    public Developer(String surname, String name, Integer salary, Long companyId) {
        this.surname = surname;
        this.name = name;
        this.salary = salary;
        this.companyId = companyId;
    }

    public Developer(String surname, String name, Integer salary, Long companyId, List<Skill> skills) {
        this.surname = surname;
        this.name = name;
        this.salary = salary;
        this.companyId = companyId;
        this.skills = skills;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Developer developer = (Developer) o;

        if (!getSurname().toLowerCase().equals(developer.getSurname().toLowerCase())) return false;
        if (!getName().toLowerCase().equals(developer.getName().toLowerCase())) return false;
        return getSalary().equals(developer.getSalary());
    }

    @Override
    public int hashCode() {
        int result = getSurname().toLowerCase().hashCode();
        result = 31 * result + getName().toLowerCase().hashCode();
        result = 31 * result + getSalary().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%-5d %-13s %-13s %-4d usd", getId(), getSurname(), getName(), getSalary());
    }
}
