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
    boolean selectedFromHand = false;
    boolean selectedFromTable = false;
    boolean selectedFromOpponent = false;
    Node selectedCard = null;
    String selectedStyle = "-fx-border-color: #0066ff; -fx-border-width: 4; -fx-background-color: #A6A6A6;";
    String notSelectedStyle = "-fx-border-color: #000; -fx-border-width: 4; -fx-background-color: #A6A6A6;";

    public void loadCard(ActionEvent event) {
        try {
//                Pane soAnyWay = FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml"));
//                Pane itsRaw = FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml"));
            opponentHand.add(FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml")), 0, 0);
            opponentHand.add(FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml")), 1, 0);
            opponentHand.add(FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml")), 2, 0);
            opponentHand.add(FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml")), 3, 0);
            opponentHand.add(FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml")), 4, 0);

            myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 0, 0);
            myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 1, 0);
            getCardImage(myPlayedCards.getChildren().get(1)).setImage(urlToImage("Images/Kobe.png"));
//            getCardAttack(myPlayedCards.getChildren().get(1)).setText();
            getCardHealth(myPlayedCards.getChildren().get(1));
            getCardNameLabel(myPlayedCards.getChildren().get(1));


            opponentPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 0, 0);
            opponentPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 1, 0);
            opponentPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 2, 0);
            opponentPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 3, 0);
            getCardNameLabel(opponentPlayedCards.getChildren().get(1)).setText("I wanna die");
//            myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 1, 0);

            myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 2, 0);
            myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 3, 0);
            myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 4, 0);
//                myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 5, 0);

            myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 0, 0);
            myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 1, 0);
            myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 2, 0);
            myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 3, 0);
            myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 4, 0);
//                ((VBox) otherPane.getChildren().get(0)).fillWidthProperty().bind(otherPane.getWidth());
//                System.out.println(getCardHealth(myPlayedCards.getChildren().get(0)).getText());
//                System.out.println(getCardNameLabel(myPlayedCards.getChildren().get(3)).getText());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    private void cardSelectedFromTable(MouseEvent e) {
////        System.out.println(e.getTarget());
////        System.out.println(e.getEventType());
//
//        Node source = (Node) e.getSource();
////        System.out.println(source);
//        Integer colIndex = GridPane.getColumnIndex(source);
//        Integer rowIndex = GridPane.getRowIndex(source);
////        System.out.printf("Mouse entered cell [%d, %d]%n", colIndex, rowIndex);
////        System.out.println(getCardNameLabel(myPlayedCards.getChildren().get(colIndex)));
//    }

    public void putCardFromHandToTable() {

    }

    public void endTurn(ActionEvent event) {
        loadCard(event);
        setMyPlayedCardsListener();
        setMyHandListener();
    }

    private void setMyPlayedCardsListener() {
        for (Node node : myPlayedCards.getChildren()) {
            node.setOnMouseClicked((MouseEvent e) -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    if (!aCardIsSelected) {
                        selectedCard = node;

                        myHand.setDisable(true);
                        opponentPlayedCards.setDisable(false);

                        aCardIsSelected = true;
                        setOpponentPlayedCardsListener();
                        selectedFromTable = true;

                        System.out.println(getCardNameLabel(node).getText());
                        VBox vBox = (VBox) node;
                        vBox.setStyle(selectedStyle);
                    } else {
                        opponentPlayedCards.setDisable(true);
                        myHand.setDisable(false);

                        disableOpponentPlayedCardListener();
                        VBox vBox = (VBox) selectedCard;
                        vBox.setStyle(notSelectedStyle);
                        selectedCard = null;
                        selectedFromTable = false;
                        aCardIsSelected = false;
                    }
                }
            });
        }
    }

    private void setMyHandListener() {
        for (Node node : myHand.getChildren()) {
            node.setOnMouseClicked((MouseEvent e) -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    if (!aCardIsSelected) {
                        selectedCard = node;
                        aCardIsSelected = true;
                        selectedFromHand = true;
                        opponentPlayedCards.setDisable(true);

                        VBox vBox = (VBox) node;
                        vBox.setStyle(selectedStyle);
                    } else {
                        VBox vBox = (VBox) selectedCard;
                        vBox.setStyle(notSelectedStyle);
                        opponentPlayedCards.setDisable(false);

                        selectedFromOpponent = false;
                        selectedCard = null;
                        selectedFromHand = false;
                        aCardIsSelected = false;
                    }
                }
            });
        }
    }

    private void setOpponentPlayedCardsListener() {
        for (Node node : opponentPlayedCards.getChildren()) {
            node.setOnMouseClicked((MouseEvent e) -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    if (selectedFromTable && !selectedFromOpponent) {
                        VBox vBox = (VBox) node;
                        vBox.setStyle(selectedStyle);
                        selectedFromOpponent = true;

                        getCardHealth(node).setText(String.valueOf(Integer.parseInt(getCardHealth(node).getText()) - Integer.parseInt(getCardAttack(selectedCard).getText())));
                        getCardHealth(selectedCard).setText(String.valueOf(Integer.parseInt(getCardHealth(selectedCard).getText()) - Integer.parseInt(getCardAttack(node).getText())));
                        if (Integer.parseInt(getCardHealth(node).getText()) <= 0) {
                            opponentPlayedCards.getChildren().remove(node);
                            selectedFromOpponent = false;
                        }

                        if (Integer.parseInt(getCardHealth(node).getText()) <= 0) {
                            myPlayedCards.getChildren().remove(selectedCard);
                            aCardIsSelected = false;
                        }
                    }
                }
            });
        }
    }

    private void disableOpponentPlayedCardListener() {
        selectedFromOpponent = false;
        for (Node node : opponentPlayedCards.getChildren()) {
            try  {
                VBox vBox = (VBox) node;
                vBox.setStyle(notSelectedStyle);
                node.setOnMouseClicked(null);
            } catch (ClassCastException e) {
                System.out.println(node);
            }
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
        opponentHand.setDisable(true);
        opponentPlayedCards.setDisable(true);
    }
}
