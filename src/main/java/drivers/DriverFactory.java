package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory
{
    public static WebDriver getNewInstance(String browserName)
    {
        ChromeOptions chromeOptions;
        switch (browserName.toLowerCase()) {
            case "chrome-headless":
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("window-size=1920,1080");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--remote-debugging-port=9222");
                return new ChromeDriver(chromeOptions);
            case "firefox":
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.download.dir", System.getProperty("user.dir") + "\\sources");
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
                FirefoxOptions options = new FirefoxOptions();
                options.setProfile(profile);
                return new FirefoxDriver(options);
            case "firefox-headless":
                FirefoxBinary firefoxBinary = new FirefoxBinary();
                firefoxBinary.addCommandLineOptions("--headless");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary(firefoxBinary);
                firefoxOptions.addArguments("--window-size=1920,1080");
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.addArguments("--disable-gpu");
                firefoxOptions.addArguments("--disable-dev-shm-usage");
                return new FirefoxDriver(firefoxOptions);
            case "edge":
                return new EdgeDriver();
            case "safari":
                return new SafariDriver();
            default:
                chromeOptions = new ChromeOptions();
                // TODO: handle browsers options
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.default_content_setting_values.notifications", 2);
                prefs.put("download.default_directory", System.getProperty("user.dir") + "\\sources");
                prefs.put("download.prompt_for_download", false);  // Don't prompt for downloads
                prefs.put("download.directory_upgrade", true);     // Allow download location upgrades
                prefs.put("safebrowsing.enabled", true);
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                chromeOptions.setExperimentalOption("prefs", prefs);
                chromeOptions.addArguments("download.default_directory=" + System.getProperty("user.dir") + "\\sources");
                chromeOptions.addArguments("--disable-extensions");// Disable safe browsing to allow all downloads
//                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("--disable-web-security");
                chromeOptions.addArguments("--no-proxy-server");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-notifications");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                capabilities.setCapability(CapabilityType.ENABLE_DOWNLOADS, true);

                chromeOptions.merge(capabilities);

                return new ChromeDriver(chromeOptions);
        }
    }
}
