package com.epam.telenettv.portal.qa.sections.carusel;

import static com.epam.telenettv.portal.qa.site.TelenettvSite.itemPage;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Image;
import com.epam.jdi.uitests.web.selenium.elements.common.Link;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import lombok.Getter;

public class Tile extends Section{

	@Getter
	@FindBy(xpath = ".//img")
	private Image poster;
	
	@FindBy(xpath = ".//a[@class = 'poster-tile__link']")
	private Link link;
	
	@Getter
	@FindBy(xpath = ".//div[contains(@class, 'ui-kit-tile-string--primary')]/div | .//div[@class = 'orion-titlecard-episodes-picker__title']")
	private Text title;
	
	public void clickLink() {
		this.mouseOver();
//		link.click();
		poster.click();
		itemPage.checkOpened();
		itemPage.waitPageLoaded();
	}
}
