package driver;

import org.apache.commons.lang3.BooleanUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariDriverService;
import org.openqa.selenium.safari.SafariOptions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.Thread.currentThread;
import static java.util.Objects.isNull;

public class SafariDriverManagerImpl extends DriverManager {

    @Override
    public void startService() {
        if (isNull(driverService.get())) {
            driverService.set(new SafariDriverService.Builder().usingAnyFreePort().build());
            try {
                driverService.get().start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected WebDriver createDriver() {
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, PageLoadStrategy.NORMAL);
        driverMap.put(getCurrentThreadId(), new SafariDriver((SafariDriverService) driverService.get(), safariOptions));
        driverMap.get(getCurrentThreadId()).manage().window().maximize();
        return driverMap.get(getCurrentThreadId());
    }

    @Override
    public WebDriver createDriver(String url) {
        DesiredCapabilities capabilities = DesiredCapabilities.safari();
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, PageLoadStrategy.NORMAL);
        capabilities.setCapability("enableVNC", BooleanUtils.toBoolean(System.getProperty("vnc", "false")));
        try {
            driverMap.put(currentThread().getId(), new RemoteWebDriver(new URL(url), capabilities));
            maximizeScreenResolution();
        } catch (MalformedURLException ignored) {
        }
        return driverMap.get(getCurrentThreadId());
    }
}