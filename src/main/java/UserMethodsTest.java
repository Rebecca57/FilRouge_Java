
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import fr.m2i.api.UsersResource;
import fr.m2i.models.User;
import methods.UsersMethods;


@ExtendWith(MockitoExtension.class)
public class UserMethodsTest {
	
	private UsersMethods userMethods;
	
	@DisplayName("TestInit")
	@BeforeEach
	public void init() {
		this.userMethods = new UsersMethods();
	}
	
	@ParameterizedTest(name="Ce test doit afficher: {2}")
	@CsvSource({"a,86f7e437faa5a7fce15d1ddcb9eaeaea377667b8,1,Bernard,Campan"})
	public void testLoginOK(String email, String password, Integer id,String firstname, String lastname) {
	
		User loginUser = new User();
		loginUser.setEmail(email);
		loginUser.setPassword(password);
		User resultatObtenu = this.userMethods.login(loginUser) ;
		
		User resultatAttendu = new User();
		resultatAttendu.setId(id);
		resultatAttendu.setFirstname(firstname);
		resultatAttendu.setLastname(lastname);
		

		assertEquals(resultatAttendu.getId(), resultatObtenu.getId());
		assertEquals(resultatAttendu.getFirstname(), resultatObtenu.getFirstname());
		
	}
	
	@DisplayName("Test getUser()")
	@Test
	public void testgetUser() {
	
		User getUser = new User();
		getUser.setId(1);
		User resultatObtenu = this.userMethods.getUser(getUser) ;
		
		User resultatAttendu = new User();
		resultatAttendu.setFirstname("Bernard");	
		assertEquals(resultatAttendu.getFirstname(), resultatObtenu.getFirstname());
		assertTrue(resultatObtenu instanceof User);
	}
	

	@ParameterizedTest(name="Ce test doit afficher: rien")
	@CsvSource({"ab,jikliuloiui"})
	public void testLoginKO(String email, String password) {
	
		System.out.println(email);
		User loginUser = new User();
		loginUser.setEmail(email);
		loginUser.setPassword(password);
		System.out.println("TEST!!!!!!!!!!!!!!!!");
		System.out.println("TEST!!!!!!!!!!!!!!!!");
		System.out.println("TEST!!!!!!!!!!!!!!!!");
		System.out.println("TEST!!!!!!!!!!!!!!!!");
		System.out.println("TEST!!!!!!!!!!!!!!!!");
		System.out.println(loginUser.getEmail());
		System.out.println(email);
		User resultatObtenu = this.userMethods.login(loginUser) ;
		
		assertNull(resultatObtenu);	
	}
	
	
	@DisplayName("Ce test doit renvoyer la bonne liste de users")
	@Test
	public void testDisplay() {
	
		ArrayList<User> resultatObtenu = this.userMethods.display() ;
		
		assertEquals(1, resultatObtenu.get(0).getId());	
		assertNull(resultatObtenu.get(0).getPassword());	
	}
	
	@DisplayName("Ce test doit renvoyer la liste with an user added")
	@Test
	public void testAdd() {
	
		User newUser = new User();
		newUser.setEmail("test@gmail.com");
		newUser.setPassword("test");
		ArrayList<User> resultatObtenu = this.userMethods.add(newUser) ;
		
		assertTrue(resultatObtenu.stream().anyMatch(e -> e.getId() == newUser.getId()));
		assertEquals(1, resultatObtenu.get(0).getId());	
		assertNull(resultatObtenu.get(0).getPassword());
	}
	

	@DisplayName("Ce test doit renvoyer la liste with an modified user")
	@Test
	public void testUpdate() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		User updateUser = (User) em.createNativeQuery("SELECT * from users where users.email=?1", User.class)
			.setParameter(1, "te@gmail.com")	
			.getSingleResult();
		em.close();
		factory.close();
		updateUser.setFirstname("test1");

		ArrayList<User> resultatObtenu = this.userMethods.update(updateUser) ;
		
		assertTrue(resultatObtenu.stream().anyMatch(e -> (e.getId() == updateUser.getId()) && (e.getFirstname() == updateUser.getFirstname()) ));
	}
	
	@DisplayName("Ce test doit vérifier les methodes updateFields")
	@Test
	public void testupdateFields() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		User updateFUser = (User) em.createNativeQuery("SELECT * from users where users.email=?1", User.class)
			.setParameter(1, "test@gmail.com")	
			.getSingleResult();
		em.close();
		factory.close();
		
		

		ArrayList<User> resultatObtenu = this.userMethods.updateField(updateFUser.getId(),"active",true);
		ArrayList<User> resultatObtenu1 = this.userMethods.updateField(updateFUser.getId(),"canShare",true);
		ArrayList<User> resultatObtenu2 = this.userMethods.updateFieldAR(updateFUser.getId(),"Collaborator",true);
		
		assertTrue(resultatObtenu.stream().anyMatch(e -> (e.getId() == updateFUser.getId()) 
				&& (e.getActive() == true) ));
		assertTrue(resultatObtenu1.stream().anyMatch(e -> (e.getId() == updateFUser.getId()) 
				&& (e.getCanShare() == true) ));
		assertTrue(resultatObtenu2.stream().anyMatch(e -> (e.getId() == updateFUser.getId()) 
				&& (e.getAccessRight().equals("Collaborator")) 
				&& (e.getSuperAdmin()) ));
		assertEquals(1, resultatObtenu.get(0).getId());	
		assertNull(resultatObtenu.get(0).getPassword());
	}
	
	
	@DisplayName("Ce test doit vérifier la supression d'un user")
	@Test
	public void testDelete() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		User deleteUser = (User) em.createNativeQuery("SELECT * from users where users.email=?1", User.class)
			.setParameter(1, "test@gmail.com")	
			.getSingleResult();
		em.close();
		factory.close();

		ArrayList<User> resultatObtenu = this.userMethods.delete(deleteUser) ;
		
		assertFalse(resultatObtenu.stream().anyMatch(e -> e.getId() == deleteUser.getId()));
		assertEquals(1, resultatObtenu.get(0).getId());	
		assertNull(resultatObtenu.get(0).getPassword());
	}
	

	
	

}
