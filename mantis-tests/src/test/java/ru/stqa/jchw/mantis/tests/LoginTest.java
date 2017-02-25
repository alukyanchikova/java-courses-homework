package ru.stqa.jchw.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.jchw.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class LoginTest extends TestBase {

    @Test
    public void testLogin () throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root2"));
        assertTrue(session.isLoggedInAs("administrator"));
    }
}
