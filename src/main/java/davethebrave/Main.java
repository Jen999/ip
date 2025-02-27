package davethebrave;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main function for handling application
 */
public class Main extends Application {
    /*
    Store task list data from past uses
     */
    private DaveTheBrave dave = new DaveTheBrave("data/davethebrave.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("DaveTheBrave");
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);

            fxmlLoader.<MainWindow>getController().setDave(dave);  // inject the Dave instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
