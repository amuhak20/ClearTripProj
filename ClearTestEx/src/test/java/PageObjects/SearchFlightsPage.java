package PageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchFlightsPage {

	protected WebDriver driver;

	public SearchFlightsPage(WebDriver driver) {
		this.driver = driver;
	}

	// WebDriverWait wait = new WebDriverWait(driver, 30);

	@FindBy(id = "RoundTrip")
	private WebElement roundTripRadioButton;

	public void clickOnRoundTripRadioButton() {
		roundTripRadioButton.click();
	}

	@FindBy(xpath = "//ul[@id='ui-id-1'][@class='autoComplete']/li/a")
	private WebElement autoSuggestFrom;

	@FindBy(xpath = "//input[@id='FromTag']")
	private WebElement fromTextField;

	public void setFromText(String str) {
		fromTextField.sendKeys(str);
		autoSuggestFrom.click();

	}

	@FindBy(xpath = "//ul[@id='ui-id-2'][@class='autoComplete']/li/a")
	private WebElement autoSuggestTo;

	@FindBy(css = "#ToTag")
	private WebElement toTextField;

	public void setToText(String str) {
		toTextField.sendKeys(str);
		autoSuggestTo.click();
	}

	@FindBy(xpath = "//div[@id='ORtrip']/section[2]/div[1]/dl/dd/div/a/i[text()='Cal']")
	private WebElement calenderIcon;

	public void clickOnCalIcon() {
		calenderIcon.click();
	}

	@FindBy(xpath = ".//*[@id='ReturnDateContainer']/dd/div/a/i[text()='Cal']")
	private WebElement returnCalenderIcon;

	public void clickOnRetunCalIcon() {
		returnCalenderIcon.click();
	}

	@FindBy(id = "Adults")
	private WebElement selectAdults;

	public void selectAdults(String str) {
		selectValues(str, selectAdults);

	}

	@FindBy(id = "Childrens")
	private WebElement selectChidrens;

	public void selectChildren(String str) {
		selectValues(str, selectChidrens);
	}

	@FindBy(id = "Infants")
	private WebElement selectInfants;

	public void selectInfants(String str) {
		selectValues(str, selectInfants);
	}

	public void selectValues(String str, WebElement ele) {
		Select sel = new Select(ele);
		sel.selectByVisibleText(str);
	}

	@FindBy(id = "SearchBtn")
	private WebElement searchFlightsBtn;

	public void clickSearchFlightsBtn() {
		searchFlightsBtn.click();
	}

	@FindBy(xpath = "//div[@id='ui-datepicker-div']/div[@class='monthBlock first']//table[@class='calendar']//td")
	private List<WebElement> dateSelection;

	public void selectDate(String str) {
		clickOnCalIcon();
		for (WebElement dates : dateSelection) {
			String dateText = dates.getText();
			System.out.println(dateText);
			if (dateText.equals(str)) {
				dates.click();
				break;
			}
		}

	}

	@FindBy(id = "modifySearchLink")
	private WebElement toFromValues;

	public String getToFromvalues() {
		String toFromVal = toFromValues.getText();
		return toFromVal;
	}

	@FindBy(xpath = "//div[@id='ui-datepicker-div']/div[@class='monthBlock first']//td/a")
	private List<WebElement> returnDateSelection;

	public void selectReturnDate(String str) {
		clickOnRetunCalIcon();
		for (WebElement dates : returnDateSelection) {
			String dateText = dates.getText();
			System.out.println(dateText);
			if (dateText.equals(str)) {
				dates.click();
				break;
			}
		}
	}

	@FindBy(xpath = ".//*[@id='ResultContainer_1_1']/section[2]/section/aside[1]/div/div[1]/form/div/div[4]/div/nav/ul/li[2]/label")
	private WebElement oneStop;

	public void clickOnOneStop() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(oneStop));
		oneStop.click();
	}

	@FindBy(xpath = ".//*[@id='flightForm']//th[@class='duration']")
	private List<WebElement> duration;

	public String getLongestDuration() {
		List<String> list = new ArrayList<String>();
		for (WebElement ele : duration) {
			String durationText = ele.getText();
			list.add(durationText);
		}
		Collections.sort(list);
		int size = list.size();
		String longestDuration = list.get(size - 1);
		return longestDuration;
	}

	@FindBy(xpath = "//form[@id='flightForm']//th[@class='price']")
	private List<WebElement> price;

	public String getNthPrice(int n) {
		int index = n - 1;
		String nThPrice = "";
		int size = price.size();
		for (int i = 0; i <= size; i++) {
			if (i == index) {
				nThPrice = price.get(i).getText();
				break;
			}
		}
		return nThPrice;
	}

}
