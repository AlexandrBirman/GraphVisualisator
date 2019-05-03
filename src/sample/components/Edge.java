package sample.components;

import javafx.scene.shape.Shape;

public class Edge {

    public Vertex sourse, target;
    public Shape line;

    public Edge(Vertex sourse, Vertex target) {
        this.sourse = sourse;
        this.target = target;
    }

    public Edge(Vertex sourse, Vertex target, Shape line) {
        this.sourse = sourse;
        this.target = target;
        this.line = line;
    }
}
