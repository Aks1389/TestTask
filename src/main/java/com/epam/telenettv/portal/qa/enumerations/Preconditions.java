package com.epam.telenettv.portal.qa.enumerations;

import static com.epam.telenettv.portal.qa.site.TelenettvSite.*;

import java.util.function.Supplier;

import com.epam.commons.linqinterfaces.JAction;
import com.epam.jdi.uitests.web.selenium.preconditions.WebPreconditions;
import com.epam.telenettv.portal.qa.pages.CustomWebPage;

public enum Preconditions implements WebPreconditions{
	IS_HOME_PAGE_OPENED(() -> mainPage.verifyOpened(), () -> openPage(mainPage)),
	IS_MOVIES_AND_SERIES_PAGE_OPENED(() -> moviesAndSeriesPage.verifyOpened(), () -> openPage(moviesAndSeriesPage)),
	IS_ITEM_PAGE_OPENED(() -> itemPage.verifyOpened(), () -> openPage(itemPage));

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

    public static void openPage(CustomWebPage page) {
        sidebar.selectPage(page.getPageName());
        page.waitPageLoaded();
    }
}