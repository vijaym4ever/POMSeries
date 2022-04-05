package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
		
	}
	
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		co.addArguments("--disable-dev-shm-usage");
		co.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
		if(Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
		/*
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.addArguments("--headless");
		}*/
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setBrowserVersion(prop.getProperty("browserversion"));
			co.setPlatformName("linux");
			co.setCapability("enableVNC", true);
			//sel 4.x
		}
		
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		MutableCapabilities caps = new MutableCapabilities();
		caps.setCapability("enableVNC", true);
		caps.setCapability("browserVersion", prop.getProperty("browserversion"));
		caps.setCapability("moz:debuggerAddress", false);
		fo = new FirefoxOptions(caps);
		
		//fo = new FirefoxOptions();
		//if(Boolean.parseBoolean(prop.getProperty("headless"))) fo.addArguments("--headless");
		//if(Boolean.parseBoolean(prop.getProperty("incognito"))) fo.addArguments("--incognito");
		/*
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			fo.addArguments("--headless");
		}
		*/
		return fo;
	}

}
