package realisation.components.logic;

import realisation.components.graphic.Vertex;
import realisation.controllers.MainController;

public class Node {
    public String name;
    public Vertex circle;

    public Node(String name, Vertex circle) {
        this.name = name;
        this.circle = circle;
    }
}
