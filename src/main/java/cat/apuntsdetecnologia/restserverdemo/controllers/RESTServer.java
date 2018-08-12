package cat.apuntsdetecnologia.restserverdemo.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.apuntsdetecnologia.restserverdemo.model.Address;
import cat.apuntsdetecnologia.restserverdemo.model.Person;


// https://o7planning.org/en/11647/spring-boot-restful-client-with-resttemplate-example


@RestController
public class RESTServer {

	// embedded tocat
	// 	http://localhost:8080/demo
	// 	http://localhost:8080/demo?first=Albert&second=Baranguer
	// external tomcat
	// 	http://localhost:8080/RESTServerDemo/demo
	// 	http://localhost:8080/RESTServerDemo/demo?first=Albert&second=Baranguer
	
	@RequestMapping("/demo")
	public Address demo(
			@RequestParam(value="first", defaultValue="John") String firstName,
			@RequestParam(value="second", defaultValue="Smith") String secondName) {
		Address address = new Address();
		Person person = new Person();
		
		person.setFirstName(firstName);
		person.setSecondName(secondName);
		address.setPerson(person);
		address.setCity("myCity");
		address.setCountry("myCountry");
		address.setDoor(1);
		address.setFloor(2);
		address.setNumber(3);
		address.setStreet("myStreet");
		address.setZipCode("myZipCode");
		
		return address;
	}
	
	@RequestMapping("/toupper")
	public Address toupper(
			@RequestBody Address address,
			@RequestParam(value="first", defaultValue="John") String firstName,
			@RequestParam(value="second", defaultValue="Smith") String secondName) {
		Address newAddress = new Address();
		Person newPerson = new Person();
		
		newPerson.setFirstName(address.getPerson().getFirstName().toUpperCase());
		newPerson.setSecondName(address.getPerson().getSecondName().toUpperCase());
		newAddress.setPerson(newPerson);
		newAddress.setCity(address.getCity().toUpperCase());
		newAddress.setCountry(address.getCountry().toUpperCase());
		newAddress.setDoor(address.getDoor());
		newAddress.setFloor(address.getFloor());
		newAddress.setNumber(address.getNumber());
		newAddress.setStreet(address.getStreet().toUpperCase());
		newAddress.setZipCode(address.getZipCode().toUpperCase());
		
		return newAddress;
	}
	
	
}
