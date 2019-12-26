import javafx.application.Platform;
import javafx.concurrent.Task;
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
import java.util.ArrayList;
import java.util.ResourceBundle;


public class PlayingScreen implements Initializable {
    @FXML private Button endTurnButton;
    @FXML private GridPane opponent;
    @FXML private GridPane opponentHand;
    @FXML private GridPane opponentPlayedCards;
    @FXML private GridPane myself;
    @FXML private GridPane myHand;
    @FXML private GridPane myPlayedCards;
    @FXML private Label opponentBanana;
    @FXML private Label opponentHealth;
    @FXML private Label myBanana;
    @FXML private Label myHealth;
    @FXML private ImageView opponentHero;
    @FXML private ImageView myHero;
    static boolean isServer = false;
    boolean aCardIsSelected = false;
    boolean selectedFromHand = false;
    boolean selectedFromTable = false;
    boolean selectedFromOpponent = false;
    boolean myTurn = false;
    int turnCounter = 1;
    boolean[] emptyIndexOnMyTable = {true, true, true, true, true, true};
    boolean[] emptyIndexOnOppTable = {true, true, true, true, true, true};
    boolean[] emptyIndexOnMyHand = {false, false, false, false, false, true, true, true, true, true};
    boolean[] emptyIndexOnOppHand = {false, false, false, false, false, true, true, true, true, true};
    ArrayList<Card> myDeck;
    Node selectedCard = null;
    String selectedStyle = "-fx-border-color: #0066ff; -fx-border-width: 4; -fx-background-color: #A6A6A6;";
    String notSelectedStyle = "-fx-border-color: #000; -fx-border-width: 4; -fx-background-color: #A6A6A6;";

    @FXML
    private void bigYeet() { // Button for leaving the game
        if(isServer){
            try {
                Server.output.writeObject("big_yeet");
                Server.output.flush();
                System.exit(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                Client.output.writeObject("big_yeet");
                Client.output.flush();
                System.exit(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void putCardFromMyHandToMyTable() {
        boolean emptySpaceExists = false;

        for (boolean empty: emptyIndexOnMyTable) { // Finding the available space for card to be put
            if (empty) {
                emptySpaceExists = true;
                break;
            }
        }

        int cardBanana = Integer.parseInt(getCardBanana(selectedCard).getText());
        int myBnn = Integer.parseInt(myBanana.getText());

        boolean bananaCondition = cardBanana <= myBnn; // Card can be played only if your Banana value is greater or equals to Banana value you have
        if (emptySpaceExists && bananaCondition) {
            myBanana.setText(Integer.toString(myBnn - cardBanana)); // Decrementing the banana value by the played cards banana value
            if (selectedFromHand) {
                myHand.getChildren().remove(selectedCard); // Removes the card from the hand
                for (int emptyIndex = 0; emptyIndex < emptyIndexOnMyTable.length; emptyIndex++) {
                    if (emptyIndexOnMyTable[emptyIndex]) {
                        selectedCard.setStyle(notSelectedStyle);
                        selectedCard = setCardDetails(selectedCard);
                        myPlayedCards.add(selectedCard, emptyIndex, 0); // Putting the card to the field
                        emptyIndexOnMyTable[emptyIndex] = false; // Changing the spot full
                        break;
                    }
                }

                String[] imageAbsolutePathArray = getCardImage(selectedCard).getImage().getUrl().split("/");
                final String imageRelativePath = imageAbsolutePathArray[imageAbsolutePathArray.length - 2] + "/" + imageAbsolutePathArray[imageAbsolutePathArray.length - 1];

                String eventDetails = "card_play,";
                eventDetails += getCardNameLabel(selectedCard).getText() + ",";
                eventDetails += imageRelativePath + ",";
                eventDetails += getCardDamage(selectedCard).getText() + ",";
                eventDetails += getCardHealth(selectedCard).getText();

                if (isServer) {
                    try {
                        Server.output.writeObject(eventDetails);
                        Server.output.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Client.output.writeObject(eventDetails);
                        Client.output.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

//                selectedCard = null;
                aCardIsSelected = false;
                selectedFromHand = false;
                setMyPlayedCardsListener();
            }
        }
    }

    public void putCardFromOppHandToOppTable(String[] eventDetails) {
        boolean emptySpaceExists = false;

        for (boolean empty: emptyIndexOnOppTable) {
            if (empty) {
                emptySpaceExists = true;
                break;
            }
        }

        if (emptySpaceExists) {
            emptyIndexOnOppHand[opponentHand.getChildren().size() - 1] = true;
            opponentHand.getChildren().remove(opponentHand.getChildren().size() - 1);

            for (int emptyIndex = 0; emptyIndex < emptyIndexOnOppTable.length; emptyIndex++) {
                if (emptyIndexOnOppTable[emptyIndex]) {
                    Node newCard;
                    newCard = setCardDetails(eventDetails[1], eventDetails[2], eventDetails[3], eventDetails[4]);
                    opponentPlayedCards.add(newCard, emptyIndex, 0);
                    emptyIndexOnOppTable[emptyIndex] = false;
                    break;
                }
            }
            setOpponentPlayedCardsListener();
        }
    }

    public void endTurn(ActionEvent event) {
        myTurn = false;
        endTurnButton.setDisable(true);
        myPlayedCards.setDisable(true);
        myHand.setDisable(true);

        if (opponentHand.getChildren().size() < 11) {
            for (int emptyIndex = 0; emptyIndex < emptyIndexOnOppHand.length; emptyIndex++) {
                if (emptyIndexOnOppHand[emptyIndex]) {
                    try {
                        Node cardBack = FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml"));
                        opponentHand.add(cardBack, emptyIndex - 1, 0);
                        emptyIndexOnOppHand[emptyIndex - 1] = false;
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (isServer) {
            if (turnCounter < 10) {
                opponentBanana.setText(Integer.toString(turnCounter));
                myBanana.setText(Integer.toString(++turnCounter));
            } else {
                opponentBanana.setText("10");
                myBanana.setText("10");
            }
        } else {
            if (turnCounter < 10) {
                int tmp = turnCounter;
                opponentBanana.setText(Integer.toString(++tmp));
                myBanana.setText(Integer.toString(++turnCounter));
            } else {
                opponentBanana.setText("10");
                myBanana.setText("10");
            }
        }

        try {
            if (isServer) {
                Server.output.writeObject("end_turn");
                Server.output.flush();
            } else {
                Client.output.writeObject("end_turn");
                Client.output.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

                        putCardFromMyHandToMyTable();
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

                        getCardHealth(node).setText(String.valueOf(Integer.parseInt(getCardHealth(node).getText()) - Integer.parseInt(getCardDamage(selectedCard).getText())));
                        getCardHealth(selectedCard).setText(String.valueOf(Integer.parseInt(getCardHealth(selectedCard).getText()) - Integer.parseInt(getCardDamage(node).getText())));
                        if (Integer.parseInt(getCardHealth(node).getText()) <= 0) {
                            opponentPlayedCards.getChildren().remove(node);
                            selectedFromOpponent = false;
                        }

                        if (Integer.parseInt(getCardHealth(selectedCard).getText()) <= 0) {
                            emptyIndexOnMyTable[myPlayedCards.getChildren().indexOf(selectedCard) - 1] = true;
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

    private Node setCardDetails(String name, String imageURL, String attack, String health) {
        try {
            Node newCard = FXMLLoader.load(getClass().getResource("CardLayoutTable.fxml"));
            getCardDamage(newCard).setText(attack);
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
            getCardDamage(newCard).setText(getCardDamage(card).getText());
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

    private Label getCardDamage(Node Card) {
        VBox vBox = (VBox) Card;
        return ((Label) ((FlowPane) ((GridPane) vBox.getChildren().get(2)).getChildren().get(0)).getChildren().get(1));
    }

    private Label getCardHealth(Node Card) {
        VBox vBox = (VBox) Card;
        return ((Label) ((FlowPane) ((GridPane) vBox.getChildren().get(2)).getChildren().get(1)).getChildren().get(0));
    }

    private Label getCardBanana(Node Card) {
        VBox vBox = (VBox) Card;
        return ((Label) ((FlowPane) ((GridPane) vBox.getChildren().get(2)).getChildren().get(2)).getChildren().get(1));
    }

    // This method creates a thread that runs in the background and listens for messages sent by the other player.
    private void listenToOtherPlayer() {
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                while (true) {
                    if (isServer) {
                        try {
                            System.out.println("in server");
                            final String message = (String) Server.input.readObject();
                            System.out.println(message);
                            if (message.equals("end_turn")) {
                                myTurn = true;
                                myPlayedCards.setDisable(false);
                                myHand.setDisable(false);
                                endTurnButton.setDisable(false);
                            }
                            if(message.equals("big_yeet")){
                                System.exit(1);
                            }

                            final String[] eventDetails = message.split(",");
                            updateUIElements(eventDetails);
                        } catch (IOException | ClassNotFoundException e) {
                            break;
                        }
                    } else {
                        try {
                            System.out.println("in client");
                            final String message = (String) Client.input.readObject();
                            if (message.equals("end_turn")) {
                                myTurn = true;
                                myPlayedCards.setDisable(false);
                                myHand.setDisable(false);
                                endTurnButton.setDisable(false);
                            }
                            if(message.equals("big_yeet")){
                                System.exit(1);
                            }


                            final String[] eventDetails = message.split(",");
                            updateUIElements(eventDetails);
                        } catch (IOException | ClassNotFoundException e) {
                            break;
                        }
                    }
                }
                return null;
            }
        };

        new Thread(task).start();
    }

    private void updateUIElements(String[] eventDetails) {
        System.out.println("in update ui elements");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (eventDetails[0].equals("card_play")) {
                    putCardFromOppHandToOppTable(eventDetails);
                }
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

            Deck deck = new Deck();
            if (isServer) {
                myHero.setImage(new Image("Images/KeanuReeves.png"));
                opponentHero.setImage(new Image("Images/PewDiePie.png"));
                myTurn = true;
                myDeck = deck.getDeck1();
            } else {
                opponentHero.setImage(new Image("Images/KeanuReeves.png"));
                myHero.setImage(new Image("Images/PewDiePie.png"));
                myPlayedCards.setDisable(true);
                myHand.setDisable(true);
                myDeck = deck.getDeck2();
                endTurnButton.setDisable(true);
            }

            for (int i = 0; i < 5; i++) {
                Node handCard = FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml"));
                getCardNameLabel(handCard).setText(myDeck.get(0).getCardName());
                getCardImage(handCard).setImage(urlToImage(myDeck.get(0).getCardURL()));
                getCardDamage(handCard).setText(Integer.toString(myDeck.get(0).getDamage()));
                getCardHealth(handCard).setText(Integer.toString(myDeck.get(0).getHealth()));
                getCardBanana(handCard).setText(Integer.toString(myDeck.get(0).getBanana()));
                myHand.add(handCard, i, 0);
                myDeck.remove(myDeck.get(0));
            }

            setMyPlayedCardsListener();
            setMyHandListener();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listenToOtherPlayer();
    }
}
