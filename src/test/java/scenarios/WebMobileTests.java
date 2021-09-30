package scenarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import setup.BaseTest;
import setup.DataProviders;

public class WebMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Google search result test",
            dataProvider = "dataProviderForWebTest", dataProviderClass = DataProviders.class)
    public void simpleWebTest(String appType, String url, String pageTitle, String searchPhrase) throws Exception {

        // Open page
        getDriver().get(url);

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 20).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        // Check page title
        assert ((WebDriver) getDriver()).getTitle().equals(pageTitle) : "This is not Google page";

        // Set opened page
        setPageObject(appType, getDriver());

        // Check that 1st search result contains searchPhrase
        getPage().getWebElement("searchField").sendKeys(searchPhrase, Keys.ENTER);
        System.out.println(getPage().getWebElements("searchResults").get(0).getText());
        assert (getPage().getWebElements("searchResults").get(0).getText().contains(searchPhrase));
    }

}
