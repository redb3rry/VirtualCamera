package camera;

import java.lang.Math;
import java.nio.Buffer;

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
        for( Line2D line : creator.getLines2D()){
            Point2D a = line.getA();
            Point2D b = line.getB();

            a.setX(a.getX()+400.0);
            a.setY(a.getY()+400.0);
            b.setX(b.getX()+400.0);
            b.setY(b.getY()+400.0);
        }
    }

    public void moveUp() {
        for (Line3D line : creator.getLines3D()) {
            Point3D a = line.getA();
            Point3D b = line.getB();
            a.setY(a.getY() + step);
            b.setY(b.getY() + step);
            projectTo2D();
        }
    }

    public void moveDown() {
        for (Line3D line : creator.getLines3D()) {
            Point3D a = line.getA();
            Point3D b = line.getB();
            a.setY(a.getY() - step);
            b.setY(b.getY() - step);
            projectTo2D();
        }
    }

    public void moveLeft() {
        for (Line3D line : creator.getLines3D()) {
            Point3D a = line.getA();
            Point3D b = line.getB();
            a.setX(a.getX() + step);
            b.setX(b.getX() + step);
            projectTo2D();
        }

    }

    public void moveRight() {
        for (Line3D line : creator.getLines3D()) {
            Point3D a = line.getA();
            Point3D b = line.getB();
            a.setX(a.getX() - step);
            b.setX(b.getX() - step);
            projectTo2D();
        }
    }

    public void moveForward() {
        for (Line3D line : creator.getLines3D()) {
            Point3D a = line.getA();
            Point3D b = line.getB();
            a.setZ(a.getZ() - step);
            b.setZ(b.getZ() - step);
            projectTo2D();
        }

    }

    public void moveBack() {
        for (Line3D line : creator.getLines3D()) {
            Point3D a = line.getA();
            Point3D b = line.getB();
            a.setZ(a.getZ() + step);
            b.setZ(b.getZ() + step);
            projectTo2D();
        }
    }

    public void rotateY(double theta) {
        double sinT = Math.sin(Math.toRadians(theta));
        double cosT = Math.cos(Math.toRadians(theta));
        for (Line3D line : creator.getLines3D()) {
            Point3D a = line.getA();
            Point3D b = line.getB();
            a.setX(a.getX() * cosT - a.getZ() * sinT);
            a.setZ(a.getZ() * cosT + a.getX() * sinT);

            b.setX(b.getX() * cosT - b.getZ() * sinT);
            b.setZ(b.getZ() * cosT + b.getX() * sinT);
            projectTo2D();
        }

    }

    public void rotateX(double theta) {
        double sinT = Math.sin(Math.toRadians(theta));
        double cosT = Math.cos(Math.toRadians(theta));
        for (Line3D line : creator.getLines3D()) {
            Point3D a = line.getA();
            Point3D b = line.getB();
            a.setY(a.getY() * cosT - a.getZ() * sinT);
            a.setZ(a.getZ() * cosT + a.getY() * sinT);

            b.setY(b.getY() * cosT - b.getZ() * sinT);
            b.setZ(b.getZ() * cosT + b.getY() * sinT);
            projectTo2D();
        }
    }


    public void rotateZ(double theta) {
        double sinT = Math.sin(Math.toRadians(theta));
        double cosT = Math.cos(Math.toRadians(theta));
        for (Line3D line : creator.getLines3D()) {
            Point3D a = line.getA();
            Point3D b = line.getB();
            a.setX(a.getX() * cosT - a.getY() * sinT);
            a.setY(a.getY() * cosT + a.getX() * sinT);

            b.setX(b.getX() * cosT - b.getY() * sinT);
            b.setY(b.getY() * cosT + b.getX() * sinT);
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
        creator.clearLines();
        for (Line3D line : creator.getLines3D()) {
            double x1, x2, y1, y2;
            double z1 = line.getA().getZ();
            double z2 = line.getB().getZ();
            if (z1 > 1) {
                x1 = line.getA().getX() * focalLen / z1;
                y1 = line.getA().getY() * focalLen / z1;
            } else {
                x1 = line.getA().getX() * focalLen;
                y1 = line.getA().getY() * focalLen;
            }
            if (z2 > 1) {
                x2 = line.getB().getX() * focalLen / z2;
                y2 = line.getB().getY() * focalLen / z2;
            } else {
                x2 = line.getB().getX() * focalLen;
                y2 = line.getB().getY() * focalLen;
            }
            creator.addLine2D(new Line2D(new Point2D(x1, y1), new Point2D(x2, y2)));
        }
        moveToCenter();
    }
}
