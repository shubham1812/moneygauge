package moneyguage.Model.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import moneyguage.Model.Bean.DbWebUserProfile;
import moneyguage.Service.Util.UniqueIdGenerator;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	static {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

		sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}

	public static void main(String[] args) {
		DbWebUserProfile user = new DbWebUserProfile();
		user.setEmail("test@gmail.com");
		user.setTitle("Mr");
		user.setFirstName("Chachi");
		user.setLastName("420");
		user.setPhoneNo("2123232");
		user.setUserProfileId(UniqueIdGenerator.generateUniqueId());
		Session session = getSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		System.out.println(session.toString());
	}

}