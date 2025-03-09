package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	private WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By usernameTextbox = By.id("Email");
    private By passwordtextbox = By.id("password");
    private By loginButton = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/div[1]/div/form/div[3]/button");

    //Add action functions
   
    public void enterUsername(String username) {
    	driver.findElement(usernameTextbox).clear();
        driver.findElement(usernameTextbox).sendKeys(username);
    }

    public void enterpassword(String password) {
    	driver.findElement(passwordtextbox).clear();
        driver.findElement(passwordtextbox).sendKeys(password);
    }
    
    public void pressSubmit() {
        driver.findElement(loginButton).click();
    }
    
}
