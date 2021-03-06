package realisation.components.graphic;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import static java.lang.Math.sqrt;

public class Line extends Path{
    protected double x1, y1, x2, y2;
    protected float radius;
    protected double startX, startY, endX, endY;
    protected double l;

    private Color lineColor = Color.web("#067d5f");
    private short lineWeight = 3;

    public Line(double x2, double y2, double x1, double y1, float radius) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.radius = radius;

        setStroke(lineColor);
        setStrokeWidth(lineWeight);
        l = sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        startX = (int) x2 - (radius + 2)*((x2-x1)/l);
        startY = (int) y2 - (radius + 2)*((y2-y1)/l);
        endX = (int) x1 - (radius + 2)*((x1-x2)/l);
        endY = (int) y1 - (radius + 2)*((y1-y2)/l);

        getElements().add(new MoveTo(startX, startY));
        getElements().add(new LineTo(endX, endY));
    }
}
