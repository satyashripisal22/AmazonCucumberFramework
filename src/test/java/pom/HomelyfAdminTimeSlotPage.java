package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomelyfAdminTimeSlotPage {

		WebDriver driver ;
		
		@FindBy(xpath = "//button[contains(@class,'MuiIconButton-sizeMedium css-1uiytwh')]") WebElement listItemBtn;
		
		@FindBy(xpath = "//div[contains(@class,'MuiDrawer-root ')]//ul[@class='MuiList-root css-1uzmcsd']/li") List<WebElement> itemList;
		
		@FindBy(xpath = "//*[text()='Time slots']") WebElement timeslotHeadingTxt;
		
		@FindBy(xpath = "table/thead/tr/th") List<WebElement> tableHead;
		
		@FindBy(xpath = ".//td[2]") WebElement startTime;
		
		@FindBy(xpath = ".//td[3]") WebElement endTime;
		
		@FindBy(xpath =".//td[4]") WebElement timeslotdetailsButton;
		
		@FindBy(xpath = "//ul[@role='menu']") WebElement menu;
		@FindBy(xpath = "(//ul[@role='menu'])/li[1]") WebElement previewMenu;
		@FindBy(xpath = "//*[text()='Time slot details']") WebElement previewMenuHeading;
		@FindBy(xpath = "//div[h3[text()='Start Time']]/following-sibling::div//p") WebElement previewStartTime;
		@FindBy(xpath = "//div[h3[text()='End Time']]/following-sibling::div//p") WebElement previewEndTime;
		
		
		public HomelyfAdminTimeSlotPage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		public void clickOnTimeSlotItem() {
			listItemBtn.click();
		}
		
		public void getItemList(String str) {
			int n = itemList.size();
			
			for(int i = 0 ; i < n; i++ ) {
				String item = itemList.get(i).getText();
				if(item.contains(str)) {
					itemList.get(i).click();
					break;
				}
			}
			
		}
		
		public String getTimeSlotHeadingTxt() {
			String timeSlotHeading = timeslotHeadingTxt.getText();
			return timeSlotHeading;
		}
		
		
		public boolean isTimeSlotsDesplayed(String str) {
			boolean flag = true;
			for(int i = 0 ; i < tableHead.size(); i++) {
				String tableHeadTxt = tableHead.get(i).getText();
				if(str.equals(tableHeadTxt))
					flag = false;			
			}
			return flag;
		}
		
		public String getStarttimeTxt() {
			return startTime.getText();
		}
		
		public String getEndtimeTxt() {
			return endTime.getText();
		}
		
		public boolean isTimeFormatCorrect(String time) { // 16:30:00
			return time.matches("\\d{2}:\\d{2}:\\d{2}");
		}
			
		public boolean clicktimeslotdetailsButton() {
			timeslotdetailsButton.click();
			return menu.isDisplayed();
		}
		public String clickOnPreview() {
			previewMenu.click();
			String previewHeadingTxt = previewMenuHeading.getText();
			return previewHeadingTxt;
		}
		
		public String getPreviewStartTime() {
			return previewStartTime.getText();
		}
		public String getPreviewEndTime() {
			return previewEndTime.getText();
		}
}
