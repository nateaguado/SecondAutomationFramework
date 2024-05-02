package projectday;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class EldarAppYardTests {
    WebDriver driver;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Butbu\\IdeaProjects\\MindtekSelenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://elarbridgelogisticsmindtek.space/#/login");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys("student1@mindtekbootcamp.com");
        driver.findElement(By.id("id_input_pass")).sendKeys("mindtek109" + Keys.ENTER);
        Thread.sleep(2000);
        driver.findElement(By.className("side-link-yards")).click();
        Thread.sleep(2000);
    }


    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(4000);
        driver.quit();
    }

    @Test
    public void containsDataTags() {
        String IdTagName = driver.findElement(By.xpath("//div[contains(text(),'ID')]")).getText();
        String InfoTagName = driver.findElement(By.xpath("//div[contains(text(),'Info')]")).getText();
        String NameTagName = driver.findElement(By.xpath("//div[contains(text(),'Name')]")).getText();
        String FleetTagName = driver.findElement(By.xpath("//div[contains(text(),'Fleet')]")).getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(IdTagName, null);
        softAssert.assertNotEquals(InfoTagName, null);
        softAssert.assertNotEquals(NameTagName, null);
        softAssert.assertNotEquals(FleetTagName, null);

    }

    @Test
    public void clickableId1() {
        String parentWindow = driver.getCurrentUrl();

        String idElementTextRowOne = driver.findElement(By.xpath("(//span[contains(text(),'Y27541')])[1]")).getText().substring(driver.findElement(By.xpath("(//span[contains(text(),'Y27541')])[1]")).getText().indexOf('2'));
        driver.findElement(By.xpath("//a[@href='#/panel/yards/27541']")).click();

        String childWindow = driver.getCurrentUrl();
        String childWindowId = childWindow.substring(driver.getCurrentUrl().indexOf('2'));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(parentWindow, childWindow);
        softAssert.assertEquals(childWindowId, idElementTextRowOne);
    }

    @Test
    public void clickableId2() {
        String parentWindow = driver.getCurrentUrl();

        String idElementTextRowOne = driver.findElement(By.xpath("(//span[contains(text(),'Y27540')])[1]")).getText().substring(driver.findElement(By.xpath("(//span[contains(text(),'Y27540')])[1]")).getText().indexOf('2'));
        driver.findElement(By.xpath("//a[@href='#/panel/yards/27540']")).click();

        String childWindow = driver.getCurrentUrl();
        String childWindowId = childWindow.substring(driver.getCurrentUrl().indexOf('2'));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(parentWindow, childWindow);
        softAssert.assertEquals(childWindowId, idElementTextRowOne);
    }

    @Test
    public void clickableId3() {
        String parentWindow = driver.getCurrentUrl();

        String idElementTextRowOne = driver.findElement(By.xpath("(//span[contains(text(),'Y27539')])[1]")).getText().substring(driver.findElement(By.xpath("(//span[contains(text(),'Y27539')])[1]")).getText().indexOf('2'));
        driver.findElement(By.xpath("//a[@href='#/panel/yards/27539']")).click();

        String childWindow = driver.getCurrentUrl();
        String childWindowId = childWindow.substring(driver.getCurrentUrl().indexOf('2'));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(parentWindow, childWindow);
        softAssert.assertEquals(childWindowId, idElementTextRowOne);
    }

    @Test
    public void clickableId4() {
        String parentWindow = driver.getCurrentUrl();

        String idElementTextRowOne = driver.findElement(By.xpath("(//span[contains(text(),'Y27538')])[1]")).getText().substring(driver.findElement(By.xpath("(//span[contains(text(),'Y27538')])[1]")).getText().indexOf('2'));
        driver.findElement(By.xpath("//a[@href='#/panel/yards/27538']")).click();

        String childWindow = driver.getCurrentUrl();
        String childWindowId = childWindow.substring(driver.getCurrentUrl().indexOf('2'));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(parentWindow, childWindow);
        softAssert.assertEquals(childWindowId, idElementTextRowOne);
    }

    @Test
    public void clickableId5() {
        String parentWindow = driver.getCurrentUrl();

        String idElementTextRowOne = driver.findElement(By.xpath("(//span[contains(text(),'Y27537')])[1]")).getText().substring(driver.findElement(By.xpath("(//span[contains(text(),'Y27537')])[1]")).getText().indexOf('2'));
        driver.findElement(By.xpath("//a[@href='#/panel/yards/27537']")).click();

        String childWindow = driver.getCurrentUrl();
        String childWindowId = childWindow.substring(driver.getCurrentUrl().indexOf('2'));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(parentWindow, childWindow);
        softAssert.assertEquals(childWindowId, idElementTextRowOne);
    }

    @Test
    public void ClickableInfo() {
        String parentWindow = driver.getCurrentUrl();

        driver.findElement(By.xpath("(//div[@class='table-list-cell show-info disabled-info'])[1]")).click();

        String childWindow = driver.getCurrentUrl();


        Assert.assertNotEquals(parentWindow, childWindow);

    }

    @Test
    public void validate5rows() {

        List<WebElement> idRows = driver.findElements(By.xpath("(//div[@class='table-list-cell show-info disabled-info'])"));
        int sum = 0;
        for (WebElement el : idRows) {
            System.out.println(idRows.getClass());
            sum++;
        }

        System.out.println(sum);

        Assert.assertEquals(sum, 5);
    }

    @Test
    public void validateMaxPageSelection() {
        List<WebElement> paginateDigits = driver.findElements(By.xpath("(//li[@class='paginate-digits'])"));
        int sum = 0;
        for (WebElement el : paginateDigits) {
            sum++;
        }
        Assert.assertTrue(sum < 4);
    }

    @Test
    public void paginateSingleRight() {
        int paginate1 = Integer.valueOf(driver.findElement(By.xpath("//button[@class='pagination-num activeBtn']")).getText());
        driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
        int paginate2 = Integer.valueOf(driver.findElement(By.xpath("//button[@class='pagination-num activeBtn']")).getText());

        Assert.assertTrue(paginate2 == paginate1 + 1);
    }

    @Test
    public void paginateDoubleRight() {
        driver.findElement(By.xpath("(//span[@class='material-icons-outlined'])[14]")).click();
        String lastPage = driver.findElement(By.xpath("//button[@class='pagination-num activeBtn']")).getText();
        driver.findElement(By.xpath("//span[contains(text(),'keyboard_arrow_right')]")).click();

        String verifyLastPage = driver.findElement(By.xpath("//button[@class='pagination-num activeBtn']")).getText();

        Assert.assertEquals(lastPage, verifyLastPage);

    }

    @Test
    public void paginateSingleLeft(){
        WebElement paginateSingleRightBtn = driver.findElement(By.xpath("(//button[@type='button'])[7]"));
        paginateSingleRightBtn.click();
        String actualPageAfterRightBtn = driver.findElement(By.xpath("//button[@class='pagination-num activeBtn']")).getText();
        String expectedPageAfterRightBtn = "2";
        WebElement paginateSingleLeftBtn = driver.findElement(By.xpath("(//span[@class='material-icons-outlined'])[12]"));
        paginateSingleLeftBtn.click();
        String expectedPageNumberAfterLeftBtn = "1";
        String actualPageNumberAfterLeftBtn = driver.findElement(By.xpath("//button[@class='pagination-num activeBtn']")).getText();

        Assert.assertEquals(actualPageAfterRightBtn, expectedPageAfterRightBtn);
        Assert.assertEquals(actualPageNumberAfterLeftBtn,expectedPageNumberAfterLeftBtn);


    }

    @Test
    public void paginateDoubleLeft() {
        driver.findElement((By.xpath("(//button[@class='pagination-num'])[2]"))).click();
        String activeElementText = driver.findElement(By.xpath("//button[@class='pagination-num activeBtn']")).getText();
        if (activeElementText.contains("3")) {
            driver.findElement(By.xpath("//button[@class='pagination-btn1']")).click();
            activeElementText = driver.findElement(By.xpath("//button[@class='pagination-num activeBtn']")).getText();
            Assert.assertTrue(activeElementText.contains("1"), "Element is not on page 1");
        } else
            System.out.println("Not on page 3");
    }
}