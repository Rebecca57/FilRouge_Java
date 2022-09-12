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

/**
@Path("/tokens")
public class TokenResources {
	
	@GET
	@Path("/validate")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	//@Consumes({MediaType.APPLICATION_JSON})
	public boolean validate(TokenModel token) throws JOSEException, ParseException {
		return TokenMethods.validateToken(token.getToken());
	}
	
}
**/