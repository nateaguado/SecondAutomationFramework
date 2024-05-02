package tests;

import Pages.SaucedemoHomePage;
import Pages.SaucedemoLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

public class SaucedemoTests extends TestBase {

//    WebDriver driver;
//
//    @BeforeMethod
//    public void setup(){
//        driver = Driver.getDriver();
//    }
//
//    @AfterMethod
//    public void teardown(){
//        driver.quit();
//    }

    @Test(groups = {"regression", "smoke", "saucedemo", "login"})
    public void loginPositive(){
        driver.get(ConfigReader.getProperty("saucedemoUrl"));

        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();
        saucedemoLoginPage.saucedemoLogin("standardUser","saucePassword");

        SaucedemoHomePage saucedemoHomePage = new SaucedemoHomePage();
        Assert.assertEquals(saucedemoHomePage.productTitle.getText(), "Products");

    }

    @Test(priority = 1, groups = {"regression", "saucedemo", "login"})
    public void loginNegative(){
        driver.get(ConfigReader.getProperty("saucedemoUrl"));

        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();
        saucedemoLoginPage.saucedemoLogin("lockedOutUser","saucePassword");

        Assert.assertEquals(saucedemoLoginPage.errorMessage.getText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(priority = 2, groups = {"regression", "smoke", "productSort"})
    public void sortPricesLowToHigh(){
        driver.get(ConfigReader.getProperty("saucedemoUrl"));
        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();
        saucedemoLoginPage.saucedemoLogin("standardUser","saucePassword");
        SaucedemoHomePage saucedemoHomePage = new SaucedemoHomePage();
        BrowserUtils.selectOptionByValue(saucedemoHomePage.productSortDropdown,"lohi");
        for(int i = 0; i<saucedemoHomePage.prices.size()-1; i++){
            double price1 = Double.parseDouble(saucedemoHomePage.prices.get(i).getText().substring(1));
            double price2 =Double.parseDouble(saucedemoHomePage.prices.get(i+1).getText().substring(1));
            Assert.assertTrue(price1<=price2, price1+" is not less than or equal to "+ price2);
        }
//        for(int i = 1; i<saucedemoHomePage.prices.size(); i++){
//            double price1 = Double.parseDouble(saucedemoHomePage.prices.get(i-1).getText().substring(1));
//            double price2 =Double.parseDouble(saucedemoHomePage.prices.get(i).getText().substring(1));
//            Assert.assertTrue(price1<=price2, price1+" is not less than or equal to "+ price2);
//        }
    }
}
