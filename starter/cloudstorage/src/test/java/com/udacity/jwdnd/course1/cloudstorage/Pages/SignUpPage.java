package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    @FindBy(id = "error-msg")
    private WebElement errorMessage;

    @FindBy(id = "success-msg")
    private WebElement successMessage;

    @FindBy(id ="inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id="inputLastName")
    private WebElement inputLastName;

    @FindBy(id="inputUsername")
    private WebElement inputUsername;

    @FindBy(id="inputPassword")
    private WebElement inputPassword;

    @FindBy(id="submit-button")
    private WebElement submitButton;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }


    public void signUp(String firstName,String lastName,String userName,String password){
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputUsername.sendKeys(userName);
        inputPassword.sendKeys(password);
        submitButton.click();
    }

    public boolean isSignupFailed()
    {
        return errorMessage.isDisplayed();
    }

    public boolean isSignupSuccess(){
        return successMessage.isDisplayed();
    }
}
