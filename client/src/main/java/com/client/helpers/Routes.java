package com.client.helpers;

import java.io.File;


public class Routes {
    
    public String imageRoute(String imgName){
        File file = new File("G:\\WarcabyGame\\client\\src\\img\\",imgName);

        return file.toURI().toString();
        
    }
}
