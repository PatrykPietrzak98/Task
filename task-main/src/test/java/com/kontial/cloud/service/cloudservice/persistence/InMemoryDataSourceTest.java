package com.kontial.cloud.service.cloudservice.persistence;

import com.kontial.cloud.service.cloudservice.dto.PersonDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryDataSourceTest {

    private InMemoryDataSource inMemoryDataSource = new InMemoryDataSource();

    @Test
    public void getAll() {
        assertEquals(18, inMemoryDataSource.getAll().size());
    }

    @Test
    void addPerson() {
        assertEquals(18, inMemoryDataSource.getAll().size());
        var person = new PersonDto();

        person.setId("a1234");
        person.setName("Tomek");
        person.setBirthday("12-03-2000");

        inMemoryDataSource.addPerson(person);

        assertEquals(19, inMemoryDataSource.getAll().size());
    }
}