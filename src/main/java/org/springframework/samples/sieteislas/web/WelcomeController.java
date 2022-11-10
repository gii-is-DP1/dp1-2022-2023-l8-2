package org.springframework.samples.sieteislas.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.sieteislas.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    
		
		
		Person juan = new Person();
		juan.setFirstName("Juan");
		juan.setLastName(" López");
		
		Person jose = new Person();
		jose.setFirstName("Jose");
		jose.setLastName(" Castro");
		
		Person maria = new Person();
		maria.setFirstName("Maria del Mar");
		maria.setLastName(" Ávila");
		
		Person victoria = new Person();
		victoria.setFirstName("Victoria");
		victoria.setLastName(" del Carmen");
		
		Person pablo = new Person();
		pablo.setFirstName("Pablo");
		pablo.setLastName(" Bermudez");
		
		Person thomas = new Person();
		thomas.setFirstName("Thomas");
		thomas.setLastName(" Österreich");
		
		List<Person> people = List.of(juan,jose,maria,victoria,pablo,thomas);
		model.put("people", people);
		model.put("title", "7 Islands");
		model.put("group", "L8-2");
		
	    return "welcome";
	  }
}
