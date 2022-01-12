package com.client.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import com.client.ClientCore;
import com.client.helpers.Routes;
import com.client.helpers.exceptions.StringLengthException;
import com.client.helpers.exceptions.StringSameValidation;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Class responsible for controlling the layout of the login screen and the
 * functionality.
 */
public class RegisteryController implements Initializable {

    @FXML
    private Button btnBack, buttonCreateAccount;
    @FXML
    private TextField newLogin, newPassword, checkNewPassword;
    @FXML
    Pane errorRegisteryNewAccount, errorPanePassword, accountCreatedSuccesfully, errorPanePasswordValidation,
            avatarError, errorSomethinkWrong, errorrThisLoginIsToShort;
    @FXML
    private ComboBox<String> avatarChoiceBox;
    @FXML
    private ImageView avatarImage;

    private static Stage stage;
    private static Parent root;
    private static final ObservableList<String> avatarId = FXCollections.observableArrayList("Avatar 1",
            "Avatar 2",
            "Avatar 3", "Avatar 4", "Avatar 5", "Avatar 6", "Avatar 7", "Avatar 8", "Avatar 9", "Avatar 10",
            "Avatar 11", "Avatar 12", "Avatar 13", "Avatar 14", "Avatar 15", "Avatar 16", "Avatar 17", "Avatar 18",
            "Avatar 19", "Avatar 20", "Avatar 21", "Avatar 22", "Avatar 23", "Avatar 24");

    /**
     * Method for initialize controller and send it to server
     * 
     * @param url            users url
     * @param resourceBundle users resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        avatarChoiceBox.setItems(avatarId);
        avatarChoiceBox.setValue("Choose avatar");
        ClientCore.getInstance().setRegisteryController(this);
    }

    private boolean allCorect = false;

    /**
     * Method which controlling registration of new users.
     * Checking if user is already registered
     * Checking if possword is enough length and checkNewPassword
     * Checking if user login length is enough
     * Checking if user choose any avatar
     * TimeUnit is needed to wait for server respond
     * 
     * @param e users actionEvent
     * @throws Exception
     * @throws StringSameValidation
     * @throws NumberFormatException
     */
    @FXML
    private void CreateAccountButtonController(ActionEvent e)
            throws NumberFormatException, StringSameValidation, Exception {

        if (e.getSource() == buttonCreateAccount) {
            if (loginValidation(newLogin.getText().toString())) {
                if (passwordValidation(newPassword.getText())) {
                    if (passwordCheckValidation(newPassword.getText().toString(),
                            checkNewPassword.getText().toString())) {
                        if (avatatValidation(avatarChoiceBox.getValue())) {
                            ClientCore.getInstance().reqCreateNewAccount(newLogin.getText(), newPassword.getText(),
                                    Integer.parseInt(avatarChoiceBox.getValue().split(" ")[1]));
                            TimeUnit.MILLISECONDS.sleep(20);
                            if (getAllCorrect()) {
                                cleanForm();
                            }
                        }
                    }
                }
            }

            if (e.getSource() == btnBack) {
                LoadLoingLauncher();
            }

        }

    }

    /**
     * Method to close the error pane
     * 
     * @param e users actionEvent
     */
    @FXML
    private void errorPaneLoginExistButton(ActionEvent e) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(errorRegisteryNewAccount);
        transition.setToX(0);
        transition.play();
    }

    /**
     * Method to close the error pane
     * 
     * @param e users actionEvent
     */
    @FXML
    private void errorPanePasswordButton(ActionEvent e) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(errorPanePassword);
        transition.setToX(0);
        transition.play();
    }

    /**
     * Method to close the error pane
     * 
     * @param e users actionEvent
     */
    @FXML
    private void accountCreatedSuccesfullyButton(ActionEvent e) {
        TranslateTransition transition = new TranslateTransition();
        accountCreatedSuccesfully.setVisible(false);
        transition.setNode(accountCreatedSuccesfully);
        transition.setToX(0);
        transition.play();
    }

    /**
     * Method to close the error pane
     * 
     * @param e users actionEvent
     */
    @FXML
    private void errorLoginValidation(ActionEvent e) {
        TranslateTransition transition = new TranslateTransition();
        accountCreatedSuccesfully.setVisible(false);
        transition.setNode(errorrThisLoginIsToShort);
        transition.setToX(0);
        transition.play();
    }

    /**
     * Method to close the error pane
     * 
     * @param e users actionEvent
     */
    @FXML
    private void errorPasswordValidtionButtonController(ActionEvent e) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(errorPanePasswordValidation);
        transition.setToX(0);
        transition.play();
    }

    /**
     * Method to close the error pane
     * 
     * @param e users actionEvent
     */
    @FXML
    private void errorSomethinkWrongButton(ActionEvent e) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(errorSomethinkWrong);
        transition.setToX(0);
        transition.play();
    }

    /**
     * Method to close the error pane
     * 
     * @param e users actionEvent
     */
    @FXML
    private void errorAvaratValidtionButtonController(ActionEvent e) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(avatarError);
        transition.setToX(0);
        transition.play();
    }

    /**
     * Method to close the error pane
     * 
     * @param e users actionEvent
     * @throws IOException throws if an error occurs
     */
    @FXML
    private void backButtonController(ActionEvent e) throws IOException {
        LoadLoingLauncher();
    }

    /**
     * Method which show which avatar is selected
     * 
     * @throws FileNotFoundException throws if an error occurs
     */
    @FXML
    private void changeAvatarPreview() throws FileNotFoundException {
        Image avatarImagePreview = new Image(Routes.imageRoute(
                "avatars\\" + avatarChoiceBox.getValue().replaceAll(" ", "") +
                        ".png"));
        avatarImage.setImage(avatarImagePreview);

    }

    /**
     * Method is invokes when account is creat succesfully, and clean the form
     * 
     * 
     */
    public void accountCreatedSuccesfullyNotification() {
        TranslateTransition transition = new TranslateTransition();
        accountCreatedSuccesfully.setVisible(true);
        transition.setNode(accountCreatedSuccesfully);
        transition.setToX(-300);
        transition.play();

    }

    /**
     * Method clean the form
     * 
     * 
     */
    private void cleanForm() {
        newLogin.setText("");
        newPassword.setText("");
        checkNewPassword.setText("");
        avatarChoiceBox.setValue("Choose avatar");
    }

    /**
     * Method which invokes when login is taken
     */
    public void errorNotification() {
        TranslateTransition transition = new TranslateTransition();
        accountCreatedSuccesfully.setVisible(true);
        transition.setNode(errorRegisteryNewAccount);
        transition.setToX(-300);
        transition.play();
    }

    /**
     * Method which invokes when any error
     */
    public void errorSomethinkWrong() {
        TranslateTransition transition = new TranslateTransition();
        accountCreatedSuccesfully.setVisible(true);
        transition.setNode(errorSomethinkWrong);
        transition.setToX(-300);
        transition.play();
    }

    /**
     * Method which load Login view
     * 
     * @throws IOException throws if an error occurs
     */
    private void LoadLoingLauncher() throws IOException {
        stage = (Stage) buttonCreateAccount.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("LoginIntoLauncher.fxml"));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method also invokes the error informationS
     * 
     * @param password users new password
     * @return Boolean true when password is longer than 4
     * @throws StringLengthException throws if password is length isn't 4 length at
     *                               least
     */
    public boolean passwordValidation(String password) throws StringLengthException {
        if (password.length() > 4) {
            return true;
        } else {
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(errorPanePasswordValidation);
            transition.setToX(-300);
            transition.play();
            return false;
        }
    }

    /**
     * Method also invokes the error information
     * 
     * @param password      users new password
     * @param checkPassword users new password check
     * @return boolean true when passwords is same
     * @throws StringSameValidation throws if passwords isn't the same
     */
    public boolean passwordCheckValidation(String password, String checkPassword) throws StringSameValidation {
        if (password.equals(checkPassword)) {
            return true;
        } else {
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(errorPanePassword);
            transition.setToX(-300);
            transition.play();
            return false;
        }
    }

    /**
     * Method also invokes the error information
     * 
     * @param string name of the avatar
     * @return boolean true when avatar is choosen
     */
    private boolean avatatValidation(String string) {
        if (string != "Choose avatar") {
            return true;
        } else {
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(avatarError);
            transition.setToX(-300);
            transition.play();
            return false;
        }
    }

    /**
     * Method also invokes the error information
     * 
     * @param string name of the lgoin
     * @return boolean true when password is longer than zero length
     */
    private boolean loginValidation(String string) {

        if (string.length() > 0) {
            return true;
        } else {
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(errorrThisLoginIsToShort);
            transition.setToX(-300);
            transition.play();
            return false;
        }
    }

    /**
     * 
     * Setter for that is user created succesfully or not
     */
    public void setAllCorrect(boolean userIsCreated) {
        allCorect = userIsCreated;
    }

    /**
     * 
     * Getter for that is user created succesfully or not
     */
    private boolean getAllCorrect() {
        return allCorect;
    }

}
