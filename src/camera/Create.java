package camera;

import java.util.ArrayList;

public class Create {
    ReadConfig readConfig;
    ArrayList<Line3D> lines3D = new ArrayList<>();
    ArrayList<Line2D> lines2D = new ArrayList<>();

    public ArrayList<Line3D> getLines3D() {
        return lines3D;
    }

    public void setLines3D(ArrayList<Line3D> lines3D) {
        this.lines3D = lines3D;
    }

    public ArrayList<Line2D> getLines2D() {
        return lines2D;
    }

    public void setLines2D(ArrayList<Line2D> lines2D) {
        this.lines2D = lines2D;
    }

    public Create(String path) {
        this.readConfig = new ReadConfig(path);
        this.lines3D = readConfig.ReadLines();
        clearLines();
    }

    public void addLine2D(Line2D line){
        lines2D.add(line);
    }

    public void clearLines(){
        lines2D.clear();
    }
}
