import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import fr.m2i.filtre.EventsFilter;
import fr.m2i.models.User;
import methods.TokenMethods;

public class FilterTest {
	
	private EventsFilter ef;
	
	@DisplayName("TestInit")
	@BeforeEach
	public void init() {
		this.ef = new EventsFilter();
	}

}
