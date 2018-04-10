package SearchTestCases;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.SearchFlightsPage;

public class SearchFlightTests {
	SearchFlightsPage searchFlights;
	WebDriver driver;
	String URL = "https://www.cleartrip.com/";

	/*
	 * @BeforeClass(alwaysRun = true) public void initiateBrowser() {
	 * FirefoxProfile geoDisabled = new FirefoxProfile();
	 * geoDisabled.setPreference("geo.enabled", false); driver = new
	 * FirefoxDriver(geoDisabled); // searchFlights = new
	 * SearchFlightsPage(driver);
	 * 
	 * searchFlights = PageFactory.initElements(driver,
	 * SearchFlightsPage.class); }
	 */

	@BeforeMethod
	public void launchBrowser() {
		FirefoxProfile geoDisabled = new FirefoxProfile();
		geoDisabled.setPreference("geo.enabled", false);
		driver = new FirefoxDriver(geoDisabled);
		// searchFlights = new SearchFlightsPage(driver);

		searchFlights = PageFactory.initElements(driver,
				SearchFlightsPage.class);
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	// Test begin here

	public void searchRoundTripFlight() {
		searchFlights.clickOnRoundTripRadioButton();
		searchFlights
				.setFromText("Bangalore, IN - Kempegowda International Airport (BLR)");
		searchFlights
				.setToText("Mumbai, IN - Chatrapati Shivaji Airport (BOM)");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String dateToday = formatter.format(date);
		String[] split = dateToday.split("/");
		String todayDate = split[0];
		String today = String.valueOf(todayDate);
		int todate = Integer.parseInt(today);
		int dateAfter5days = todate + 5;
		String date5daysAfter = String.valueOf(dateAfter5days);
		String todateValue = String.valueOf(todate);
		System.out.println(todateValue);
		System.out.println(dateAfter5days);
		searchFlights.selectDate(todateValue);
		try {
			Thread.sleep(6000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		searchFlights.selectReturnDate(date5daysAfter);
		try {
			Thread.sleep(6000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		searchFlights.selectAdults("2");

		searchFlights.selectChildren("1");

		searchFlights.selectInfants("1");

		searchFlights.clickSearchFlightsBtn();

	}

	@Test(description = "Select longest flight with 1 stop")
	public void getLongestFlightWith1Stop() {
		searchRoundTripFlight();
		searchFlights.clickOnOneStop();
		String longestDuration = searchFlights.getLongestDuration();
		Assert.assertEquals(longestDuration, "55m");
	}

	@Test(description = "Select 4th price")
	public void get4thPrice() {
		searchRoundTripFlight();
		String fourthPrice = searchFlights.getNthPrice(4);
		System.out.println(fourthPrice);

	}

	@Test(description = "Select 5th price")
	public void get5thPrice() {
		searchRoundTripFlight();
		String fifthPrice = searchFlights.getNthPrice(5);
		System.out.println(fifthPrice);

	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}

	/*
	 * @AfterClass public void quitBrowser() { driver.quit(); }
	 */

}
