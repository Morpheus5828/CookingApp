package app.foodapp.controller.login;

import app.foodapp.model.node.login.SignUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SignUpTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private SignUp simpleSignUp;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void userAlreadyExistTest() throws IOException {
        simpleSignUp = new SignUp();
        simpleSignUp.userRegister("Morpheus", "123", "Vegan");
        assertEquals(simpleSignUp.userAlreadyExist("Morpheus,123,Vegan,\n"), true);
        assertEquals(simpleSignUp.userAlreadyExist("Chloé,123951,Vegan,\n"), false);
    }

    @Test
    public void userRegister() throws IOException {
        simpleSignUp = new SignUp();
        simpleSignUp.userRegister("Ophelie", "6552153", "Vegan");
        assertEquals("User has been add successfully", outputStreamCaptor.toString().trim());
    }

    @Test
    public void userRegisterWithoutPassword() throws IOException {
        simpleSignUp = new SignUp();
        simpleSignUp.userRegister("Murphy", "", "Vegan");
        assertEquals("You forget to enter password, please try again", outputStreamCaptor.toString().trim());
    }

    @Test
    public void userRegisterWithoutUsername() throws IOException {
        simpleSignUp = new SignUp();
        simpleSignUp.userRegister("", "password", "Vegan");
        assertEquals("You forget to enter username, please try again", outputStreamCaptor.toString().trim());
    }

}
