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

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import fr.m2i.models.ImageBlob;
import fr.m2i.models.User;
import fr.m2i.singleton.PrivateKey;
import methods.TokenMethods;
import methods.UsersMethods;


@Path("/users")
public class UsersResource {
	
	
	
	public static ArrayList<User> listeTaches = new ArrayList<User>();
	public UsersMethods umethod = new UsersMethods();
	public TokenMethods tm = new TokenMethods();

	//Récupérer la liste des users
	@SuppressWarnings("hiding")
	@GET
	@Path("/all")
	@Produces({MediaType.APPLICATION_JSON})
	public ArrayList<User> display(){
		return umethod.display();
	}
	
	
	//Login method called
	@POST
	@Path("/login")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ResponseEntity<User> login(User user){
		
		//get the user if exists 
		User body = umethod.login(user);
		//No user with these email/password access
		if (body == null){
			return null;
		//User found
		}else {
			//Set token in the response Header 
			HttpHeaders responseHeaders = new HttpHeaders();
		    responseHeaders
		    	.set("authorization",tm.issueToken(body));
			return ResponseEntity.ok()
				      .headers(responseHeaders)
				      //and User in the responsebody
				      .body(body);			
		}	
	}
	
	@GET
	@Path("/get")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public User getUser(User user){
		return umethod.getUser(user);
	}
	
	//Récupérer la liste des users
	@POST
	@Path("/add")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> add(User user){
		System.out.println("Injected USER");
		System.out.println(user);
		return umethod.add(user);
	}
	
	//Delete an user
	@DELETE
	@Path("/delete")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> delete(User user){
		System.out.println("Injected USER");
		System.out.println(user.getClass());
		return umethod.delete(user);
	}
	
	//Update an user 
	@PUT
	@Path("/update")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> update(User user,@PathParam("id") Integer id ){
		return umethod.update(user);
	}
	
	@PUT
	@Path("/updateFieldActive")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> updateFieldActive(User user){
		return umethod.updateField(user.getId(),"active",user.getActive());
	}
	
	@PUT
	@Path("/updateFieldCanShare")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> updateFieldCanShare(User user){
		return umethod.updateField(user.getId(),"canShare",user.getCanShare());
	}
	
	@PUT
	@Path("/updateFieldAccessRight")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> updateFieldAccessRight(User user){
		return umethod.updateFieldAR(user.getId(),user.getAccessRight(), user.getSuperAdmin());
	}

}
