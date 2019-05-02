package sample.controllers;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.awt.*;

public class MainController {

    private int numberOfNodes = 0;
    @FXML
    private Group canvasGroup;

    @FXML
    public void handle(MouseEvent ev) {

        System.out.println("here" + ev.getEventType());
        numberOfNodes++;
        NodeFX circle = new NodeFX(ev.getX(), ev.getY(), 1, String.valueOf(numberOfNodes));
        canvasGroup.getChildren().add(circle);

        ScaleTransition tr = new ScaleTransition(Duration.millis(100), circle);
        tr.setByX(10f);
        tr.setByY(10f);
        tr.setInterpolator(Interpolator.EASE_OUT);
        tr.play();


    }

    public class NodeFX extends Circle {

        Point point;
        Label id;

        public NodeFX(double x, double y, double rad, String name) {
            super(x, y, rad);
            point = new Point((int) x, (int) y);
            id = new Label(name);
            id.setTextFill(Color.web("#BFFDE0"));

            canvasGroup.getChildren().add(id);
            id.setLayoutX(x - 18);
            id.setLayoutY(y - 18);
            this.setOpacity(0.5);
            this.setId("node");
            this.setFill(Color.web("#20D590"));

        }
    }
}

