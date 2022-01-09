package com.lab1;

import com.Database.UserInformationPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UIPTest {
    @Test
    public void test(){
        UserInformationPackage uip = new UserInformationPackage("tl", "tp", 1);
        Assertions.assertEquals("tl", uip.getLogin());
        Assertions.assertEquals("tp", uip.getPassword());
        Assertions.assertEquals(1, uip.getAvatarNbr());
        uip.setAvatarNbr(2);
        uip.setPassword("tp2");
        uip.setLogin("tl2");
        Assertions.assertEquals("tl2", uip.getLogin());
        Assertions.assertEquals("tp2", uip.getPassword());
        Assertions.assertEquals(2, uip.getAvatarNbr());
    }
}
