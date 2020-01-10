package com.epam.telenettv.portal.qa.sections.carusel;

import java.util.Optional;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class CaruselBelt extends Section{

	@FindBy(xpath = ".//div[contains[@class, 'carousel-arrow-left']")
	private Button arrowLeft;
	
	@FindBy(xpath = ".//div[@class = 'carousel-item']")
	private Elements<Tile> items;
	
	@FindBy(xpath = ".//div[contains[@class, 'carousel-arrow-right']")
	private Button arrowRight;
	
	public Tile findItem(String title) {
		Optional<Tile> foundItem = items.stream().filter(item -> item.getTitle().getText().equals(title)).findFirst();
		if(foundItem.isPresent()) {
			int attempts = 3;
			while(attempts > 0 && !foundItem.get().isDisplayed()) {
				scrollToTheRight();
				attempts--;
			}
			return foundItem.get();
		} else {
			return null;
		}
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
		action.moveToElement(itemsBox, rectangle.getWidth()-10, rectangle.getHeight()/2).build().perform();
		arrowRight = new Button(wait.until(ExpectedConditions.elementToBeClickable(arrowRight.getWebElement())));
		arrowRight.setParent(this);
	}
	
	private void hoverOverLeftArrow() {
		WebDriver webDriver = this.getDriver();
		WebElement itemsBox = this.getWebElement();
		Rectangle rectangle = itemsBox.getRect();
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		
		Actions action = new Actions(webDriver);
		action.moveToElement(itemsBox, 10, rectangle.getHeight()/2).build().perform();
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