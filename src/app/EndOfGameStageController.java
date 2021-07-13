package app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class EndOfGameStageController {
    private Stage gameStage;

    @FXML
    private Text playAgainField, winnerField, exitField;

    public void setWinner(char winner) {
        if(winner == 'O' || winner == 'X') {
            winnerField.setText(winner + " is a Winner!");
        } else {
            winnerField.setText("Tie!");
        }
    }

    public void transferGameStage(Stage gameStage) {
        this.gameStage = gameStage;
    }

    @FXML
    private void onPlayAgainFieldEntered() {
        playAgainField.underlineProperty().setValue(true);
    }

    @FXML
    private void onPlayAgainFieldExited() {
        playAgainField.underlineProperty().setValue(false);
    }

    @FXML
    private void onExitFieldEntered() {
        exitField.underlineProperty().setValue(true);
    }

    @FXML
    private void onExitFieldExited() {
        exitField.underlineProperty().setValue(false);
    }

    @FXML
    private void onPlayAgainFieldClicked() throws IOException {
        playAgainField.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("GameStage.fxml")));
        Parent root = loader.load();
        gameStage.setTitle("Tic-Tac-Toe");
        gameStage.getIcons().add(new Image(Objects.requireNonNull(main.class.getResourceAsStream("graphics\\icon.png"))));
        gameStage.setScene(new Scene(root));
        gameStage.setResizable(false);
        gameStage.show();
    }

    @FXML
    private void onExitFieldClicked() {
        System.exit(0);
    }

}
