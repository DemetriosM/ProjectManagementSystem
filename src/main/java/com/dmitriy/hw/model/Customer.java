package com.dmitriy.hw.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends BaseModel{
    @Column
    private String name;
    @Column
    private String city;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Project> projects;

    public Customer() {
    }

    public Customer(String name, String city) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!getName().toLowerCase().equals(customer.getName().toLowerCase())) return false;
        if (customer.getCity() == null && getCity() != null) return false;
        return getCity() != null ?
                getCity().toLowerCase().equals(customer.getCity().toLowerCase()) : customer.getCity() == null;
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
