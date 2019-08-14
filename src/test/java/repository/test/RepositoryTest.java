package repository.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.test.Application;
import com.test.UserData;
import com.test.repository.UserDataRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = Application.class)
public class RepositoryTest {

	@Autowired
	UserDataRepository userDataRepository;

	@Test
	public void testSaveUserData() {
		UserData userDataInput = new UserData(0, "test", "test");
		userDataRepository.save(userDataInput);
		UserData userDataResult = userDataRepository.findByUsername("test");
		assertNotNull(userDataResult);
		assertEquals(userDataInput.getUsername(), userDataResult.getUsername());
		assertEquals(userDataInput.getPassword(), userDataInput.getPassword());
	}
}
