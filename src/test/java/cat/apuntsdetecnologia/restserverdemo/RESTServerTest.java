package cat.apuntsdetecnologia.restserverdemo;


import static org.hamcrest.Matchers.*;  // is
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cat.apuntsdetecnologia.restserverdemo.controllers.RESTServer;
import cat.apuntsdetecnologia.restserverdemo.model.Address;
import cat.apuntsdetecnologia.restserverdemo.model.Person;

// https://spring.io/guides/gs/testing-web/
// https://jsonpath.herokuapp.com/

@RunWith(SpringRunner.class)
@WebMvcTest(RESTServer.class)
public class RESTServerTest {

	private static final Logger logger = LoggerFactory.getLogger(RESTServerTest.class);
	
	@Autowired
	private MockMvc mvc;

	@Test
	public void demoTest() {
		try {
			mvc.perform(get("/demo?first=Albert&second=Baranguer"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.person.firstName", is("Albert")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void toUpperTest() {
		Address address = new Address();
		Person person = new Person();
		
		person.setFirstName("Albert");
		person.setSecondName("Baranguer");
		address.setPerson(person);
		address.setCity("myCity");
		address.setCountry("myCountry");
		address.setDoor(1);
		address.setFloor(2);
		address.setNumber(3);
		address.setStreet("myStreet");
		address.setZipCode("myZipCode");
		
		// http://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/
		ObjectMapper mapper = new ObjectMapper();
		
		String content;
		
		try {
			content = mapper.writeValueAsString(address);
		} catch (JsonProcessingException e1) {
			content = "";
		}
		
		try {
			mvc.perform(post("/toupper")
					.contentType(MediaType.APPLICATION_JSON)
					.content( content ))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.person.firstName", is("ALBERT")));
		} catch (Exception e) {
			logger.debug("Error : "  + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
