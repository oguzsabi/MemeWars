import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    // This class is not being used
    public static void main(String[] args) {
        Event event = new Event(new User());
        DefaultCards defaultCards = new DefaultCards();
//        //System.out.println("Card: 0 " + defaultCards.cards.get(0));
//        System.out.println("Card: 1 " + defaultCards.cards.get(1));
//        System.out.println("Card: 2 " + defaultCards.cards.get(2));
//
//        //event.play(defaultCards.cards.get(0), defaultCards.cards.get(0));
//        event.play(defaultCards.cards.get(1),defaultCards.cards.get(2));
//        event.play(defaultCards.cards.get(2), defaultCards.cards.get(2));
//        //event.battle(defaultCards.cards.get(1), defaultCards.cards.get(2));
//
//        //System.out.println("Card: 0 " + defaultCards.cards.get(0));
//        System.out.println("Card: 1 " + defaultCards.cards.get(1));
//        System.out.println("Card: 2 " + defaultCards.cards.get(2));

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PlayingScreen.fxml"));

        Scene s = new Scene(root);
        primaryStage.setScene(s);
        primaryStage.setTitle("Meme Wars");
        primaryStage.show();
    }
}
