package stepdefinition;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.HomelyfAdminUserPage;
import pom.HomelyfLoginPage;

public class HomelyfLoginStep {
	WebDriver driver;
	HomelyfLoginPage hlp;
	HomelyfAdminUserPage hlap;

	@Given("User opens URL {string} on Chrome")
	public void user_opens_url_on_chrome(String url) {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--incognito");
	    driver = new ChromeDriver(option);
	    driver.get(url);
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	@When("User enters Username as {string} and Password as {string}")
	public void user_enters_username_as_and_password_as(String uname, String pwd) throws InterruptedException {
		hlp = new HomelyfLoginPage(driver);
		hlp.enterUsername(uname);
		Thread.sleep(1000);
		hlp.enterPassword(pwd);
	}

	@And("User click on login button")
	public void user_click_on_login_button() {
		hlp.clickOnLoginBtn();
	}

	@Then("User navigate to Home Page")
	public void user_navigate_to_home_page(){
		String pageTitle = driver.getTitle();
		System.out.println("----------Title: "+pageTitle+"---------------");
		Assert.assertEquals(driver.getTitle().contains(pageTitle), true);
		System.out.println("Page title matched successfully");
	}
	
	//--------------------Search User By Email-------------------------------------
	
	@Given("Select User option on Home Page")
	public void select_user_option_on_home_page(){
		hlap = new HomelyfAdminUserPage(driver);
		WebElement option = driver.findElement(By.xpath("//h2[text()='Users']"));
		System.out.println(option.getText());
	}

	@When("Enter email Address in Search Field")
	public void enter_email_address_in_search_field() throws InterruptedException {
		hlap.enterEmailAdd("satyashripisal2209@gmail.com");
		Thread.sleep(1000);
	    
	}

	@Then("Display User on select Email Address")
	public void display_user_on_select_email_address(){
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getPageSource().contains("Home Lyf"), true);
	}
	
	@And("Click on Actions button")
	public void click_on_actions_button() {
		hlap.clickOnActionBtn();
	}

	@And("Select Preview option")
	public void select_preview_option() {
		hlap.selectOption();
	}
	@Then("User should open Preview Popup")
	public void user_should_open_preview_popup() {
		System.out.println(hlap.getTextOfPreviewPopup());
		String expectedText = "User details";
		Assert.assertEquals(hlap.getTextOfPreviewPopup(), expectedText);
	}


	
}
