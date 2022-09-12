import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.m2i.api.UsersResource;
import fr.m2i.models.User;
import methods.UsersMethods;

@ExtendWith(MockitoExtension.class)
public class UserResourceTest {
	
	//@InjectMocks
		@Mock
		UsersMethods um;
		
		UsersResource ur;
		
		@BeforeEach
		public void init() {
			ur = new UsersResource();
			ur.umethod = um;
		}
		
		
		@Test
		public void testdisplay() throws Exception {
			
			// GIVEN
			ArrayList<User> listeUsers = new ArrayList<User>();
			listeUsers.add(new User());
			
			when(um.display()).thenReturn(listeUsers);	

			// WHEN
			final ArrayList<User> result = ur.display();
					
			// THEN
			verify(um).display();
			assertThat(result).isEqualTo(listeUsers);
		}
		
		@Test
		public void testGetUser() throws Exception {
			
			// GIVEN
			User user = new User();
			User repUser = new User();
			when(um.getUser(user)).thenReturn(repUser);	

			// WHEN
			final User result = ur.getUser(user);
					
			// THEN
			verify(um).getUser(user);
			assertThat(result).isEqualTo(repUser);
		}
		
		@Test
		public void testadd() throws Exception {
			
			// GIVEN
			User user = new User();
			ArrayList<User> listeUsers = new ArrayList<User>();
			listeUsers.add(new User());
			when(um.add(user)).thenReturn(listeUsers);	

			// WHEN
			final ArrayList<User> result = ur.add(user);
					
			// THEN
			verify(um).add(user);
			assertThat(result).isEqualTo(listeUsers);
		}
		
		@Test
		public void testdelete() throws Exception {
			
			// GIVEN
			User user = new User();
			ArrayList<User> listeUsers = new ArrayList<User>();
			listeUsers.add(new User());
			when(um.delete(user)).thenReturn(listeUsers);	

			// WHEN
			final ArrayList<User> result = ur.delete(user);
					
			// THEN
			verify(um).delete(user);
			assertThat(result).isEqualTo(listeUsers);
		}
		
		@Test
		public void testUpdate() throws Exception {
			
			// GIVEN
			User user = new User();
			ArrayList<User> listeUsers = new ArrayList<User>();
			listeUsers.add(new User());
			when(um.update(user)).thenReturn(listeUsers);	

			// WHEN
			final ArrayList<User> result = ur.update(user);
					
			// THEN
			verify(um).update(user);
			assertThat(result).isEqualTo(listeUsers);
		}
		
		@Test
		public void testUpdateFieldC() throws Exception {
			
			// GIVEN
			User user = new User();
			user.setId(1);
			user.setCanShare(true);
			ArrayList<User> listeUsers = new ArrayList<User>();
			listeUsers.add(new User());
			when(um.updateField(1,"canShare",true)).thenReturn(listeUsers);	

			// WHEN
			final ArrayList<User> result = ur.updateFieldCanShare(user);
					
			// THEN
			verify(um).updateField(1,"canShare",true);
			assertThat(result).isEqualTo(listeUsers);
		}
		
		@Test
		public void testUpdateFieldA() throws Exception {
			
			// GIVEN
			User user = new User();
			user.setId(1);
			user.setActive(true);
			ArrayList<User> listeUsers = new ArrayList<User>();
			listeUsers.add(new User());
			when(um.updateField(1,"active",true)).thenReturn(listeUsers);	

			// WHEN
			final ArrayList<User> result = ur.updateFieldActive(user);
					
			// THEN
			verify(um).updateField(1,"active",true);
			assertThat(result).isEqualTo(listeUsers);
		}
		
		@Test
		public void testUpdateFieldAR() throws Exception {
			
			// GIVEN
			User user = new User();
			user.setId(1);
			user.setAccessRight("Administrator");
			user.setSuperAdmin(true);
			ArrayList<User> listeUsers = new ArrayList<User>();
			listeUsers.add(new User());
			when(um.updateFieldAR(1,"Administrator",true)).thenReturn(listeUsers);	

			// WHEN
			final ArrayList<User> result = ur.updateFieldAccessRight(user);
					
			// THEN
			verify(um).updateFieldAR(1,"Administrator",true);
			assertThat(result).isEqualTo(listeUsers);
		}
		
		

}
