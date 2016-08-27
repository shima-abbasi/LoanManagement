package dataAccess.entity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * Created by Dotin school 5 on 8/24/2016.
 */
public class Test {
    public static void main(String[] args) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        RealCustomer realCustomer=new RealCustomer(1, "shima","abbasi","khosro","1371/01/13","0014626950");
        session.save(realCustomer);
        tx.commit();
        session.close();


    }
}
