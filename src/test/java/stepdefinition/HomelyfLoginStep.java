package stepdefinition;

import static org.testng.Assert.assertTrue;

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
import pom.HomelyfAdminTimeSlotPage;
import pom.HomelyfAdminUserPage;
import pom.HomelyfLoginPage;

public class HomelyfLoginStep {
	WebDriver driver;
	HomelyfLoginPage hlp;
	HomelyfAdminUserPage hlap;
	HomelyfAdminTimeSlotPage timeslotpage;

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
	
	//--------------------Time Slot Verification-------------------------------------
	
	@Given("logged in as an admin")
	public void logged_in_as_an_admin() {
		timeslotpage = new HomelyfAdminTimeSlotPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@And("navigate to timeslot page")
	public void navigate_to_timeslot_page() {
		timeslotpage.clickOnTimeSlotItem();
		timeslotpage.getItemList();
		Assert.assertEquals("Time slots", timeslotpage.getTimeSlotHeadingTxt());
	}

	@When("view the time slots on page")
	public void view_the_time_slots_on_page() {
		Assert.assertEquals(timeslotpage.isTimeSlotsDesplayed("ID"), true);
		Assert.assertEquals(timeslotpage.isTimeSlotsDesplayed("Start Time"), true);
		Assert.assertEquals(timeslotpage.isTimeSlotsDesplayed("End Time"), true);
	}

	@Then("verify the id Start time end time colomn shows correct format")
	public void verify_the_id_start_time_end_time_colomn_shows_correct_format() {
		
		String startTimeTxt = timeslotpage.getStarttimeTxt();
		String endTimeTxt = timeslotpage.getEndtimeTxt();
		System.out.println("---> starttime: "+startTimeTxt);
		System.out.println("---> endtime: "+endTimeTxt);
		assertTrue(timeslotpage.isTimeFormatCorrect(startTimeTxt));
		assertTrue(timeslotpage.isTimeFormatCorrect(endTimeTxt));
	}

	@When("click on kebab menu")
	public void click_on_kebab_menu() {
		boolean actual = timeslotpage.clicktimeslotdetailsButton();
		assertTrue(actual);
	}

	@And("click on preview option")
	public void click_on_preview_option() {
		String previewHeading = timeslotpage.clickOnPreview();
		Assert.assertEquals(previewHeading,"Time slot details");
	}

	@Then("verify the start and end time showing correct")
	public void verify_the_start_and_end_time_showing_correct() {
	   String previewStartTime = timeslotpage.getPreviewStartTime();
	   String previewEndTime = timeslotpage.getPreviewEndTime();
	   
	   Assert.assertEquals(timeslotpage.isTimeFormatCorrect(previewStartTime), true);
	   Assert.assertEquals(timeslotpage.isTimeFormatCorrect(previewEndTime), true);
	}
}
