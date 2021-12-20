package com.server;

import com.Database.UserDataBase;
import com.Database.UserInformationPackage;

import java.io.IOException;

/**
 * class that manages the player database
 */
public class DataBaseManager {
    UserDataBase dataBase;

    DataBaseManager() {
        dataBase = new UserDataBase();
        try {
            dataBase.load();
        } catch (IOException | ClassNotFoundException e) {
            ServerCore.getInstance().getController().append("couldn't load user database, creating new one");
            dataBase.dumpDatabase();
        }
    }

    public void saveDB() {
        try {
            dataBase.save();
        } catch (IOException exception) {
            ServerCore.getInstance().getController().append("couldnt save user database");
        }
    }

    public void addUser(String login, String password, int avatar) {
        dataBase.addUser(new UserInformationPackage(login, password, avatar));
    }

    public UserInformationPackage getUserByLogin(String login) {
        return dataBase.getUserByLogin(login);
    }

    public boolean checkIfUserInDatabase(String login) {
        return dataBase.checkIfUserInDatabase(login);
    }

    public void updatePassword(String login, String newPassword) {
        dataBase.updatePassword(login, newPassword);
    }

    public void changeAvatar(String login, int newAvatar) {
        dataBase.changeAvatar(login, newAvatar);
    }
}
