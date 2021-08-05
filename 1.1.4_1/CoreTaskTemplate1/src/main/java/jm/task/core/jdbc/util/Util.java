package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/db";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "E629rn2868!&";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Не удалось установить подключение к БД.");
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            return connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось установить подключение к БД.");
        }
    }

    private static final SessionFactory sessionFactory;

    static {
        try {
            Properties      properties = new Properties();
            //assert false;
            properties.setProperty("dialect", "org.hibernate.dialect.MySQL5Dialect");
            properties.setProperty("hibernate.hbm2ddl.auto", "update");
            properties.setProperty("hibernate.connection.username", USER_NAME);
            properties.setProperty("hibernate.connection.password", PASSWORD);
            properties.setProperty("hibernate.connection.url", URL);
            sessionFactory = new org.hibernate.cfg.Configuration()
                    .addProperties(properties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError("Ошибка подключения к БД.");
        }
    }

    public static SessionFactory getSessionFactory() {

        return sessionFactory;
    }
}

