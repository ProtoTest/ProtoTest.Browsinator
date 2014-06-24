package com.prototest;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;


public class Browser {

    public static Config config;
    private static WebDriver webDriver;

    public Browser (WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public static void runInternetExplorer() {
        System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer32.exe");
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        ieCapabilities.setCapability("ignoreZoomSetting", true);
        config = new Config();
        if (config.getBrowserIE()) {
            System.out.println("Launching Internet Explorer...");
            new Browser(new InternetExplorerDriver(ieCapabilities));
            resizeWindow();
            webDriver.get(config.getUrl().toString());
            waitForPageToLoad();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            takeScreenshotIE(FileSys.outputFolderIE.toPath());
            webDriver.quit();
            //webDriver.close();  >>> Unable to get browser error - DO NOT USE

        }
    }

    public static void runFirefox() {
        config = new Config();
        if (config.getBrowserFirefox()) {
            System.out.println("Launching Firefox...");
            new Browser(new FirefoxDriver());
            resizeWindow();
            webDriver.get(config.getUrl().toString());
            waitForPageToLoad();
            takeScreenshot(FileSys.outputFolderFirefox.toPath());
            webDriver.quit();
        }
    }

    public static void runChrome() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        config = new Config();
        if (config.getBrowserChrome()) {
            System.out.println("Launching Chrome...");
            new Browser(new ChromeDriver());
            resizeWindow();
            webDriver.get(config.getUrl().toString());
            waitForPageToLoad();
            takeScreenshot(FileSys.outputFolderChrome.toPath());
            webDriver.quit();
        }
    }

    public static Browser runSafari() {
        return new Browser(new SafariDriver());
    }

    public static void waitForPageToLoad() {
        System.out.println("Waiting for page to load...");
        while (true)
        {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            //String script = "function ping() {document.readyState === \"complete\"; return true;} ping();";
            String script = "return document.readyState === \"complete\";";

            Boolean loaded = (Boolean) js.executeScript(script);
            if (loaded) {
                break;
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Page should have loaded...");
    }

    public static void resizeWindow(){
        webDriver.manage().window().maximize();
        ((JavascriptExecutor) webDriver).executeScript("window.focus();");
    }

    public static void takeScreenshot(Path path){
        System.out.println("Capturing screenshot...");

        String separator = System.getProperty("file.separator");
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH_mm_ss");
        String timestamp = sdf.format(date);

        File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(path + separator + "Screenshot " + timestamp + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void takeScreenshotIE(Path path){
        System.out.println("Capturing screenshot...");

        String separator = System.getProperty("file.separator");
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH_mm_ss");
        String timestamp = sdf.format(date);

        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture = null;
        try {
            capture = new Robot().createScreenCapture(screenRect);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(capture, "png", new File(path + separator + "Screenshot " + timestamp + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
