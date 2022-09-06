package fr.m2i.api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.m2i.models.Shares;
import fr.m2i.models.User;
import methods.ShareCalendarMethods;

@Path("/shareCalendar")
public class ShareCalendarResources {
	

	@POST
	@Path("/getContacts")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> get(User user){
		Integer id = user.getId();
		return ShareCalendarMethods.getContacts(id);
	}
	
	@POST
	@Path("/getNotContacts")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> getNot(User user){
		return ShareCalendarMethods.getNotContacts(user.getId());
	}
	
	@POST
	@Path("/add")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> add(User user){
		return ShareCalendarMethods.addContact(user);
	}
	
	@DELETE
	@Path("/delete")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> delete(User user){
		return ShareCalendarMethods.deleteContact(user);
	}
	
	
	
	
	
	@POST
	@Path("/getShares")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> getShares(User user){
		return ShareCalendarMethods.getShares(user.getId());
	}
	
	@POST
	@Path("/getNotShares")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> getNotShares(User user){
		return ShareCalendarMethods.getNotShares(user.getId());
	}
	
	@POST
	@Path("/addShare")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> addShare(User user) throws Exception{
		return ShareCalendarMethods.addShare(user);
	}
	
	@DELETE
	@Path("/deleteShare")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public ArrayList<User> deleteShare(User user){
		return ShareCalendarMethods.deleteShare(user);
	}
	
	@POST
	@Path("/editR")
	//@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public void editR(Shares share){
		System.out.println(share);
		System.out.println(share.getUserId()+" "+share.getCalendarId()+share.getId()+share.isReadonly());
		ShareCalendarMethods.editR(share);
	}
	
	@POST
	@Path("/getShare")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Shares getShare(Shares share){
		System.out.println(share);
		System.out.println(share.getUserId()+" "+share.getCalendarId()+share.getId()+share.isReadonly());
		return ShareCalendarMethods.getShare(share);
	}
	
	
	


}
