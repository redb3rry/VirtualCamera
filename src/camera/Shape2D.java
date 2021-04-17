package camera;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Shape2D {
    ArrayList<Point2D> points;
    Color color;
    Boolean in = false;

    Shape3D parentShape;

    public ArrayList<Point2D> getPoints() {
        return points;
    }

    public Shape3D getParentShape() {
        return parentShape;
    }

    public void setPoints(ArrayList<Point2D> points) {
        this.points = points;
    }

    public Shape2D(ArrayList<Point2D> points, Shape3D parentShape) {
        this.points = points;
        this.color = parentShape.color;
        this.parentShape = parentShape;
    }

    @Override
    public String toString() {
        return "Shape2D{" +
                "points=" + points +
                '}';
    }

    public double getZ(double x, double y){
        return parentShape.getZ(x, y);
    }
}
