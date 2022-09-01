package fr.m2i.api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import methods.TokenMethods;


@Path("tokens")
public class TokenResources {
	
	@GET
	@Path("/get")
	@Produces({MediaType.APPLICATION_JSON})
	//@Consumes({MediaType.APPLICATION_JSON})
	public String getToken() {
		return TokenMethods.issueToken("Administrator");
	}

}
