package stepdefinition;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import io.cucumber.java.en.*;
import pom.LoginPage;

public class LoginStep {

	WebDriver driver;
	static LoginPage loginpg;
	@Given("Open browser")
	public void open_browser() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--headless");
	    driver = new ChromeDriver(option);
	}

	@And("Launch then Amazon shopping url {string}")
	public void launch_then_amazon_shopping_url(String URL) {
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		loginpg = new LoginPage(driver);
		loginpg.clickOnSignInLink();
//		Actions actions = new Actions(driver);
//		actions.moveToElement(loginpg.moveToSignInLink()).build().perform();
		
	}

	@When("Customer Enters email id {string}")
	public void customer_enters_email_id(String EmailId) {
		//loginpg = new LoginPage(driver);
		loginpg.enterEmailId(EmailId);
	}

	@And("click on continue button")
	public void click_on_continue_button() {
		loginpg.clickOnContinueBtn();
	}

	@And("Customer Enters password as {string}")
	public void customer_enters_password_as(String password) {
	    loginpg.enterPassword(password);
	}

	@And("click on sign in button")
	public void click_on_sign_in_button() throws InterruptedException {
	    loginpg.clickOnSignIn();
	    //Thread.sleep(Duration.ofSeconds(2000));
	}

	@Then("Customer redirect to amazon shopping home page title should be {string}")
	public void customer_redirect_to_amezon_shopping_home_page_titile_should_be(String pageTitle) {
		System.out.println("----------Title: "+driver.getTitle()+"---------------");
		Assert.assertEquals(driver.getTitle().contains(pageTitle), true);
		System.out.println("Page title matched successfully");
	}
}
