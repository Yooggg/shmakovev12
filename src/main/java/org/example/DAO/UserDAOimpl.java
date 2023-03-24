package org.example.DAO;
import org.example.DBConnector;
import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserDAOimpl implements USERDAO {

    private static final String getUserById = "SELECT * FROM USERDATA WHERE ID = ?;";
    private static final String getAll = "SELECT * FROM USERDATA";
    private static final String depName = "SELECT DEP_NAME FROM DEPARTMENT JOIN USERDATA ON DEPARTMENT.ID=USERDATA.DEP_ID WHERE USERDATA.ID = ? AND USERDATA.DEP_ID = ?;";
    private static final String createUser = "INSERT INTO USERDATA(ID, SURNAME, NAME, SECONDNAME, POSITION, DEP_ID, SALARY) VALUES(?,?,?,?,?,?,?);";
    private static final String changeInfo = "UPDATE USERDATA SET SURNAME = ?, NAME = ?, SECONDNAME = ?, POSITION = ?, DEP_ID = ?, SALARY = ? WHERE ID = ?;";
    private static final String deleteUser = "DELETE FROM USERDATA WHERE ID = ?;";
    @Override
    public User getByID(int ID) {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement get = connection.prepareStatement(getUserById);

            get.setInt(1,ID);
            ResultSet result = get.executeQuery();
            result.next();

            User user = new User();
            user.setId(result.getInt("ID"));
            user.setSurname(result.getString("SURNAME"));
            user.setName(result.getString("NAME"));
            user.setSecondname(result.getString("SECONDNAME"));
            user.setPosition(result.getString("POSITION"));
            user.setDep_id(result.getInt("DEP_ID"));
        //    user.setDepartment(result.getString("DEPARTMENT"));
            user.setSalary(result.getInt("SALARY"));

            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String showDep(User user){
        try (Connection connection = DBConnector.getConnection()){
            PreparedStatement statement = connection.prepareStatement(depName);

            statement.setInt(1,user.getId());
            statement.setInt(2,user.getDep_id());

            ResultSet result = statement.executeQuery();

            result.next();

            return result.getString("DEP_NAME");

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void createUser(User user) {
        try(Connection connection = DBConnector.getConnection()){
            PreparedStatement insert = connection.prepareStatement(createUser);
            insert.setInt(1,user.getId());
            insert.setString(2,user.getSurname());
            insert.setString(3,user.getName());
            insert.setString(4,user.getSecondname());
            insert.setString(5,user.getPosition());
            insert.setInt(6,user.getDep_id());
        //    insert.setString(6,user.getDepartment());
            insert.setInt(7,user.getSalary());
            insert.execute();
            System.out.println("User added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeInfo(int ID) {
        try (Connection connection = DBConnector.getConnection()){
            PreparedStatement change = connection.prepareStatement(changeInfo);
            change.setInt(7,ID);

            User user = new User(getByID(ID));


            System.out.println("Введите какое поле вы хотите изменить: ");
            Thread.sleep(1000);
            System.out.println("1 - Фамилию, 2 - Имя, 3 - Отчество, 4 - Должность, 5 - Отдел, 6 - Зарплату, 0 - Выход");

            Integer[] array = {1, 2, 3, 4, 5, 6};
            List<Integer> list = new ArrayList<>(Arrays.asList(array));
            Scanner scanner = new Scanner(System.in);
            int var = scanner.nextInt();
            while (var != 0){
                switch (var) {
                    case 1 -> {
                        System.out.println("Введите новую фамилию: ");
                        list.remove(0);
                        change.setString(1, scanner.next());
                    }
                    case 2 -> {
                        System.out.println("Введите новое имя: ");
                        list.remove(1);
                        change.setString(2, scanner.next());
                    }
                    case 3 -> {
                        System.out.println("Введите новое Отчество: ");
                        list.remove(2);
                        change.setString(3, scanner.next());
                    }
                    case 4 -> {
                        System.out.println("Введите новую должность: ");
                        list.remove(3);
                        change.setString(4, scanner.next());
                    }
                    case 5 -> {
                        System.out.println("Введите ID нового отдела: ");
                        list.remove(4);
                        change.setInt(5, scanner.nextInt());
                    }
                    case 6 -> {
                        System.out.println("Введите новую зарплату: ");
                        list.remove(5);
                        change.setInt(6, scanner.nextInt());
                    }
                }
                System.out.println("Что еще изменить? ");
                System.out.println("1 - Фамилию, 2 - Имя, 3 - Отчество, 4 - Должность, 5 - Отдел, 6 - Зарплату, 0 - Выход");
                var = scanner.nextInt();
            }

            for (var variable : list) {
                switch (variable){
                    case 1 -> change.setString(1,user.getSurname());
                    case 2 -> change.setString(2,user.getName());
                    case 3 -> change.setString(3,user.getSecondname());
                    case 4 -> change.setString(4,user.getPosition());
                    case 5 -> //    change.setString(5,user.getDepartment());
                            change.setInt(5,user.getDep_id());
                    case 6 -> change.setInt(6,user.getSalary());
                }
            }
            System.out.println("User info changed");
            change.execute();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int ID) {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement delete = connection.prepareStatement(deleteUser);
            delete.setInt(1,ID);
            delete.execute();
            System.out.println("User deleted");
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
