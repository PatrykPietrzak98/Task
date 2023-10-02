package com.kontial.cloud.service.cloudservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kontial.cloud.service.cloudservice.dto.PersonDto;
import com.kontial.cloud.service.cloudservice.model.Person;
import com.kontial.cloud.service.cloudservice.persistence.InMemoryDataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InMemoryDataSource inMemoryDataSource;


    @Test
    void persons() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/persons")).andExpect(status().isOk());
    }

    @Test
    void personsSummary() throws Exception {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("a1234", "Abigail", LocalDate.of(1990,5,23)));
        persons.add(new Person("b5678", "Tom", LocalDate.of(1983, 2,12)));
        persons.add(new Person("c1234", "Austin", LocalDate.of(1995, 1,25)));
        persons.add(new Person("d1234", "Abigail", LocalDate.of(1999,6,20)));
        persons.add(new Person("e3444", "Abigail", LocalDate.of(1993, 7,23)));
        when(inMemoryDataSource.getAll()).thenReturn(persons);

        mockMvc.perform(get("http://localhost:8080/api/persons/summary"))
                .andExpect(status().isOk()).andExpect(content().json("{Abigail:3,Austin:1,Tom:1}"));
    }

    @Test
    void addPerson() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setId("a1123");
        personDto.setName("Alice");
        personDto.setBirthday("01-01-1990");

        when(inMemoryDataSource.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(post("http://localhost:8080/api/person").contentType("application/json").content(asJson(personDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Person Added!"));

    }

    public static String asJson(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}