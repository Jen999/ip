/*
Handles the main logic of running the program and scanning user input
 */

package davethebrave;

//import davethebrave.ui.Ui;
//import davethebrave.task.TaskManager;
//import davethebrave.storage.Storage;
//import davethebrave.parser.Parser;
//
//import java.util.Arrays;
//import java.util.Scanner;
//import java.util.List;
//
//public class DaveTheBrave {
//    private Storage storage;
//    private TaskManager taskManager;
//    private Ui ui;
//
//    public DaveTheBrave(String filePath) {
//        ui = new Ui();
//
//        storage = new Storage(filePath);
//        taskManager = new TaskManager(storage.loadTasksFromFile(), storage, ui);
//    }
//
//    public void run() {
//        List<String> greetings = Arrays.asList("hello", "hi", "hey", "yo");
//        List<String> goodbyes = Arrays.asList("bye", "goodbye");
//
//        /*
//        Scanner object for user input
//         */
//        Scanner scanner = new Scanner(System.in);
//
//        ui.showWelcome();
//
//        while (true) {
//            /*
//            Read user input
//             */
//            String command = scanner.nextLine();
//
//            /*
//            Exit when user types the command 'bye'
//             */
//            if (goodbyes.contains(command.toLowerCase())) {
//                ui.showGoodbye();
//                break;
//            }
//
//            /*
//            Additional greetings
//             */
//            if (greetings.contains(command.toLowerCase())) {
//                ui.respondHello();
//                continue;
//            }
//
//            // Cheer
//            if (command.equalsIgnoreCase("cheer")) {
//                ui.showCheer();
//                continue;
//            }
//
//            Parser.parseCommand(command, taskManager);
//        }
//        /*
//        Close scanner
//         */
//        scanner.close();
//    }
//    public static void main(String[] args) {
//        new DaveTheBrave("data/davethebrave.txt").run();
//    }
//}


import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import davethebrave.ui.DialogBox;

public class DaveTheBrave extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(DaveTheBrave.class.getResourceAsStream("/images/user.png"));
    private Image daveImage = new Image(DaveTheBrave.class.getResourceAsStream("/images/dave.jpg"));

    @Override
    public void start(Stage stage) {
        //Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        // Handling user input
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        //Formatting the window to look as expected
        stage.setTitle("DaveTheBrave");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        stage.setScene(scene);
        stage.show();

        //More code to be added here later
    }
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(new DialogBox(userInput.getText(), userImage));
        userInput.clear();
    }
}