package camera;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Shape3D {
    ArrayList<Point3D> points;
    Color color;
    double a, b, c, d;

    public ArrayList<Point3D> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point3D> points) {
        this.points = points;
    }

    public Shape3D(ArrayList<Point3D> points, Color color) {
        this.points = points;
        this.color = color;
        setEquation();
    }

    @Override
    public String toString() {
        return "Shape3D{" +
                "points=" + points +
                '}';
    }

    private void setEquation() {
        Point3D A = points.get(0);
        Point3D B = points.get(1);
        Point3D C = points.get(2);

        Point3D AB = new Point3D(A.getX() - B.getX(), A.getY() - B.getY(), A.getZ() - B.getZ());
        Point3D BC = new Point3D(B.getX() - C.getX(), B.getY() - C.getY(), B.getZ() - C.getZ());

        Point3D abc = new Point3D(AB.getY() * BC.getZ() - AB.getZ() * BC.getY(), AB.getZ() * BC.getX() - AB.getX() * BC.getZ(), AB.getX() * BC.getY() - AB.getY() * BC.getX());
        a = abc.getX();
        b = abc.getY();
        c = abc.getZ();
        d = a * A.getX() + b * A.getY() + c * A.getZ();
    }

    public double getZ(double x, double y) {
        return (a*x + b*y - d)/(-1)*c;
    }
}
