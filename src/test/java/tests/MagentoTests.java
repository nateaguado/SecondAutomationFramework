package tests;

import Pages.MagentoCreateAccountPage;
import Pages.MagentoHomePage;
import Pages.MagentoSignInPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.Random;
import java.util.UUID;

import static org.bouncycastle.cms.RecipientId.password;

public class MagentoTests extends TestBase {

    String email;
    String password;
    String firstName;
    String lastName;


    @DataProvider(name = "createAccountData")
    public static Object[][] testData() {
        return new Object[][]{
                {"Jane","Patel",BrowserUtils.getRandomEmail(),"aprilrogers28"},
                {"Adam", "Lee",BrowserUtils.getRandomEmail(), "aprilrogers28!"},
        };
    }
    @Test(groups = {"regression","smoke","magento"}, dataProvider = "createAccountData")
    public void createAccountPositive(String firstName, String lastName, String email, String password) {
        driver.get(ConfigReader.getProperty("magentoUrl"));
        MagentoHomePage magentoHomePage = new MagentoHomePage();
        magentoHomePage.createAccountLink.click();
        MagentoCreateAccountPage magentoCreateAccountPage = new MagentoCreateAccountPage();



        driver.navigate().refresh();
        magentoHomePage.createAccountLink.click();
        magentoCreateAccountPage.firstNameInput.sendKeys(firstName);
        magentoCreateAccountPage.lastNameInput.sendKeys(lastName);
        magentoCreateAccountPage.emailInput.sendKeys(email);
        magentoCreateAccountPage.passwordInput.sendKeys(password);
        magentoCreateAccountPage.passwordConfirmationInput.sendKeys(password);
        magentoCreateAccountPage.createAccountBtn.click();


        if(firstName.equals("Adam")){
            Assert.assertEquals(driver.getTitle(), "My Account");
        }else
           Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    @Test(groups = {"regression","smoke","magento"}, dependsOnMethods ={"createAccountPositive"} )
    public void signIn(){
        driver.get(ConfigReader.getProperty("magentoUrl"));
        MagentoHomePage magentoHomePage = new MagentoHomePage();
        magentoHomePage.signIn.click();
        driver.navigate().refresh();
        MagentoSignInPage magentoSignInPage = new MagentoSignInPage();
        magentoHomePage.signIn.click();
        magentoSignInPage.emailInput.sendKeys(email);
        magentoSignInPage.passwordInput.sendKeys(password);
        magentoSignInPage.signInBtn.click();

        String expectedText = "Welcome, "+firstName+" "+lastName+"!";
        BrowserUtils.waitForTextToBePresent(magentoHomePage.welcomeTab, expectedText);

        Assert.assertEquals(magentoHomePage.welcomeTab.getText(), expectedText);
    }
}
