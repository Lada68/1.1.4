package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        UserServiceImpl us = new UserServiceImpl();
        Scanner scr = new Scanner(System.in);
        System.out.println("Выберите операцию из предложенных:\n" +
                "Для выхода из программы введите 0;\n" +
                "1. Создать новую таблицу;\n" +
                "2. Удалить таблицу;\n" +
                "3. Добавить пользователя в таблицу;\n" +
                "4. Удалить пользователя из таблицы;\n" +
                "5. Вывести список пользователей из таблицы;\n" +
                "6. Очистить таблицу.");

        while (true) {
            int input = scr.nextInt();
            if (input != 0) {
                switch (input) {
                    case 1:
                        us.createUsersTable();
                        break;
                    case 2:
                        us.dropUsersTable();
                        break;
                    case 3:
                        System.out.println("Введите фамилию, имя, возраст пользователя");
                        us.saveUser(scr.next(), scr.next(), scr.nextByte());
                        break;
                    case 4:
                        System.out.println("Введите id пользователя");
                        us.removeUserById(scr.nextLong());
                        break;
                    case 5:
                        System.out.println(us.getAllUsers());
                        break;
                    case 6:
                        us.cleanUsersTable();
                        break;
                    default:
                        System.out.println("Выберите операцию из предложенных.\n Для выхода введите 0");
                        break;
                }
            } else break;
        }
    }
}
