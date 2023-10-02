package com.kontial.cloud.service.cloudservice.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonValidatorUtilTest {
    @Test
    public void testIsValidDate_should_return_true(){
        String date = "25-12-1990";

        assertTrue(PersonValidatorUtil.isValidDate(date));
    }

    @Test
    public void testIsValidDate_should_return_false(){
        String date = "25/12/1990";

        assertFalse(PersonValidatorUtil.isValidDate(date));
    }

    @Test
    public void testIsValidId_id_with_uppercase_char_should_return_true(){
        String id = "A1234";

        assertTrue(PersonValidatorUtil.isValidId(id));
    }

    @Test
    public void testIsValidId_id_with_lowercase_char_should_return_true(){
        String id = "a1234";

        assertTrue(PersonValidatorUtil.isValidId(id));
    }

    @Test
    public void testIsValidId_id_with_two_char_should_return_false(){
        String id = "Aa234";

        assertFalse(PersonValidatorUtil.isValidId(id));
    }

    @Test
    public void testIsValidId_id_without_char_should_return_false(){
        String id = "12345";

        assertFalse(PersonValidatorUtil.isValidId(id));
    }

    @Test
    public void testIsValidId_too_long_id_should_return_false(){
        String id = "a12345";

        assertFalse(PersonValidatorUtil.isValidId(id));
    }
}