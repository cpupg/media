package com.sheepfly.media;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class App {
    public static void main(String[] args) throws Exception {
        String dir = "src/main/resources";
        String path = dir + File.separator + "configs" + File.separator + "jpa.xml";
        System.out.println(path);
        InputStream is = new FileInputStream(path);
        int available = is.available();
        System.out.println(available);
    }
}
