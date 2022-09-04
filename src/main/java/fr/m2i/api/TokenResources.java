package fr.m2i.api;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.nimbusds.jose.JOSEException;

import fr.m2i.models.TokenModel;
import fr.m2i.models.User;
import fr.m2i.singleton.PrivateKey;
import methods.TokenMethods;


@Path("/tokens")
public class TokenResources {
	
	@GET
	@Path("/get")
	@Produces({MediaType.APPLICATION_JSON})
	//@Consumes({MediaType.APPLICATION_JSON})
	public String getToken() {
		return TokenMethods.issueToken(new User());
	}
	
	
	@GET
	@Path("/validate")
	//@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	//@Consumes({MediaType.APPLICATION_JSON})
	public void validate(TokenModel token) throws JOSEException, ParseException {
		System.out.println(token.getToken());
		System.out.println(token.getToken().getClass());
		System.out.println(PrivateKey.getINSTANCE().getKey());
		TokenMethods.validateToken(token.getToken());
	}
	

	@GET
	@Path("/test")
	@Produces({MediaType.APPLICATION_JSON})
	//@Consumes({MediaType.APPLICATION_JSON})
	public ResponseEntity<User> test()  {
		
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("token", 
	      "Bean 45615645233qs3d2sf23s8f3sd87f27sd83f78s3fs");
	    responseHeaders.set("token2", 
	  	      "Bean 56256618646d2sf23s8f3sd87f27sd83f78s3fs");
		return ResponseEntity.ok()
			      .headers(responseHeaders)
			      .body(new User());
	}
	
}
