package fr;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import fr.m2i.models.User;
import methods.TokenMethods;

public class TokenMethodsTest {
	
	private TokenMethods tokenMethods;
	
	@DisplayName("TestInit")
	@BeforeEach
	public void init() {
		this.tokenMethods = new TokenMethods();
	}
	
	@DisplayName("Ce test doit renvoyer un nouveau token")
	@Test
	public void testIssue() throws JOSEException, ParseException {
		
		User tokenUser = new User();
		tokenUser.setAccessRight("Administrator");

		String resultatObtenu = this.tokenMethods.issueToken(tokenUser) ;
		SignedJWT decodedJWT = SignedJWT.parse(resultatObtenu);
		String accessRight = (String) decodedJWT.getPayload()
				.toJSONObject().get("accessRight");
		
		assertTrue(this.tokenMethods.validateToken(resultatObtenu));
		assertTrue(accessRight.equals("Administrator"));
	}
	
	

}
