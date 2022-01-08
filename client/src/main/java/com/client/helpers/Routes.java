package com.client.helpers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Routes {

    public static String imageRoute(String imgName) {
        if (imgName == null || imgName.trim().length() == 0) {
            throw new NullPointerException(
                    "Cannot find image when route is empty string");
        }

        File file = new File("src\\main\\java\\com\\client\\img\\" + imgName);
        return file.toURI().toString();

    }

    public static String styleRoute(String styleFileName) throws MalformedURLException {
        if (styleFileName == null || styleFileName.trim().length() == 0) {
            throw new NullPointerException(
                    "Cannot find styleFileName with empty string");
        }
        File file = new File("src\\main\\java\\com\\client\\styles\\" + styleFileName);
        return file.toURI().toString();

    }

    public static URL viewsRoute(String viewName) throws MalformedURLException {
        if (viewName == null || viewName.trim().length() == 0) {
            throw new NullPointerException(
                    "Cannot find viewName with empty string");
        }

        File file = new File("src\\main\\java\\com\\client\\views\\" + viewName);
        return file.toURI().toURL();
    }
}
