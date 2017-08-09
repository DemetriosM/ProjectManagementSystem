package com.dmitriy.hw.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "developers")
public class Developer extends BaseModel {
    @Column
    private String surname;
    @Column
    private String name;
    @Column
    private Integer salary;
    @ManyToOne
    @JoinColumn(name = "companies_id")
    private Company company;
    @Transient
    private Long companyId;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "developers_has_skills",
            joinColumns = @JoinColumn(name = "developers_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "skills_id", referencedColumnName = "id"))
    private List<Skill> skills;
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "developers_has_projects",
            joinColumns = @JoinColumn(name = "developers_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "projects_id", referencedColumnName = "id"))
    private List<Project> projects;

    public Developer() {
    }

    public Developer(String surname, String name, Integer salary, Long companyId) {
        this.surname = surname;
        this.name = name;
        this.salary = salary;
        this.companyId = companyId;
    }

    public Developer(String surname, String name, Integer salary, Company company) {
        this.surname = surname;
        this.name = name;
        this.salary = salary;
        this.company = company;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getCompanyId() {
        return company.getId();
    }

    public void setCompanyId(Long companyId) {
        this.company.setId(companyId);
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
        return String.format("%-5d %-13s %-13s %-4d usd %-7s",
                getId(), getSurname(), getName(), getSalary(), getCompany().getName());
    }
}
