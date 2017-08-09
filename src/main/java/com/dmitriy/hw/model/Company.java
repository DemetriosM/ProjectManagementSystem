package com.dmitriy.hw.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company extends BaseModel {
    @Column
    private String name;
    @Column
    private String city;
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "companies_has_projects",
        joinColumns = @JoinColumn(name = "companies_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "projects_id", referencedColumnName = "id"))
    private List<Project> projects;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Developer> developers;

    public Company() {
    }

    public Company(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (!getName().toLowerCase().equals(company.getName().toLowerCase())) return false;
        if (company.getCity() == null && getCity() != null) return false;
        return getCity() != null ?
                getCity().toLowerCase().equals(company.getCity().toLowerCase()) : company.getCity() == null;
    }

    @Override
    public int hashCode() {
        int result = getName().toLowerCase().hashCode();
        result = 31 * result + (getCity() != null ? getCity().toLowerCase().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%-5d %-13s %-13s", getId(), getName(), getCity());
    }
}
