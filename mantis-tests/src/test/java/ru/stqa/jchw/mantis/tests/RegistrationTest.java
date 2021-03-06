package ru.stqa.jchw.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.jchw.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.testng.AssertJUnit.assertTrue;

public class RegistrationTest extends TestBase {

    @Test
    public void testRegistration () throws IOException {
        // given
        final String username = UUID.randomUUID().toString();
        final String password = "password";
        final String email = username + "@localhost.localdomain";

        // when
        app.getRegistrationHelper().start(username, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.getRegistrationHelper().finish(confirmationLink, password);

        // then
        assertTrue(app.newSession().login(username, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
