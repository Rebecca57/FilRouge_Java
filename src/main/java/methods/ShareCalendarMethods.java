package methods;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.m2i.models.Calendars;
import fr.m2i.models.Shares;
import fr.m2i.models.User;

public class ShareCalendarMethods {
	
	/****************************** CONTACTS *******************************/
	
	public static ArrayList<User> getContacts(Integer id){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
			
		@SuppressWarnings("unchecked")
		ArrayList<User> listeContacts = (ArrayList<User>) em.createNativeQuery("SELECT * FROM users as u1 INNER JOIN calendars u2 ON u1.id = u2.user_id INNER JOIN shares u3 ON u3.calendar_id = u2.id WHERE u3.user_id = ? AND u1.id!=?", User.class)
			.setParameter(1,id)
			.setParameter(2,id)
			.getResultList();

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

		em.close();
		factory.close();
		
		return listeContacts;
	}
	
	public static ArrayList<User> addContact(User user){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		User userToAdd = (User) em.createNativeQuery("SELECT * FROM users WHERE users.email=?",User.class)
				.setParameter(1,user.getEmail())
				.getSingleResult();
		
		Calendars calendar = (Calendars) em.createNativeQuery("SELECT * FROM calendars WHERE calendars.user_id = ?",Calendars.class)
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
		factory.close();

		return listeContacts;
	}
	
public static ArrayList<User> deleteContact(User user){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		Calendars calendar = (Calendars) em.createNativeQuery("SELECT u1.* FROM calendars u1 INNER JOIN users u2 ON u1.user_id = u2.id WHERE u2.email = ? ",Calendars.class)//"SELECT u1.id FROM calendars as u1, users as u2  WHERE (u2.email=? AND u1.user_id = u2.id)"
				.setParameter(1,user.getEmail())
				
				.getSingleResult();
		/**
		Calendars CalToId = (Calendars) em.createNativeQuery("SELECT * FROM calendars WHERE calendars.user_id=?",Calendars.class)//"SELECT u1.id FROM calendars as u1, users as u2  WHERE (u2.email=? AND u1.user_id = u2.id)"
				.setParameter(1,UserToId.getId())
				.getSingleResult();**/
		
		Shares share = (Shares) em.createNativeQuery("SELECT * FROM shares as u1, calendars as u2 WHERE (u1.user_id = ? AND u1.calendar_id = ?)",Shares.class)
				.setParameter(1,user.getId())
				.setParameter(2,calendar.getId())
				.setMaxResults(1)
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


		em.close();
		factory.close();
		
		return listeContacts;
	}

	
	
	
	/****************************** SHARES *******************************/
	
	
	public static ArrayList<User> getShares(Integer id){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		@SuppressWarnings("unchecked")
		ArrayList<User> listeShares = (ArrayList<User>) em.createNativeQuery("SELECT * from users WHERE users.id in (SELECT u3.user_id FROM users as u1, calendars as u2, shares as u3 WHERE (u2.user_id=u1.id AND u2.id =u3.calendar_id AND u2.user_id=? AND u3.user_id!=? ));", User.class)
			.setParameter(1,id)
			.setParameter(2,id)
			.getResultList();

		System.out.println(listeShares);
		em.close();
		factory.close();

		
		return listeShares;
	}
	
	public static ArrayList<User> getNotShares(Integer id){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		@SuppressWarnings("unchecked")
		ArrayList<User> listeContacts = (ArrayList<User>) em.createNativeQuery("select * from users where (users.id not in (SELECT u3.user_id FROM users u1, calendars u2, shares u3 WHERE (u2.user_id=u1.id AND u2.id =u3.calendar_id AND u2.user_id=?1)) AND users.id!=?2)", User.class)
			.setParameter(1,id)
			.setParameter(2,id)
			.getResultList();

		System.out.println(listeContacts);
		em.close();
		factory.close();

		
		return listeContacts;
	}
	

	public static ArrayList<User> addShare(User user) throws Exception{
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		Calendars calendarToAdd = (Calendars) em.createNativeQuery(" Select * FROM calendars WHERE calendars.user_id =?",Calendars.class)
				.setParameter(1,user.getId())
				.getSingleResult();
		User userId = (User) em.createNativeQuery(" Select * FROM users WHERE users.email=?",User.class)
				.setParameter(1,user.getEmail())
				.getSingleResult();
		
		/*Calendars calendar = (Calendars) em.createNativeQuery("SELECT * FROM calendars WHERE calendars.user_id = ?",Calendars.class)
				.setParameter(1,userToAdd.getId())
				.getSingleResult();*/
		
		Shares shares = new Shares(userId.getId(),calendarToAdd.getId(),false);
		@SuppressWarnings("unchecked")
		ArrayList<Shares> already = (ArrayList<Shares>) em.createNativeQuery(" Select * FROM Shares as u1 WHERE (u1.user_id = ? AND u1.calendar_id=?)",Shares.class)
				.setParameter(1,shares.getUserId())
				.setParameter(2,shares.getCalendarId())
				.getResultList();
		
		if (already.isEmpty()) {
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
		}else {
			throw new Exception("You already share your Calendar to this user!");
		}
		
		em.close();
		factory.close();
		
		ArrayList<User> listeShares = ShareCalendarMethods.getShares(user.getId());
		System.out.println(listeShares);
		return listeShares;
	}
	
	

	public static ArrayList<User> deleteShare(User user) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		Shares shareD = (Shares) em.createNativeQuery("select u1.* from shares u1 where u1.user_id=(Select users.id FROM users WHERE users.email=?1) AND u1.calendar_id=(Select calendars.id FROM calendars WHERE calendars.user_id =?2)",Shares.class)
				.setParameter(1,user.getEmail())
				.setParameter(2,user.getId())
				.getSingleResult();
		
		boolean transac = false;
		try {
			em.getTransaction().begin();
			em.remove(shareD);
			transac=true;
		}finally {
			if (transac) {
				em.getTransaction().commit();
			}
			else {
				em.getTransaction().rollback();
			}	
		}	
		
		ArrayList<User> listeShares = ShareCalendarMethods.getShares(user.getId());
		System.out.println(listeShares);
		return listeShares;
		
	}
	
	public static void editR(Shares share) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		System.out.println(share.getUserId()+" "+share.getCalendarId());
		
		Shares updateShare = (Shares) em.createNativeQuery("select u1.* from shares u1 where u1.user_id=?1 AND u1.calendar_id=(Select calendars.id FROM calendars WHERE calendars.user_id =?2)",Shares.class)
				.setParameter(1,share.getUserId())
				.setParameter(2,share.getCalendarId())
				.getSingleResult();
		
		System.out.println(updateShare);
		
		boolean transac = false;
		try {
			em.getTransaction().begin();
			updateShare.setReadonly(share.isReadonly());
				transac=true;
		}finally {
			if (transac) {
				em.getTransaction().commit();
			}
			else {
				em.getTransaction().rollback();
			}	
		}

	
		em.close();
		factory.close();
	}


	public static Shares getShare(Shares share) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		System.out.println(share.getUserId()+" "+share.getCalendarId());
		
		Shares totalShare = (Shares) em.createNativeQuery("select u1.* from shares u1 where u1.user_id=?1 AND u1.calendar_id=(Select calendars.id FROM calendars WHERE calendars.user_id =?2)",Shares.class)
				.setParameter(1,share.getUserId())
				.setParameter(2,share.getCalendarId())
				.getSingleResult();
		
		em.close();
		factory.close();
		
		return totalShare;
		
	}


}
