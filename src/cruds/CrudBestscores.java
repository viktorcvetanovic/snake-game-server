/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cruds;

import database.HibernateUtil;
import entity.Bestscores;
import entity.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author vikto
 */
public class CrudBestscores {

    public static List<Bestscores> getAllBestScores() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Bestscores> bestScoresList = session.createCriteria(Bestscores.class).list();
        transaction.commit();
        session.close();
        return bestScoresList;
    }
}
