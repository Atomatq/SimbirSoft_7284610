import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.config.BaseConfig;
import org.example.config.ConfigManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.TestInstance.*;


@TestInstance(Lifecycle.PER_CLASS)
public class BaseTest {
    protected WebDriver webDriver;
    protected BaseConfig config = ConfigManager.getConfig();

    @BeforeAll
    public void loadConfig() {

    }


    @BeforeEach
    public void init() throws MalformedURLException {
//        webDriver = WebDriverManager.chromedriver().driverVersion("109.0.5414.74").create();
        ChromeOptions options = new ChromeOptions();
        webDriver = new RemoteWebDriver(new URL("http://localhost:4444/"), options);
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        webDriver.manage().window().maximize();
        webDriver.get(config.baseURL());
    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }
}
