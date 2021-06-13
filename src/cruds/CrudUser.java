/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cruds;

import database.HibernateUtil;
import entity.User;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;

/**
 *
 * @author vikto
 */
public class CrudUser {

    @Transactional
    public static void addUser(User user) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
//            user.getBestscoresList().forEach(score -> score.setUserId(user));
            session.save(user);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public static void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public static void deleteUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public static List<User> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createCriteria(User.class).list();
        transaction.commit();
        session.close();
        return userList;
    }

    public static User getUser(Integer user_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, user_id);
        transaction.commit();
        session.close();
        return user;
    }

}
