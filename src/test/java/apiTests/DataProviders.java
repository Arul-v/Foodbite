package apiTests;

import org.testng.annotations.DataProvider;

public class DataProviders extends BaseClass{
    @DataProvider(name = "validDataForLogin")
    public static Object[][] validDataForLogin() {
        return new Object[][]{
                {"gowthamraj@gmail.com", "test@"}
        };
    }
}