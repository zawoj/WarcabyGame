package com.client.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.client.controllers.RegisteryController;
import com.client.helpers.exceptions.StringLengthException;
import com.client.helpers.exceptions.StringSameValidation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

@ExtendWith(ApplicationExtension.class)
public class RegisteryControllerTest {
    RegisteryController instance;

    @BeforeEach
    public void init() {
        instance = new RegisteryController();
    }

    @Test
    @DisplayName("Checking: password length validation")
    void testPasswordLength() throws StringLengthException {
        assertEquals(true, instance.passwordValidation("12345"));
    }

    @Test
    @DisplayName("Checking: Throw exception password length validation")
    void testThrowExceptionPasswordLength() throws StringLengthException {
        Throwable exception = assertThrows(StringLengthException.class, () -> instance.passwordValidation("123"));
        assertEquals("Password must be at least 4 characters", exception.getMessage());
    }

    @Test
    @DisplayName("Checking: password and checkPassword are same")
    void testPasswordCheckPassword() throws StringSameValidation {
        assertEquals(true, instance.passwordCheckValidation("12345", "12345"));
    }

    @Test
    @DisplayName("Checking: Throw exception password length validation")
    void testThrowExceptionPasswordCheckPassword() throws StringSameValidation {
        Throwable exception = assertThrows(StringSameValidation.class,
                () -> instance.passwordCheckValidation("12345", "123"));
        assertEquals("Password are not same", exception.getMessage());
    }
}
