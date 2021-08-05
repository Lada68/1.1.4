package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import lombok.Getter;
import lombok.SneakyThrows;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao, Serializable {
    @Getter
    private static final UserDaoHibernateImpl INSTANCE = new UserDaoHibernateImpl();


    @SneakyThrows
    @Override
    public void createUsersTable() {
        String sql = " create table Пользователи (id integer not null auto_increment, name varchar(50), " +
                "lastname varchar(50), age integer NOT NULL, primary key (id))";
        Session session = Util.getSessionFactory().openSession();
        if (session.isOpen()) {
            System.out.println("Соединение с БД установлено");
        }
        Transaction x = null;
        try {
            x = session.beginTransaction();
            org.hibernate.query.Query executeQuery = session.createSQLQuery(sql);
            int y = executeQuery.executeUpdate();
            x.commit();
            System.out.println("Таблица успешно создана.");
        } catch (Exception e) {
            System.out.println("Ошибка создания таблицы");
            assert x != null;
            x.rollback();
        } finally {
            session.close();
        }
    }

    @SneakyThrows
    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE пользователи";
        Session session = Util.getSessionFactory().openSession();
        if (session.isOpen()) {
            System.out.println("Соединение с БД установлено");
        }
        Transaction x = null;
        try {
            x = session.beginTransaction();
            Query executeQuery = session.createSQLQuery(sql);
            int y = executeQuery.executeUpdate();
            x.commit();
            System.out.println("Таблица успешно удалена");
        } catch (Exception e) {
            System.out.println("Ошибка удаления таблицы");
            assert x != null;
            x.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = Util.getSessionFactory().openSession();
        if (session.isOpen()) {
            System.out.println("Соединение с БД установлено");
        }
        Transaction x = null;
        try {
            x = session.beginTransaction();
            session.save(user);
            x.commit();
            System.out.println("Пользователь " + user.getName() + " добавлен в БД");
        } catch (Exception e) {
            System.out.println("Ошибка добавления пользователя.");
            assert x != null;
            x.rollback();
        }
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        User user = new User();
        Session session = Util.getSessionFactory().openSession();
        if (session.isOpen()) {
            System.out.println("Соединение с БД установлено");
        }
        Transaction x = null;
        try {
            x = session.beginTransaction();
            user = session.get(User.class, id);
            session.delete(user);
            System.out.println("Пользователь " + user.getName() + " удален из БД");
            x.commit();
        } catch (Exception e) {
            System.out.println("Ошибка удаления пользователя.");
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        if (session.isOpen()) {
            System.out.println("Соединение с БД установлено");
        }
        Transaction x = null;
        try {
            x = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            org.hibernate.query.Query<User> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса");
            assert x != null;
            x.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        if (session.isOpen()) {
            System.out.println("Соединение с БД установлено");
        }
        Transaction x = null;
        try {
            x = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            Query<User> query = session.createQuery(criteriaQuery);
            for (User user1 : query.getResultList()) {
                session.delete(user1);
            }
            x.commit();
            System.out.println("Данные успешно удалены");
        } catch (Exception e) {
            System.out.println("Ошибка удаления данных");
            x.rollback();
        } finally {
            session.close();
        }
    }
}
