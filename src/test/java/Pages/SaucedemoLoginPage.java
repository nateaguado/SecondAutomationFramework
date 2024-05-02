package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

public class SaucedemoLoginPage {

    WebDriver driver;

    public SaucedemoLoginPage() {
    driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    public WebElement usernameInput;

    @FindBy(id = "login-button")
    public WebElement loginBtn;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(xpath = "//h3[@data-test='error']")
    public WebElement errorMessage;

    /**
     * this method logs into the Saucedemo app using the username
     * and password keys provided in the parameter
     * @param usernameKey
     * @param passwordKey
     */


    public void saucedemoLogin(String usernameKey, String passwordKey){
        usernameInput.sendKeys(ConfigReader.getProperty(usernameKey));
        passwordInput.sendKeys(ConfigReader.getProperty(passwordKey));
        loginBtn.click();
    }
}
