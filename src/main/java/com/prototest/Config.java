package com.prototest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


public class Config {

    private static Properties properties = new Properties();
    private URL url;
    private String host;
    private boolean BrowserIE;
    private boolean BrowserFirefox;
    private boolean BrowserChrome;
    private boolean BrowserSafari;
    private String outputPath;
    private int minWidthPix;
    private int maxWidthPix;
    private int stepSizePix;
    private int maxHeightPix;

    String propFileName = "config.properties";
    InputStream propertiesInputStream = Browsinator.class.getClassLoader().getResourceAsStream(propFileName);

    public Config() {

        //LOAD CONFIG.PROPERTIES FILE
        try {
            properties.load(propertiesInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        //LOAD CONFIG OPTIONS
        try {
            //URL
            String urlString = properties.getProperty("testUrl");
            if (urlString.matches("")) {
                throw new RuntimeException("URL cannot be null.");
            }
            if (!urlString.matches("https??://.*")) {
                url = new URL("http://" + urlString);
            } else {
                url = new URL(urlString);
            }

            //HOST
            String hostString = properties.getProperty("hostIP");
            if (hostString.matches("")) {
                throw new RuntimeException("Host cannot be null.");
            }
            if (!(hostString.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}") || hostString.matches("localhost"))) {
                throw new RuntimeException("Host is not a properly formatted IP address.");
            }
            host = new String(hostString);

            //BROWSERS
            String ieYes = properties.getProperty("IE");
            BrowserIE = Boolean.valueOf(ieYes);
            String firefoxYes = properties.getProperty("Firefox");
            BrowserFirefox = Boolean.valueOf(firefoxYes);
            String chromeYes = properties.getProperty("Chrome");
            BrowserChrome = Boolean.valueOf(chromeYes);
            String safariYes = properties.getProperty("Safari");
            BrowserSafari = Boolean.valueOf(safariYes);

            //OUTPUT PATH
            outputPath = properties.getProperty("fileOutputPath");

            //BROWSER SIZING
            minWidthPix = Integer.parseInt(properties.getProperty("minWidthPixels"));
            maxWidthPix = Integer.parseInt(properties.getProperty("maxWidthPixels"));
            stepSizePix = Integer.parseInt(properties.getProperty("stepSizePixels"));
            maxHeightPix = Integer.parseInt(properties.getProperty("maxHeightPixels"));
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public URL getUrl() {
        System.out.println("Loading Test URL: (" + url + ").");
        return url;
    }

    public String getHost() {
        System.out.println("Loading Test Host: (" + host + ").");
        return host;
    }

    public boolean getBrowserIE() {
        if(BrowserIE){
            System.out.println("Internet Explorer selected? (Yes).");
        }
        else {
            System.out.println("Internet Explorer selected? (No).");
        }
        return BrowserIE;
    }

    public boolean getBrowserFirefox() {
        if(BrowserFirefox){
            System.out.println("Firefox selected? (Yes).");
        }
        else {
            System.out.println("Firefox selected? (No).");
        }
        return BrowserFirefox;
    }

    public boolean getBrowserChrome() {
        if(BrowserChrome){
            System.out.println("Chrome selected? (Yes).");
        }
        else {
            System.out.println("Chrome selected? (No).");
        }
        return BrowserChrome;
    }

    public boolean getBrowserSafari() {
        if(BrowserSafari){
            System.out.println("Safari selected? (Yes).");
        }
        else {
            System.out.println("Safari selected? (No).");
        }
        return BrowserSafari;
    }

    public String getOutputPath() {
        System.out.println("Loading Files Output Path: (" + outputPath + ").");
        return outputPath;
    }

    public int getBrowserWidthMin() {
        System.out.println("Loading Minimum Browser Size: (" + minWidthPix + ").");
        return minWidthPix;
    }

    public int getBrowserWidthMax() {
        System.out.println("Loading Minimum Browser Size: (" + maxWidthPix + ").");
        return maxWidthPix;
    }

    public int getBrowserWidthStep() {
        System.out.println("Loading Minimum Browser Size: (" + stepSizePix + ").");
        return stepSizePix;
    }

    public int getBrowserHeight() {
        System.out.println("Loading Minimum Browser Size: (" + maxHeightPix + ").");
        return maxHeightPix;
    }
}
