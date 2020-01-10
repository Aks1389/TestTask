package com.epam.telenettv.portal.qa.sections;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.complex.Menu;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import com.epam.telenettv.portal.qa.enumerations.Pages;

public class Sidebar extends Section{

	@FindBy(xpath = ".//a/span[@class = 'navigation-title']")
	public Menu<Pages> sidebarMenu;
	
	public void selectPage(Pages page) {
		sidebarMenu.select(page);
	}
}
