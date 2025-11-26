package Data_Driven_Testing;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class Browser {

    public static WebDriver browsersetup(String browser) {

        WebDriver driver;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        if (browser.equals("WindowChrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }
        else if (browser.equals("WindowEdge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else if (browser.equals("WindowFirefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else {
            throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        driver.manage().window().maximize();
        return driver;
    }
}
