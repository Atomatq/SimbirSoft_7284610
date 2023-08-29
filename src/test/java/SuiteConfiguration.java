import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.Properties;

public class SuiteConfiguration {
    private Properties properties;

    public SuiteConfiguration() throws IOException {
        properties = new Properties();
        properties.load(SuiteConfiguration.class.getResourceAsStream("application.properties"));
    }

    public Capabilities getCapabilities() throws IOException {
        String capabilitiesFile = properties.getProperty("capabilities");

        Properties capsProps = new Properties();
        capsProps.load(BaseTest.class.getResourceAsStream(capabilitiesFile));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        for (String name : capsProps.stringPropertyNames()) {
            String value = capsProps.getProperty(name);
            if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
                capabilities.setCapability(name, Boolean.valueOf(value));
            } else {
                capabilities.setCapability(name, value);
            }
        }

        return capabilities;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
