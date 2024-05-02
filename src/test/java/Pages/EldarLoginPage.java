package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

public class EldarLoginPage {

    WebDriver driver;

    public EldarLoginPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@class='input-login']")
    public WebElement usernameInput;

    @FindBy(xpath = "//input[@class='input-pass']")
    public WebElement passInput;

    @FindBy(xpath = "//button[@class='btn-login']")
    public WebElement loginBtn;

    @FindBy(xpath = "//a[@class='side-link-yards']")
    public WebElement pagesBtn;

    public void loginEldar(String userNameKey, String passWordKey){
        usernameInput.sendKeys(ConfigReader.getProperty(userNameKey));
        passInput.sendKeys(ConfigReader.getProperty(passWordKey));
        loginBtn.click();

    }



}