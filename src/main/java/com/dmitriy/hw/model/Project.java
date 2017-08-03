package com.dmitriy.hw.model;

public class Project extends BaseModel {
    private String name;
    private Long customerId;
    private Integer cost;

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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomer(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
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
        return String.format("%-5d %-13s %-8d", getId(), getName(), getCost());
    }
}
