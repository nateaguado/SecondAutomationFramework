package projectday;

import Pages.EldarAddYardPage;
import Pages.EldarLoginPage;
import Pages.EldarPagesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.EldarTestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class EldarAppAddYardTest extends EldarTestBase {

    @Test
    public void maxCharacters() throws InterruptedException {
        EldarLoginPage eldarLoginPage = new EldarLoginPage();
        eldarLoginPage.pagesBtn.click();
        EldarPagesPage eldarPagesPage = new EldarPagesPage();
        eldarPagesPage.addYard.click();
        EldarAddYardPage eldarAddYardPage = new EldarAddYardPage();
        eldarAddYardPage.apt_suit.sendKeys("1034 S.Golf cul de sac street Chicago");
        Thread.sleep(10000);
        eldarAddYardPage.city.sendKeys("55155151");
        Assert.assertEquals(driver.findElement(By.xpath("(//input[@class='input-form border disabled-yard'])[2]")).getAttribute("value"), "1034 S.Golf cul de sac street Chica");
    }

    @Test
    public void cityRequired() throws InterruptedException {
        EldarLoginPage eldarLoginPage = new EldarLoginPage();
        eldarLoginPage.pagesBtn.click();
        EldarPagesPage eldarPagesPage = new EldarPagesPage();
        eldarPagesPage.addYard.click();
        EldarAddYardPage eldarAddYardPage = new EldarAddYardPage();
        eldarAddYardPage.city.sendKeys("Chicago");
        Thread.sleep(2000);
        eldarAddYardPage.city.sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);
        Assert.assertEquals(eldarAddYardPage.errorMessage.getText(), "This field is required.");
    }

    @Test(priority = 2)
    public void validatePossibleErrorMessageForAptInput() throws InterruptedException {

        EldarLoginPage eldarLoginPage = new EldarLoginPage();
        eldarLoginPage.pagesBtn.click();
        EldarPagesPage eldarPagesPage = new EldarPagesPage();
        eldarPagesPage.addYard.click();

        EldarAddYardPage eldarAddYardPage = new EldarAddYardPage();
        eldarAddYardPage.apt_suit.sendKeys("abcd@123");

        String actualResult = eldarAddYardPage.errorMessage.getText();

        Assert.assertEquals(actualResult, "Invalid input");


    }

    @Test(priority = 1)
    public void ValidCharAtAptInput() {
        EldarLoginPage eldarLoginPage = new EldarLoginPage();
        eldarLoginPage.pagesBtn.click();
        EldarPagesPage eldarPagesPage = new EldarPagesPage();
        eldarPagesPage.addYard.click();

        EldarAddYardPage eldarAddYardPage = new EldarAddYardPage();
        eldarAddYardPage.apt_suit.sendKeys("1a,./-:% #");

        try {
            driver.findElement(By.xpath("//span[@class='input-errors']")).getText();
        } catch (NoSuchElementException el) {
            String expectedResult = el.toString();
            Assert.assertNotEquals(expectedResult, "Invalid input");
            System.out.println(expectedResult);
        }
    }

    @Test
    public void validateCityInputMaxChar() {
        EldarLoginPage eldarLoginPage = new EldarLoginPage();
        eldarLoginPage.pagesBtn.click();
        EldarPagesPage eldarPagesPage = new EldarPagesPage();
        eldarPagesPage.addYard.click();

        EldarAddYardPage eldarAddYardPage = new EldarAddYardPage();
        eldarAddYardPage.city.sendKeys("abcdefghijklmnopqrstuvwxyzabce");
        String actualResult = eldarAddYardPage.city.getAttribute("value");
        Assert.assertEquals(actualResult, "abcdefghijklmnopqrstuvwxyzab");
    }

    @Test
    public void cityInvalidInput() throws InterruptedException {
        EldarLoginPage eldarLoginPage = new EldarLoginPage();
        eldarLoginPage.pagesBtn.click();
        EldarPagesPage eldarPagesPage = new EldarPagesPage();
        eldarPagesPage.addYard.click();
        EldarAddYardPage eldarAddYardPage = new EldarAddYardPage();
        eldarAddYardPage.city.sendKeys("@");
        Thread.sleep(3000);
        System.out.println(eldarAddYardPage.errorMessage.getText());
        Assert.assertEquals(eldarAddYardPage.errorMessage.getText(), "Invalid input");
    }

    @Test
    public void stateNoInputText() throws InterruptedException {
        EldarLoginPage eldarLoginPage = new EldarLoginPage();
        eldarLoginPage.pagesBtn.click();
        EldarPagesPage eldarPagesPage = new EldarPagesPage();
        eldarPagesPage.addYard.click();
        EldarAddYardPage eldarAddYardPage = new EldarAddYardPage();
        try {
            eldarAddYardPage.stateDropdown.sendKeys("abc");
        } catch (Exception ex) {
            String errorMsg = ex.toString();
            Assert.assertTrue(errorMsg.contains("org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element: {\"method\":\"css selector\",\"selector\":\"#\\33 261\"}\n"));
                   ;
        }
    }
    @Test
    public void verifySelect(){
        EldarLoginPage eldarLoginPage = new EldarLoginPage();
        eldarLoginPage.pagesBtn.click();
        EldarPagesPage eldarPagesPage = new EldarPagesPage();
        eldarPagesPage.addYard.click();
        EldarAddYardPage eldarAddYardPage = new EldarAddYardPage();
        Assert.assertEquals( eldarAddYardPage.stateDropdown.getTagName().toString(), "select");
    }
    @Test
    public void stateErrorMsg1() throws InterruptedException {
        EldarLoginPage eldarLoginPage = new EldarLoginPage();
        eldarLoginPage.pagesBtn.click();
        EldarPagesPage eldarPagesPage = new EldarPagesPage();
        eldarPagesPage.addYard.click();
        EldarAddYardPage eldarAddYardPage = new EldarAddYardPage();
        Select select = new Select(eldarAddYardPage.stateDropdown);
        select.selectByIndex(2);
        Thread.sleep(2000);
        select.deselectByIndex(2);
        Thread.sleep(2000);
        System.out.println(eldarAddYardPage.errorMessage.getText());

    }

    @Test
    public void validateCityInputCharacters(){

        EldarLoginPage eldarLoginPage = new EldarLoginPage();
        eldarLoginPage.pagesBtn.click();
        EldarPagesPage eldarPagesPage = new EldarPagesPage();
        eldarPagesPage.addYard.click();

        EldarAddYardPage eldarAddYardPage = new EldarAddYardPage();
        eldarAddYardPage.city.sendKeys("abc--def");


        try {
            eldarAddYardPage.errorMessage.getText();
        } catch (NoSuchElementException ex){
            String actualMsg = ex.toString();
            Assert.assertNotEquals(actualMsg,"Invalid input");
        }
    }

    @Test
    public void maxZip(){
        EldarLoginPage eldarLoginPage = new EldarLoginPage();
        eldarLoginPage.pagesBtn.click();
        EldarPagesPage eldarPagesPage = new EldarPagesPage();
        eldarPagesPage.addYard.click();

        EldarAddYardPage eldarAddYardPage =new EldarAddYardPage();
        eldarAddYardPage.zip.sendKeys("123456");
        Assert.assertEquals(eldarAddYardPage.zip.getAttribute("value"), "12345");
    }

    @Test
    public void minZip(){
        EldarLoginPage eldarLoginPage = new EldarLoginPage();
        eldarLoginPage.pagesBtn.click();
        EldarPagesPage eldarPagesPage = new EldarPagesPage();
        eldarPagesPage.addYard.click();

        EldarAddYardPage eldarAddYardPage = new EldarAddYardPage();
        eldarAddYardPage.zip.sendKeys("1234");
        Assert.assertEquals(eldarAddYardPage.errorMessage.getText(), "Min length is 5 characters, currently it is 4");
    }

    @Test
    public void zipRequired(){
        EldarLoginPage eldarLoginPage = new EldarLoginPage();
        eldarLoginPage.pagesBtn.click();
        EldarPagesPage eldarPagesPage = new EldarPagesPage();
        eldarPagesPage.addYard.click();
        EldarAddYardPage eldarAddYardPage = new EldarAddYardPage();

        eldarAddYardPage.zip.sendKeys("12234");
        eldarAddYardPage.zip.sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);
        Assert.assertEquals(eldarAddYardPage.errorMessage.getText(), "This field is required.");

    }

    @Test
    public void zipValidTextField(){
        EldarLoginPage eldarLoginPage = new EldarLoginPage();
        eldarLoginPage.pagesBtn.click();
        EldarPagesPage eldarPagesPage = new EldarPagesPage();
        eldarPagesPage.addYard.click();
        EldarAddYardPage eldarAddYardPage = new EldarAddYardPage();
        eldarAddYardPage.zip.sendKeys("abc");
        Assert.assertEquals(eldarAddYardPage.zip.getAttribute("value"), "abc");
    }
}
