package pom;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.directory.SearchResult;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Services {
	
	WebDriver driver;
	
	@FindBy(xpath = "//button[(contains(text(),'Categories'))]") WebElement categoriesTab;
	@FindBy(css = "input[class*='MuiInputBase-input']") WebElement categoriesSearchBox;
	@FindBy(css = "input[class*='MuiInputBase-input']") WebElement searchBox;
//	@FindBy(xpath = "//tbody[@class='MuiTableBody-root css-1xnox0e']//tr") List<WebElement> tableList;
	@FindBy(xpath = "//tr/td[1]") List<WebElement> tableList;
	@FindBy(css = "*[class*='MuiSvgIcon-fontSizeMedium MuiSelect-icon']") WebElement rowPerPage;
	@FindBy(xpath = "//table/tbody/tr") WebElement listData;
	@FindBy(xpath = "//*[@role='combobox']") WebElement rowPerPageDropDownBtn;
	@FindBy(xpath = "//li[@role='option'][1]") WebElement pagedropdownTenCount;
	@FindBy(xpath = "//table/tbody/tr/td[3]") WebElement actionBtn;
	@FindBy(xpath = "//div[contains(@class, 'MuiPopover-paper')]//ul[contains(@class, 'MuiList-root')]//li") List<WebElement> actionList;
	@FindBy(xpath = "//h2[contains(text(),'Category details')]") WebElement previewHeading;
	@FindBy(xpath = "//button[contains(text(),'Add')]") WebElement addCategoryBtn;
	@FindBy(xpath = "//h2[contains(text(),'Add category')]") WebElement addCategoryHeading;
	@FindBy(xpath = "(//input[@class='MuiInputBase-input MuiInput-input css-mnn31'])[1]") WebElement addCategoryName;
	@FindBy(xpath = "(//input[@class='MuiInputBase-input MuiInput-input css-mnn31'])[2]") WebElement addCategoryDescription;
	@FindBy(xpath = "(//input[@class='MuiInputBase-input MuiInput-input css-mnn31'])[3]") WebElement addCategoryImage;
	@FindBy(xpath = "//button[contains(text(),'Cancel')]") WebElement addCategoryCancelBtn;
	
	
	

	
	public Services(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public String getcategotiesTabTxt() {
		String categoriesTxt = categoriesTab.getText();
		return categoriesTxt;
	}
	
	public List<String> getsearchReults(String str)  {
		categoriesSearchBox.clear();
		categoriesSearchBox.sendKeys(str);
			
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2)); 
//		wait.until(ExpectedConditions.visibilityOfAllElements(tableList));
		
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}
		
		List<String> list = new ArrayList<String>();		
		
		for(WebElement element : tableList) {
			
			String searchResults = element.getText();
			System.out.println(searchResults);
			
			if(searchResults.contains(str)) {
				list.add(searchResults);
			}
		}
		return list;
	}
	
	
	public int getResultCount() {
		return tableList.size();
	}
	
	public void clickRowPerPageCount(int rowCount) {
		rowPerPage.click();
	}
	
	public boolean isDisplayCategoriesList() {
		return listData.isDisplayed();	
	}
	
	public void enterSearchBoxTxt(String text) {
		searchBox.sendKeys(text);
	}
	
	public int getPageRowCount() {
		return tableList.size();
	}
	
	public boolean checkPagination() {
		rowPerPageDropDownBtn.click();
		pagedropdownTenCount.click();
		int resultCount = tableList.size();
		int pagedropdown = Integer.parseInt(pagedropdownTenCount.getText());
		return resultCount ==pagedropdown;
	}
	
	public String actionDetails() {
		actionBtn.isDisplayed();
		actionBtn.click();
		
		for(WebElement element: actionList){
			String list = element.getText();
			if(list.equals("Preview")) {
				element.click();
				break;
			}
		}
		return previewHeading.getText();
	}
	
	
	public String validateAddCategory() {
		addCategoryBtn.isDisplayed();
		addCategoryBtn.click();
		return addCategoryHeading.getText();
	}
	
	public String isClickableCatogoryBtn() {
		addCategoryBtn.click();
		return addCategoryHeading.getText();
	}
	
	public void fillCategoryValidDetails(String name, String description) {
		try {
			addCategoryName.sendKeys(name);
			addCategoryDescription.sendKeys(description);
			Thread.sleep(2000);
			addCategoryCancelBtn.click();
		} catch (Exception e) {
			System.out.println("Something Went wrong"+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}
