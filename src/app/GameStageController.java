package app;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class GameStageController implements Initializable {

    private BooleanProperty booleanProperty1 = new SimpleBooleanProperty(true);
    private BooleanProperty booleanProperty2 = new SimpleBooleanProperty(false);
    private ArrayList<ImageView> Xs;
    private ArrayList<ImageView> Os;
    private ArrayList<Button> tileList;

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

        int i = 0;
        for(Node n : tileGrid.getChildren()) {
            if (n != null) {
                int finalI = i;
                n.setOnMouseClicked(e -> {
                    if(booleanProperty1.get()) {
                        Os.get(finalI).setVisible(true);
                    } else {
                        Xs.get(finalI).setVisible(true);
                    }
                    tileList.get(finalI).setDisable(true);
                    booleanProperty1.setValue(!booleanProperty1.get());
                    booleanProperty2.setValue(!booleanProperty2.get());
                });
            }
            i++;
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
