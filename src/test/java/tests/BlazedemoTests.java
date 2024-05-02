package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.BrowserUtils;
import utilities.ConfigReader;

import utilities.TestBase;



public class BlazedemoTests extends TestBase {

    String fromCityStr ="Paris";
    String toCityStr = "Rome";



    @Test(groups = {"regression", "smoke", "blazedemo"})
    public void findFlights(){
        driver.get(ConfigReader.getProperty("blazedemoUrl"));
        WebElement fromCity = driver.findElement(By.name("fromPort"));
        BrowserUtils.selectOptionByValue(fromCity, fromCityStr);
        WebElement toCity = driver.findElement(By.name("toPort"));
        BrowserUtils.selectOptionByValue(toCity, toCityStr);

        WebElement findFlightsBtn = driver.findElement(By.xpath("//input[@type='submit']"));
        findFlightsBtn.click();

        String expectedText = "Flights from Paris to Rome:";
        String actualText = driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void flightInfo(){
        driver.get(ConfigReader.getProperty("blazedemoUrl"));
       BrowserUtils.selectOptionByValue(driver.findElement(By.name("fromPort")), fromCityStr);
       BrowserUtils.selectOptionByValue(driver.findElement(By.name("toPort")), toCityStr);

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        String expectedFlightNum = driver.findElement(By.xpath("//tr[1]/td[2]")).getText();
        String expectedAirline = driver.findElement(By.xpath("//tr[1]/td[3]")).getText();
        String expectedPrice = driver.findElement(By.xpath("//tr[1]/td[6]")).getText();
        driver.findElement(By.xpath("//tr[1]/td[1]/input")).click();

        String actualAirline = driver.findElement(By.xpath("//p[contains(text(), 'Airline')]")).getText();
        String actualFlightNum =  driver.findElement(By.xpath("//p[contains(text(), 'Flight Number')]")).getText();
        String actualPrice = driver.findElement(By.xpath("//p[contains(text(), 'Price')]")).getText();

        actualAirline = actualAirline.substring(actualAirline.lastIndexOf(" ")).trim();
        actualFlightNum = actualFlightNum.substring(actualFlightNum.lastIndexOf(" ")).trim();
        actualPrice = actualPrice.substring(actualPrice.lastIndexOf(" ")).trim();

      //  This is a hard assert and its stops the execution after the first assertion error
//        Assert.assertEquals(actualAirline, expectedAirline);
//        Assert.assertEquals(actualFlightNum, expectedFlightNum);
//        Assert.assertEquals(actualPrice, expectedPrice);

        SoftAssert softAssert = new SoftAssert();
// This is a soft assert and it asserts all the soft asserts with assertAll() method
        softAssert.assertEquals(actualAirline, expectedAirline);
        softAssert.assertEquals(actualFlightNum, expectedFlightNum);
        softAssert.assertEquals(actualPrice, expectedPrice);
        softAssert.assertAll();

    }
}
