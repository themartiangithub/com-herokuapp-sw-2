package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {
    //Declaring BaseURL
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        //enter username and password
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//i[@class = 'fa fa-2x fa-sign-in']")).click();
        String expected = "Secure Area";
        String actual = driver.findElement(By.xpath("//div[@class = 'example']//h2[text() = ' Secure Area']")).getText();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        //enter username and password
        driver.findElement(By.id("username")).sendKeys("tomsmith1");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//i[@class = 'fa fa-2x fa-sign-in']")).click();
        String expected = "Your username is invalid!\n" + "×";
        String actual = driver.findElement(By.xpath("//div[@class = 'flash error']")).getText();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void verifyThePasswordErrorMessage() {
        //enter username and password
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");
        driver.findElement(By.xpath("//i[@class = 'fa fa-2x fa-sign-in']")).click();
        String expected = "Your password is invalid!\n" + "×";
        String actual = driver.findElement(By.xpath("//div[@class = 'flash error']")).getText();
        Assert.assertEquals(expected,actual);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
