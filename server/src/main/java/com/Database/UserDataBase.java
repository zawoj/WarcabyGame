package com.Database;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

import com.helpers.Routes;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserDataBase implements Serializable {
    private LinkedList<UserInformationPackage> users = new LinkedList<>();

    public void addUser(UserInformationPackage userToAdd) {
        users.add(userToAdd);
    }

    public UserInformationPackage getUserByLogin(String login) {
        for (UserInformationPackage uip : users) {
            if (uip.getLogin().equals(login)) {
                return uip;
            }
        }
        return null;
    }

    public boolean checkIfUserInDatabase(String login) {
        for (UserInformationPackage uip : users) {
            if (uip.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public void updatePassword(String login, String newPassword) {
        for (UserInformationPackage uip : users) {
            if (uip.getLogin().equals(login)) {
                uip.setPassword(newPassword);
            }
        }
    }

    public void save() throws IOException {
        JSONObject mainObject = new JSONObject();

        for (UserInformationPackage uip : users) {
            JSONObject userInfo = new JSONObject();
            userInfo.put("Login", uip.getLogin());
            userInfo.put("Password", uip.getPassword());
            userInfo.put("Avatar", uip.getAvatarNbr());
            JSONArray userLogin = new JSONArray();
            userLogin.put(userInfo);

            mainObject.append("Users", userInfo);
        }
        try (FileWriter Data = new FileWriter(Routes.databaseRoute("Users.json"))) {
            Data.write(mainObject.toString());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void load() {
        try {
            String contents = new String((Files.readAllBytes(Path.of(Routes.databaseRoute("Users.json").toURI()))));
            JSONObject JSONfilObject = new JSONObject(contents);
            JSONArray usersJsonArray = JSONfilObject.getJSONArray("Users");

            for (int i = 0; i < usersJsonArray.length(); i++) {
                JSONObject user = usersJsonArray.getJSONObject(i);
                users.add(new UserInformationPackage((String) user.get("Login"), (String) user.get("Password"),
                        (Integer) user.get("Avatar")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void changeAvatar(String login, int newAvatar) {
        for (UserInformationPackage uip : users) {
            if (uip.getLogin().equals(login)) {
                uip.setAvatarNbr(newAvatar);
            }
        }
    }

    public void dumpDatabase() {
        users = new LinkedList<>();
    }
}
