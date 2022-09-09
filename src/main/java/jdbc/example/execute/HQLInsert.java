package jdbc.example.execute;

import jdbc.example.model.Customers;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HQLInsert {

    public static void main(String[] args) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Customers customers = new Customers();
            customers.setCustomerNumber(497);
            customers.setCustomerName("cus name 1");
            customers.setAddressLine1("Ha dong");
            customers.setCity("Ha Noi");
            customers.setCountry("VN");
            customers.setContactFirstName("f cus name");
            customers.setContactLastName("l last name");
            customers.setPhone("098659236");
            session.save(customers);
            transaction.commit();
        } catch (HibernateException hex) {
            if (transaction != null)
                transaction.rollback();
            hex.printStackTrace();
        }
    }
}
