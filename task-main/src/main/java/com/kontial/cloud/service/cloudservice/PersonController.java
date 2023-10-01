package com.kontial.cloud.service.cloudservice;

import com.kontial.cloud.service.cloudservice.dto.PersonDto;
import com.kontial.cloud.service.cloudservice.model.Person;
import com.kontial.cloud.service.cloudservice.persistence.InMemoryDataSource;
import com.kontial.cloud.service.cloudservice.util.PersonValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;


@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

	private InMemoryDataSource inMemoryDataSource;

	@Autowired
	public PersonController(InMemoryDataSource inMemoryDataSource) {
		this.inMemoryDataSource = inMemoryDataSource;
	}

	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public ResponseEntity<?> persons() {
		try {
			var persons = inMemoryDataSource.getAll();
			return ResponseEntity.ok(persons.stream()
					.map(person -> Map.of("id", person.id(),"name", person.name(), "year", person.birthday().getYear()))
					.collect(Collectors.toList()));
		} catch (RuntimeException ex){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred");
		}
	}

	@RequestMapping(value = "/persons/summary", method = RequestMethod.GET)
	public ResponseEntity<?> personsSummary() {
		try {
			var persons = inMemoryDataSource.getAll();
			return ResponseEntity.ok(persons.stream()
					.sorted(Comparator.comparing(Person::name))
					.collect(Collectors.groupingBy(Person::name, LinkedHashMap::new, Collectors.counting())));
		}catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred");
		}
	}

	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public ResponseEntity<String> addPerson(@RequestBody PersonDto personDto) {
		try {
			if(PersonValidatorUtil.isValidId(personDto.getId())){
				var persons = inMemoryDataSource.getAll();
				if (persons.stream().anyMatch(person -> person.id().equals(personDto.getId()))){
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The person with the given Id already exists ");
				}
				if (personDto.getName() == null){
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The name cannot be empty");
				}
				if (!PersonValidatorUtil.isValidDate(personDto.getBirthday())){
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect date format (dd-MM-yyyy)");
				}

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.TEXT_PLAIN);

				PersonDto newPerson = new PersonDto();
				newPerson.setId(personDto.getId());
				newPerson.setName(personDto.getName());
				newPerson.setBirthday(personDto.getBirthday());

				inMemoryDataSource.addPerson(newPerson);
				return ResponseEntity.ok().headers(headers).body("Person Added!");
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong id format");
		}catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred");
		}
	}
}
