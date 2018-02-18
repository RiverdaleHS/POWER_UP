package com.team2915.POWER_UP.util;

import java.io.*;

public class CSVUtilities {

    public CSVUtilities (){
    }

    public static void logCSVLine(String path, String line) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(path), true);
        PrintWriter pm = new PrintWriter(fos);
        pm.println(line);
        pm.close();
    }
}
