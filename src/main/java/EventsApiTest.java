
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;


import fr.m2i.api.EventsResource;

import fr.m2i.models.Event;
import methods.EventsMethods;



@ExtendWith(MockitoExtension.class)
public class EventsApiTest {
	
	//@InjectMocks
	@Mock
	EventsMethods em;
	
	EventsResource er;
	
	@BeforeEach
	public void init() {
		er = new EventsResource();
		er.eMethod = em;
	}
	
	
	@Test
	public void testdisplay() throws Exception {
		
		// GIVEN
		ArrayList<Event> listeEvents = new ArrayList<Event>();
		listeEvents.add(new Event());
		when(em.display("")).thenReturn(listeEvents);	

		// WHEN
		final ArrayList<Event> result = er.display("","");
				
		// THEN
		verify(em).display("");
		assertThat(result).isEqualTo(listeEvents);
	}
	
	@Test
	public void testAddEvent() throws Exception {
		
		// GIVEN
		Event Event = new Event();
		ArrayList<Event> listeEvents = new ArrayList<Event>();
		listeEvents.add(new Event());
		
		when(em.add(Event,"")).thenReturn(listeEvents);	

		// WHEN
		final ArrayList<Event> result = er.add(Event,"");
				
		// THEN
		verify(em).add(Event,"");
		assertThat(result).isEqualTo(listeEvents);
	}
	
	@Test
	public void testDeleteEvent() throws Exception {
		
		// GIVEN
		Event Event = new Event();
		ArrayList<Event> listeEvents = new ArrayList<Event>();
		listeEvents.add(new Event());
		
		when(em.delete(Event,"")).thenReturn(listeEvents);	

		// WHEN
		final ArrayList<Event> result = er.delete(Event,"");
				
		// THEN
		verify(em).delete(Event, "");
		assertThat(result).isEqualTo(listeEvents);
	}
	
	@Test
	public void testUpdateEvent() throws Exception {
		
		// GIVEN
		Event Event = new Event();
		ArrayList<Event> listeEvents = new ArrayList<Event>();
		listeEvents.add(new Event());
		
		when(em.update(Event,"")).thenReturn(listeEvents);	

		// WHEN
		final ArrayList<Event> result = er.update(Event,"");
				
		// THEN
		verify(em).update(Event,"");
		assertThat(result).isEqualTo(listeEvents);
	}
}