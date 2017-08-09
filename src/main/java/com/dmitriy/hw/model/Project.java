package com.dmitriy.hw.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project extends BaseModel {
    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name = "customers_id")
    private Customer customer;
    @Transient
    private Long customerId;
    @Column
    private Integer cost;
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "developers_has_projects",
            joinColumns = @JoinColumn(name = "projects_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "developers_id", referencedColumnName = "id"))
    private List<Developer> developers;

    public Project() {
    }

    public Project(String name, Customer customer, Integer cost) {
        this.name = name;
        this.customer = customer;
        this.cost = cost;
    }

    public Project(String name, Long customerId, Integer cost) {
        this.name = name;
        this.customerId = customerId;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getCustomerId() {
        return customer.getId();
    }

    public void setCustomerId(Long customerId) {
        this.customer.setId(customerId);
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
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

        Project project = (Project) o;

        if (!getName().toLowerCase().equals(project.getName().toLowerCase())) return false;
        return getCustomerId().equals(project.getCustomerId());
    }

    @Override
    public int hashCode() {
        int result = getName().toLowerCase().hashCode();
        result = 31 * result + getCustomerId().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%-5d %-13s %-8d %-7s",
                getId(), getName(), getCost(), getCustomer());
    }
}
