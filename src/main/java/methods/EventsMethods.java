package methods;
import java.util.ArrayList;

//import javax.activation.DataSource;
//import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.m2i.models.Event;
import fr.m2i.models.User;
public class EventsMethods {

	
	//@Resource(name="dataSource")
	//private DataSource dataSource;
	
	public static ArrayList<Event> display(String id) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		@SuppressWarnings("unchecked")
		/*<Event> eventsList = (ArrayList<Event>) em.createNativeQuery("SELECT * from events INNER JOIN calendars ON events.calendar_id = calendars.id INNER JOIN users ON calendars.user_id=users.id WHERE users.id=?;", Event.class)*/
		ArrayList<Event> eventsList = (ArrayList<Event>) em.createNativeQuery("SELECT * from events,calendars,users WHERE events.calendar_id = calendars.id AND calendars.user_id=users.id AND users.id=?;", Event.class)
		.setParameter(1,id)		
		.getResultList();

		em.close();
		System.out.println("GET ALL EVENTS");
		System.out.println(eventsList.get(0));
		return eventsList;
	}
	
	//get the events of a day from a user
	/*public static ArrayList<Event> get(Event event) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		//Integer id =event.getIdCalendar();
		java.util.Date utilDate  = event.getDateEvent();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		//Integer calendarId = event.getIdCalendar();
		System.out.println("DATE");
		System.out.println(utilDate);
		System.out.println(sqlDate);

		@SuppressWarnings("unchecked")
		ArrayList<Event> eventsList = (ArrayList<Event>) em.createNativeQuery("SELECT * from events WHERE date=? AND calendar_id=?", Event.class)
				.setParameter(1,sqlDate)
				.setParameter(2,calendarId)
				.getResultList();*/

		/*em.close();
		System.out.println(" GET EVENTS FROM DATE");
		System.out.println(eventsList);
		
		return eventsList;
	}*/
	
	//ADD A USER IN TH DB
	public static ArrayList<Event> add(Event event, String id) {
		
		System.out.println(event);
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
System.out.println(event);
		boolean transac = false;
		try {
			em.getTransaction().begin();
			 /*em.createNativeQuery("INSERT INTO events(name, date, start_time, end_time, description) VALUES ('?','?','?','?','?') WHERE events.calendar_id = calendars.id AND calendars.user_id=users.id AND users.id=?", Event.class)
			.setParameter(1,event.getNameEvent())	
			.setParameter(2,event.getDateEvent())
			.setParameter(3,event.getStartTimeEvent())
			.setParameter(4,event.getEndTimeEvent())
			.setParameter(5,event.getDescription())
			.setParameter(6,id);*/
			 System.out.println(event);
			User user = (User) em.createNativeQuery("SELECT * from users WHERE users.id=?",User.class)
					.setParameter(1,id)
					.getSingleResult();
			event.setIdCalendar(user.getCalendar());
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
		ArrayList<Event> eventsList = (ArrayList<Event>) em.createNativeQuery("SELECT * from events,calendars,users WHERE events.calendar_id = calendars.id AND calendars.user_id=users.id AND users.id=?;", Event.class)
		.setParameter(1,id)		
		.getResultList();
		em.close();
		
		return eventsList;
	}
	
	
	//DELETE A USER IN THE DB
	public static ArrayList<Event> delete(Event event, String id){
			
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		System.out.println(event);
		System.out.println(event.getId());
		Event eventD = em.find(Event.class,event.getId());
			
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
		ArrayList<Event> eventsList = (ArrayList<Event>) em.createNativeQuery("SELECT * from events,calendars,users WHERE events.calendar_id = calendars.id AND calendars.user_id=users.id AND users.id=?;", Event.class)
		.setParameter(1,id)		
		.getResultList();
		em.close();
		
		return eventsList;
	}
	
	//UPDATE AN USER IN THE DB
	public static ArrayList<Event> update(Event event, String id){
			
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		System.out.println(event);
		Integer idN = Integer.parseInt(id);
		
		Event updateEvent = em.find(Event.class, event.getId());
		boolean transac = false;
		try {
			em.getTransaction().begin();
			updateEvent.setNameEvent(event.getNameEvent());
			updateEvent.setDateEvent(event.getDateEvent());
			updateEvent.setStartTimeEvent(event.getStartTimeEvent());
			updateEvent.setEndTimeEvent(event.getEndTimeEvent());
			updateEvent.setDescription(event.getDescription());
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
		ArrayList<Event> eventsList = (ArrayList<Event>) em.createNativeQuery("SELECT * from events,calendars,users WHERE events.calendar_id = calendars.id AND calendars.user_id=users.id AND users.id=?;", Event.class)
		.setParameter(1,id)		
		.getResultList();
		em.close();
		
		return eventsList;
	}

}
