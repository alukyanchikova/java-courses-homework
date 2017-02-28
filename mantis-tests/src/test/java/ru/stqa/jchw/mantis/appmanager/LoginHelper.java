package ru.stqa.jchw.mantis.appmanager;

import org.openqa.selenium.By;

import java.util.Properties;

public class LoginHelper extends HelperBase{

    private Properties properties;

    public LoginHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String login, String password) {
        wd.get(app.getProperty("web.baseUrl") + "login_page.php");
        type(By.name("username"), login);
        type(By.name("password"), password);
        clickOnForm(By.cssSelector("input[type='submit']"));
    }
}
