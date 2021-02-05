package com.trycloud.tests.base;

import com.trycloud.utilities.ConfigurationReader;
import com.trycloud.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    public WebDriver driver;

    @BeforeMethod

    public void setUp(){

        driver = WebDriverFactory.getDriver("chrome");

        driver.manage().window().maximize();

        driver.get("http://qa2.trycloud.net/index.php/login");

        String user = ConfigurationReader.getProperty("username");
        String password = ConfigurationReader.getProperty("password");

        WebElement usernameInput = driver.findElement(By.xpath("//input[@id='user']"));

        usernameInput.sendKeys(user);

        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));

        passwordInput.sendKeys(password);

        driver.findElement(By.xpath("//input[@type='submit']")).click();










    }

    @AfterMethod

    public void tearDown(){
        driver.close();
    }



}
