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

    public void initialize(){
        prepareBackground();
        camera = new Camera("src/camera/config.txt");
        camera.projectTo2D();
        camera.moveToCenter();
        draw();
    }

    @FXML
    public void readKeys(KeyEvent e){
        if(camera != null){
            String key = e.getCode().toString();
            switch (key) {
                case "A" -> camera.moveLeft();
                case "D" -> camera.moveRight();
                case "W" -> camera.moveForward();
                case "S" -> camera.moveBack();
                case "Q" -> camera.rotateZ(-1);
                case "E" -> camera.rotateZ(1);
                case "LEFT" -> camera.rotateY(-1);
                case "RIGHT" -> camera.rotateY(1);
                case "UP" -> camera.rotateX(-1);
                case "DOWN" -> camera.rotateX(1);
                case "SPACE" -> camera.moveUp();
                case "CONTROL" -> camera.moveDown();
                case "EQUALS" -> camera.zoom(10);
                case "MINUS" -> camera.zoom(-10);
            }
            draw();
        }
    }

    private void prepareBackground(){
        GraphicsContext gc = viewport.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillRect(0,0,800,800);
    }

    private void draw(){
        prepareBackground();
        GraphicsContext gc = viewport.getGraphicsContext2D();
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(1);
        gc.beginPath();
        for(Line2D line : camera.getCreator().getLines2D()){
            gc.moveTo(line.getA().getX(), line.getA().getY());
            gc.lineTo(line.getB().getX(), line.getB().getY());
        }
        gc.stroke();
    }
}
