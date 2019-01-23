package pages;

import data.User;
import org.openqa.selenium.By;

import static data.Constants.LOGIN_PAGE_URL;


public class LoginPage extends BasePage<LoginPage> {

    final protected By usernameInput = By.xpath("//*[@name='username']");
    final protected By passwordInput = By.xpath("//*[@name='password']");
    final protected By loginButton = By.xpath("//button[@type='submit']");
    final protected By successMessage = By.xpath("//*[@class='flash success']");
    final protected By errorMessage = By.xpath("//*[@class='flash error']");

    @Override
    protected String getPageUrl() {
        return LOGIN_PAGE_URL;
    }

    private void login(User user) {
        clearAndFill(usernameInput, user.getUsername());
        clearAndFill(passwordInput, user.getPassword());
        click(loginButton);
    }

    public void openAndLogin(User user) {
        open();
        login(user);
    }


    public boolean isSuccessMessageDisplayed() {
        return isElementVisible(successMessage);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementVisible(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }
}