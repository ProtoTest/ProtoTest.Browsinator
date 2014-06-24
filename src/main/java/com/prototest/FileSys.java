package com.prototest;

import java.io.File;
import java.text.SimpleDateFormat;


public class FileSys {

    public static Config config;
    public static File outputFolderMain;
    public static File outputFolderIE;
    public static File outputFolderFirefox;
    public static File outputFolderChrome;
    public static File outputFolderSafari;


    public static void createOutputFolder(){

        config = new Config();
        String path = config.getOutputPath();
        String separator = System.getProperty("file.separator");
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH_mm");
        String timestamp = sdf.format(date);
        String folderName = "Screenshots " + timestamp;

        File outputDir = new File(path + separator + folderName);
        System.out.println("Creating screenshots output folder: (" + outputDir + ").");
        outputDir.mkdirs();
        outputFolderMain = outputDir;
    }

    public static void createBrowserFolders(){
        config = new Config();
        String separator = System.getProperty("file.separator");
        if (config.getBrowserIE()){
            File outputDirIE = new File(outputFolderMain + separator + "Internet Explorer");
            System.out.println("Creating screenshots output subfolder for Internet Explorer...");
            outputDirIE.mkdirs();
            outputFolderIE = outputDirIE;
        }
        if (config.getBrowserFirefox()){
            File outputDirFirefox = new File(outputFolderMain + separator + "Firefox");
            System.out.println("Creating screenshots output subfolder for Firefox...");
            outputDirFirefox.mkdirs();
            outputFolderFirefox = outputDirFirefox;
        }
        if (config.getBrowserChrome()){
            File outputDirChrome = new File(outputFolderMain + separator + "Chrome");
            System.out.println("Creating screenshots output subfolder for Chrome...");
            outputDirChrome.mkdirs();
            outputFolderChrome = outputDirChrome;
        }
        if (config.getBrowserSafari()){
            File outputDirSafari = new File(outputFolderMain + separator + "Safari");
            System.out.println("Creating screenshots output subfolder for Safari...");
            outputDirSafari.mkdirs();
            outputFolderSafari = outputDirSafari;
        }
    }
}
