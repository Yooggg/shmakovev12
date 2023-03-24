package org.example.model;

import org.example.DAO.DepartmentDAOimpl;

public class Department {
    private int id = 0;
    private String name;

    public Department() {
        if(DepartmentDAOimpl.lastID() == 0){
            id++;
        } else{
            this.id = DepartmentDAOimpl.lastID() + 1;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
