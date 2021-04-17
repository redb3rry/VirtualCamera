package camera;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

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
            draw();
        }
    }

    private void prepareBackground(){
        GraphicsContext gc = viewport.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,800,800);
    }

    private void draw() {
        prepareBackground();
        GraphicsContext gc = viewport.getGraphicsContext2D();
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(1);
        gc.beginPath();
        for(Shape2D shape : camera.getCreator().getShapes2D()){
            gc.setStroke(shape.color);
            gc.moveTo(shape.getPoints().get(0).getX(), shape.getPoints().get(0).getY());
            gc.lineTo(shape.getPoints().get(1).getX(), shape.getPoints().get(1).getY());
            gc.lineTo(shape.getPoints().get(2).getX(), shape.getPoints().get(2).getY());
            gc.lineTo(shape.getPoints().get(0).getX(), shape.getPoints().get(0).getY());
            //System.out.println(shape.getPoints().get(0).getX());
            //System.out.println(shape.getPoints());
            //System.out.println(shape.getParentShape().getPoints());
        }
        gc.stroke();
    }
}
