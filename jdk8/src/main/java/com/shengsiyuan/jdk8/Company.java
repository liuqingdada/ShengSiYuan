package com.shengsiyuan.jdk8;

import java.util.List;

public class Company {

    private String name;

    private List<Employee> mEmployees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return mEmployees;
    }

    public void setEmployees(List<Employee> employees) {
        mEmployees = employees;
    }
}
