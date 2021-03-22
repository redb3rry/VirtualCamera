package camera;

public class Line3D {
    Point3D a,b;

    public Point3D getA() {
        return a;
    }

    public void setA(Point3D a) {
        this.a = a;
    }

    public Point3D getB() {
        return b;
    }

    @Override
    public String toString() {
        return "Line3D{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    public void setB(Point3D b) {
        this.b = b;
    }

    public Line3D(Point3D a, Point3D b) {
        this.a = a;
        this.b = b;
    }
}
