package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "loginSubmit")
    private WebElement loginSubmit;

    @FindBy(id="error-msg")
    private WebElement errorMessage;

    @FindBy(id="logout-msg")
    private WebElement logoutMessage;


    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }


    public void login(String userName,String password){
        inputUsername.sendKeys(userName);
        inputPassword.sendKeys(password);
        loginSubmit.click();
    }

    public boolean isLoginFailed(){
        return errorMessage.isDisplayed();
    }

    public boolean isLogoutSuccessful(){
        return logoutMessage.isDisplayed();
    }


}
