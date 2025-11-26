package Data_Driven_Test;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Data_Driven_Testing.Browser;
import Data_Driven_Testing.ExcelUtil;
import Data_Driven_Testing.LoginPage;
import Data_Driven_Testing.ConfigReader; // Import the new utility

public class LoginTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        // 1. Get Browser from properties (Optional, but best practice)
        // If you want to keep hardcoded "WindowChrome", you can, but this is better:
        String browserName = ConfigReader.getProperty("browser"); 
        
        // 2. Initialize driver
        driver = Browser.browsersetup(browserName != null ? browserName : "WindowChrome");

        // 3. Get URL from properties file instead of hardcoding
        String appUrl = ConfigReader.getProperty("URL");
        driver.get(appUrl);
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        return ExcelUtil.getExcelData("src/test/resources/Test_Data/Login.xlsx", "Sheet1");
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}