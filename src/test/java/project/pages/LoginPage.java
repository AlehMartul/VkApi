package project.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final String XPATH_LOGIN_FIELD = "//input[@id='index_email']";
    private ITextBox txbLoginField = AqualityServices.getElementFactory()
            .getTextBox(By.xpath(XPATH_LOGIN_FIELD), "Login field");
    private ITextBox txbPasswordField = AqualityServices.getElementFactory()
            .getTextBox(By.xpath("//input[@id='index_pass']"), "Password field");
    private IButton btnLogIn = AqualityServices.getElementFactory()
            .getButton(By.xpath("//button[@id='index_login_button']"), "Login button");

    public LoginPage() {
        super(By.xpath(XPATH_LOGIN_FIELD), "Login page");
    }

    public void enterLogin(String login) {
        AqualityServices.getLogger().info("Entering login");
        txbLoginField.sendKeys(login);
    }

    public void enterPassword(String password) {
        AqualityServices.getLogger().info("Entering password");
        txbPasswordField.sendKeys(password);
    }

    public void clickOnLoginButton() {
        AqualityServices.getLogger().info("Clicking on 'Login' button");
        btnLogIn.click();
    }
}
