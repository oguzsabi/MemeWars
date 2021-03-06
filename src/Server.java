import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Application {

    public static ObjectOutputStream output;
    public static ObjectInputStream input;
    public static ServerSocket server;
    public static Socket connection;

    public static void main(String[] args) {
        try {
            PlayingScreen.isServer = true;
            server = new ServerSocket(6789, 100);
            while (true) {
                try {
                    waitForConnection();
                    setupStreams();
                    launch(args);
//                    whileChatting();
                } catch (EOFException e) {
//                    showMessage("\n Server ended the connection! ");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PlayingScreen.fxml"));

        Scene s = new Scene(root);
        primaryStage.setScene(s);
        primaryStage.setTitle("Server");
        primaryStage.show();
    }

    @Override
    public void stop() {
        try {
            closeConnection();
            server.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //wait for connection, then display connection information
    private static void waitForConnection() throws IOException {
        connection = server.accept();
        System.out.println("someone connected");
    }

    //get stream to send and receive data
    private static void setupStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();

        input = new ObjectInputStream(connection.getInputStream());
    }

    //close streams and sockets after you are done
    private static void closeConnection() {
        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
