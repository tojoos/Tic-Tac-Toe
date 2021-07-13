package app;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameStageController implements Initializable {

    private BooleanProperty booleanProperty1 = new SimpleBooleanProperty(true);
    private BooleanProperty booleanProperty2 = new SimpleBooleanProperty(false);
    private ArrayList<ImageView> Xs;
    private ArrayList<ImageView> Os;
    private ArrayList<Button> tileList;

    private char [][] gameBoard = new char [3][3];

    @FXML
    private GridPane O_grid, X_grid, tileGrid;

    @FXML
    private Group O_TurnGroup, X_TurnGroup, verticalLineGroup, horizontalLineGroup, crossLineGroup;

    @FXML
    private ImageView X_0, X_1, X_2, X_3, X_4, X_5, X_6, X_7, X_8,
                      O_0, O_1, O_2, O_3, O_4, O_5, O_6, O_7, O_8;

    @FXML
    private Button tileButton0, tileButton1, tileButton2, tileButton3, tileButton4, tileButton5, tileButton6, tileButton7, tileButton8;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearTable();
        initializeLists();

        for(int i = 0; i < 3; i++) {
            Arrays.fill(gameBoard[i], '-');
        }

        int i = 0;
        for(Node n : tileGrid.getChildren()) {
            if (n != null) {
                int finalI = i;
                n.setOnMouseClicked(e -> {
                    if(booleanProperty1.get()) {
                        Os.get(finalI).setVisible(true);
                        if(finalI>5) {
                            gameBoard[2][finalI-6] = 'O';
                        } else if(finalI > 2) {
                            gameBoard[1][finalI-3] = 'O';
                        } else {
                            gameBoard[0][finalI] = 'O';
                        }
                    } else {
                        Xs.get(finalI).setVisible(true);
                        if(finalI>5) {
                            gameBoard[2][finalI-6] = 'X';
                        } else if(finalI > 2) {
                            gameBoard[1][finalI-3] = 'X';
                        } else {
                            gameBoard[0][finalI] = 'X';
                        }
                    }
                    tileList.get(finalI).setDisable(true);
                    booleanProperty1.setValue(!booleanProperty1.get());
                    booleanProperty2.setValue(!booleanProperty2.get());

                    if(!checkGameStatus('X') && !checkGameStatus('O')) {
                        if (isGameBoardFull()) {
                            showEndOfTheGameStage('T');
                        }
                    }

                });
            }
            i++;
        }
    }

    private boolean isGameBoardFull() {
        int counter = 0;
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(gameBoard[i][j]=='-')
                    counter++;
            }
        }
        return counter == 0;
    }

    private boolean checkGameStatus(char XorO) {
        if((gameBoard[0][0] == XorO && gameBoard[1][1] == XorO && gameBoard[2][2] == XorO) ||
           (gameBoard[0][2] == XorO && gameBoard[1][1] == XorO && gameBoard[2][0] == XorO) ||
           (gameBoard[0][0] == XorO && gameBoard[0][1] == XorO && gameBoard[0][2] == XorO) ||
           (gameBoard[1][0] == XorO && gameBoard[1][1] == XorO && gameBoard[1][2] == XorO) ||
           (gameBoard[2][0] == XorO && gameBoard[2][1] == XorO && gameBoard[2][2] == XorO) ||
           (gameBoard[0][0] == XorO && gameBoard[1][0] == XorO && gameBoard[2][0] == XorO) ||
           (gameBoard[0][1] == XorO && gameBoard[1][1] == XorO && gameBoard[2][1] == XorO) ||
           (gameBoard[0][2] == XorO && gameBoard[1][2] == XorO && gameBoard[2][2] == XorO)) {
            showEndOfTheGameStage(XorO);
            return true;
        } else {
            return false;
        }
    }

    private void showEndOfTheGameStage(char XorO) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("EndOfGameStage.fxml")));
            Parent root = loader.load();
            EndOfGameStageController endOfGameStageController = loader.getController();
            endOfGameStageController.setWinner(XorO);
            endOfGameStageController.transferGameStage((Stage) tileGrid.getScene().getWindow());
            Stage endGameStage = new Stage();
            endGameStage.setTitle("Tic-Tac-Toe");
            endGameStage.initModality(Modality.APPLICATION_MODAL);
            endGameStage.getIcons().add(new Image(Objects.requireNonNull(main.class.getResourceAsStream("graphics\\icon.png"))));
            endGameStage.setScene(new Scene(root));
            endGameStage.setResizable(false);
            endGameStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void clearTable() {
        if(O_grid!=null && X_grid!=null) {
            for(int i=0;i<3;i++) {
                for(int j=0;j<3;j++) {
                    (O_grid.getChildren().get(i*3+j)).setVisible(false);
                    (X_grid.getChildren().get(i*3+j)).setVisible(false);
                }
            }
        }
    }

    private void initializeLists() {
        Xs = new ArrayList<>(
                Arrays.asList(X_0, X_1, X_2,
                              X_3, X_4, X_5,
                              X_6, X_7, X_8));

        Os = new ArrayList<>(
                Arrays.asList(O_0, O_1, O_2,
                              O_3, O_4, O_5,
                              O_6, O_7, O_8));

        tileList = new ArrayList<>(
                Arrays.asList(tileButton0, tileButton1, tileButton2,
                              tileButton3, tileButton4, tileButton5,
                              tileButton6, tileButton7, tileButton8));
    }

    @FXML
    private Label onePlayerButton, twoPlayersButton;

    @FXML
    private void onTwoPlayersButtonEntered() {
        twoPlayersButton.underlineProperty().setValue(true);
    }

    @FXML
    private void onTwoPlayersButtonExited() {
        twoPlayersButton.underlineProperty().setValue(false);
    }

    @FXML
    private void onOnePlayerButtonEntered() {
        onePlayerButton.underlineProperty().setValue(true);
    }

    @FXML
    private void onOnePlayerButtonExited() {
        onePlayerButton.underlineProperty().setValue(false);
    }

    @FXML
    private Text newGameText;

    @FXML
    private void onTwoPlayersButtonClicked() {
        hideNewGameControls();
        O_TurnGroup.visibleProperty().bind(booleanProperty1);
        X_TurnGroup.visibleProperty().bind(booleanProperty2);
        tileGrid.setDisable(false);
    }

    @FXML
    private void onOnePlayerButtonClicked() {
        hideNewGameControls();
        O_TurnGroup.visibleProperty().bind(booleanProperty1);
        X_TurnGroup.visibleProperty().bind(booleanProperty2);
        tileGrid.setDisable(false);
    }

    private void hideNewGameControls() {
        onePlayerButton.setVisible(false);
        twoPlayersButton.setVisible(false);
        newGameText.setVisible(false);
    }

    private void showNewGameControls() {
        onePlayerButton.setVisible(true);
        twoPlayersButton.setVisible(true);
        newGameText.setVisible(true);
    }
}
