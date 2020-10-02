package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import javax.validation.constraints.AssertTrue;
import javax.xml.transform.Result;
import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	static String firstName = "testFirst";
	static String lastName = "testLast";
	static String userName = "testUser";
	static String password = "testPassword";

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	/*Write a test that verifies that an unauthorized user can only access the
	login and signup pages.*/

	@Test
	@Order(1)
	public void unauthorizedUserTest_1(){
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login",driver.getTitle());

		driver.get("http://localhost:" + this.port + "/credentials");
		Assertions.assertEquals("Login",driver.getTitle());

		driver.get("http://localhost:" + this.port + "/files");
		Assertions.assertEquals("Login",driver.getTitle());

		driver.get("http://localhost:" + this.port + "/notes");
		Assertions.assertEquals("Login",driver.getTitle());

	}

	/*Write a test that signs up a new user, logs in, verifies that the home
	page is accessible, logs out, and verifies that the home page is no longer accessible.*/
	@Test
	@Order(2)
	public void signUpNewUserTest_2(){


		driver.get("http://localhost:" + this.port + "/signup");
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.signUp(firstName,lastName,userName,password);

		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(userName,password);

		// Login and check if user is able to access home page
		Assertions.assertEquals("Home", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");
		HomePage homePage = new HomePage(driver);
		homePage.logout();

		//Logout and verify that the user is not able to access home page
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login",driver.getTitle());

	}


//	@Test
//	@Order(3)
//	public void createNotesTest_3() throws InterruptedException {
//
//		String title = "Test Title";
//		String body = "Test Body";
//
//		driver.get("http://localhost:" + this.port + "/signup");
//		SignUpPage signUpPage = new SignUpPage(driver);
//		signUpPage.signUp(firstName,lastName,userName,password);
//
//
//		driver.get("http://localhost:" + this.port + "/login");
//		LoginPage loginPage = new LoginPage(driver);
//		loginPage.login(userName,password);
//
//		driver.get("http://localhost:" + this.port + "/home");
//		Thread.sleep(1000);
//		HomePage homePage = new HomePage(driver);
//     	homePage.selectNotesTab();
//
//		Thread.sleep(1000);
//     	NotesPage notesPage = new NotesPage(driver);
//     	notesPage.clickAddButton();
//
//		Thread.sleep(1000);
//		notesPage.addNotes(title,body);
//		notesPage.clickSubmitNotes();
//		Thread.sleep(1000);
//
//		ResultPage resultPage = new ResultPage(driver);
//		Assertions.assertTrue(resultPage.isSuccessMessageDisplayed());
//
//		driver.get("http://localhost:" + this.port + "/home");
//		Thread.sleep(1000);
//		homePage = new HomePage(driver);
//		homePage.selectNotesTab();
//
//		notesPage = new NotesPage(driver);
//		Assertions.assertTrue(notesPage.doesNotesExist(title));
//	}

	@Test
	@Order(3)
	public void createNotesTest_3() throws InterruptedException {

		String title = "Test Title";
		String body = "Test Body";

		driver.get("http://localhost:" + this.port + "/signup");
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.signUp(firstName,lastName,userName,password);


		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(userName,password);

		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		HomePage homePage = new HomePage(driver);
		homePage.selectNotesTab();

		Thread.sleep(1000);
		NotesPage notesPage = new NotesPage(driver);
		notesPage.clickAddButton();

		Thread.sleep(1000);
		notesPage.addNotes(title,body);
		notesPage.clickSubmitNotes();
		Thread.sleep(1000);

		ResultPage resultPage = new ResultPage(driver);
		Assertions.assertTrue(resultPage.isSuccessMessageDisplayed());

		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		homePage = new HomePage(driver);
		homePage.selectNotesTab();

		notesPage = new NotesPage(driver);
		Assertions.assertTrue(notesPage.doesNotesExist(title));
	}


	@Test
	@Order(4)
	public void updateNotesTest_4() throws InterruptedException {
		String updatedTitle = "Updated Test Title";
		String title = "Test Title";
		String body = "Test Body";

		driver.get("http://localhost:" + this.port + "/signup");
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.signUp(firstName,lastName,userName,password);


		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(userName,password);

		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		HomePage homePage = new HomePage(driver);
		homePage.selectNotesTab();

		Thread.sleep(1000);
		NotesPage notesPage = new NotesPage(driver);
		notesPage.clickAddButton();

		Thread.sleep(1000);
		notesPage.addNotes(title,body);
		notesPage.clickSubmitNotes();
		Thread.sleep(1000);

		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		homePage = new HomePage(driver);

		homePage.selectNotesTab();
		Thread.sleep(1000);
		notesPage.editNotes(updatedTitle);

		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		homePage = new HomePage(driver);
     	homePage.selectNotesTab();

     	notesPage = new NotesPage(driver);
     	Assertions.assertTrue(notesPage.doesNotesExist(updatedTitle));

	}


	@Test
	@Order(5)
	public void deleteNoteTest_5() throws InterruptedException {
		String title = "Test Title";
		String body = "Test Body";

		driver.get("http://localhost:" + this.port + "/signup");
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.signUp(firstName,lastName,userName,password);


		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(userName,password);

		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		HomePage homePage = new HomePage(driver);
     	homePage.selectNotesTab();

		Thread.sleep(1000);
     	NotesPage notesPage = new NotesPage(driver);
     	notesPage.clickAddButton();

		Thread.sleep(1000);
		notesPage.addNotes(title,body);
		notesPage.clickSubmitNotes();
		Thread.sleep(1000);

		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		homePage = new HomePage(driver);
		homePage.selectNotesTab();
		Thread.sleep(1000);

		notesPage = new NotesPage(driver);
		notesPage.deleteNote();
		Thread.sleep(1000);

		ResultPage resultPage = new ResultPage(driver);
		Assertions.assertTrue(resultPage.isSuccessMessageDisplayed());
	}


}
