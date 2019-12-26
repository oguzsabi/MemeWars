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
    int turnCounter = 1;
    ArrayList<Card> myDeck;
    ArrayList<Node> myHandList = new ArrayList<>();
    ArrayList<Node> myPlayedList = new ArrayList<>();
    ArrayList<Node> oppPlayedList = new ArrayList<>();
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

    @FXML
    private void clickToHero(){
        if(selectedFromTable) {
            opponentHealth.setText(String.valueOf(Integer.parseInt(opponentHealth.getText()) - Integer.parseInt(getCardDamage(selectedCard).getText())));

            if (Integer.parseInt(opponentHealth.getText()) <= 0) {
                bigYeet();
            }

            try{
                if(isServer) {
                    String eventDetails;
                    eventDetails = "hit_hero,";
                    eventDetails += getCardDamage(selectedCard).getText();

                    aCardIsSelected = false;
                    selectedFromTable = false;
                    selectedCard.setStyle(notSelectedStyle);
                    selectedCard.setDisable(true);
                    myHand.setDisable(false);

                    Server.output.writeObject(eventDetails);
                    Server.output.flush();
                } else {
                    String eventDetails;
                    eventDetails = "hit_hero,";
                    eventDetails += getCardDamage(selectedCard).getText();

                    aCardIsSelected = false;
                    selectedFromTable = false;
                    selectedCard.setStyle(notSelectedStyle);
                    selectedCard.setDisable(true);
                    myHand.setDisable(false);

                    Client.output.writeObject(eventDetails);
                    Client.output.flush();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void setMyPlayedCardsEnabled() {
        for (Node node: myPlayedCards.getChildren())
            node.setDisable(false);
    }

    private void setOppPlayedCardsEnabled() {
        for (Node node: opponentPlayedCards.getChildren())
            node.setDisable(false);
    }

    public void putCardFromMyHandToMyTable() {
        int cardBanana = Integer.parseInt(getCardBanana(selectedCard).getText());
        int myBnn = Integer.parseInt(myBanana.getText());
        String bananaCost = "";

        boolean bananaCondition = cardBanana <= myBnn; // Card can be played only if your Banana value is greater or equals to Banana value you have
        if (myHand.getChildren().size() < 11 && bananaCondition) {
            opponentPlayedCards.setDisable(false);
            setOppPlayedCardsEnabled();
            myBanana.setText(Integer.toString(myBnn - cardBanana)); // Decrementing the banana value by the played cards banana value
            if (selectedFromHand) {
                myHand.getChildren().remove(1, myHand.getChildren().size()); // Removes the card from the hand
                myHandList.remove(selectedCard);

                for (int i = 0; i < myHandList.size(); i++) {
                    myHand.add(myHandList.get(i), i, 0);
                }

                selectedCard.setStyle(notSelectedStyle);
                bananaCost = getCardBanana(selectedCard).getText();
                selectedCard = setCardDetails(selectedCard);
                myPlayedCards.add(selectedCard, myPlayedCards.getChildren().size() - 1, 0); // Putting the card to the field
                myPlayedList.add(selectedCard);

                String[] imageAbsolutePathArray = getCardImage(selectedCard).getImage().getUrl().split("/"); // Gets the image path of the card
                final String imageRelativePath = imageAbsolutePathArray[imageAbsolutePathArray.length - 2] + "/" + imageAbsolutePathArray[imageAbsolutePathArray.length - 1];

                String eventDetails = "card_play,";
                eventDetails += getCardNameLabel(selectedCard).getText() + ",";
                eventDetails += imageRelativePath + ",";
                eventDetails += getCardDamage(selectedCard).getText() + ",";
                eventDetails += getCardHealth(selectedCard).getText() + ",";
                eventDetails += bananaCost;

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
            opponentHand.getChildren().remove(opponentHand.getChildren().size() - 1);
            Node newCard;
            newCard = setCardDetails(eventDetails[1], eventDetails[2], eventDetails[3], eventDetails[4]);
            opponentPlayedCards.add(newCard, opponentPlayedCards.getChildren().size() - 1, 0);
            oppPlayedList.add(newCard);

            setOpponentPlayedCardsListener();
    }

    public void drawCardFromDeck() {
                    Card cardInfo = myDeck.get(0);
                    myDeck.remove(myDeck.get(0));
                    Node newCard;
                    newCard = setCardDetails(cardInfo.getCardName(), cardInfo.getCardURL(), Integer.toString(cardInfo.getDamage()), Integer.toString(cardInfo.getHealth()), Integer.toString(cardInfo.getBanana()));
                    myHand.add(newCard, myHand.getChildren().size() - 1, 0);
                    myHandList.add(newCard);
//                    myHand.getChildren().remove(1, myHand.getChildren().size());
//                    myHand.getChildren().addAll(myHandList);
            setMyHandListener();
    }

    public void endTurn(ActionEvent event) {
        endTurnButton.setDisable(true);
        myPlayedCards.setDisable(true);
        myHand.setDisable(true);

        if (opponentHand.getChildren().size() < 11) {
            try {
                Node cardBack = FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml")); // Adds a card back picture to Opponents Hand
                opponentHand.add(cardBack, opponentHand.getChildren().size() - 1, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (isServer) {
            if (turnCounter < 10) { // Banana value will be increase until 10 and will be reset after each round
                opponentBanana.setText(Integer.toString(turnCounter));
                myBanana.setText(Integer.toString(++turnCounter));
            } else { // Banana value is capped at 10
                opponentBanana.setText("10");
                myBanana.setText("10");
            }
        } else {
            if (turnCounter < 10) { // Banana value will be increase until 10 and will be reset after each round
                int tmp = turnCounter;
                opponentBanana.setText(Integer.toString(++tmp));
                myBanana.setText(Integer.toString(++turnCounter));
            } else { // Banana value is capped at 10
                opponentBanana.setText("10");
                myBanana.setText("10");
            }
        }

        try {
            if (isServer) {
                Server.output.writeObject("end_turn"); // sending the end turn information to opponent
                Server.output.flush();
            } else {
                Client.output.writeObject("end_turn"); // sending the end turn information to opponent
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

                        myHand.setDisable(true); // MyHand set Disable because next card should be selected form the opponents hand in order to attack
                        opponentPlayedCards.setDisable(false); // Opponents Card are now clickable and can be attacked
                        setOppPlayedCardsEnabled();

                        aCardIsSelected = true;
                        setOpponentPlayedCardsListener();
                        selectedFromTable = true;

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
                        opponentPlayedCards.setDisable(true); // Opponents played cards disabled because Player can not play a card to opponent field

                        VBox vBox = (VBox) node;
                        vBox.setStyle(selectedStyle);

                        putCardFromMyHandToMyTable(); // Playing the card to the field
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
        for (Node node : opponentPlayedCards.getChildren()) { // adds listener to each opponent card
            node.setOnMouseClicked((MouseEvent e) -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    if (selectedFromTable && !selectedFromOpponent) {
//                        VBox vBox = (VBox) node;
//                        vBox.setStyle(selectedStyle);
                        selectedFromOpponent = true;
                        String eventDetails = "hit_card,";
                        getCardHealth(node).setText(String.valueOf(Integer.parseInt(getCardHealth(node).getText()) - Integer.parseInt(getCardDamage(selectedCard).getText()))); // Dealing damage with selected card to opponent card
                        getCardHealth(selectedCard).setText(String.valueOf(Integer.parseInt(getCardHealth(selectedCard).getText()) - Integer.parseInt(getCardDamage(node).getText()))); // Selected cards getting damaged by oppponent card
                        // Defender and attacker names
                        eventDetails += getCardNameLabel(node).getText() + ",";
                        eventDetails += getCardNameLabel(selectedCard).getText();

                        if (Integer.parseInt(getCardHealth(node).getText()) <= 0) { // If opponent card dies
                            opponentPlayedCards.getChildren().remove(1, opponentPlayedCards.getChildren().size());
                            oppPlayedList.remove(node);

                            for (int i = 0; i < oppPlayedList.size(); i++) {
                                opponentPlayedCards.add(oppPlayedList.get(i), i, 0);
                            }

                            selectedFromOpponent = false;
                        }

                        if (Integer.parseInt(getCardHealth(selectedCard).getText()) <= 0) { // If Selected card dies
                            myPlayedCards.getChildren().remove(1, myPlayedCards.getChildren().size());
                            myPlayedList.remove(selectedCard);

                            for (int i = 0; i < myPlayedList.size(); i++) {
                                myPlayedCards.add(myPlayedList.get(i), i, 0);
                            }

                            myHand.setDisable(false);
                            aCardIsSelected = false;
                        }

                        try{
                            if(isServer) {
                                aCardIsSelected = false;
                                selectedFromTable = false;
                                selectedCard.setStyle(notSelectedStyle);
                                selectedCard.setDisable(true);
                                myHand.setDisable(false);

                                Server.output.writeObject(eventDetails);
                                Server.output.flush();
                            } else {
                                aCardIsSelected = false;
                                selectedFromTable = false;
                                selectedCard.setStyle(notSelectedStyle);
                                selectedCard.setDisable(true);
                                myHand.setDisable(false);

                                Client.output.writeObject(eventDetails);
                                Client.output.flush();
                            }
                        } catch (IOException evt){
                            evt.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    private void  disableOpponentPlayedCardListener() {
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
            // Creating the card with the information passed from the socket
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

    private Node setCardDetails(String name, String imageURL, String attack, String health, String banana) {
        try {
            Node newCard = FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml"));
            getCardNameLabel(newCard).setText(name);
            getCardImage(newCard).setImage(urlToImage(imageURL));
            getCardDamage(newCard).setText(attack);
            getCardHealth(newCard).setText(health);
            getCardBanana(newCard).setText(banana);

            return newCard;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Node setCardDetails(Node card) {
        try {
            // Creating the card from the hand
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
    // Getters and Setters
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

    private void listenToOtherPlayer() {
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                while (true) {
                    if (isServer) {
                        // Server Listening
                        try {
                            // Action Cases
                            System.out.println("in server");
                            final String message = (String) Server.input.readObject();
                            if(message.equals("big_yeet")){
                                System.exit(1);
                            }

                            final String[] eventDetails = message.split(",");
                            updateUIElements(eventDetails);
                        } catch (IOException | ClassNotFoundException e) {
                            break;
                        }
                    } else {
                        // Client Listening
                        try {
                            // Action Cases
                            System.out.println("in client");
                            final String message = (String) Client.input.readObject();
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

    private void heroDamageTaken(String[] eventDetails){
        myHealth.setText(Integer.toString(Integer.parseInt(myHealth.getText()) - Integer.parseInt(eventDetails[1])));
        if (Integer.parseInt(myHealth.getText()) <= 0) {
            bigYeet();
        }
    }

    private void cardDamageTaken(String[] eventDetails){
        Node defender = myPlayedCards.getChildren().get(1);
        Node attacker = opponentPlayedCards.getChildren().get(1);

        for(int i = 1 ; i < myPlayedCards.getChildren().size() ;i++){
            defender = myPlayedCards.getChildren().get(i);
            if(getCardNameLabel(defender).getText().equals(eventDetails[1])){
                break;
            }
        }
        for(int i = 1;i<opponentPlayedCards.getChildren().size();i++){
            attacker = opponentPlayedCards.getChildren().get(i);
            if(getCardNameLabel(attacker).getText().equals(eventDetails[2])){
                break;
            }
        }
        getCardHealth(defender).setText(String.valueOf(Integer.parseInt(getCardHealth(defender).getText()) - Integer.parseInt(getCardDamage(attacker).getText())));
        getCardHealth(attacker).setText(String.valueOf(Integer.parseInt(getCardHealth(attacker).getText()) - Integer.parseInt(getCardDamage(defender).getText())));

        if(Integer.parseInt(getCardHealth(defender).getText()) <= 0){
            myPlayedCards.getChildren().remove(1, myPlayedCards.getChildren().size());
            myPlayedList.remove(defender);

            for (int i = 0; i < myPlayedList.size(); i++) {
                myPlayedCards.add(myPlayedList.get(i), i, 0);
            }
        }
        if(Integer.parseInt(getCardHealth(attacker).getText()) <= 0) {
            opponentPlayedCards.getChildren().remove(1, opponentPlayedCards.getChildren().size());
            oppPlayedList.remove(attacker);

            for (int i = 0; i < oppPlayedList.size(); i++) {
                opponentPlayedCards.add(oppPlayedList.get(i), i, 0);
            }
        }

    }

    private void updateUIElements(String[] eventDetails) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                switch (eventDetails[0]) {
                    case "card_play":
                        opponentBanana.setText(Integer.toString(Integer.parseInt(opponentBanana.getText()) - Integer.parseInt(eventDetails[5])));
                        putCardFromOppHandToOppTable(eventDetails);
                        break;

                    case "end_turn":
                        myPlayedCards.setDisable(false);
                        myHand.setDisable(false);
                        endTurnButton.setDisable(false);

                        setMyPlayedCardsEnabled();
                        drawCardFromDeck();
                        break;
                    case "hit_hero":
                        heroDamageTaken(eventDetails);
                        break;
                    case "hit_card":
                        cardDamageTaken(eventDetails);
                        break;
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // adds 5 card back to opponent hand
            opponentHand.add(FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml")), 0, 0);
            opponentHand.add(FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml")), 1, 0);
            opponentHand.add(FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml")), 2, 0);
            opponentHand.add(FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml")), 3, 0);
            opponentHand.add(FXMLLoader.load(getClass().getResource("OpponentHandCardBack.fxml")), 4, 0);

            Deck deck = new Deck();
            if (isServer) {
                // Hero pictures set for server side
                myHero.setImage(new Image("Images/KeanuReeves.png"));
                opponentHero.setImage(new Image("Images/PewDiePie.png"));
                myDeck = deck.getDeck1();
            } else {
                // Hero pictures set for client side
                opponentHero.setImage(new Image("Images/KeanuReeves.png"));
                myHero.setImage(new Image("Images/PewDiePie.png"));

                myPlayedCards.setDisable(true);
                myHand.setDisable(true);
                myDeck = deck.getDeck2();
                endTurnButton.setDisable(true);
            }

            for (int i = 0; i < 5; i++) {
                // Gets 5 card from the deck and adds those cards to the hand
                Node handCard = FXMLLoader.load(getClass().getResource("CardLayoutHand.fxml"));
                getCardNameLabel(handCard).setText(myDeck.get(0).getCardName());
                getCardImage(handCard).setImage(urlToImage(myDeck.get(0).getCardURL()));
                getCardDamage(handCard).setText(Integer.toString(myDeck.get(0).getDamage()));
                getCardHealth(handCard).setText(Integer.toString(myDeck.get(0).getHealth()));
                getCardBanana(handCard).setText(Integer.toString(myDeck.get(0).getBanana()));
                myHandList.add(handCard);
                myHand.add(handCard, i, 0);
                //Removing the card from the deck
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
