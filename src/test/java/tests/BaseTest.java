package tests;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium_core.DriverManager;
import selenium_core.DriverManagerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wdWait;
    DriverManager driverManager;
    String ctm = String.valueOf(System.currentTimeMillis());

    public void init(String browser, String version, int wait) {
        driverManager = DriverManagerFactory.getDriverManager(browser);
        this.driver = driverManager.getWebDriver(version);
        this.driver.manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);

        System.out.println("THREAD_ID: " + Thread.currentThread().getId());
        System.out.println("CHROME_ID: " + ((ChromeDriver) driver).getSessionId());
    }

    public void quitDriver() {
        driverManager.quitWebDriver();
    }

    public void takeScreenshot(String fileName) throws IOException {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/screenshot/" + fileName + "_" + ctm + ".png"));
    }

    public void reportScreenshot(String fileName, String allureName) throws IOException {
        takeScreenshot(fileName);
        Path content = Paths.get("src/screenshot/" + fileName + "_" + ctm + ".png");

        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment(allureName, is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
