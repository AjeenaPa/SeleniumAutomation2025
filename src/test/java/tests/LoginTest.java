package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest{
	
	@Test
    public void testValidlogin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterUsername("admin");
		loginPage.pressSubmit();
		String title = driver.getTitle();
		System.out.println("Title : "+ title);
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
	}
	
}
