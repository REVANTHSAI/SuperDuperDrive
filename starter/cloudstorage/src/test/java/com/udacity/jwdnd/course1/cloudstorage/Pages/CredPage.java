package com.udacity.jwdnd.course1.cloudstorage.Pages;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CredPage {

    @FindBy(id = "add-credentials")
    WebElement addCredentialsButton;

    @FindBy(id = "credential-url")
    WebElement credentialURL;

    @FindBy(id = "credential-username")
    WebElement credentialUsername;

    @FindBy(id = "credential-password")
    WebElement credentialPassword;

    @FindBy(id="credential-submit")
    WebElement credentialSubmitButton;

    @FindBy(id="credentialTable")
    WebElement credentialTable;

    WebDriver webDriver;

    public CredPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.webDriver = driver;
    }

    public void clickAddCredentials(){
        addCredentialsButton.click();
    }

    public void populateCredentials(String url,String userName,String password){
        credentialURL.sendKeys(url);
        credentialUsername.sendKeys(userName);
        credentialPassword.sendKeys(password);
    }

    public void clickSubmitCredentials(){
        credentialSubmitButton.click();
    }


    public boolean doesCredExist(String credURL){
        List<WebElement> credList = credentialTable.findElements(By.tagName("th"));
        Boolean created = false;
        for (int i=0; i < credList.size(); i++) {
            WebElement titleElement = credList.get(i);
            if (titleElement.getAttribute("innerHTML").equals(credURL)) {
                created = true;
                break;
            }
        }
        return created;
    }

    public void editCred(String credURL){
        WebDriverWait wait = new WebDriverWait (webDriver, 30);
        List<WebElement> credList = credentialTable.findElements(By.tagName("td"));
        WebElement editElement = null;
        for (int i = 0; i < credList.size(); i++) {
            WebElement element = credList.get(i);
            editElement = element.findElement(By.name("edit-cred"));
            if (editElement != null){
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(editElement)).click();
        wait.until(ExpectedConditions.elementToBeClickable(credentialURL));
        credentialURL.clear();
        credentialURL.sendKeys(credURL);
        credentialSubmitButton.click();
    }

    public void deleteCred(){
        WebDriverWait wait = new WebDriverWait (webDriver, 30);
        List<WebElement> credList = credentialTable.findElements(By.tagName("td"));
        WebElement deleteElement = null;
        for (int i = 0; i < credList.size(); i++) {
            WebElement element = credList.get(i);
            deleteElement = element.findElement(By.name("delete-cred"));
            if (deleteElement != null){
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(deleteElement)).click();
    }




}
