package Data_Driven_Test;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import Data_Driven_Testing.LoginPage;
import Data_Driven_Testing.ExcelUtil;

import java.io.IOException;

public class LoginTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--disable-blink-features=AutomationControlled");
    	options.setExperimentalOption("useAutomationExtension", false);
    	options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

    	driver = new ChromeDriver(options);
    	driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    	driver.manage().window().maximize();
       
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
