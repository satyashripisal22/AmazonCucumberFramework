package pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomelyfAdminUserPage {

	WebDriver driver;
	@FindBy(xpath = "//input[@placeholder='Search user']")
	WebElement searchbox;
	@FindBy(xpath = "//table[@class='MuiTable-root css-a0xgdx']")
	WebElement searchResult;
	@FindBy(xpath = "//table[@class='MuiTable-root css-a0xgdx']/tbody/tr")
	List<WebElement> tableRows;

	@FindBy(xpath = "//table/tbody/tr/td[7]")
	WebElement Actionsbtn;
	@FindBy(xpath = "//li[@role='menuitem']")
	List<WebElement> options;
	
	@FindBy(xpath = "//h2[text()='User details']")
	WebElement headingPreviewText;

	public HomelyfAdminUserPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void enterEmailAdd(String email) {
		searchbox.sendKeys(email);
	}

	public boolean searchCustomerByEmail(String email) {

		boolean found = false;

		// total no. of rows in grid
		int ttlRows = tableRows.size();
		System.out.println(ttlRows);

		// total no of columns
		// int ttlColumns =
		// tableColumns.size();//table[@width='100%']/tbody/tr/td[2]//table[@width='100%']/tbody/tr[9]/td[2]

		for (int i = 1; i <= ttlRows; i++) // iterate all the rows //table[@id='customers-grid']/tbody/tr[1]/td[2]
		{
			WebElement webElementEmail = driver
					.findElement(By.xpath("//table[@class='MuiTable-root css-a0xgdx']/tbody/tr[" + i + "]/td[3]"));
			String actualEmailAdd = webElementEmail.getText();

			if (actualEmailAdd.contains(email)) {
				found = true;
			}

		}
		return found;

	}
	public void clickOnActionBtn() {
		Actionsbtn.click();
	}
	
	public void selectOption() {
		int size = options.size();
		System.out.println(size);
		for(int i=0;i<size;i++) {
			String optiontext = options.get(i).getText();
			if(optiontext.contains("Preview")) {
				options.get(i).click();
				break;
			}
		}
		
	}
	public String getTextOfPreviewPopup() {
		String text = headingPreviewText.getText();
		return text;
		
	}

}
