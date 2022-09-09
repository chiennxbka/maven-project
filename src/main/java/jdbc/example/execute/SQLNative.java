package jdbc.example.execute;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class SQLNative {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String delQuery = "select * from customers cu where cu.customerNumber =:number";
            Query query = session.createNativeQuery(delQuery);
            query.setParameter("number", 496);
            List<Object> customers = (List<Object>)query.getResultList();
            customers.forEach(System.out::println);
        } catch (HibernateException hex) {
            hex.printStackTrace();
        }
    }
}
