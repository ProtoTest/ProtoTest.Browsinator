package com.prototest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class Browsinator
{

    public static void main(String[] args) throws IOException {
        FileSys.createOutputFolder();
        FileSys.createBrowserFolders();
        Browser.runFirefox();

        System.out.println("EXECUTION COMPLETE.");
    }

}
