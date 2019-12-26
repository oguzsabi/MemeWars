import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Application {
    public static ObjectOutputStream output;
    public static ObjectInputStream input;
    public static String message = "";
    public static String serverIP;
    public static Socket connection;

    public static void main(String[] args) {
        serverIP = "192.168.1.33";
        try {
            connectToServer();
            setupStreams();
//            whileChatting();
            launch(args);
        } catch (EOFException e) {
//            showMessage("\n Client terminated connection!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    //connect to server
    private static void connectToServer() throws IOException { // Server Connection establishing here
//        showMessage("Attempting connection... \n");
        connection = new Socket(InetAddress.getByName(serverIP), 6789); // server IP and server Ports are predefined with values 127.0.0.1 and 6789
//        showMessage("Connected to: " + connection.getInetAddress().getHostName());
    }


    private static void setupStreams() throws IOException { //setup streams to send and receive messages
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
//        showMessage("\n Streams are good to go! \n");
    }


    private static void closeConnection() { //close the streams and sockets
        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PlayingScreen.fxml"));

        Scene s = new Scene(root);
        primaryStage.setScene(s);
        primaryStage.setTitle("Client");
        primaryStage.show();
    }
}

