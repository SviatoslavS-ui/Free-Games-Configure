package pages;

import libs.ConfigClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import parentPage.ParentPage;

import java.io.IOException;

public class LoginPage extends ParentPage {

    @FindBy(xpath = "//input[@id='btnLogin']")
    private WebElement loginButton;
    @FindBy(xpath = "//input[@id='txtUsername']")
    private WebElement loginNameXpath;
    @FindBy(xpath = "//input[@id='txtPassword']")
    private WebElement loginPassXpath;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openPageLogin() {
        try {
            webDriver.get(ConfigClass.getConfigValue("base_url") + "/Login.aspx?ReturnUrl=%2f");
            logger.info("Login page was opened");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Unable to open this page");
        }
    }

    public void inputLoginName() {
        try {
            actionsElements.enterTextToTextFields(loginNameXpath, ConfigClass.getConfigValue("login_name"));
            logger.info("Name typed");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            logger.error("can`t get name from file ...");
        }
    }

    public void inputLoginPassword() {
        try {
            actionsElements.enterTextToTextFields(loginPassXpath, ConfigClass.getConfigValue("login_password"));
            logger.info("Password typed");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            logger.error("can`t get password from file ...");
        }
    }

    public void pressLoginButton() {
        actionsElements.clickButton(loginButton);
    }

}
