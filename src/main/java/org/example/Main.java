package org.example;

import org.example.DAO.DepartmentDAOimpl;
import org.example.DAO.UserDAOimpl;
import org.example.model.Department;
import org.example.model.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        printMenu();
        Scanner scanner = new Scanner(System.in);
        int var1 = scanner.nextInt();

        UserDAOimpl userDAOimpl = new UserDAOimpl();
        DepartmentDAOimpl departmentDAOimpl = new DepartmentDAOimpl();
        while (var1 != 0){

            switch (var1){

                case 1 -> {
                    firstChoice();
                    int var2 = scanner.nextInt();

                    while (var2 != 0){
                        switch (var2){
                            case 1 -> {
                                System.out.println("Введите ID отдела: ");
                                int id = scanner.nextInt();
                                System.out.println(departmentDAOimpl.getByID(id));
                            }
                            case 2 -> {
                                System.out.println("Введите ID отдела у которого нужно изменить название: ");
                                int id = scanner.nextInt();
                                departmentDAOimpl.changeInfo(id);
                            }
                            case 3 -> {
                                Department department = new Department();
                                System.out.println("Введите название отдела: ");
                                department.setName(scanner.next());
                                departmentDAOimpl.createDepartment(department);
                            }
                            case 4 -> {
                                System.out.println("Введите ID отдела, который нужно удалить: ");
                                int id = scanner.nextInt();
                                departmentDAOimpl.delete(id);
                            }
                        }
                        firstChoice();
                        var2 = scanner.nextInt();
                    }

                }
                case 2 -> {
                    secondChoice();
                    int var2 = scanner.nextInt();

                    while (var2 != 0){
                        switch(var2){
                            case 1 -> {
                                System.out.println("Введите ID пользователя: ");
                                int id = scanner.nextInt();
                                System.out.println(userDAOimpl.getByID(id) + "  Отдел - " + UserDAOimpl.showDep(userDAOimpl.getByID(id)));

                            }
                            case 2 -> {
                                System.out.println("Введите ID пользователя у которого нужно изменить данные: ");
                                int id = scanner.nextInt();
                                userDAOimpl.changeInfo(id);
                            }
                            case 3 -> {
                                User user = new User();
                                System.out.println("Введите Фамилию пользователя: ");
                                user.setSurname(scanner.next());
                                System.out.println("Введите Имя пользователя: ");
                                user.setName(scanner.next());
                                System.out.println("Введите Отчество пользователя: ");
                                user.setSecondname(scanner.next());
                                System.out.println("Введите Должность пользователя: ");
                                user.setPosition(scanner.next());
                                System.out.println("Введите Отдел пользователя: ");
                                Thread.sleep(1000);
                                System.out.println("Отделы: ");
                                DepartmentDAOimpl.getAll();
                                user.setDep_id(scanner.nextInt());
                                //    user.setDepartment(scanner.next());
                                System.out.println("Введите Зарплату пользователя: ");
                                user.setSalary(scanner.nextInt());
                                userDAOimpl.createUser(user);
                            }
                            case 4 -> {
                                System.out.println("Введите ID пользователя, которого нужно удалить: ");
                                int id = scanner.nextInt();
                                userDAOimpl.delete(id);
                            }
                        }
                        secondChoice();
                        var2 = scanner.nextInt();
                    }

                }
            }
            printMenu();
            var1 = scanner.nextInt();
        }
    }

    public static void printMenu(){
        System.out.println("Введите команду: ");
        System.out.println("1 - Работа с отделами, 2 - Работа с пользователями, 0 - Выход");
        //System.out.println("1 - Получить пользователя по ID, 2 - Отредактировать данные сотрудника, 3 - Внесить нового сотрудника, 4 - Удалить сотрудника, 0 - Выход ");
    }

    public static void firstChoice(){
        System.out.println("Введите команду: ");
        System.out.println("1 - Получить отдел по ID, 2 - Отредактировать данные отдела, 3 - Внесить новый отдел, 4 - Удалить отдел, 0 - Выход ");
    }

    public static void secondChoice(){
        System.out.println("Введите команду: ");
        System.out.println("1 - Получить пользователя по ID, 2 - Отредактировать данные сотрудника, 3 - Внесить нового сотрудника, 4 - Удалить сотрудника, 0 - Выход ");
    }
}