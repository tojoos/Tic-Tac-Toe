package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class main extends Application {

    @Override
    public void start(Stage mainStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("GameStage.fxml")));
        Parent root = loader.load();
        mainStage.setTitle("Tic-Tac-Toe");
        mainStage.getIcons().add(new Image(Objects.requireNonNull(main.class.getResourceAsStream("graphics\\icon.png"))));
        mainStage.setScene(new Scene(root));
        mainStage.setResizable(false);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
