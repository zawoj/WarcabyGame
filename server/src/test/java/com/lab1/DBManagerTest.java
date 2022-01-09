package com.lab1;

import com.Database.UserDataBase;
import com.Database.UserInformationPackage;
import com.server.DataBaseManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

import java.io.IOException;


public class DBManagerTest {
    @Test
    public void test() throws IOException {
        DataBaseManager dbm = new DataBaseManager();
        UserDataBase b = Mockito.mock(UserDataBase.class);
        dbm.dataBase = b;
        Mockito.when(b.getUserByLogin("testLogin")).thenReturn(new UserInformationPackage("testLogin", "testPassword", 1));
        Assertions.assertEquals("testPassword", dbm.getUserByLogin("testLogin").getPassword());
        dbm.updatePassword("tl", "tp");
        Mockito.verify(b).updatePassword("tl", "tp");
        dbm.changeAvatar("tl", 6);
        Mockito.verify(b).changeAvatar("tl", 6);
        Mockito.when(b.checkIfUserInDatabase("tl")).thenReturn(true);
        Mockito.when(b.checkIfUserInDatabase("tfl")).thenReturn(false);
        Assertions.assertTrue(dbm.checkIfUserInDatabase("tl"));
        Assertions.assertFalse(dbm.checkIfUserInDatabase("tfl"));
        dbm.addUser("A", "B", 3);
        dbm.saveDB();
        Mockito.verify(b).save();
    }

}
