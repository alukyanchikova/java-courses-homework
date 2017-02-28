package ru.stqa.jchw.mantis.appmanager;

import org.openqa.selenium.By;

public class ResetHelper extends HelperBase{

    public ResetHelper(ApplicationManager app) {
        super(app);
    }

    public void selectManageUserPage() {
        clickOnForm(By.cssSelector("a[href*='/www/mantisbt-2.1.0/manage_user_page.php'"));

    }
    public void selectUserById(String userLogin) {
        clickOnForm(By.linkText(userLogin.toString()));
    }

    public void clickSubmitPassword() {
        clickOnForm(By.id("manage-user-reset-form"));
    }
}
