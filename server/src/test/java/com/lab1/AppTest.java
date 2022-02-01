package com.lab1;

import static org.junit.Assert.*;

import com.server.App;
import com.server.ServerCore;
import javafx.application.Application;
import javafx.application.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
/**
 * Unit test for simple App.
 */
public class AppTest
{

    //this test can't be run with other test because there are two tests that launch the javaFX app which causes an Exception
//    @Test
//    public void window()
//    {
//        System.out.println("please close the window that will appear in a moment");
//        App.main(null);
//        Assertions.assertNotNull(ServerCore.getInstance().getController());
//    }
}
