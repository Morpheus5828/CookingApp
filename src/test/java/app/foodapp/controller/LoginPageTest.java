package app.foodapp.controller;

import app.foodapp.controller.login.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginPageTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private LoginPage simpleLogin;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void checkUserNameLoginTest() throws IOException {
        simpleLogin = new LoginPage();
        simpleLogin.checkUserLogin("Morph", "123");
        assertEquals("Username not exist, please create an account", outputStreamCaptor.toString().trim());
    }

    @Test
    public void checkUserPasswordTest() throws IOException {
        simpleLogin = new LoginPage();
        simpleLogin.checkUserLogin("Morpheus", "12");
        assertEquals("Password doesn't match, please try again", outputStreamCaptor.toString().trim());
    }

    @Test
    public void checkUserLoginTest() throws IOException {
        simpleLogin = new LoginPage();
        simpleLogin.checkUserLogin("Morpheus", "123");
        assertEquals("Login validated", outputStreamCaptor.toString().trim());
    }
}
