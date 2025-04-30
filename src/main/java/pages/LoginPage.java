package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import util.Log;

public class LoginPage {
	private WebDriver driver;

   

    //Page Factory
    
    @FindBy(id = "Email")
    WebElement usernameTextbox;
    
    @FindBy(id = "Password")
    WebElement passwordtextbox;
    
    @FindBy(xpath = "//*[@id=\"main\"]/div/div/div/div[2]/div[1]/div/form/div[3]/button")
    WebElement loginButton;
    
    
    // Locators
//    private By usernameTextbox = By.id("Email");
//    private By passwordtextbox = By.id("password");
//    private By loginButton = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/div[1]/div/form/div[3]/button");

    
    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    //Add action functions
   
    public void enterUsername(String username) {
    	
    	usernameTextbox.clear();
    	usernameTextbox.sendKeys(username);
//    	driver.findElement(usernameTextbox).clear();
//        driver.findElement(usernameTextbox).sendKeys(username);
    }

    public void enterpassword(String password) {
    	
    	passwordtextbox.clear();
    	passwordtextbox.sendKeys(password);
//    	driver.findElement(passwordtextbox).clear();
//        driver.findElement(passwordtextbox).sendKeys(password);
    }
    
    public void pressSubmit() {
    	Log.info("Clicking Login button..");
    	loginButton.click();
        //driver.findElement(loginButton).click();
    }
    
}
