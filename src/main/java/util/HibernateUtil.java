package util;
import dataAccess.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author nb
 */
public class HibernateUtil {
private static  final SessionFactory  sessionFactory = new Configuration().configure().buildSessionFactory();

    public HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
