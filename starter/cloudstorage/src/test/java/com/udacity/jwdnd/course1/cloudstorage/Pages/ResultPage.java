package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    @FindBy(id = "success-message")
    private WebElement successMessage;

    @FindBy(id = "fail-message")
    private WebElement failMessage;

    @FindBy(id = "error-message")
    private WebElement errorMessage;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public boolean isSuccessMessageDisplayed(){
        return successMessage.isDisplayed();
    }

    public boolean isFailMessageDisplayed(){
        return failMessage.isDisplayed();
    }

    public boolean isErrorMessageDisplayed(){
        return errorMessage.isDisplayed();
    }
}
