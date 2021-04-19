package camera;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Math.abs;

public class Controller {
    private Camera camera;

    @FXML
    private Canvas viewport;

    public void initialize() {
        prepareBackground();
        camera = new Camera("src/camera/config.txt");
        camera.projectTo2D();
        camera.moveToCenter();
        draw();
    }

    @FXML
    public void readKeys(KeyEvent e) {
        if (camera != null) {
            String key = e.getCode().toString();
            switch (key) {
                case "A":
                    camera.moveLeft();
                    break;
                case "D":
                    camera.moveRight();
                    break;
                case "W":
                    camera.moveForward();
                    break;
                case "S":
                    camera.moveBack();
                    break;
                case "Q":
                    camera.rotateZ(-1);
                    break;
                case "E":
                    camera.rotateZ(1);
                    break;
                case "LEFT":
                    camera.rotateY(-1);
                    break;
                case "RIGHT":
                    camera.rotateY(1);
                    break;
                case "UP":
                    camera.rotateX(-1);
                    break;
                case "DOWN":
                    camera.rotateX(1);
                    break;
                case "SPACE":
                    camera.moveUp();
                    break;
                case "CONTROL":
                    camera.moveDown();
                    break;
                case "EQUALS":
                    camera.zoom(10);
                    break;
                case "MINUS":
                    camera.zoom(-10);
                    break;
            }
//            draw();
            newDraw();
        }
    }

    private void prepareBackground() {
        GraphicsContext gc = viewport.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 800, 800);
    }

    private void draw() {
        prepareBackground();
        GraphicsContext gc = viewport.getGraphicsContext2D();
        gc.setLineWidth(1);
        for (Shape2D shape : camera.getCreator().getShapes2D()) {
            gc.setStroke(shape.color);
            gc.beginPath();
            gc.moveTo(shape.getPoints().get(0).getX(), shape.getPoints().get(0).getY());
            gc.lineTo(shape.getPoints().get(1).getX(), shape.getPoints().get(1).getY());

            gc.moveTo(shape.getPoints().get(1).getX(), shape.getPoints().get(1).getY());
            gc.lineTo(shape.getPoints().get(2).getX(), shape.getPoints().get(2).getY());

            gc.moveTo(shape.getPoints().get(2).getX(), shape.getPoints().get(2).getY());
            gc.lineTo(shape.getPoints().get(0).getX(), shape.getPoints().get(0).getY());
            gc.stroke();
            //System.out.println(shape.getParentShape().getZ(0, 0));
        }
        gc.setStroke(Color.WHITE);
        gc.beginPath();
        gc.moveTo(400, 390);
        gc.lineTo(400, 410);
        gc.moveTo(390, 400);
        gc.lineTo(410, 400);
        gc.stroke();
    }

    private void newDraw() {
        prepareBackground();
        GraphicsContext gc = viewport.getGraphicsContext2D();
        gc.setLineWidth(1);
        for(double y = 801; y>-1; y--) {
            camera.getCreator().sortLines();
            ArrayList<Pair> crosses = new ArrayList<>();
            for (Line2D line : camera.getCreator().getLines()) {
                Pair<Boolean, Double> cross = line.doCross(y);
                if (cross.getKey()) {
                    crosses.add(new Pair(line, cross.getValue()));
                }
            }
            Collections.sort(crosses, new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    Double v1 = (Double) o1.getValue();
                    Double v2 = (Double) o2.getValue();
                    if (v1 > v2) {
                        return 1;
                    } else if (v1 < v2) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
            //System.out.println(crosses);
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            ArrayList<Shape2D> currentShapes = new ArrayList<>();

            for (int i = 0; i < crosses.size(); i++) {
                Pair<Line2D, Double> currentCross = crosses.get(i);
                double x = currentCross.getValue();
                Line2D line = currentCross.getKey();

                if (currentShapes.remove(line.shape)) {
                    if (currentShapes.size() == 0) {
                        continue;
                    }
                } else {
                    currentShapes.add(line.shape);
                }

                if (currentShapes.size() == 1) {
                    Shape2D shapeToColor = currentShapes.get(0);
                    double nextX = (Double) crosses.get(i + 1).getValue();
                    Color color = shapeToColor.color;
                    gc.setStroke(color);
                    gc.beginPath();
                    gc.moveTo(x, y);
                    gc.lineTo(nextX, y);
                    gc.stroke();
                } else {
                    double minZ = Double.MAX_VALUE;
                    Shape2D shapeToColor = line.shape;
                    double nextX = (Double) crosses.get(i + 1).getValue();
                    for (Shape2D shape : currentShapes) {
                        shape.z = shape.getZ((x-400+nextX-400)/(2*camera.getFocalLen()),(y-400)/camera.getFocalLen());
//                        System.out.println(((x)/(camera.getFocalLen())) + " " + (y/ camera.getFocalLen()));
//                        System.out.println(x + " " + nextX);
//                        System.out.println(shape.z + " " + shape.color);
                        if (shape.z < minZ) {
                            minZ = shape.z;
                            shapeToColor = shape;
                        }
                    }
//                    System.out.println("###");
//                    System.out.println(shapeToColor);
//                    double nextX = (Double) crosses.get(i + 1).getValue();
                    Color color = shapeToColor.color;
                    gc.setStroke(color);
                    gc.beginPath();
                    gc.moveTo(x, y);
                    gc.lineTo(nextX, y);
                    gc.stroke();
                }
            }
        }
    }
}
