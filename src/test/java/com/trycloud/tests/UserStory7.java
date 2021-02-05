package com.trycloud.tests;

import com.github.javafaker.Faker;
import com.trycloud.tests.base.TestBase;
import com.trycloud.utilities.ConfigurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class UserStory7 extends TestBase {
    //#1 implicit with Testbase


    @Test
    public void tc01(){
        Faker faker = new Faker();
        //Talk folder native to User7 acct.
        //guaranteed happy path
        String existingFile = "Talk";
        //using User7 because it has to exist since we are using it(questionable);
        //user doesnt show up in search as a result
        String existingUser = ConfigurationReader.getProperty("username");
        //random letters(replaces ? randomly)(?=8)
        String random = faker.letterify("????????");
        //Collecting all test data to be run(File,User7,random)
        ArrayList<String> testData = new ArrayList<String>();
        testData.addAll(Arrays.asList(existingFile, existingUser, random));
        //loop to test all 3
        for (String each : testData) {
            WebElement magGlass = driver.findElement(By.xpath("//div[contains(@class,'unified-search')]"));
            magGlass.click();
            //Thread.sleep(2000);
            WebElement inputPopUp = driver.findElement(By.xpath("//input[@class='unified-search__form-input']"));
            Assert.assertTrue(inputPopUp.isDisplayed(), "Magnify glass clicked: not input pop up");
            //no Keys.ENTER it opes first result
            inputPopUp.sendKeys(each);
            //sleep to budget time for Results to populate
            //?Does Implicit wait only work transition html page to page and not js scripts?
            //Thread.sleep(1000);
            //tries to make array list if populates
            //array list of all search results
            try {
                ArrayList<WebElement> fileSearchRes = new ArrayList<WebElement>(driver.findElements(By.xpath("//ul//*[@class='unified-search__result-line-one']")));
                //Assuming title will contain ref, loop will check each for search
                for (WebElement r : fileSearchRes) {
                    String title = r.getAttribute("title");
                    Assert.assertTrue(title.contains(each));
                }
            } catch (InvalidSelectorException e) {
                String noResult = driver.findElement(By.xpath("//h2[@class='empty-content__title']")).getText();
                Assert.assertTrue(noResult.contains(each));
            }
            //returns to origin Url page to loop through
            driver.get("http://qa2.trycloud.net/index.php/login");
        }

    }
}
/*
User Story:
    As a user, I should be able to access search any item/ users from the homepage.

Test case #1 -
    Verify users can search any files/folder/users from the search box.
        1. Login as a user
        2. Click magnifier icon on the right top
        3. Search any existing file/folder/user name
        4. Verify the app displays the expected result option
 */