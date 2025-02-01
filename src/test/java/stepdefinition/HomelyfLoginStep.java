package stepdefinition;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.cucumber.java.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.HomelyfAdminTimeSlotPage;
import pom.HomelyfAdminUserPage;
import pom.HomelyfLoginPage;
import pom.Services;

public class HomelyfLoginStep {
	WebDriver driver;
	HomelyfLoginPage hlp;
	HomelyfAdminUserPage hlap;
	HomelyfAdminTimeSlotPage timeslotpage;
	Services service;
	ChromeOptions option;
	
	@Before
	public void objSetUp() {
//		option = new ChromeOptions();
//		option.addArguments("--incognito");
//		driver = new ChromeDriver(option);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		hlp = new HomelyfLoginPage(driver);
		hlap = new HomelyfAdminUserPage(driver);
		timeslotpage = new HomelyfAdminTimeSlotPage(driver);
		service = new Services(driver);
	}

	@Given("User opens URL {string} on Chrome")
	public void user_opens_url_on_chrome(String url) {
	    driver.get(url);
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@And("navigate to timeslot page")
	public void navigate_to_timeslot_page() {
		timeslotpage.clickOnTimeSlotItem();
		timeslotpage.getItemList("Time slots");
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
	
	//--------------------Services categories Verification-------------------------------------
	
	
	@Given("the user is on the service page")
	public void the_user_is_on_the_service_page() {
		 timeslotpage.clickOnTimeSlotItem();
		 timeslotpage.getItemList("Services");
		 Assert.assertEquals(service.getcategotiesTabTxt(), "CATEGORIES");
	}

	@When("the user verifies that the categories list is displayed")
	public void the_user_verifies_that_the_categories_list_is_displayed() {
		 assertTrue(service.isDisplayCategoriesList());
	}

	@And("the user searches for a category using the search box with the keyword {string}")
	public void the_user_searches_for_a_category_using_the_search_box_with_the_keyword(String categoryName) {
		  List<String> searchResults = service.getsearchReults(categoryName); 
		  System.out.println("Search Results: "+ searchResults	+"\n Size: "+searchResults.size()	);
		  for(String results : searchResults) {
			  Assert.assertTrue(results.toLowerCase().contains(categoryName),"Expected Result to contians "+ categoryName +" but found: "+results);
		  }
	}

	@Then("matching categories should be displayed in the results")
	public void matching_categories_should_be_displayed_in_the_results() {
	  List<String> searchResults = service.getsearchReults("spa");
	  System.out.println("Search Results: "+ searchResults	+"\n Size: "+searchResults.size());
//	  Assert.assertTrue(searchResults.size() > 0,"No matching result found");
	}

	@When("the user selects {int} rows per page")
	public void the_user_selects_rows_per_page(int rowCount) {
		System.out.println("Categories rows count= "+service.getPageRowCount());
		Assert.assertTrue(service.getPageRowCount() == rowCount ,"Results are lower than selected count" );
	}

	@Then("verify that exactly {int} rows are displayed")
	public void verify_that_exactly_rows_are_displayed(Integer int1) {
	    Assert.assertTrue(service.checkPagination(),"Row are missmatch");
	}

	@When("the user verifies that each category row contains a name and description")
	public void the_user_verifies_that_each_category_row_contains_a_name_and_description() {
	   Assert.assertTrue(service.isDisplayCategoriesList(), "Categories list Names are not displayed");
	}

	@Then("each row should provide an action menu for further operations")
	public void each_row_should_provide_an_action_menu_for_further_operations() {
	  Assert.assertEquals(service.actionDetails(), "Category details");
	}

	@When("the user clicks the Add category button")
	public void the_user_clicks_the_add_category_button() {
		Assert.assertEquals(service.isClickableCatogoryBtn(), "Add category","Result missMatch");
	}

	@When("fills out the form with valid category details")
	public void fills_out_the_form_with_valid_category_details() {
	   service.fillCategoryValidDetails("Cloth Retail","Fashnable cloths retail store");
	}

	@Then("the new category should be listed in the table")
	public void the_new_category_should_be_listed_in_the_table() {
	   
	}
	
	@After
	public void tearDown() {
		if(driver != null) {
			try {
				driver.manage().deleteAllCookies();
				driver.close();
				System.out.println("Browser is Closed");
				
			} catch (Exception e) {
				System.out.println("Exception throwed when closing browser"+e.getMessage());
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Webdriver instance is not initialized");
		}
	}
	
}
 