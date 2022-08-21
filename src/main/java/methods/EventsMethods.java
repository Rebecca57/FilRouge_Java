package methods;

import java.util.ArrayList;

//import javax.activation.DataSource;
//import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.m2i.models.Event;
public class EventsMethods {

	
	//@Resource(name="dataSource")
	//private DataSource dataSource;
	
	public static ArrayList<Event> display() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		@SuppressWarnings("unchecked")
		ArrayList<Event> eventsList = (ArrayList<Event>) em.createNativeQuery("SELECT * from events", Event.class)
				.getResultList();

		em.close();
		System.out.println("LISTE USERS");
		System.out.println(eventsList.get(0));
		return eventsList;
	}
	
	//ADD A USER IN TH DB
	public static ArrayList<Event> add(Event event) {
		
		System.out.println(event);
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		boolean transac = false;
		try {
			em.getTransaction().begin();
			em.persist(event);
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
		ArrayList<Event> eventsList = (ArrayList<Event>) em.createNativeQuery("SELECT * from events", Event.class)
				.getResultList();
		em.close();
		
		return eventsList;
	}
	
	
	//DELETE A USER IN THE DB
	public static ArrayList<Event> delete(Event event){
			
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		System.out.println(event);
		System.out.println(event.get_id());
		Event eventD = em.find(Event.class,event.get_id());
			
		boolean transac = false;
		try {
			em.getTransaction().begin();
			em.remove(eventD);
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
		ArrayList<Event> eventsList = (ArrayList<Event>) em.createNativeQuery("SELECT * from events", Event.class)
				.getResultList();
		em.close();
		
		return eventsList;
	}
	
	//UPDATE AN USER IN THE DB
	public static ArrayList<Event> update(Event event){
			
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		System.out.println(event);
		System.out.println(event.get_id());
		//Event event = em.find(Event.class,event.getId());
		
		Event updateEvent = em.find(Event.class, event.get_id());
		boolean transac = false;
		try {
			em.getTransaction().begin();
			updateEvent.setNameEvent(event.getNameEvent());
			updateEvent.setDateEvent(event.getDateEvent());
			updateEvent.setStartTimeEvent(event.getStartTimeEvent());
			updateEvent.setEndTimeEvent(event.getEndTimeEvent());
			updateEvent.setDescription(event.getDescription());
			updateEvent.setIdCalendar(event.getIdCalendar());


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
		ArrayList<Event> eventsList = (ArrayList<Event>) em.createNativeQuery("SELECT * from events", Event.class)
				.getResultList();
		em.close();
		
		return eventsList;
	}

}
