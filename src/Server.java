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

//    public Server() {
//        super("My Instant Messenger");
//        userText = new JTextField();
//        userText.setEditable(false);
//        userText.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        sendMessage(e.getActionCommand());
//                        userText.setText("");
//                    }
//                }
//        );
//        add(userText, BorderLayout.SOUTH);
//        chatWindow = new JTextArea();
//        chatWindow.setEditable(false);
//        add(new JScrollPane(chatWindow));
//        setSize(300, 150);
//        setVisible(true);
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PlayingScreen.fxml"));

        Scene s = new Scene(root);
        primaryStage.setScene(s);
        primaryStage.setTitle("Meme Wars");
        primaryStage.show();
    }

    @Override
    public void stop() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //set up and run the server
//    public void startRunning() {
//        try {
//            server = new ServerSocket(6789, 100);
//            while (true) {
//                try {
//                    waitForConnection();
//                    setupStreams();
//                    whileChatting();
//                } catch (EOFException e) {
////                    showMessage("\n Server ended the connection! ");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    closeCrap();
//                }
//            }
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//    }

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

    //send a message to client
    private void sendMessage(String message) {
        try {
            output.writeObject("SERVER - " + message);
            output.flush();

//            showMessage("\nSERVER - " + message);
        } catch (IOException e) {
//            chatWindow.append("\n ERROR: DUDE I CAN'T SEND THAT MESSAGE");
        }
    }
}
