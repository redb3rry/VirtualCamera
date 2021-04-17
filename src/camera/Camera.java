package camera;

import java.lang.Math;
import java.util.ArrayList;

public class Camera {
    private Create creator;
    private double focalLen = 400;
    private final double step = 10;

    public Create getCreator() {
        return creator;
    }

    public void setCreator(Create creator) {
        this.creator = creator;
    }

    public double getFocalLen() {
        return focalLen;
    }

    public void setFocalLen(double focalLen) {
        this.focalLen = focalLen;
    }

    public Camera(String path) {
        this.creator = new Create(path);
    }

    public void moveToCenter() {
        for (Shape2D shape : creator.getShapes2D()) {
            Point2D a = shape.getPoints().get(0);
            Point2D b = shape.getPoints().get(1);
            Point2D c = shape.getPoints().get(2);

            a.setX(a.getX()+400.0);
            a.setY(a.getY()+400.0);
            b.setX(b.getX()+400.0);
            b.setY(b.getY()+400.0);
            c.setX(c.getX()+400.0);
            c.setY(c.getY()+400.0);
        }
    }

    public void moveUp() {
        for (Shape3D shape : creator.getShapes3D()) {
            Point3D a = shape.getPoints().get(0);
            Point3D b = shape.getPoints().get(1);
            Point3D c = shape.getPoints().get(2);
            a.setY(a.getY() + step);
            b.setY(b.getY() + step);
            c.setY(c.getY() + step);
            projectTo2D();
        }
    }

    public void moveDown() {
        for (Shape3D shape : creator.getShapes3D()) {
            Point3D a = shape.getPoints().get(0);
            Point3D b = shape.getPoints().get(1);
            Point3D c = shape.getPoints().get(2);
            a.setY(a.getY() - step);
            b.setY(b.getY() - step);
            c.setY(c.getY() - step);
            projectTo2D();
        }
    }

    public void moveLeft() {
        for (Shape3D shape : creator.getShapes3D()) {
            Point3D a = shape.getPoints().get(0);
            Point3D b = shape.getPoints().get(1);
            Point3D c = shape.getPoints().get(2);
            a.setX(a.getX() + step);
            b.setX(b.getX() + step);
            c.setX(c.getX() + step);
            projectTo2D();
        }

    }

    public void moveRight() {
        for (Shape3D shape : creator.getShapes3D()) {
            Point3D a = shape.getPoints().get(0);
            Point3D b = shape.getPoints().get(1);
            Point3D c = shape.getPoints().get(2);
            a.setX(a.getX() - step);
            b.setX(b.getX() - step);
            c.setX(c.getX() - step);
            projectTo2D();
        }
    }

    public void moveForward() {
        for (Shape3D shape : creator.getShapes3D()) {
            Point3D a = shape.getPoints().get(0);
            Point3D b = shape.getPoints().get(1);
            Point3D c = shape.getPoints().get(2);
            a.setZ(a.getZ() - step);
            b.setZ(b.getZ() - step);
            c.setZ(c.getZ() - step);
            projectTo2D();
        }

    }

    public void moveBack() {
        for (Shape3D shape : creator.getShapes3D()) {
            Point3D a = shape.getPoints().get(0);
            Point3D b = shape.getPoints().get(1);
            Point3D c = shape.getPoints().get(2);
            a.setZ(a.getZ() + step);
            b.setZ(b.getZ() + step);
            c.setZ(c.getZ() + step);
            projectTo2D();
        }
    }

    public void rotateY(double theta) {
        double sinT = Math.sin(Math.toRadians(theta));
        double cosT = Math.cos(Math.toRadians(theta));
        for (Shape3D shape : creator.getShapes3D()) {
            Point3D a = shape.getPoints().get(0);
            Point3D b = shape.getPoints().get(1);
            Point3D c = shape.getPoints().get(2);

            a.setX(a.getX() * cosT - a.getZ() * sinT);
            a.setZ(a.getZ() * cosT + a.getX() * sinT);

            b.setX(b.getX() * cosT - b.getZ() * sinT);
            b.setZ(b.getZ() * cosT + b.getX() * sinT);

            c.setX(c.getX() * cosT - c.getZ() * sinT);
            c.setZ(c.getZ() * cosT + c.getX() * sinT);
            projectTo2D();
        }

    }

    public void rotateX(double theta) {
        double sinT = Math.sin(Math.toRadians(theta));
        double cosT = Math.cos(Math.toRadians(theta));

        for (Shape3D shape : creator.getShapes3D()) {
            Point3D a = shape.getPoints().get(0);
            Point3D b = shape.getPoints().get(1);
            Point3D c = shape.getPoints().get(2);

            a.setY(a.getY() * cosT - a.getZ() * sinT);
            a.setZ(a.getZ() * cosT + a.getY() * sinT);

            b.setY(b.getY() * cosT - b.getZ() * sinT);
            b.setZ(b.getZ() * cosT + b.getY() * sinT);

            c.setY(c.getY() * cosT - c.getZ() * sinT);
            c.setZ(c.getZ() * cosT + c.getY() * sinT);

            projectTo2D();
        }
    }


    public void rotateZ(double theta) {
        double sinT = Math.sin(Math.toRadians(theta));
        double cosT = Math.cos(Math.toRadians(theta));
        for (Shape3D shape : creator.getShapes3D()) {
            Point3D a = shape.getPoints().get(0);
            Point3D b = shape.getPoints().get(1);
            Point3D c = shape.getPoints().get(2);

            a.setX(a.getX() * cosT - a.getY() * sinT);
            a.setY(a.getY() * cosT + a.getX() * sinT);

            b.setX(b.getX() * cosT - b.getY() * sinT);
            b.setY(b.getY() * cosT + b.getX() * sinT);

            c.setX(c.getX() * cosT - c.getY() * sinT);
            c.setY(c.getY() * cosT + c.getX() * sinT);

            projectTo2D();
        }
    }

    public void zoom(double x){
        focalLen+=x;
        if( focalLen > 1000){
            focalLen = 1000;
        } else if(focalLen < 10){
            focalLen = 10;
        }
        projectTo2D();
    }


    public void projectTo2D() {
        creator.clearShapes();
        for (Shape3D shape : creator.getShapes3D()) {
            double x1, x2, x3, y1, y2, y3;
            double z1 = shape.getPoints().get(0).getZ();
            double z2 = shape.getPoints().get(1).getZ();
            double z3 = shape.getPoints().get(2).getZ();
            if (z1 > 1) {
                x1 = shape.getPoints().get(0).getX() * focalLen / z1;
                y1 = shape.getPoints().get(0).getY() * focalLen / z1;
            } else {
                x1 = shape.getPoints().get(0).getX() * focalLen;
                y1 = shape.getPoints().get(0).getY() * focalLen;
            }
            if (z2 > 1) {
                x2 = shape.getPoints().get(1).getX() * focalLen / z2;
                y2 = shape.getPoints().get(1).getY() * focalLen / z2;
            } else {
                x2 = shape.getPoints().get(1).getX() * focalLen;
                y2 = shape.getPoints().get(1).getY() * focalLen;
            }
            if (z3 > 1) {
                x3 = shape.getPoints().get(2).getX() * focalLen / z3;
                y3 = shape.getPoints().get(2).getY() * focalLen / z3;
            } else {
                x3 = shape.getPoints().get(2).getX() * focalLen;
                y3 = shape.getPoints().get(2).getY() * focalLen;
            }
            ArrayList<Point2D> points = new ArrayList<>();
            points.add(new Point2D(x1, y1));
            points.add(new Point2D(x2, y2));
            points.add(new Point2D(x3, y3));
            creator.addShape2D(new Shape2D(points, shape));
        }
        moveToCenter();
    }
}
