import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PlayingScreen {
    @FXML private Button endTurnButton;
    @FXML private Pane firstPane;
    @FXML private Pane otherPane;
    public void loadFxml (ActionEvent event) {
        try {
            if (firstPane.getChildren().isEmpty()) {
                Pane soAnyWay =  FXMLLoader.load(getClass().getResource("CardLayout.fxml"));
                Pane itsRaw =  FXMLLoader.load(getClass().getResource("CardLayout.fxml"));
                firstPane.getChildren().add(soAnyWay);
                getCardNameLabel().setText("This is another way.");
                getCardImage().setImage(urlToImage("./Images/soAnyWay.png"));
                getCardAttack().setText("4");
                getCardHealth().setText("1");
                otherPane.getChildren().add(itsRaw);
            } else {
                firstPane.getChildren().remove(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endTurn(ActionEvent event) {
        loadFxml(event);
    }

    private Label getCardNameLabel() {
        VBox myVbox = (VBox) firstPane.getChildren().get(0);
        return ((Label) ((GridPane) myVbox.getChildren().get(0)).getChildren().get(0));
    }

    private ImageView getCardImage() {
        VBox myVbox = (VBox) firstPane.getChildren().get(0);
        return ((ImageView) myVbox.getChildren().get(1));
    }

    private Image urlToImage(String url) {
        return new Image(url);
    }

    private Label getCardAttack() {
        VBox myVbox = (VBox) firstPane.getChildren().get(0);
        return ((Label) ((FlowPane) ((GridPane) myVbox.getChildren().get(2)).getChildren().get(0)).getChildren().get(1));
    }

    private Label getCardHealth() {
        VBox myVbox = (VBox) firstPane.getChildren().get(0);
        return ((Label) ((FlowPane) ((GridPane) myVbox.getChildren().get(2)).getChildren().get(1)).getChildren().get(0));
    }
}
