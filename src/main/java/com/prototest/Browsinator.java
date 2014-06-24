package com.prototest;

import java.io.IOException;


public class Browsinator
{

    public static void main(String[] args) throws IOException {

        FileSys.createOutputFolder();
        FileSys.createBrowserFolders();
        Browser.runInternetExplorer();
        Browser.runFirefox();
        Browser.runChrome();

        System.out.println("EXECUTION COMPLETE.");
    }

}
