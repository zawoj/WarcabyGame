package com.helpers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * class for managing program files
 */
public class Routes {
    /**
     * route to views folder
     * @param viewName name of the view
     * @return url to view
     * @throws MalformedURLException
     */
    public static URL viewsRoute(String viewName) throws MalformedURLException {
        File file = new File("src\\main\\java\\com\\views\\" + viewName);
        return file.toURI().toURL();
    }
    /**
     * route to styles folder
     * @param styleFileName name of the style
     * @return url to style
     * @throws MalformedURLException
     */
    public static String styleRoute(String styleFileName) throws MalformedURLException {
        File file = new File("src\\main\\java\\com\\styles\\" + styleFileName);
        return file.toURI().toString();
    }
    /**
     * route to database folder
     * @param dbFileName name of the view
     * @return url to database
     * @throws MalformedURLException
     */
    public static File databaseRoute(String dbFileName) throws MalformedURLException {
        File file = new File("DB\\" + dbFileName);
        return file;
    }
}
