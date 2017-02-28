package ru.stqa.jchw.mantis.tests;

import org.junit.Assume;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.jchw.mantis.model.MailMessage;
import ru.stqa.jchw.mantis.model.UserData;

import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ResetPasswordTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testResetPassword () throws IOException {
        // given
        List<UserData> listOfUsersFromDb = app.db().findUserByLogin("test");
        Assume.assumeTrue(listOfUsersFromDb.size() == 1);
        UserData user = listOfUsersFromDb.get(0);
        String userLogin = user.getLogin();
        String userEmail = user.getEmail();
        String password = "password";

        // when
        app.getLoginHelper().login(app.getProperty("login"), app.getProperty("password"));
        app.getResetHelper().selectManageUserPage();
        app.getResetHelper().selectUserById(userLogin);
        app.getResetHelper().clickSubmitPassword();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);

        String confirmationLink = findConfirmationLink(mailMessages, userEmail);
        app.getRegistrationHelper().finish(confirmationLink, password);

        // then
        assertTrue(app.newSession().login(userLogin, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }



}
