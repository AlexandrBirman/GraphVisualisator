package realisation.components.graphic;

import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import static java.lang.Math.sqrt;

public class Arrow extends Line {
    private static final double defaultArrowHeadSize = 15;

    public Arrow(double x1, double y1, double x2, double y2, double arrowHeadSize, float radius) {
        super(x1, y1, x2, y2, radius);

        //ArrowHead
        double angle = Math.atan2((endY - startY), (endX - startX)) - Math.PI / 2.0;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        //point1
        double xx1 = (-1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
        double yy1 = (-1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;
        //point2
        double xx2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
        double yy2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;

        getElements().add(new LineTo(xx1, yy1));
        getElements().add(new LineTo(xx2, yy2));
        getElements().add(new LineTo(endX, endY));
    }

    public Arrow(double startX, double startY, double endX, double endY, float radius) {
        this(startX, startY, endX, endY, defaultArrowHeadSize, radius);
    }
}