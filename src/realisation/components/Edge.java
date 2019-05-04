package realisation.components;

import javafx.scene.shape.Shape;

public class Edge {

    public Node sourse, target;
    public Shape line;

    public Edge(Node sourse, Node target) {
        this.sourse = sourse;
        this.target = target;
    }

    public Edge(Node sourse, Node target, Shape line) {
        this.sourse = sourse;
        this.target = target;
        this.line = line;
    }
}
