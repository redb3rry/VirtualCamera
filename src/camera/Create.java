package camera;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Create {
    ReadConfig readConfig;
    ArrayList<Shape3D> shapes3D = new ArrayList<>();
    ArrayList<Shape2D> shapes2D = new ArrayList<>();
    ArrayList<Line2D> lines = new ArrayList<>();

    public Create(String path) {
        this.readConfig = new ReadConfig(path);
        this.shapes3D = readConfig.ReadLines();
        clearShapes();
    }

    public ArrayList<Shape3D> getShapes3D() {
        return shapes3D;
    }

    public ArrayList<Shape2D> getShapes2D() {
        return shapes2D;
    }

    public ArrayList<Line2D> getLines() {
        return lines;
    }

    public void addShape2D(Shape2D shape){
        shapes2D.add(shape);
        if(shape.getPoints().get(0).getY() != shape.getPoints().get(1).getY())
            lines.add(new Line2D(shape.getPoints().get(0),shape.getPoints().get(1), shape));
        if(shape.getPoints().get(0).getY() != shape.getPoints().get(2).getY())
            lines.add(new Line2D(shape.getPoints().get(0),shape.getPoints().get(2), shape));
        if(shape.getPoints().get(1).getY() != shape.getPoints().get(2).getY())
            lines.add(new Line2D(shape.getPoints().get(1),shape.getPoints().get(2), shape));
    }

    public void clearShapes(){
        shapes2D.clear();
        lines.clear();
    }

    public void sortLines(){
        Collections.sort(lines);
    }
}
