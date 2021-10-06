package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.PageObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest implements IDriver {

    private static AppiumDriver<? extends WebElement> appiumDriver;
    static IPageObject page;

    @Override
    public AppiumDriver<? extends WebElement> getDriver() {
        return appiumDriver;
    }

    public IPageObject getPage() {
        return page;
    }

    @Parameters({"platformName", "appType", "deviceName", "udid", "browserName","app","appPackage","appActivity","bundleId", "cloud"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName,
                      String appType,
                      @Optional("") String deviceName,
                      @Optional("") String udid,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId,
                      @Optional("") Boolean cloud
    ) throws Exception {
        System.out.println("Before: app type - " + appType);
        setAppiumDriver(platformName, deviceName, udid, browserName, app, appPackage, appActivity, bundleId, cloud);
        setPageObject(appType, appiumDriver);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        System.out.println("Tear down test suite");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String udid, String browserName,
                                 String app, String appPackage, String appActivity, String bundleId, Boolean cloud) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);

        if (app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());

        //mandatory web capabilities
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");

        // Capabilities for test of Android native app on EPAM Mobile Cloud
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);

        // Capabilities for test of iOS native app on EPAM Mobile Cloud
        capabilities.setCapability("bundleId", bundleId);

        if(platformName.equals("iOS")) capabilities.setCapability("automationName","XCUITest");

        if(cloud){
            try {
                String tokenEncoded = java.net.URLEncoder.encode(System.getProperty("token"), "UTF-8");
                appiumDriver = new AppiumDriver<>(new URL("https://EPM-TSTF:" + tokenEncoded + "@mobilecloud.epam.com/wd/hub"), capabilities);
            } catch (MalformedURLException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                appiumDriver = new AppiumDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

    }

    protected void setPageObject(String appType, AppiumDriver<? extends WebElement> appiumDriver) throws Exception {
        page = new PageObject(appType, appiumDriver);
    }

}
