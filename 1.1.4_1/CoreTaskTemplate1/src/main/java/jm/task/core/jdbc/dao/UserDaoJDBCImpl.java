package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();
    private final Scanner scr = new Scanner(System.in);
    private static final UserDaoJDBCImpl INSTANCE = new UserDaoJDBCImpl();

    private UserDaoJDBCImpl() {

    }

    public static UserDaoJDBCImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void createUsersTable() {
        Connection connection = null;
        try {
            connection = util.getConnection();
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }

            try {
                connection.createStatement().executeUpdate("CREATE TABLE new (id INTEGER not NULL AUTO_INCREMENT," +
                        " name VARCHAR(45), lastname VARCHAR (25)," +
                        " age INTEGER not NULL, PRIMARY KEY (id))");
            } catch (SQLException e) {
                System.out.println("Таблица уже существует");
                return;
            }
            System.out.println("Таблица new успешно создана");
        } catch (SQLException throwables) {
            try {
                throw new SQLException(throwables);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } finally {
            try {
                assert connection != null;
                connection.close();
                System.out.println("Соединение с БД закрыто");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Connection connection = null;
        try {
            connection = util.getConnection();
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
            try {
                connection.createStatement().executeUpdate("DROP TABLE new ");
            } catch (SQLException throwables) {
                System.out.println("Такой таблицы не существует.");
                return;
            }
            System.out.println("Таблица удалена");
        } catch (SQLException throwables) {
            System.out.println("Ошибка подключения к БД");
        } finally {
            try {
                assert connection != null;
                connection.close();
                System.out.println("Соединение с БД закрыто");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Connection connection = null;
        try {
            connection = util.getConnection();
            if (connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
            try {
                connection.createStatement().executeUpdate("INSERT INTO new (name, lastname, age) VALUES " +
                        "('" + name + "', '" + lastName + "', '" + age + "')");

            } catch (SQLException e) {
                System.out.println("Такой таблицы не существует");
                return;
            }
            System.out.println("Пользователь " + name + " добавлен в таблицу ");
        } catch (SQLException throwables) {
            System.out.println("Ошибка подключения к БД");
        } finally {
            try {
                assert connection != null;
                connection.close();
                System.out.println("Соединение с БД закрыто");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Connection connection = null;
        try {
            connection = util.getConnection();
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
            try {
                connection.createStatement().executeUpdate("DELETE FROM new WHERE ID = " + id);
            } catch (SQLException throwables) {
                System.out.println("Ошибка в названии таблицы / id пользователя");
                return;
            }
            System.out.println("Пользователь " + id + " удален из таблицы ");
        } catch (SQLException throwables) {
            System.out.println("Ошибка подключения к БД");
        } finally {
            try {
                assert connection != null;
                connection.close();
                System.out.println("Соединение с БД закрыто");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<User>();
        Connection connection = null;
        try {
            connection = util.getConnection();
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
            User user = new User();
            try {
                ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM new");
                while (rs.next()) {
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setLastName(rs.getString("lastname"));
                    user.setAge(rs.getByte("age"));
                    list.add(user);
                }
            } catch (SQLException throwables) {
                System.out.println("Ошибка");
            }
            return list;
        } catch (SQLException throwables) {
            System.out.println("Ошибка подключения к БД");
        } finally {
            try {
                assert connection != null;
                connection.close();
                System.out.println("Соединение с БД закрыто");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Connection connection = null;
        try {
            connection = util.getConnection();
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
            try {
                connection.createStatement().execute("delete from new");

            } catch (SQLException throwables) {
                System.out.println("Такой таблицы не существует.");
                return;
            }
            System.out.println("Таблица  очищена");
        } catch (SQLException throwables) {
            System.out.println("Ошибка подключения к БД");
        } finally {
            try {
                assert connection != null;
                connection.close();
                System.out.println("Соединение с БД закрыто");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

