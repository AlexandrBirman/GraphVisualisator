package realisation.components;

import realisation.controllers.MainController;

public class Node {
    public String name;
    public MainController.VertexGraphic circle;

    public Node(String name, MainController.VertexGraphic circle) {
        this.name = name;
        this.circle = circle;
    }
}
