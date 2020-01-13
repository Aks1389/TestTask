package com.epam.telenettv.portal.qa.sections.carusel;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.telenettv.portal.qa.site.TelenettvSite.isElementBeyondTheScreen;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import com.epam.telenettv.utils.AwaitilityHelper;

import lombok.Getter;

public class CaruselBelt extends Section{

	@FindBy(xpath = ".//div[contains(@class, 'carousel-arrow-left')]")
	private Button arrowLeft;
	
	@Getter
	@FindBy(xpath = ".//div[@class = 'carousel-item']")
	private Elements<Tile> items;
	
	@FindBy(xpath = ".//div[contains(@class, 'carousel-arrow-right')]")
	private Button arrowRight;
	
	/**
	 * Takes elements in a carusel that are within screen frames and waits until all posters are loaded
	 * */
	public CaruselBelt waitLoaded(String sectionName) {
		logger.debug("Number of items in carusel: " + items.size());
		List<Tile> filteredItems = items.stream().filter(item -> !isElementBeyondTheScreen(item)).collect(Collectors.toList());
		
		AwaitilityHelper.await(15, () -> {
				return filteredItems.stream().allMatch(item -> {
					logger.debug(String.format("\n\rChecking item's %s picture loaded...", item.getTitle().getText()));
					return item.getPoster().isDisplayed();
				});
			}, 3, String.format("Couldn't wait until all posters in a section '%s' get loaded.", sectionName)
		);
		return this;
	}
	
	public Tile findItem(String title) {
		logger.debug("Number of items in a carusel: " + items.size());
		Optional<Tile> foundItem = items.stream().filter(item -> item.getTitle().getText().equals(title)).findFirst();
		Assert.assertTrue(foundItem.isPresent(), "There is no such title: " + title);
		moveCaruselToItem(foundItem.get());
		return foundItem.get();
	}
	
	public Tile findItem(int number) {
		logger.debug("Number of items in a carusel: " + items.size());
		Tile foundItem = items.get(number);
		moveCaruselToItem(foundItem);
		return foundItem;
	}
	
	public void moveCaruselToItem(Tile foundItem) {
		int attempts = 3;
		while(attempts > 0) {
			if(isElementBeyondTheScreen(foundItem)) {
				scrollToTheRight();				
			} else {
				break;
			}
			attempts--;
		}
		Assert.assertFalse(isElementBeyondTheScreen(foundItem), "Failed to scroll to the required item");
	}
	
	public boolean isLeftArrowHidden() {
		return !arrowLeft.getAttribute("class").contains("hidden");
	}
	
	public boolean isRightHidden() {
		return !arrowRight.getAttribute("class").contains("hidden");
	}
	
	public void hoverOverRightArrow() {
		WebDriver webDriver = this.getDriver();
		WebElement itemsBox = this.getWebElement();
		Rectangle rectangle = itemsBox.getRect();
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		
		Actions action = new Actions(webDriver);
		action.moveToElement(itemsBox, rectangle.getWidth()/2-10, 0).build().perform();
		arrowRight = new Button(wait.until(ExpectedConditions.elementToBeClickable(arrowRight.getWebElement())));
		arrowRight.setParent(this);
	}
	
	private void hoverOverLeftArrow() {
		WebDriver webDriver = this.getDriver();
		WebElement itemsBox = this.getWebElement();
		Rectangle rectangle = itemsBox.getRect();
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		
		Actions action = new Actions(webDriver);
		action.moveToElement(itemsBox, 10 - rectangle.getWidth()/2, 0).build().perform();
		arrowLeft = new Button(wait.until(ExpectedConditions.elementToBeClickable(arrowLeft.getWebElement())));
		arrowLeft.setParent(this);
	}
	
	private void scrollToTheRight() {
		hoverOverRightArrow();
		arrowRight.click();
	}
	
	private void scrollToTheLeft() {
		hoverOverLeftArrow();
		arrowLeft.click();
	}
}