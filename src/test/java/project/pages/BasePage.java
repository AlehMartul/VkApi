package project.pages;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.By;

public abstract class BasePage {

    private By locator;
    private String pageName;

    protected BasePage(By locator, String pageName) {
        this.locator = locator;
        this.pageName = pageName;
    }

    public boolean isPageLoaded(){
        return AqualityServices.getElementFactory().getLabel(this.locator, this.pageName).state().waitForDisplayed();


    }
}