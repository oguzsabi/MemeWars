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

//    public Client(String host) {
//        super("Client-side");
//        serverIP = host;
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
//
//        add(userText, BorderLayout.SOUTH);
//        chatWindow = new JTextArea();
//        chatWindow.setEditable(false);
//        add(new JScrollPane(chatWindow));
//        setSize(300, 150);
//        setVisible(true);
//    }

    public static void main(String[] args) {
        serverIP = "127.0.0.1";
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
//    public static void startRunning() {
//        try {
//            connectToServer();
//            setupStreams();
////            whileChatting();
//        } catch (EOFException e) {
////            showMessage("\n Client terminated connection!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            closeCrap();
//        }
//    }

    //connect to server
    private static void connectToServer() throws IOException {
//        showMessage("Attempting connection... \n");
        connection = new Socket(InetAddress.getByName(serverIP), 6789);
//        showMessage("Connected to: " + connection.getInetAddress().getHostName());
    }

    //setup streams to send and receive messages
    private static void setupStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
//        showMessage("\n Streams are good to go! \n");
    }

    //while chatting with server
    private void whileChatting() throws IOException {
//        ableToType(true);
        do {
            try {
                message = (String) input.readObject();
//                showMessage("\n" + message);
            } catch (ClassNotFoundException e) {
//                showMessage("I do not know that object type");
            }
        } while (!message.equals("SERVER - END"));
    }

    //close the streams and sockets
    private static void closeConnection() {
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

    //send messages to the server
//    private void sendMessage(String message) {
//        try {
//            output.writeObject("CLIENT - " + message);
//            output.flush();
//            showMessage("\nCLIENT - " + message);
//        } catch (IOException e) {
//            chatWindow.append("\n Something went wrong!");
//        }
//    }

//    //change/update chatWindow
//    private void showMessage(final String message) {
//        SwingUtilities.invokeLater(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        chatWindow.append(message);
//                    }
//                }
//        );
//    }

    //gives user permission to type into the text box
//    private void ableToType(final boolean tof) {
//        SwingUtilities.invokeLater(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        userText.setEditable(tof);
//                    }
//                }
//        );
//    }
}
