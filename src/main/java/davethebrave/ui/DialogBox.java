package davethebrave.ui;

import davethebrave.MainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String s, Image i) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(s);
        displayPicture.setImage(i);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    private void changeDialogStyle(String commandType) {
        if (commandType == null) {
            commandType = "UnknownCommand";
        }

        switch (commandType) {
            case "AddCommand":
                dialog.getStyleClass().add("add-label");
                break;
            case "ChangeMarkCommand":
                dialog.getStyleClass().add("marked-label");
                break;
            case "DeleteCommand":
                dialog.getStyleClass().add("delete-label");
                break;
            case "FindCommand":
                dialog.getStyleClass().add("find-label");
                break;
            case "InvalidCommand":
                dialog.getStyleClass().add("error-label");
                break;
            case "GreetingCommand":
                dialog.getStyleClass().add("greeting-label");
                break;
            case "CheerCommand":
                dialog.getStyleClass().add("cheer-label");
                break;
            case "GoodbyeCommand":
                dialog.getStyleClass().add("goodbye-label");
                break;
            default:
                dialog.getStyleClass().add("default-label");
        }
    }



    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    public static DialogBox getDaveDialog(String s, Image i, String commandType) {
        var db = new DialogBox(s, i);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
}