package tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import dataproviders.ExcelDataProvider;
import pages.LoginPage;
import util.ExtentReportManager;
import util.Log;

public class LoginTest extends BaseTest{
	
	@Test(dataProvider = "TestData", dataProviderClass = ExcelDataProvider.class)
    public void testValidlogin(String username, String pass) {
		Log.info("Start login test..");
		test = ExtentReportManager.createTest("Login Test"+username); //create a new test in the report
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.enterUsername(username);
		loginPage.enterpassword(pass);
		loginPage.pressSubmit();
		
		String title = driver.getTitle();
		
		Log.info("Verifying page title..");
		test.info("Verifying page title");
		System.out.println("Title : "+ title);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains("nopCommerce demo store. Login"));
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
		Log.info("Login test completed..");
		test.pass("Login Successful");
	}
	
	//@Test(dataProvider = "TestData", dataProviderClass = ExcelDataProvider.class)
	public void testWithInvalidCredntials(String username, String pass) {
		Log.info("Start login test..");
		test = ExtentReportManager.createTest("Testing Invalid Credential");
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.enterUsername(username);
		loginPage.enterpassword(pass);
		loginPage.pressSubmit();
		
		String title = driver.getTitle();
		
		Log.info("Verifying page title..");
		test.info("Verifying page title");
		System.out.println("Title : "+ title);
		Assert.assertEquals(driver.getTitle(), "Admin area demo123");
		Log.info("Login test completed..");
		test.fail("Login UnSuccessful");
	}
}
