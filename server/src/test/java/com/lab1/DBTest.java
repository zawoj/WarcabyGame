package com.lab1;

import com.Database.UserDataBase;
import com.Database.UserInformationPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DBTest {
    @Test
    public void test(){
        UserDataBase db = new UserDataBase();
        db.addUser(new UserInformationPackage("tl", "tp", 1));
        Assertions.assertEquals(1, db.getUserByLogin("tl").getAvatarNbr());
        db.changeAvatar("tl", 2);
        Assertions.assertEquals(2, db.getUserByLogin("tl").getAvatarNbr());
        Assertions.assertEquals("tp", db.getUserByLogin("tl").getPassword());
        db.updatePassword("tl", "tp2");
        Assertions.assertEquals("tp2", db.getUserByLogin("tl").getPassword());
        Assertions.assertTrue(db.checkIfUserInDatabase("tl"));
        db.dumpDatabase();
        Assertions.assertFalse(db.checkIfUserInDatabase("tl"));
    }
}
