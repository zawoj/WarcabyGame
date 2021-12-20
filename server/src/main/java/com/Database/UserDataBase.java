package com.Database;

import java.io.*;
import java.util.LinkedList;

public class UserDataBase implements Serializable {
    private LinkedList<UserInformationPackage> users = new LinkedList<>();
    public void addUser(UserInformationPackage userToAdd){
        users.add(userToAdd);
    }
    public UserInformationPackage getUserByLogin(String login){
        for(UserInformationPackage uip : users){
            if(uip.getLogin().equals(login)){
                return uip;
            }
        }
        return null;
    }
    public boolean checkIfUserInDatabase(String login){
        for(UserInformationPackage uip : users){
            if(uip.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }
    public void updatePassword(String login, String newPassword){
        for(UserInformationPackage uip : users){
            if(uip.getLogin().equals(login)){
                uip.setPassword(newPassword);
            }
        }
    }
    public void save() throws IOException {
        File save = new File("save.txt");
        FileOutputStream fos = new FileOutputStream(save);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(users);
    }
    public void load() throws IOException, ClassNotFoundException {
        File save = new File("save.txt");
        FileInputStream fis = new FileInputStream(save);
        ObjectInputStream ois = new ObjectInputStream(fis);
        users = (LinkedList<UserInformationPackage>) ois.readObject();
    }
    public void dumpDatabase(){
        users = new LinkedList<>();
    }
}
