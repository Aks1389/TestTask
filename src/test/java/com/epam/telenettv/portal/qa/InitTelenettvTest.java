package com.epam.telenettv.portal.qa;

import static com.epam.telenettv.portal.qa.site.TelenettvSite.mainPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import com.epam.telenettv.portal.qa.site.TelenettvSite;

public class InitTelenettvTest extends TestNGBase {

	protected static Properties prop = loadProperties("test.properties");
	
	@BeforeSuite(alwaysRun = true)
    public void setUp() {
		WebSite.init(TelenettvSite.class);
        TelenettvSite.setWaitingElementsTimeout(Integer.parseInt(prop.getProperty("timeout.wait.element", "20")));
        mainPage.open();
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
}
