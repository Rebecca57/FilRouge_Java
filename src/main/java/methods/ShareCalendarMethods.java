package methods;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.m2i.models.Calendar;
import fr.m2i.models.Shares;
import fr.m2i.models.User;

public class ShareCalendarMethods {
	
	public static ArrayList<User> getContacts(Integer id){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		
		
		@SuppressWarnings("unchecked")
		ArrayList<User> listeContacts = (ArrayList<User>) em.createNativeQuery("SELECT * FROM users as u1 INNER JOIN calendars u2 ON u1.id = u2.user_id INNER JOIN shares u3 ON u3.calendar_id = u2.id WHERE u3.user_id = ?", User.class)
			.setParameter(1,id)
			.getResultList();

		System.out.println(listeContacts);
		em.close();

		
		return listeContacts;
	}
	
	
	public static ArrayList<User> getNotContacts(Integer id){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		@SuppressWarnings("unchecked")
		ArrayList<User> listeContacts = (ArrayList<User>) em.createNativeQuery("select * from users where id not in (SELECT u1.id FROM users as u1 INNER JOIN calendars u2 ON u1.id = u2.user_id INNER JOIN shares u3 ON u3.calendar_id = u2.id WHERE u3.user_id =?);", User.class)
			.setParameter(1,id)
			.getResultList();

		System.out.println(listeContacts);
		em.close();

		
		return listeContacts;
	}
	
	
	
	public static ArrayList<User> addContact(User user){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		User userToAdd = (User) em.createNativeQuery("SELECT * FROM users WHERE users.email=?",User.class)
				.setParameter(1,user.getEmail())
				.getSingleResult();
		
		Calendar calendar = (Calendar) em.createNativeQuery("SELECT * FROM calendars WHERE calendars.user_id = ?",Calendar.class)
				.setParameter(1,userToAdd.getId())
				.getSingleResult();
		
		Shares shares = new Shares(user.getId(),calendar.getId(),false);
		boolean transac = false;
		try {
			em.getTransaction().begin();
			em.persist(shares);
			transac = true;
		}
		finally {
			if (transac) {
				em.getTransaction().commit();
			}
			else {
				em.getTransaction().rollback();
			}	
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<User> listeContacts = (ArrayList<User>) em.createNativeQuery("SELECT * FROM users as u1 INNER JOIN calendars u2 ON u1.id = u2.user_id INNER JOIN shares u3 ON u3.calendar_id = u2.id WHERE u3.user_id = ?", User.class)
			.setParameter(1,user.getId())
			.getResultList();

		System.out.println(listeContacts);
		em.close();

		
		return listeContacts;
	}
	
	public static ArrayList<User> deleteContact(User user){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		User UserToId = (User) em.createNativeQuery("SELECT * FROM users WHERE users.email=? ",User.class)//"SELECT u1.id FROM calendars as u1, users as u2  WHERE (u2.email=? AND u1.user_id = u2.id)"
				.setParameter(1,user.getEmail())
				.getSingleResult();
		
		Calendar CalToId = (Calendar) em.createNativeQuery("SELECT * FROM calendars WHERE calendars.user_id=?",Calendar.class)//"SELECT u1.id FROM calendars as u1, users as u2  WHERE (u2.email=? AND u1.user_id = u2.id)"
				.setParameter(1,UserToId.getId())
				.getSingleResult();
		
		Shares share = (Shares) em.createNativeQuery("SELECT * FROM shares as u1 WHERE (u1.user_id = ? AND u1.calendar_id = ?)",Shares.class)
				.setParameter(1,user.getId())
				.setParameter(2,CalToId.getId())
				.getSingleResult();
		
		boolean transac = false;
		try {
			em.getTransaction().begin();
			em.remove(share);
			transac=true;
		}finally {
			if (transac) {
				em.getTransaction().commit();
			}
			else {
				em.getTransaction().rollback();
			}	
		}	
		
		@SuppressWarnings("unchecked")
		ArrayList<User> listeContacts = (ArrayList<User>) em.createNativeQuery("SELECT * FROM users as u1 INNER JOIN calendars u2 ON u1.id = u2.user_id INNER JOIN shares u3 ON u3.calendar_id = u2.id WHERE u3.user_id = ?", User.class)
			.setParameter(1,user.getId())
			.getResultList();

		System.out.println(listeContacts);
		em.close();
		
		return listeContacts;
	}

}
