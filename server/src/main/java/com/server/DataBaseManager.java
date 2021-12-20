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
        try{
            dataBase.load();
        }catch ( IOException | ClassNotFoundException e){
            ServerCore.getInstance().getController().append("could load user database, creating new one");
            dataBase.dumpDatabase();
        }
    }

    public void saveDB(){
        try{
            dataBase.save();
        } catch (IOException exception) {
            ServerCore.getInstance().getController().append("couldnt save user database");
        }
    }

    public UserInformationPackage getUserByLogin(String login){
        return dataBase.getUserByLogin(login);
    }
    public boolean checkIfUserInDatabase(String login){
        return dataBase.checkIfUserInDatabase(login);
    }
    public void updatePassword(String login, String newPassword){
        dataBase.updatePassword(login,newPassword);
    }
}
