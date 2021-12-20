package com.Database;

import java.io.Serializable;

public class UserInformationPackage implements Serializable {
    private String login;
    private String password;
    private int avatarNbr;
    UserInformationPackage(String login, String password, int avatar){
        this.login=login;
        this.password=password;
        this.avatarNbr=avatar;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAvatarNbr() {
        return avatarNbr;
    }

    public void setAvatarNbr(int avatarNbr) {
        this.avatarNbr = avatarNbr;
    }


}
