import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;

public class HibernateSQLUpdate {

    public static void main(String[] args) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "update Customers cu set cu.customerName =:name where cu.customerNumber =:number";
            Query query = session.createQuery(hql);
            query.setParameter("name", "Customer updated");
            query.setParameter("number", 219);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException hex) {
            if (transaction != null)
                transaction.rollback();
            hex.printStackTrace();
        }
    }

    public static class HibernateSQLInsert {

        public static void main(String[] args) {
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                String hql = "update Customers cu set cu.customerName =:name where cu.customerNumber =:number";
                Query query = session.createQuery(hql);
                query.setParameter("name", "Customer updated");
                query.setParameter("number", 219);
                query.executeUpdate();
                transaction.commit();
            } catch (HibernateException hex) {
                if (transaction != null)
                    transaction.rollback();
                hex.printStackTrace();
            }
        }
    }
}
