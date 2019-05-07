package realisation.components.logic;

import realisation.controllers.MainController;

public class Node {
    public String name;
    public MainController.Vertex circle;

    public Node(String name, MainController.Vertex circle) {
        this.name = name;
        this.circle = circle;
    }
}
