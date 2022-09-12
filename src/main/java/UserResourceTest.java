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
		

}
