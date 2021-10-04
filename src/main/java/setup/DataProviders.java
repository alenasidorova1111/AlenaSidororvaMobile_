package setup;

import entities.NativeAndroidTestData;
import entities.WebTestData;
import org.testng.annotations.DataProvider;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class DataProviders {

    @DataProvider(name = "dataProviderForNativeTest")
    public static Object[][] dataProviderForNativeTest() throws IOException {
        Reader reader = new FileReader("src/test/resources/testData/DataForNativeAndroidTests.json");
        NativeAndroidTestData data = new Gson().fromJson(reader, NativeAndroidTestData.class);

        return new Object[][]{
                {data.getEmail(), data.getUsername(), data.getPassword(), data.getPageTitle()},
        };
    }

    @DataProvider(name = "dataProviderForNativeIOSTest")
    public static Object[][] dataProviderForNativeIOSTest() throws FileNotFoundException {
        Reader reader = new FileReader("src/test/resources/testData/DataForNativeIOSTests.json");
        NativeAndroidTestData data = new Gson().fromJson(reader, NativeAndroidTestData.class);

        return new Object[][]{
                {data.getEmail(), data.getUsername(), data.getPassword(), data.getPageTitle()},
        };
    }

    @DataProvider(name = "dataProviderForWebTest")
    public static Object[][] dataProviderForWebTest() throws FileNotFoundException {
        Reader reader = new FileReader("src/test/resources/testData/DataForWebTests.json");
        WebTestData data = new Gson().fromJson(reader, WebTestData.class);

        return new Object[][]{
                {data.getURL(), data.getPageTitle(), data.getSearchPhrase()},
        };
    }
}
