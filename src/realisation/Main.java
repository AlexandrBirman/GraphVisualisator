package realisation;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = new FXMLLoader(this.getClass().getResource("FXML/Main.fxml")).load();
        stage.getIcons().add(new Image("res/images/icon.png"));
        stage.setTitle("Graph Visualisator");
        stage.setScene(new Scene(root));
        stage.setMinWidth(1200);
        stage.setMinHeight(800);
        //stage.setFullScreenExitHint("");
        //stage.setFullScreen(true);
        //stage.setMaximized(true);
        stage.show();


    }



    public static void main(String[] args) {
        launch(args);
    }
}