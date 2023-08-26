import org.junit.jupiter.api.*;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.TestInstance.*;


@TestInstance(Lifecycle.PER_CLASS)
public class BaseTest {
    protected WebDriver webDriver;
    protected Capabilities capabilities;
    protected SuiteConfiguration config;
    protected URL grid;

    @BeforeAll
    public void loadConfig() throws IOException {
        config = new SuiteConfiguration();
        capabilities = config.getCapabilities();
        grid = new URL(config.getValue("grid"));
    }


    @BeforeEach
    public void init() {
        webDriver = new RemoteWebDriver(grid, capabilities);
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        webDriver.manage().window().maximize();
        webDriver.get(config.getValue("base.url"));
    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }
}
