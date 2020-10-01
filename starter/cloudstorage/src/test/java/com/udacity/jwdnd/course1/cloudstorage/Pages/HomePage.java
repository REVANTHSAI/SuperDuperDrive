package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id="logout-button")
    private WebElement logOutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotes;


    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void logout(){
        logOutButton.click();
    }

    public void selectNotesTab(){
        navNotes.click();
    }

}
