package com.epam.telenettv.portal.qa;

import static com.epam.telenettv.portal.qa.site.TelenettvSite.mainPage;
import static java.lang.Runtime.getRuntime;
import static java.lang.String.format;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.epam.jdi.uitests.core.logger.LogLevels;
import com.epam.jdi.uitests.web.selenium.driver.SeleniumDriverFactory;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import com.epam.telenettv.portal.qa.site.TelenettvSite;

public class InitTelenettvTest extends TestNGBase {

	private static Properties prop = loadProperties("test.properties");
	private String driverProcessName;
	
	@BeforeSuite(alwaysRun = true)
    public void setUp() {
		setDriver();
		WebSite.init(TelenettvSite.class);
		WebSettings.logger.setLogLevel(LogLevels.DEBUG);
        TelenettvSite.setWaitingElementsTimeout(Integer.parseInt(prop.getProperty("timeout.wait.element", "20")));
        mainPage.open();
    }
	
	private void setDriver() {
		SeleniumDriverFactory factory = WebSettings.getDriverFactory();
		
		String browserName = prop.getProperty("browser");
		switch (browserName) {
			case "chrome":{
				driverProcessName = "chromedriver";
				System.setProperty("webdriver.chrome.driver", factory.getDriverPath() + "chromedriver.exe");
				WebSettings.useDriver(() -> {
					return factory.webDriverSettings.apply(new ChromeDriver());
				});
				break;
			}
			case "opera":{
				driverProcessName = "operadriver";
				System.setProperty("webdriver.opera.driver", factory.getDriverPath() + "operadriver.exe");
				WebSettings.useDriver(() -> {
					return factory.webDriverSettings.apply(new OperaDriver());
				});
				break;
			}
		}
	}

	public static Properties loadProperties(String file) throws AssertionError{
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(file));
        } catch (Exception e) {
            try {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                InputStream resourceStream = loader.getResourceAsStream(file);
                props.load(resourceStream);

            } catch (FileNotFoundException e1) {
                throw new AssertionError("File with locator's information not found: " + e.toString());
            } catch (IOException e1) {
                throw new AssertionError("IO error while trying to reach locator's information file: " + e.toString());
            }
        }
        return props;
    }
	
//	@AfterSuite(alwaysRun = true)
	public void teardown() throws IOException {
		getRuntime().exec(format("taskkill /F /IM %s.exe /T", driverProcessName));
	}
}
