package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.windows.WindowsDriver;

public class NotepadTest{
	
	public static WindowsDriver<?> driver = null;
	
	@BeforeClass
	public void setUp() {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("app", "C:\\Windows\\System32\\notepad.exe");
		cap.setCapability("platformName", "Windows");
		cap.setCapability("deviceName", "WindowsPC");
		try {
		driver = new WindowsDriver<WebElement>(new URL("http://127.0.0.1:4723"), cap);
		} catch(MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void cleanUp() {
		driver.quit();
		setUp();
	}
  
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void checkHelpAboutNowTest() {
		driver.findElementByName("Help").click();
		driver.findElementByName("About Notepad").click();
		driver.findElementByName("OK").click();
	}
	
	@Test
	public void enterTextInNotePad() {
		driver.findElementByName("Text Editor").sendKeys("Ronnie Wainaina was here :)");
		driver.findElementByName("Text Editor").clear();
		driver.findElementByName("Edit").click();
		driver.findElementByAccessibilityId("26").click();
		String value = driver.findElementByName("Text Editor").getAttribute("Value.Value");
		System.out.println(value);
	}
}
