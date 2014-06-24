package com.prototest;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

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

    public static Browser runInternetExplorer() {
        return new Browser(new InternetExplorerDriver());
    }

    public static void runFirefox() {
        System.out.println("Launching Firefox...");
        config = new Config();
        new Browser(new FirefoxDriver());
        webDriver.get(config.getUrl().toString());
        //waitForAjax();
        takeScreenshotArray(FileSys.outputFolderFirefox.toPath());
        webDriver.quit();
    }

    public static Browser runChrome() {
        return new Browser(new ChromeDriver());
    }

    public static Browser runSafari() {
        return new Browser(new SafariDriver());
    }

    public static void waitForAjax() {
        System.out.println("Waiting for page to load...");
        while (true)
        {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            String script = "function ping() {document.readyState === \"complete\"; return true;} ping();";
            Boolean ajaxComplete = (Boolean) js.executeScript(script);
            if (ajaxComplete) {
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

    public static void takeScreenshotArray(Path path){
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
}
