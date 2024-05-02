package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class EldarAddYardPage {

    WebDriver driver;

    public EldarAddYardPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "yard_location")
    public WebElement name;

    @FindBy(className = "select-form border disabled-user active")
    public WebElement statusDropDown;

    @FindBy(id = "id_address")
    public WebElement street;

    @FindBy(id = "id_apt_suite_company_co")
    public WebElement apt_suit;

    @FindBy(id = "id_city")
    public WebElement city;

    @FindBy(name = "state")
    public WebElement stateDropdown;

    @FindBy(name= "zip_code")
    public WebElement zip;

    @FindBy(xpath = "//span[@class='input-errors']")
    public WebElement errorMessage;
}
