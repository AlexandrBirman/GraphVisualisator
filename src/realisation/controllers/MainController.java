package realisation.controllers;

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
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.util.Duration;
import realisation.components.graphic.Arrow;
import realisation.components.graphic.Line;
import realisation.components.graphic.Vertex;
import realisation.components.logic.Node;


import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    private boolean directed = false,
                    undirected = true,
                    weighted = false,
                    unweighted = true,

                    addVertex = true,
                    addEdge = false,
                    wasResetBefore = false;

    private float radius = 10f;

    private int numberOfNodes = 0;
    Vertex selectedNode = null;

    private Shape intersection;

    List<Vertex> vertexes = new ArrayList<>();
    List<Shape> edges = new ArrayList<>();

    @FXML
    private Arrow arrow;

    @FXML
    private Label sourceText = new Label("Source"), weight;


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
    public Pane canvas;

    @FXML
    private Line edgeLine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        unweightedRadioButton.setSelected(true);

        undirectedRadioButton.setSelected(true);

        directedRadioButton.setOnAction(event -> {
            if (!directed) {
                resetHandle(event);
                directedRadioButton.setSelected(true);
                directed = true;
                undirected = false;
            }
            System.out.println("directed");
        });
        undirectedRadioButton.setOnAction(event -> {
            if (!undirected) {
                resetHandle(event);
                undirectedRadioButton.setSelected(true);
                directed = false;
                undirected = true;
            }
            System.out.println("undirected");
        });
        weightedRadioButton.setOnAction(event -> {
            if (!weighted) {
                resetHandle(event);
                weightedRadioButton.setSelected(true);
                weighted = true;
                unweighted = false;
            }
            System.out.println("weighted");
        });
        unweightedRadioButton.setOnAction(event -> {
            if (!unweighted) {
                resetHandle(event);
                unweightedRadioButton.setSelected(true);
                weighted = false;
                unweighted = true;
            }
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
            Vertex circle = new Vertex(mouseEvent.getX(), mouseEvent.getY(), 1, String.valueOf(numberOfNodes), canvas);

            canvas.getChildren().add(circle);
            circle.setOnMousePressed(mouseHandler);

            ScaleTransition tr = new ScaleTransition(Duration.millis(100), circle);
            tr.setByX(radius);
            tr.setByY(radius);
            tr.setInterpolator(Interpolator.EASE_OUT);
            tr.play();
        }

    }

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Vertex circle = (Vertex) mouseEvent.getSource();

            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED && mouseEvent.getButton() == MouseButton.PRIMARY) {
                if (!circle.isSelected) {

                    if (selectedNode != null) {

                        if (undirected) {
                            edgeLine = new Line(selectedNode.point.x, selectedNode.point.y, circle.point.x, circle.point.y, radius);
                            canvas.getChildren().add(edgeLine);
                            edgeLine.setId("line");
                        }

                        if (directed){
                            arrow = new Arrow(selectedNode.point.x, selectedNode.point.y, circle.point.x, circle.point.y, radius);
                            canvas.getChildren().add(arrow);
                            arrow.setId("arrow");
                        }

                        if (weighted) {
                            weight = new Label();
                            weight.setLayoutX(((selectedNode.point.x) + (circle.point.x)) / 2);
                            weight.setLayoutY(((selectedNode.point.y) + (circle.point.y)) / 2);
                            weight.setTextFill(Color.web("#BFFDE0"));

                            TextInputDialog dialog = new TextInputDialog("0");
                            dialog.setTitle(null);
                            dialog.setHeaderText("Enter Weight of the Edge :");
                            dialog.setContentText(null);

                            Optional<String> result = dialog.showAndWait();
                            if (result.isPresent()) {
                                weight.setText(result.get());
                            } else {
                                weight.setText("0");
                            }
                            canvas.getChildren().add(weight);
                        }

                        if (unweighted){

                        }

                        selectedNode.isSelected = false;
                        FillTransition ft1 = new FillTransition(Duration.millis(200), selectedNode, Color.RED, Color.web("#068d55"));
                        ft1.play();
                        selectedNode = null;
                        return;
                    }

                    if(addEdge) {
                        FillTransition ft = new FillTransition(Duration.millis(200), circle, Color.web("#068d55"), Color.RED);
                        ft.play();
                        circle.isSelected = true;
                        selectedNode = circle;
                        return;
                    }

                }

                else {
                    FillTransition ft1 = new FillTransition(Duration.millis(200), circle, Color.RED, Color.web("#068d55"));
                    ft1.play();
                    circle.isSelected = false;
                    selectedNode = null;
                }
            }

        }
    };

    @FXML
    public void resetHandle(ActionEvent event) {

        canvas.getChildren().clear();

        numberOfNodes = 0;
        selectedNode = null;

        addVertexToggleButton.setSelected(true);
        addVertexToggleButton.setDisable(false);
        addEdgeToggleButton.setSelected(false);
        addEdgeToggleButton.setDisable(true);
        addVertex = true;
        addEdge = false;


        if(!wasResetBefore) {
            undirectedRadioButton.setSelected(true);
            unweightedRadioButton.setSelected(true);
            weighted = false;
            unweighted = true;
            directed = false;
            undirected = true;
        }
        if (!wasResetBefore)
            wasResetBefore = true;

        System.out.println("RESET");
    }

}

