package fc.Client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest{
    Login login = new Login("login", "pass");

    @Test getLoginTest(){
        assertEquals("login", login.getLogin());
    }

    @Test
    void setLoginTest(){
        login.setLogin("log");
        assertEquals("log", login.getLogin());
    }

    @Test getPWTest(){
        assertEquals("pass", login.getPassword());
    }

    @Test
    void setPWTest(){
        login.setPassword("log");
        assertEquals("password", login.getPassword());
    }
}