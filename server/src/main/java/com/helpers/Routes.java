package com.helpers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Routes {

    public static URL viewsRoute(String viewName) throws MalformedURLException {
        File file = new File("src\\main\\java\\com\\views\\" + viewName);
        return file.toURI().toURL();
    }

    public static String styleRoute(String styleFileName) throws MalformedURLException {
        File file = new File("src\\main\\java\\com\\styles\\" + styleFileName);
        return file.toURI().toString();
    }

    public static File databaseRoute(String dbFileName) throws MalformedURLException {
        File file = new File("DB\\" + dbFileName);
        return file;
    }
}
