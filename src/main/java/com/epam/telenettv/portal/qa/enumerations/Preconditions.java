package com.epam.telenettv.portal.qa.enumerations;

import static com.epam.telenettv.portal.qa.site.TelenettvSite.mainPage;
import static com.epam.telenettv.portal.qa.site.TelenettvSite.waitLoading;
import java.util.function.Supplier;

import com.epam.commons.linqinterfaces.JAction;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.preconditions.WebPreconditions;

public enum Preconditions implements WebPreconditions{
	IS_HOME_PAGE_OPENED(() -> mainPage.verifyOpened(), () -> openPage(mainPage));

	private Supplier<Boolean> checkAction;
    private JAction moveToAction;
	
    Preconditions(Supplier<Boolean> checkAction, JAction moveToAction) {
        this.checkAction = checkAction;
        this.moveToAction = moveToAction;
    }

    @Override
    public Boolean checkAction() {
        return checkAction.get();
    }

    @Override
    public void moveToAction() {
        moveToAction.invoke();
    }

    public static void openPage(WebPage page) {
        page.open();
        waitLoading();
    }
}
