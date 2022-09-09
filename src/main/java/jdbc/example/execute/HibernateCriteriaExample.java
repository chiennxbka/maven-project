package jdbc.example.execute;

import jdbc.example.model.Customers;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class HibernateCriteriaExample {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customers> criteriaQuery = builder.createQuery(Customers.class);
            Root<Customers> root = criteriaQuery.from(Customers.class);
            /*select list with multiple condition*/
//            criteriaQuery.select(root).where(builder.equal(root.get("customerNumber"), 496));
            criteriaQuery.select(root).where(builder.and(
                    builder.equal(root.get("customerNumber"), 496),
                    builder.like(root.get("city"), "%Auckland%"),
                    builder.equal(root.get("addressLine1"), "Arenales 1938 3'A'")));


            List<Customers> list = session.createQuery(criteriaQuery).getResultList();
            list.forEach(System.out::println);

            // update data
            CriteriaUpdate<Customers> criteriaUpdate = builder.createCriteriaUpdate(Customers.class);
            Root<Customers> rootUpdate = criteriaUpdate.from(Customers.class);
            criteriaUpdate.set("city", "Ha Noi, Viet Nam Update").where(builder.equal(rootUpdate.get("customerNumber"), 496));
            Transaction transaction = session.beginTransaction();
            int result = session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
            System.out.println(result);
            // delete data
            CriteriaDelete<Customers> criteriaDelete = builder.createCriteriaDelete(Customers.class);
            Root<Customers> rootDelete = criteriaDelete.from(Customers.class);
            criteriaDelete.where(builder.equal(rootDelete.get("customerNumber"), 480));
            transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
