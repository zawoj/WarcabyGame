package com.Database;

import java.io.Serializable;

/**
 * information about user
 */
public class UserInformationPackage implements Serializable {
    private String login;
    private String password;
    private int avatarNbr;

    /**
     * creates information
     * 
     * @param login    users login
     * @param password users password
     * @param avatar   users avatar
     */
    public UserInformationPackage(String login, String password, int avatar) {
        this.login = login;
        this.password = password;
        this.avatarNbr = avatar;
    }

    /**
     * return login
     * 
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * sets login
     * 
     * @param login login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * returns password
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password
     * 
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * returns avatar
     * 
     * @return avatar number
     */
    public int getAvatarNbr() {
        return avatarNbr;
    }

    /**
     * sets avatar
     * 
     * @param avatarNbr avatar number
     */
    public void setAvatarNbr(int avatarNbr) {
        this.avatarNbr = avatarNbr;
    }

}
