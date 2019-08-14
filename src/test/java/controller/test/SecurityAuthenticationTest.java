package controller.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.test.Application;
import com.test.UserData;
import com.test.repository.UserDataRepository;
import com.test.util.StaticVariable;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class SecurityAuthenticationTest {
	@Autowired
	UserDataRepository userDataRepository;

	@Autowired
	private MockMvc mvc;

	@Test
	public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/test")).andExpect(status().isForbidden());
	}

	@Test
	public void shouldGenerateAuthToken() throws Exception {
		UserData userTest = new UserData(0, "test", "test");
		userDataRepository.save(userTest);
		UserData userDataResult = userDataRepository.findByUsername("test");
		assertNotNull(userDataResult);
		String token = Jwts.builder().setSubject("test").setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + StaticVariable.JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, StaticVariable.SECRET).compact();
		assertNotNull(token);
		token = "Bearer " + token;
		mvc.perform(MockMvcRequestBuilders.get("/test").header("Authorization", token)).andExpect(status().isOk());
	}
}
