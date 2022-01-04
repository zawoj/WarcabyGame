package com.board;

//TODO: do wyjebania
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;

public class AppWindow extends Application {
    static Group group = new Group();

    @Override
    public void start(Stage stage) {
        try {
            ChineseCheckersBoard board = new ChineseCheckersBoardBuilder().setSize(5).setNumberOfPlayers(6).build();
            board.printInTerminal();
            ChineseCheckersBoardAdapter BoardAdapter = new ChineseCheckersBoardAdapter(board);
            Field[][] fields = BoardAdapter.getFields();
            for (Field[] a : fields) {
                for (Field c : a) {
                    if (c != null)
                        group.getChildren().add(c);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.show();

    }

    public static Group getBoard() {
        return group;
    }

    public static void launchApp() {
        launch();
    }
}
