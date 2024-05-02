package utilities;

import Pages.EldarLoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class EldarTestBase {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("eldarUrl"));
        Thread.sleep(1200);
        EldarLoginPage eldarLoginPage = new EldarLoginPage();
        Thread.sleep(1200);
        eldarLoginPage.loginEldar("eldarUsername", "eldarPassword");
        Thread.sleep(1200);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}


