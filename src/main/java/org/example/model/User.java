package org.example.model;

import org.example.DAO.DepartmentDAOimpl;
import org.example.DAO.UserDAOimpl;

public class User {

    private int id = 0;
    private String surname;
    private String name;
    private String secondname;
    private String position;
    private int dep_id;
    //private String department;
    private int salary;

    public User() {
        if(UserDAOimpl.lastID() == 0){
            id++;
        } else{
            this.id = UserDAOimpl.lastID() + 1;
        }
    }

    public User(User user) {
        this.id = user.getId();
        this.surname = user.getSurname();
        this.name = user.getName();
        this.secondname = user.getSecondname();
        this.position = user.getPosition();
        this.dep_id = user.getDep_id();
    //    this.department = user.getDepartment();
        this.salary = user.getSalary();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

//    public String getDepartment() {
//        return department;
//    }

//    public void setDepartment(String department) {
//        this.department = department;
//    }


    public int getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", secondname='" + secondname + '\'' +
                ", position='" + position + '\'' +
                ", department id='" + dep_id + '\'' +
                ", department name'"  + '\'' +
                ", salary=" + salary +
                '}';
    }
}
