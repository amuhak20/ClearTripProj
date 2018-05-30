package BasePackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class BaseClass {

	private static WebDriver driver = null;

	// private static BaseClass instance = null;

	private BaseClass() {

	}

	public static WebDriver getWebDriverInstance() {
		if (driver == null) {

			FirefoxProfile geoDisabled = new FirefoxProfile();
			geoDisabled.setPreference("geo.enabled", false);
			driver = new FirefoxDriver(geoDisabled); // driver = new
														// FirefoxDriver();

			// instance = new BaseClass();
		}
		return driver;
	}

}