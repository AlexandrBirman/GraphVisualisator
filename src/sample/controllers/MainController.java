package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import sample.components.Vertex;


import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    private static boolean directed = false,
                          undirected = true,
                          weighted = false,
                          unweighted = true;
    boolean addVertex = true,
            addEdge = false;

    private int numberOfNodes = 0;
    VertexGraphic selectedNode = null;

    private Shape intersect;


    @FXML
    private JFXRadioButton directedRadioButton,
                           undirectedRadioButton,
                           weightedRadioButton,
                           unweightedRadioButton;

    @FXML
    private JFXToggleButton addVertexToggleButton,
                            addEdgeToggleButton;

    @FXML
    private JFXButton resetButton,
                      clearButton;

    @FXML
    private Pane canvas;

    @FXML
    private Line edgeLine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        directedRadioButton.setOnAction(event -> {
            directed = true;
            undirected = false;
            System.out.println("directed");
        });
        undirectedRadioButton.setOnAction(event -> {
            directed = false;
            undirected = true;
            System.out.println("undirected");
        });
        weightedRadioButton.setOnAction(event -> {
            weighted = true;
            unweighted = false;
            System.out.println("weighted");
        });
        unweightedRadioButton.setOnAction(event -> {
            weighted = false;
            unweighted = true;
            System.out.println("unweighted");
        });

        addVertexToggleButton.setSelected(true);
        addEdgeToggleButton.setDisable(true);

    }

    @FXML
    public void addVertexHandle(ActionEvent event){
        addVertex = true;
        addEdge = false;
        addVertexToggleButton.setSelected(true);
        addEdgeToggleButton.setSelected(false);
    }

    @FXML
    public void addEdgeHandle(ActionEvent event){
        addVertex = false;
        addEdge = true;
        addVertexToggleButton.setSelected(false);
        addEdgeToggleButton.setSelected(true);
    }


    @FXML
    public void handle(MouseEvent mouseEvent) {
        if(addVertex) {
            if (numberOfNodes == 1) {
                addEdgeToggleButton.setDisable(false);
            }
            System.out.println("here " + mouseEvent.getEventType());
            numberOfNodes++;
            VertexGraphic circle = new VertexGraphic(mouseEvent.getX(), mouseEvent.getY(), 1, String.valueOf(numberOfNodes));

            canvas.getChildren().add(circle);
            circle.setOnMousePressed(mouseHandler);

            ScaleTransition tr = new ScaleTransition(Duration.millis(100), circle);
            tr.setByX(10f);
            tr.setByY(10f);
            tr.setInterpolator(Interpolator.EASE_OUT);
            tr.play();
        }

    }

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            VertexGraphic circle = (VertexGraphic) mouseEvent.getSource();
            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED && mouseEvent.getButton() == MouseButton.PRIMARY) {
                if (!circle.isSelected) {
                    if (selectedNode != null) {
                        edgeLine = new Line(selectedNode.point.x, selectedNode.point.y, circle.point.x, circle.point.y);
                        edgeLine.setStroke(Color.web("#051B1F"));
                        edgeLine.setStrokeWidth(2);
                        canvas.getChildren().add(edgeLine);
                        edgeLine.setId("line");
                        if (addVertex || addEdge) {
                            selectedNode.isSelected = false;
                            FillTransition ft1 = new FillTransition(Duration.millis(300), selectedNode, Color.WHITESMOKE, Color.web("#11DF6D"));
                            ft1.play();
                        }
                        selectedNode = null;
                        return;

                    }
                    if(!addVertex) {
                        FillTransition ft = new FillTransition(Duration.millis(300), circle, Color.web("#11DF6D"), Color.WHITESMOKE);
                        ft.play();
                        circle.isSelected = true;
                        selectedNode = circle;
                    }
                }
                else {
                    circle.isSelected = false;
                    FillTransition ft1 = new FillTransition(Duration.millis(300), circle, Color.WHITESMOKE, Color.web("#11DF6D"));
                    ft1.play();
                    selectedNode = null;
                }
            }

        }
    };

    public class VertexGraphic extends Circle {

        Vertex vertex;
        Point point;
        Label id;
        boolean isSelected = false;

        public VertexGraphic(double x, double y, double rad, String name) {
            super(x, y, rad);
            vertex = new Vertex(name, this);
            point = new Point((int) x, (int) y);
            id = new Label(name);
            id.setTextFill(Color.web("#BFFDE0"));
            canvas.getChildren().add(id);
            id.setLayoutX(x - 18);
            id.setLayoutY(y - 18);
            this.setOpacity(0.5);
            this.setId("node");
            this.setFill(Color.web("#11DF6D"));

        }
        
    }
}

