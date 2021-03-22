package camera;

public class Line2D {
    Point2D a,b;

    public Point2D getA() {
        return a;
    }

    @Override
    public String toString() {
        return "Line2D{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    public void setA(Point2D a) {
        this.a = a;
    }

    public Point2D getB() {
        return b;
    }

    public void setB(Point2D b) {
        this.b = b;
    }

    public Line2D(Point2D a, Point2D b) {
        this.a = a;
        this.b = b;
    }
}
