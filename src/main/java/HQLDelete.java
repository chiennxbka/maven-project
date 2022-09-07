import jdbc.example.model.Customers;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;

public class HQLDelete {

    public static void main(String[] args) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String delQuery = " delete from Customers cu where cu.customerNumber =:number";
            Query query = session.createQuery(delQuery);
            query.setParameter("number", 497);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException hex) {
            if (transaction != null)
                transaction.rollback();
            hex.printStackTrace();
        }
    }
}
