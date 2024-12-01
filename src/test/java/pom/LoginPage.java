package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(css="a[id^='nav-link-accountList']") WebElement SignInlink;
	
	@FindBy(css="span[class^='nav-action']") WebElement signInBtn;

	@FindBy(name="email") WebElement emailId;
	
	@FindBy(name="password") WebElement pwd;
	@FindBy(id="continue") WebElement continuebtn;
	
	@FindBy(id="signInSubmit") WebElement signInbtn;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnSignInLink() {
		SignInlink.click();
	}
	
	public void clickOnSignInBtn() {
		signInBtn.click();
	}
	
	public void enterEmailId(String EmailId) {
		emailId.sendKeys(EmailId);
	}
	
	public void clickOnContinueBtn() {
		continuebtn.click();
	}
	
	public void enterPassword(String password) {
		pwd.sendKeys(password);
	}
	
	public void clickOnSignIn() {
		signInbtn.click();
	}
	
	
	
	
}
