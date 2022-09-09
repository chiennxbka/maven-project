package jdbc.example.execute;

import jdbc.example.model.Customers;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class HibernateMain {

    public static void main(String[] args) {
//        Transaction transaction = null;// can cho insert, update & delete
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String sql = "select cu from Customers cu";
            Query query = session.createQuery(sql);
//            query.executeUpdate();
            List<Customers> customers = (List<Customers>)query.getResultList();
            customers.forEach(System.out::println);
//            transaction.commit();
        }catch (Exception exception){
//            if (transaction != null) {
//                transaction.rollback();
//            }
            exception.printStackTrace();
        }
    }
}
//    https://www.javaguides.net/2018/11/hibernate-query-language-insert-update.html
