package realisation.components.graphic;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import realisation.components.logic.Node;

import java.awt.*;

public class Vertex extends Circle {

    Node node;
    public Point point;
    javafx.scene.control.Label id;
    public boolean isSelected = false;

    public Vertex(double x, double y, double rad, String name, Pane canvas) {
        super(x, y, rad);
        //node = new Node(name, this);
        point = new Point((int) x, (int) y);
        id = new Label(name);
        id.setTextFill(javafx.scene.paint.Color.web("#BFFDE0"));
        canvas.getChildren().add(id);
        id.setLayoutX(x - 20);
        id.setLayoutY(y - 20);
        id.setFont(new Font("Arial", 15));
        this.setId("node");
        this.setFill(Color.web("#11DF6D"));
    }

}