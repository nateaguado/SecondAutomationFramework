package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class MagentoHomePage {

    WebDriver driver;

    public MagentoHomePage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Create an Account")
    public WebElement createAccountLink;

    @FindBy(xpath = "(//span[@class='logged-in'])[1]")
    public WebElement welcomeTab;

    @FindBy(linkText = "Sign In")
    public WebElement signIn;

}
