package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public NotesPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
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

    public boolean doesNotesExist(String noteTitle,String noteBody){
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





}
