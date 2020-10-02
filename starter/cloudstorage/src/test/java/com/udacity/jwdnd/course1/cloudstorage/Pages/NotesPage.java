package com.udacity.jwdnd.course1.cloudstorage.Pages;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NotesPage {

    @FindBy(id = "add-notes")
    private WebElement addNotes;

    @FindBy(id = "userTable")
    private WebElement userTable;


    @FindBy(id = "note-submit")
    private WebElement submitNotes;

    @FindBy(id = "note-title")
    private WebElement notesTitle;

    @FindBy(id = "note-description")
    private WebElement notesDescription;

    WebDriver webDriver;

    public NotesPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.webDriver = driver;
    }

    public void addNotes(String title,String desc){
        notesTitle.sendKeys(title);
        notesDescription.sendKeys(desc);
    }

    public void clickAddButton(){
        addNotes.click();
    }

    public void clickSubmitNotes(){
        submitNotes.click();
    }

    public boolean doesNotesExist(String noteTitle){
        List<WebElement> notesTitleList = userTable.findElements(By.tagName("th"));

        Boolean created = false;
        for (int i=0; i < notesTitleList.size(); i++) {
            WebElement titleElement = notesTitleList.get(i);
            if (titleElement.getAttribute("innerHTML").equals(noteTitle)) {
                created = true;
                break;
            }
        }
        return created;
    }

    public void editNotes(String newNoteTitle){
        WebDriverWait wait = new WebDriverWait (webDriver, 30);
        WebElement notesTable = userTable;
        List<WebElement> notesList = notesTable.findElements(By.tagName("td"));
        WebElement editElement = null;
        for (int i = 0; i < notesList.size(); i++) {
            WebElement element = notesList.get(i);
            editElement = element.findElement(By.name("edit"));
            if (editElement != null){
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(editElement)).click();
        WebElement notetitle = webDriver.findElement(By.id("note-title"));
        wait.until(ExpectedConditions.elementToBeClickable(notetitle));
        notetitle.clear();
        notetitle.sendKeys(newNoteTitle);
        submitNotes.click();

    }

    public void deleteNote(){
        WebDriverWait wait = new WebDriverWait (webDriver, 30);
        WebElement notesTable = userTable;
        List<WebElement> notesList = notesTable.findElements(By.tagName("td"));
        WebElement deleteElement = null;
        for (int i = 0; i < notesList.size(); i++) {
            WebElement element = notesList.get(i);
            deleteElement = element.findElement(By.name("delete"));
            if (deleteElement != null){
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(deleteElement)).click();
    }





}
