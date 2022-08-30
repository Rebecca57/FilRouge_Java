package methods;

import java.util.ArrayList;

import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.m2i.models.User;

public class UsersMethods {
	
	//@Resource(name="dataSource")
	//private DataSource dataSource;
	

	public static User login(User userEntered) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		@SuppressWarnings("unchecked")
		ArrayList<User> listeUsers = (ArrayList<User>) em.createNativeQuery("SELECT * from users", User.class)
				.getResultList();

		em.close();
		
		for (User user: listeUsers) {
			if (user.getEmail().equals(userEntered.getEmail()) && user.getPassword().equals(userEntered.getPassword()) && user.getActive()) {
				user.setPassword(null);
				return user;
			}
		}
		System.out.println("NO USER ");
		return null;	
	}
	
	public static ArrayList<User> display() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		@SuppressWarnings("unchecked")
		ArrayList<User> listeUsers = (ArrayList<User>) em.createNativeQuery("SELECT * from users", User.class)
				.getResultList();

		listeUsers.stream().forEach((User user) -> user.setPassword(null));
		em.close();
		System.out.println("LISTE USERS");
		System.out.println("listeUsers");
		return listeUsers;
	}
	
	//ADD A USER IN THE DB
	public static ArrayList<User> add(User user) {
		
		System.out.println(user);
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		boolean transac = false;
		try {
			em.getTransaction().begin();
			em.persist(user);
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
		ArrayList<User> usersList = (ArrayList<User>) em.createNativeQuery("SELECT * from users", User.class)
				.getResultList();
		em.close();
		usersList.stream().forEach((User userN) -> userN.setPassword(null));
		
		return usersList;
	}
	
	
	//DELETE A USER IN THE DB
	public static ArrayList<User> delete(User user){
			
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		System.out.println(user);
		System.out.println(user.getId());
		User userD = em.find(User.class,user.getId());
			
		boolean transac = false;
		try {
			em.getTransaction().begin();
			em.remove(userD);
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
		ArrayList<User> usersList = (ArrayList<User>) em.createNativeQuery("SELECT * from users", User.class)
				.getResultList();
		em.close();
		usersList.stream().forEach((User userN) -> userN.setPassword(null));
		return usersList;
	}
	
	//UPDATE AN USER IN THE DB
	public static ArrayList<User> update(User user){
			
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		User updateUser = em.find(User.class, user.getId());
		boolean transac = false;
		try {
			em.getTransaction().begin();
			updateUser.setFirstname(user.getFirstname());
			updateUser.setLastname(user.getLastname());
			updateUser.setEmail(user.getEmail());
			updateUser.setBirthday(user.getBirthday());
			updateUser.setPhone(user.getPhone());
			updateUser.setPictureUrl(user.getPictureUrl());
			updateUser.setWorkArea(user.getWorkArea());
			updateUser.setAddress(user.getAddress());
			updateUser.setCity(user.getCity());
			updateUser.setPostalCode(user.getPostalCode());
			updateUser.setAccessRight(user.getAccessRight());
			updateUser.setSuperAdmin(user.getSuperAdmin());
			updateUser.setCanShare(user.getCanShare());
			updateUser.setActive(user.getActive());
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
		ArrayList<User> usersList = (ArrayList<User>) em.createNativeQuery("SELECT * from users", User.class)
				.getResultList();
		em.close();
		usersList.stream().forEach((User userN) -> userN.setPassword(null));
		
		return usersList;
	}
	
	
	
	//UPDATE AN USER FIELD
	public static ArrayList<User> updateField(Integer id, String field, boolean value){
			
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		//System.out.println(user);
		//System.out.println(user.getId());
		//User user = em.find(User.class,user.getId());
		
		User updateUser = em.find(User.class, id);
		boolean transac = false;
		try {
			em.getTransaction().begin();
			if (field.equals("active")) {
				updateUser.setActive(value);
			}
			if (field.equals("canShare")) {
				updateUser.setCanShare(value);
			}
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
		ArrayList<User> usersList = (ArrayList<User>) em.createNativeQuery("SELECT * from users", User.class)
				.getResultList();
		em.close();
		usersList.stream().forEach((User user) -> user.setPassword(null));
		
		return usersList;
	}
	
	public static ArrayList<User> updateFieldAR(Integer id, String value, boolean superAdmin){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		//System.out.println(user);
		//System.out.println(user.getId());
		//User user = em.find(User.class,user.getId());
		
		User updateUser = em.find(User.class, id);
		boolean transac = false;
		try {
			em.getTransaction().begin();
				updateUser.setAccessRight(value);
				updateUser.setSuperAdmin(superAdmin);

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
		ArrayList<User> usersList = (ArrayList<User>) em.createNativeQuery("SELECT * from users", User.class)
				.getResultList();
		em.close();
		usersList.stream().forEach((User user) -> user.setPassword(null));
		
		return usersList;
	}

	public static User getUser(User user) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

			User updateUser = em.find(User.class, user.getId());
		
		em.close();
		updateUser.setPassword(null);
		
		return updateUser;
	}
	
	

}
