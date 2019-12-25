import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javax.swing.*;
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
    static boolean isServer = false;
    boolean aCardIsSelected = false;
    boolean selectedFromHand = false;
    boolean selectedFromTable = false;
    boolean selectedFromOpponent = false;
    boolean[] emptyIndexOnTable = {true, true, true, true, true, true};
    boolean[] emptyIndexOnHand = {false, false, false, false, false, true, true, true, true, true};
    Node selectedCard = null;
    String selectedStyle = "-fx-border-color: #0066ff; -fx-border-width: 4; -fx-background-color: #A6A6A6;";
    String notSelectedStyle = "-fx-border-color: #000; -fx-border-width: 4; -fx-background-color: #A6A6A6;";

    public void loadCard(ActionEvent event) {
        try {
//                Pane soAnyWay = FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml"));
//                Pane itsRaw = FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml"));

//            myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 0, 0);
//            myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 1, 0);
//            getCardImage(myPlayedCards.getChildren().get(1)).setImage(urlToImage("Images/Kobe.png"));
//            getCardAttack(myPlayedCards.getChildren().get(1)).setText();
//            getCardHealth(myPlayedCards.getChildren().get(1));
//            getCardNameLabel(myPlayedCards.getChildren().get(1));

//            opponentPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 0, 0);
//            opponentPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 1, 0);
//            opponentPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 2, 0);
//            opponentPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 3, 0);
//            opponentPlayedCards.add(setCardDetails("3", "1", "ANANI", "Images/BadLuckBrian.png"), 4, 0);
////            opponentPlayedCards.add(, 4, 0);
//            getCardNameLabel(opponentPlayedCards.getChildren().get(1)).setText("I wanna die");
//            getCardAttack(opponentPlayedCards.getChildren().get(1)).setText("3");
//            myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 1, 0);
//
//            myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 2, 0);
//            myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 3, 0);
//            myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 4, 0);
//            myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 5, 0);
//                myPlayedCards.add(FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml")), 5, 0);

            myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 0, 0);
            myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 1, 0);
            myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 2, 0);
            myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 3, 0);
            myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 4, 0);
//            myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 5, 0);
//            myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 6, 0);
//            myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 7, 0);
//            myHand.add(FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml")), 8, 0);
//                ((VBox) otherPane.getChildren().get(0)).fillWidthProperty().bind(otherPane.getWidth());
//                System.out.println(getCardHealth(myPlayedCards.getChildren().get(0)).getText());
//                System.out.println(getCardNameLabel(myPlayedCards.getChildren().get(3)).getText());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void putCardFromHandToTable() {
        boolean emptySpaceExists = false;

        for (boolean empty: emptyIndexOnTable) {
            if (empty) {
                emptySpaceExists = true;
                break;
            }
        }

        if (emptySpaceExists) {
            if (selectedFromHand) {
                myHand.getChildren().remove(selectedCard);
                for (int emptyIndex = 0; emptyIndex < emptyIndexOnTable.length; emptyIndex++) {
                    if (emptyIndexOnTable[emptyIndex]) {
                        selectedCard.setStyle(notSelectedStyle);
                        selectedCard = setCardDetails(selectedCard);
                        myPlayedCards.add(selectedCard, emptyIndex, 0);
                        emptyIndexOnTable[emptyIndex] = false;
                        break;
                    }
                }

//                selectedCard = null;
                aCardIsSelected = false;
                selectedFromHand = false;
                setMyPlayedCardsListener();
            }
        }
    }

    public void endTurn(ActionEvent event) {
        try {
            if (isServer) {
                Server.output.writeObject("remove_card");
                Server.output.flush();
            } else {
                Client.output.writeObject("remove_card");
                Client.output.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

                        try {
                            if (isServer) {
                                Server.output.writeObject("end turn pressed");
                                Server.output.flush();
                            } else {
                                Client.output.writeObject("end turn pressed");
                                Client.output.flush();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

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

                        putCardFromHandToTable();


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

                        if (Integer.parseInt(getCardHealth(selectedCard).getText()) <= 0) {
                            emptyIndexOnTable[myPlayedCards.getChildren().indexOf(selectedCard) - 1] = true;
                            myPlayedCards.getChildren().remove(selectedCard);
                            myHand.setDisable(false);
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

    private Node setCardDetails(String attack, String health, String name, String imageURL) {
        try {
            Node newCard = FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml"));
            getCardAttack(newCard).setText(attack);
            getCardHealth(newCard).setText(health);
            getCardNameLabel(newCard).setText(name);
            getCardImage(newCard).setImage(urlToImage(imageURL));

            return newCard;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Node setCardDetails(Node card) {
        try {
            Node newCard = FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml"));
            getCardAttack(newCard).setText(getCardAttack(card).getText());
            getCardHealth(newCard).setText(getCardHealth(card).getText());
            getCardNameLabel(newCard).setText(getCardNameLabel(card).getText());
            getCardImage(newCard).setImage(getCardImage(card).getImage());

            return newCard;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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

    private void listenToOtherPlayer() {
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("in runnable run");
//                if (isServer) {
//                    try {
//                        final String message = (String) Server.input.readObject();
//                        if (message.equals("remove_card")) {
////                            myPlayedCards.getChildren().remove(1);
//                            System.out.println(Thread.getAllStackTraces());
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//
//                }
//            }
//        });

//        new Thread(new Runnable() {
//            @Override public void run() {
//                while (true) {
//                    Platform.runLater(new Runnable() {
//                        @Override public void run() {
//                            System.out.println("in run");
//                        }
//                    });
//                }
//            }
//        }).start();
//        Runnable listener = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("in runnable run");
//                while (true) {
//                    if (isServer) {
//                        try {
//                            final String message = (String) Server.input.readObject();
//                            if (message.equals("remove_card")) {
//                                myPlayedCards.getChildren().remove(1);
////                                System.out.println(Thread.getAllStackTraces());
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (ClassNotFoundException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//
//                    }
//                }
//            }
//        };
//
//        Thread serverThread = new Thread(listener);
//        serverThread.start();

        Task task = new Task<Void>() {
            @Override
            public Void call() {
//                System.out.println("in task");
                while (true) {
                    if (isServer) {
                        try {
                            final String message = (String) Server.input.readObject();
                            System.out.println(message);
                            if (message.equals("remove_card")) {
                                updateUIElements();
//                                myPlayedCards.getChildren().removeAll();
//                                System.out.println(Thread.getAllStackTraces());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {

                    }
                }
            }
        };

        new Thread(task).start();
    }

    private void updateUIElements() {
        System.out.println("in update ui elements");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("in runnable run");
                System.out.println(myPlayedCards.getChildren().remove(1));
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        opponentHand.setDisable(true);
        opponentPlayedCards.setDisable(true);

        try {
            opponentHand.add(FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml")), 0, 0);
            opponentHand.add(FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml")), 1, 0);
            opponentHand.add(FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml")), 2, 0);
            opponentHand.add(FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml")), 3, 0);
            opponentHand.add(FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml")), 4, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        listenToOtherPlayer();
    }
}
