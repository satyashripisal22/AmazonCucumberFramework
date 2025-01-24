package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomelyfLoginPage {

	@FindBy(xpath="(//div[@class='MuiStack-root css-4sh3ee']//div[2]//div//input)[1]") private WebElement username;
	@FindBy(xpath="(//div[@class='MuiStack-root css-4sh3ee']//div[3]//div//input)[1]") private WebElement password;
	@FindBy(css="button[class^='MuiButtonBase-root MuiButton-root']:nth-of-type(1)") private WebElement loginbtn;
	
	
	public HomelyfLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String uname) {
		username.sendKeys(uname);
	}
	
	public void enterPassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void clickOnLoginBtn() {
		loginbtn.click();
	}
}
