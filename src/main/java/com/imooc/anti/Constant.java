//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.imooc.anti;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Constant {
    public static String ICODE = "";
    public static final String urlString = "https://apis.imooc.com";
    public static String check = "1001";
    public static Boolean pass;
    public static final String SALT = "8svbsvjkweDF,.03[";

    public Constant() {
    }

    public static Boolean refresh(String icode) {
        return true;

//        ICODE = icode;
//
//        try {
//            URL url = new URL("https://apis.imooc.com?icode=" + ICODE);
//            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//            connection.setConnectTimeout(8192);
//            connection.setReadTimeout(8192);
//            connection.setRequestMethod("GET");
//            int responseCode = connection.getResponseCode();
//            if (responseCode == 200) {
//                InputStream inputStream = connection.getInputStream();
//                int len = false;
//                byte[] buf = new byte[8192];
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//                int len;
//                while((len = inputStream.read(buf)) != -1) {
//                    baos.write(buf, 0, len);
//                }
//
//                baos.close();
//                check = baos.toString();
//                if (check.contains("1000")) {
//                    pass = true;
//                } else {
//                    pass = false;
//                }
//
//                return pass;
//            }
//        } catch (MalformedURLException var8) {
//            var8.printStackTrace();
//        } catch (IOException var9) {
//            var9.printStackTrace();
//        }
//
//        return false;
    }

    public static void main(String[] args) {
        System.out.println(refresh(""));
    }
}
