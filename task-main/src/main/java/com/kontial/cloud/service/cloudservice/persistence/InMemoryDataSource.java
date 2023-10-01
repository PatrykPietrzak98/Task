package com.kontial.cloud.service.cloudservice.persistence;

import com.kontial.cloud.service.cloudservice.dto.PersonDto;
import com.kontial.cloud.service.cloudservice.model.Person;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryDataSource {

    private static List<Person> persons;

    static {
        persons = new ArrayList<>();
        persons.add(new Person("h2314", "Thomas", LocalDate.of(1999,12,25)));
        persons.add(new Person("f5962", "Thomas", LocalDate.of(1982,2,26)));
        persons.add(new Person("e5891", "Evelin", LocalDate.of(1955,10,9)));
        persons.add(new Person("t7811", "Oliver", LocalDate.of(1996,5,25 )));
        persons.add(new Person("z5894", "Oliver", LocalDate.of(1998,4,15)));
        persons.add(new Person("s8971", "Oliver", LocalDate.of(1956,1,23)));
        persons.add(new Person("u5841", "Oliver", LocalDate.of(2000,8,8)));
        persons.add(new Person("n2361", "Jennifer", LocalDate.of(1974,7,19)));
        persons.add(new Person("w2054", "John", LocalDate.of(1966,11,28)));
        persons.add(new Person("x9815", "Mike", LocalDate.of(1954,1,11)));
        persons.add(new Person("c6358", "Henry", LocalDate.of(1985,5,22)));
        persons.add(new Person("a2601", "Lucas", LocalDate.of(1980,3,25)));
        persons.add(new Person("e8450", "Alice", LocalDate.of(1979,6,27)));
        persons.add(new Person("w9640", "Alice", LocalDate.of(1978,9,14)));
        persons.add(new Person("e5036", "Alice", LocalDate.of(1967,1,28)));
        persons.add(new Person("t8405", "Andrea", LocalDate.of(2002,8,23)));
        persons.add(new Person("u7840", "Ava", LocalDate.of(1991,9,16)));
        persons.add(new Person("i6922", "Ava", LocalDate.of(1961,6,1)));
    }

    public List<Person> getAll() {
        return persons;
    }

    public void addPerson(PersonDto person){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthday = LocalDate.parse(person.getBirthday(), dtf);
        persons.add(new Person(person.getId(), person.getName(), birthday));
    }
}
