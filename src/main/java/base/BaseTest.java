package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.observer.entity.MediaEntity;

import util.EmailUtils;
import util.ExtentReportManager;
import util.Log;

public class BaseTest {

	protected static WebDriver driver;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	
	@BeforeSuite
	public void setupReport() {
		extent = ExtentReportManager.getReportInstance();//Calling extentReports method
	}
	
	@AfterSuite
	public void teardownReport() {
		extent.flush(); // Write everything to the file
		String reportPath = ExtentReportManager.reportPath;
		EmailUtils.sendTestReport(reportPath);
	}
	
	
	
    @BeforeMethod
    public void setup() {
        //WebDriverManager.chromedriver().setup(); not required with latest selenium dependency
        Log.info("Setting up WebDriver");
    	driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Log.info("Navigationg to test URL");
        driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
    }

    @AfterMethod
	public void tearDown(ITestResult result) {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			
			//String screenshotPath = ExtentReportManager.captureScreenshot(driver, "LoginFailure");
			String screenshotPath = ExtentReportManager.captureScreenshot(driver, result.getName());
			test.fail("Test Failed.. Check Screenshot", 
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		
		
		if (driver != null) {
			Log.info("Closing Browser...");
			driver.quit();
		}
	}
}
