package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.ResultsPage;

public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	Properties prop;
	
	LoginPage loginPage;
	AccountsPage accPage;
	RegisterPage regPage;
	ResultsPage resultsPage;
	ProductInfoPage productInfoPage;
	SoftAssert softAssert;
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("-----before suite----------");
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("-----after suite----------");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("-----before method----------");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("-----after method----------");
	}
	
	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		System.out.println("new driver created---before Test");
		prop = df.init_prop();
		//driver = df.init_driver("chrome");
		driver = df.init_driver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		System.out.println("-----after test----------");
		driver.quit();
	}

}
