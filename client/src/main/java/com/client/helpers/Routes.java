package com.client.helpers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Routes {

    public static String imageRoute(String imgName) {
        File file = new File("src\\main\\java\\com\\client\\img\\" + imgName);

        return file.toURI().toString();

    }

    public static String styleRoute(String styleFileName) throws MalformedURLException {
        // Documentation css
        // https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html
        File file = new File("src\\main\\java\\com\\client\\styles\\" + styleFileName);

        return file.toURI().toString();

    }

    public static URL viewsRoute(String viewName) throws MalformedURLException {
        File file = new File("src\\main\\java\\com\\client\\views\\" + viewName);

        return file.toURI().toURL();

    }
}
