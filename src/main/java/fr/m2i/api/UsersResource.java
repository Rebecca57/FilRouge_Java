package fr.m2i.api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestParam;

import fr.m2i.models.User;
import methods.UsersMethods;


@Path("/users")
public class UsersResource {
	
	public static ArrayList<User> listeTaches = new ArrayList<User>();

	//Récupérer la liste des users
	@SuppressWarnings("hiding")
	@GET
	@Path("/all")
	@Produces({MediaType.APPLICATION_JSON})
	public ArrayList<User> display(){
		return UsersMethods.display();
	}
	
	
	//Récupérer la liste des users
	@POST
	@Path("/add")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> add(User user){
		System.out.println("Injected USER");
		System.out.println(user);
		return UsersMethods.add(user);
	}
	
	//Delete an user
	@DELETE
	@Path("/delete")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> delete(User user){
		System.out.println("Injected USER");
		System.out.println(user.getClass());
		return UsersMethods.delete(user);
	}
	
	//Update an user 
	@PUT
	@Path("/update")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> update(User user,@PathParam("id") Integer id ){
		return UsersMethods.update(user);
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
