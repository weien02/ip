import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import qwerty.ui.Qwerty;

/**
 * A GUI for Qwerty using FXML.
 */
public class Main extends Application {

    private Qwerty qwerty = new Qwerty("./data/Qwerty.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Qwerty");
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setQwerty(qwerty);  // inject the Qwerty instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
