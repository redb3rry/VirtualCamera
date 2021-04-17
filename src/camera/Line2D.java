package camera;

import javafx.util.Pair;

public class Line2D implements Comparable<Line2D> {
    Point2D a, b;
    Shape2D shape;

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

    public Line2D(Point2D a, Point2D b, Shape2D shape) {
        this.a = a;
        this.b = b;
        this.shape = shape;
    }


    @Override
    public int compareTo(Line2D o) {
        Point2D a1 = this.a;
        Point2D b1 = this.b;
        Point2D a2 = o.getA();
        Point2D b2 = o.getB();
        Point2D minYPoint = a1.getY() < b1.getY() ? b1 : a1;
        Point2D minYPoint2 = a2.getY() < b2.getY() ? b2 : a2;
        if (minYPoint.getY() > minYPoint2.getY()) {
            return -1;
        } else if (minYPoint.getY() < minYPoint2.getY()) {
            return 1;
        } else {
            Point2D minXPoint = a1.equals(minYPoint) ? b1 : a1;
            Point2D minXPoint2 = a2.equals(minYPoint2) ? b2 : a2;
            if (minXPoint.getX() < minXPoint2.getX()) {
                return -1;
            } else if (minXPoint.getX() > minXPoint2.getX()) {
                return 1;
            }
        }
        return 0;
    }

    public Pair<Boolean,Double> doCross(double y) {
        if(y < this.a.getY() && y < this.b.getY())
            return new Pair<>(false,0.0);
        if(y > this.a.getY() && y > this.b.getY())
            return new Pair<>(false,0.0);

        if(this.a.getX() - this.b.getX() == 0)
            return new Pair<>(true,this.a.getX());

        double a = (this.a.getY() - this.b.getY()) / (this.a.getX() - this.b.getX());
        double b = this.a.getY() - a * this.a.getX();
        double x = (y - b) / a;

        if(x < this.a.getX() && x < this.b.getX())
            return new Pair<>(false,0.0);
        if(x > this.a.getX() && x > this.b.getX())
            return new Pair<>(false,0.0);

        return new Pair<>(true,x);
    }
}
