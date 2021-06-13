/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cruds;

import database.HibernateUtil;
import entity.Skins;
import entity.User;

import org.hibernate.Transaction;

import javax.transaction.Transactional;
import org.hibernate.Session;

/**
 *
 * @author vikto
 */
public class CrudSkin {

    @Transactional
    public static void addSkin(Skins skin) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(skin);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public static Skins getSkin(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Skins s = (Skins) session.get(Skins.class, id);
        transaction.commit();
        session.close();
        return s;
    }
}
