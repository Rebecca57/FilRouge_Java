package fr.m2i.api;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import fr.m2i.models.Event;
import fr.m2i.models.EventId;
import fr.m2i.models.User;
import methods.EventsMethods;
import methods.TokenMethods;
import methods.UsersMethods;

@Path("/events")
public class EventsResource {
	
	public static ArrayList<Event> listeTaches = new ArrayList<Event>();
	
	//Récupérer la liste des Events
	@POST
	@Path("/all")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
	public ArrayList<Event> display(String ide,@QueryParam("id") String id){
		return EventsMethods.display(id);
	}
	
	
	//Récupérer la liste des Events
	@POST
	@Path("/add")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<Event> add(Event event,@QueryParam("id") String id){	
		return EventsMethods.add(event,id);
	}
	
	//Delete an Event
	@POST
	@Path("/delete")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<Event> delete(Event Event,@QueryParam("id") String id){
		System.out.println("Injected Event");
		System.out.println(Event.getClass());
		return EventsMethods.delete(Event, id);
	}
	
	//Update an Event 
	@PUT
	@Path("/update")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<Event> update(Event Event,@QueryParam("id") String id){
		return EventsMethods.update(Event, id );
	}

}
