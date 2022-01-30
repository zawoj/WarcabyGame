package com.server;

import com.Database.UserDataBase;
import com.Database.UserInformationPackage;

import java.io.IOException;

/**
 * class that manages the player database
 */


public class DataBaseManager {
    public UserDataBase dataBase;


    /**
     * creates manager and loads database
     */
    public DataBaseManager() {
        dataBase = new UserDataBase();
        dataBase.load();
    }

    /**
     * saves database
     */
    public void saveDB() {
        try {
            dataBase.save();
        } catch (IOException exception) {
            ServerCore.getInstance().getController().append("couldnt save user database");
        }
    }

    /**
     * adds new user to database
     * 
     * @param login    users login
     * @param password users password
     * @param avatar   users avatar
     */
    public void addUser(String login, String password, int avatar) {
        dataBase.addUser(new UserInformationPackage(login, password, avatar));
    }

    /**
     * returns user by login
     * 
     * @param login users login
     * @return users information
     */
    public UserInformationPackage getUserByLogin(String login) {
        return dataBase.getUserByLogin(login);
    }

    /**
     * checks if user is in database
     * 
     * @param login users login
     * @return true if user in database false otherwise
     */
    public boolean checkIfUserInDatabase(String login) {
        return dataBase.checkIfUserInDatabase(login);
    }

    /**
     * updates users password
     * 
     * @param login       users login
     * @param newPassword users new password
     */
    public void updatePassword(String login, String newPassword) {
        dataBase.updatePassword(login, newPassword);
    }

    /**
     * changes users avatar
     * 
     * @param login     users login
     * @param newAvatar users new avatar
     */
    public void changeAvatar(String login, int newAvatar) {
        dataBase.changeAvatar(login, newAvatar);
    }

}
