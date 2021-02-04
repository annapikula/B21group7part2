package com.trycloud.tests.base;

import com.trycloud.tests.base.TestBase;
import com.trycloud.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class UserStory3TC_6_7 extends TestBase {





    @Test
    public void TestCase_6  (){
        setUp();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


      WebElement addFolder =  driver.findElement(By.xpath("//a[@class='button new']"));
        addFolder.click();

        WebElement NewFolder = driver.findElement(By.xpath("//a[@class='menuitem']"));
        NewFolder.click();

        WebElement AddName = driver.findElement(By.xpath("//input[@id='view13-input-folder']"));
        NewFolder.sendKeys("Javid Darvish");

        WebElement submit = driver.findElement(By.xpath("//input[@class='icon-confirm']"));
       submit.click();

       WebElement displayed = driver.findElement(By.xpath(""));
       displayed.isDisplayed();


        String actualName = displayed.getText();
        String expectedName = "Javid Darvish";

        Assert.assertEquals(actualName,expectedName);


    }

    @Test
    public void TestCase_7(){
        setUp();














    }

















}

