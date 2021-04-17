package camera;

import java.util.ArrayList;

public class Create {
    ReadConfig readConfig;
    ArrayList<Shape3D> shapes3D = new ArrayList<>();
    ArrayList<Shape2D> shapes2D = new ArrayList<>();

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

    public void addShape2D(Shape2D shape){
        shapes2D.add(shape);
    }

    public void clearShapes(){
        shapes2D.clear();
    }
}
