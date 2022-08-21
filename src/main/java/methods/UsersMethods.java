package methods;

import java.util.ArrayList;

import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.m2i.models.User;

public class UsersMethods {
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	public static ArrayList<User> display() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		@SuppressWarnings("unchecked")
		ArrayList<User> listeTaches = (ArrayList<User>) em.createNativeQuery("SELECT * from users", User.class)
				.getResultList();

		em.close();
		System.out.println("LISTE USERS");
		System.out.println(listeTaches.get(0));
		return listeTaches;
	}
	
	//ADD A USER IN TH DB
	public static ArrayList<User> add(User user) {
		
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
		
		return usersList;
	}
	
	//UPDATE AN USER IN THE DB
	public static ArrayList<User> update(User user){
			
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		System.out.println(user);
		System.out.println(user.getId());
		//User user = em.find(User.class,user.getId());
		
		User updateUser = em.find(User.class, user.getId());
		boolean transac = false;
		try {
			em.getTransaction().begin();
			updateUser.setFirstname(user.getFirstname());
			updateUser.setLastname(user.getLastname());
			updateUser.setEmail(user.getEmail());
			updateUser.setBirthday(user.getBirthday());
			updateUser.setPassword(user.getPassword());
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
		
		return usersList;
	}

}
