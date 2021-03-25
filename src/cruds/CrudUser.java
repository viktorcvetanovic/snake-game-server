/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cruds;

import database.HibernateUtil;
import entity.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author vikto
 */
public class CrudUser {

    public static Integer addUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Integer userId = (Integer) session.save(user);
        tx.commit();
        session.close();
        return userId;
    }

}
