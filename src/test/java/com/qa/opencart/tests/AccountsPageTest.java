package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccountsPageTitle();
		System.out.println("Acc Page Title : " + actTitle);
		Assert.assertEquals(Constants.ACCOUNTS_PAGE_TITLE, actTitle);
	}
	
	@Test
	public void accPageUrlTest() {
		String actUrl = accPage.getAccountsPageURL();
		System.out.println("Acc Page URL : " + actUrl);
		Assert.assertTrue(actUrl.contains(Constants.ACCOUNTS_PAGE_URL_FRACTION));
	}
	
	@Test
	public void accPageHeaderTest() {
		String header = accPage.getAccPageHeader();
		System.out.println("Acc Page Header is : " + header);
		Assert.assertEquals(Constants.ACCOUNTS_PAGE_HEADER, header);
	}

	@Test
	public void logoutLinkTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test
	public void searchExistTest() {
		Assert.assertTrue(accPage.searchExist());
	}
	
	@Test
	public void accPageSectionsTest() {
		List<String> accSectionslist = accPage.getAccPageSections();
		System.out.println("actual section list :" + accSectionslist);
		Assert.assertEquals(Constants.ACCOUNTS_PAGE_SECTIONS_LIST, accSectionslist);
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Apple"}
			};	
	}
	
	
	@Test(dataProvider = "productData")
	public void searchTest(String productName) {
		resultsPage = accPage.doSearch(productName);
		Assert.assertTrue(resultsPage.getProductListCount()>0);
		
	}
	
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro"},
			{"Macbook", "MacBook Air"},
			{"iMac", "iMac"},
			{"Apple", "Apple Cinema 30\""}
		};
	}
	
	@Test(dataProvider = "productSelectData")
	public void selectProduct(String productName, String mainProductName) {
		resultsPage = accPage.doSearch(productName);
		productInfoPage = resultsPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeaderName(), mainProductName);
	}
}
