package scenarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import setup.BaseTest;
import setup.DataProviders;

public class WebIOSTests extends BaseTest {

    @Test(description = "Google search result test",
            dataProvider = "dataProviderForWebTest", dataProviderClass = DataProviders.class)
    public void simpleWebTest(String url, String pageTitle, String searchPhrase) throws Exception {

        // Open page
        getDriver().get(url);

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 70).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        // Check page title
        assert ((WebDriver) getDriver()).getTitle().equals(pageTitle) : "This is not Google page";

        // Search
        getPage().getWebElement("searchField").sendKeys(searchPhrase);
        getPage().getWebElement("searchField").submit();

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 70).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        // Check that 2nd search result contains searchPhrase
        assert (getPage().getWebElements("searchResults").get(1).getText().contains(searchPhrase));
    }

}
