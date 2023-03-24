package org.example.DAO;

import org.example.DBConnector;
import org.example.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DepartmentDAOimpl implements DepartmentDAO{

    private static final String getDepartmentById = "SELECT * FROM DEPARTMENT WHERE ID = ?;";
    private static final String getAll = "SELECT * FROM DEPARTMENT";
    private static final String createDepartment = "INSERT INTO DEPARTMENT(ID, DEP_NAME) VALUES(?,?);";
    private static final String changeInfo = "UPDATE DEPARTMENT SET DEP_NAME = ? WHERE ID = ?;";
    private static final String deleteDepartment = "DELETE FROM DEPARTMENT WHERE ID = ?;";

    @Override
    public Department getByID(int ID){
        try (Connection connection = DBConnector.getConnection()){
            PreparedStatement statement = connection.prepareStatement(getDepartmentById);
            statement.setInt(1,ID);
            ResultSet result = statement.executeQuery();
            result.next();

            Department department = new Department();
            department.setId(result.getInt("ID"));
            department.setName(result.getString("DEP_NAME"));

            return department;

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static Map<Integer, String> getAll() {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(getAll);
            ResultSet resultSet = statement.executeQuery();

            Map<Integer, String> info = new HashMap<>();

            while (resultSet.next()){
                info.put(resultSet.getInt("ID"), resultSet.getString("DEP_NAME"));
            }

            for (var a : info.entrySet()){
                System.out.println("ID - " + a.getKey() + "\t" + "Название отдела - " + a.getValue());
            }
            return info;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createDepartment(Department department) {
        try (Connection connection = DBConnector.getConnection()){
            PreparedStatement insert = connection.prepareStatement(createDepartment);
            insert.setInt(1,department.getId());
            insert.setString(2,department.getName());
            insert.execute();
            System.out.println("Department added");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeInfo(int ID) {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement change = connection.prepareStatement(changeInfo);
            change.setInt(2,ID);

            System.out.println("Введите новое название отдела: ");
            Scanner scanner = new Scanner(System.in);
            change.setString(1,scanner.next());

            System.out.println("Название отдела изменено");
            change.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int ID) {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement delete = connection.prepareStatement(deleteDepartment);
            delete.setInt(1,ID);
            delete.execute();
            System.out.println("Department deleted");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static int lastID(){
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(getAll);
            ResultSet resultSet = statement.executeQuery();

            int count = 0;

            while (resultSet.next()){
                count++;
            }
            return count;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
