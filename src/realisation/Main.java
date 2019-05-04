package realisation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = new FXMLLoader(this.getClass().getResource("FXML/Main.fxml")).load();
        //stage.getIcons().add(new Image("applicationIcon.png"));
        stage.setTitle("Graph Visualisator");
        stage.setScene(new Scene(root));
        stage.show();


    }



    public static void main(String[] args) {
        launch(args);
    }
}