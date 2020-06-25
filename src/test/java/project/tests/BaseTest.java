package project.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import framework.utils.ReadPropertyUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    public static final String RESOURCES_PATH = "src/main/resources/";
    public static final String VK_API_PROPERTIES = "config.properties";
    private static final String MAIN_URL = new ReadPropertyUtil(BaseTest.RESOURCES_PATH, BaseTest.VK_API_PROPERTIES)
            .getProperty("mainUrl");

    @BeforeMethod
    protected void beforeMethod() {
        getBrowser().maximize();
        getBrowser().goTo(MAIN_URL);
        AqualityServices.getLogger().info("Opening login page");
    }

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
            AqualityServices.getLogger().info("Closing browser");
        }
    }

    private Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}
