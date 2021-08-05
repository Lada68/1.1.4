package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;


public class UserServiceImpl implements UserService {
    public static UserDaoJDBCImpl addInstance() {
      return UserDaoJDBCImpl.getInstance();
   }
//
//    public void createUsersTable() {
//        addInstance().createUsersTable();
//    }
//
//    public void dropUsersTable() {
//        addInstance().dropUsersTable();
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//        addInstance().saveUser(name, lastName, age);
//    }
//
//    public void removeUserById(long id) {
//        addInstance().removeUserById(id);
//    }
//
//    public List<User> getAllUsers() {
//
//        return addInstance().getAllUsers();
//    }
//
//    public void cleanUsersTable() {
//        addInstance().cleanUsersTable();
//    }
    @Override
    public void createUsersTable() {
        UserDaoHibernateImpl.getINSTANCE().createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        UserDaoHibernateImpl.getINSTANCE().dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        UserDaoHibernateImpl.getINSTANCE().saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        UserDaoHibernateImpl.getINSTANCE().removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {

        return UserDaoHibernateImpl.getINSTANCE().getAllUsers();

    }

    @Override
    public void cleanUsersTable() {
        UserDaoHibernateImpl.getINSTANCE().cleanUsersTable();
    }
}
