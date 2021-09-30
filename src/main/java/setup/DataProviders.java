package setup;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "dataProviderForNativeTest")
    public static Object[][] dataProviderForNativeTest() {
        String email = "sid@gmail.com";
        String username = "Sid";
        String password = "passSid12345";
        String pageTitle = "BudgetActivity";

        return new Object[][]{
                {email, username, password, pageTitle},
        };
    }

    @DataProvider(name = "dataProviderForWebTest")
    public static Object[][] dataProviderForWebTest() {
        String URL = "https://www.google.com/";
        String pageTitle = "Google";
        String searchPhrase = "EPAM";
        String appType = "web";

        return new Object[][]{
                {appType, URL, pageTitle, searchPhrase},
        };
    }

}
