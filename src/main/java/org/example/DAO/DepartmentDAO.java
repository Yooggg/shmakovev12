package org.example.DAO;

import org.example.model.Department;

import java.sql.SQLException;
import java.util.Map;

public interface DepartmentDAO {
    Department getByID(int ID) throws SQLException;

    void createDepartment(Department department);

    void changeInfo(int ID);

    void delete(int ID);

}
