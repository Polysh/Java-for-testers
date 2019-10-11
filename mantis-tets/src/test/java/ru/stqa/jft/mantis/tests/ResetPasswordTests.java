package ru.stqa.jft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.jft.mantis.model.MailMessage;
import ru.stqa.jft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {


    @Test
    public void testResetPassword() throws IOException, MessagingException {
        UserData dbUser = app.db().users().iterator().next();
        String user = dbUser.getUserName();
        String email = dbUser.getEmail();
        String oldPassword = dbUser.getPassword();

        app.registration().start(System.getProperty("web.adminLogin"), System.getProperty("web.Password"));
        app.us().resetUserPassword(user);
        List<MailMessage> mailMessages = app.james().waitForMail(user, oldPassword, 60000);
        String newPassword = findPassword(mailMessages, email);

        assertTrue(app.newSession().login(user, newPassword));

    }

    private String findPassword(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("password").nonSpace().oneOrMore().build();
        String password = regex.getText(mailMessage.text).substring(9, 42);
        return password;
    }


}
