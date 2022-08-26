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
import javax.ws.rs.core.MediaType;

import fr.m2i.models.Event;
import methods.EventsMethods;

@Path("/events")
public class EventsResource {
	
	public static ArrayList<Event> listeTaches = new ArrayList<Event>();

	//Récupérer la liste des Events
	@SuppressWarnings("hiding")
	@POST
	@Path("/get")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<Event> get(Event event){
		return EventsMethods.get(event);
	}
	
	//Récupérer la liste des Events
	@SuppressWarnings("hiding")
	@GET
	@Path("/all")
	@Produces({MediaType.APPLICATION_JSON})
	public ArrayList<Event> display(){
		return EventsMethods.display();
	}
	
	
	//Récupérer la liste des Events
	@POST
	@Path("/add")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<Event> add(Event Event){
		System.out.println("Injected Event");
		System.out.println(Event);
		return EventsMethods.add(Event);
	}
	
	//Delete an Event
	@DELETE
	@Path("/delete")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<Event> delete(Event Event){
		System.out.println("Injected Event");
		System.out.println(Event.getClass());
		return EventsMethods.delete(Event);
	}
	
	//Update an Event 
	@PUT
	@Path("/update")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<Event> update(Event Event,@PathParam("id") Integer id ){
		return EventsMethods.update(Event);
	}

	/**
	//Ajouter une tache dans la liste
	@POST
	@Path("/all")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public static void add(@FormParam("nom") String nom,
			@FormParam("desc") String desc,
			@FormParam("date") String date) {
		Tache newTache = new Tache(nom,desc,date);
		System.out.println("POST method add: "+newTache);
		TachesRessource.listeTaches.add(newTache);
	}
	
	//Supprimer une tache
	@DELETE
	@Path("/tache/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public static void delete(@PathParam("id") int id) {
		for (int i =0; i<listeTaches.size();i++) {
			TachesRessource.listeTaches.remove(id);
		}
		System.out.println(listeTaches.size());
	}
	**/

}
