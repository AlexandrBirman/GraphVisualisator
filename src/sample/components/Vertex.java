package sample.components;

import sample.controllers.MainController;

public class Vertex {
    public String name;
    public MainController.VertexGraphic circle;

    public Vertex(String name, MainController.VertexGraphic circle) {
        this.name = name;
        this.circle = circle;
    }
}
