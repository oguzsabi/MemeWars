import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayingScreen implements Initializable {
    @FXML private Button endTurnButton;
    @FXML private GridPane opponent;
    @FXML private GridPane opponentHand;
    @FXML private GridPane opponentPlayedCards;
    @FXML private GridPane myself;
    @FXML private GridPane myHand;
    @FXML private GridPane myPlayedCards;
    boolean aCardIsSelected = false;
    Node selectedCard = null;
    String selectedStyle = "-fx-border-color: #0066ff; -fx-border-width: 4; -fx-background-color: #A6A6A6;";
    String notSelectedStyle = "-fx-border-color: #000; -fx-border-width: 4; -fx-background-color: #A6A6A6;";

    public void loadCard(ActionEvent event) {
        try {
            if (myPlayedCards.getChildren().isEmpty()) {
//                Pane soAnyWay = FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml"));
//                Pane itsRaw = FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml"));
                myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 0, 0);
                myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 1, 0);
                myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 2, 0);
                myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 3, 0);
                myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 4, 0);
//                myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 5, 0);
                myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 0, 0);
                myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 1, 0);
                myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 2, 0);
                myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 3, 0);
                myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 4, 0);
                myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 5, 0);
                myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 6, 0);
//                ((VBox) otherPane.getChildren().get(0)).fillWidthProperty().bind(otherPane.getWidth());
                System.out.println(getCardHealth(myPlayedCards.getChildren().get(0)).getText());
                System.out.println(getCardNameLabel(myPlayedCards.getChildren().get(3)).getText());
            } else {
                myPlayedCards.getChildren().remove(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cardSelectedFromTable(MouseEvent e) {
//        System.out.println(e.getTarget());
//        System.out.println(e.getEventType());

        Node source = (Node) e.getSource();
//        System.out.println(source);
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
//        System.out.printf("Mouse entered cell [%d, %d]%n", colIndex, rowIndex);
//        System.out.println(getCardNameLabel(myPlayedCards.getChildren().get(colIndex)));
    }

    public void endTurn(ActionEvent event) {
        loadCard(event);
        for (Node node : myPlayedCards.getChildren()) {
            node.setOnMouseClicked((MouseEvent e) -> {
                if (e.getButton() == MouseButton.PRIMARY && node != null) {
                    if (!aCardIsSelected) {
                        selectedCard = node;
                        aCardIsSelected = true;
                        System.out.println(getCardNameLabel(node).getText());
                        VBox vBox = (VBox) node;
                        vBox.setStyle(selectedStyle);
//                    vBox.setBorder(new Border(new BorderStroke(Color.BLUE,
//                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                    node.setOnMouseClicked(null);
                    } else {
                        VBox vBox = (VBox) selectedCard;
//                    vBox.setBorder(new Border(new BorderStroke(Color.BLACK,
//                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.)));
                        vBox.setStyle(notSelectedStyle);
                        selectedCard = null;
                        aCardIsSelected = false;
                    }
                }
            });
        }

        for (Node node : myHand.getChildren()) {
            node.setOnMouseClicked((MouseEvent e) -> {
                if (e.getButton() == MouseButton.PRIMARY && node != null) {
                    if (!aCardIsSelected) {
                        selectedCard = node;
                        aCardIsSelected = true;
                        System.out.println(getCardNameLabel(node).getText());
                        VBox vBox = (VBox) node;
                        vBox.setStyle(selectedStyle);
                    } else {
                        VBox vBox = (VBox) selectedCard;
                        vBox.setStyle(notSelectedStyle);
                        selectedCard = null;
                        aCardIsSelected = false;
                    }
                }
            });
        }
    }

    private Label getCardNameLabel(Node Card) {
        VBox vBox = (VBox) Card;
        return ((Label) ((GridPane) vBox.getChildren().get(0)).getChildren().get(0));
    }

    private ImageView getCardImage(Node Card) {
        VBox vBox = (VBox) Card;
        return ((ImageView) vBox.getChildren().get(1));
    }

    private Image urlToImage(String url) {
        return new Image(url);
    }

    private Label getCardAttack(Node Card) {
        VBox vBox = (VBox) Card;
        return ((Label) ((FlowPane) ((GridPane) vBox.getChildren().get(2)).getChildren().get(0)).getChildren().get(1));
    }

    private Label getCardHealth(Node Card) {
        VBox vBox = (VBox) Card;
        return ((Label) ((FlowPane) ((GridPane) vBox.getChildren().get(2)).getChildren().get(1)).getChildren().get(0));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
