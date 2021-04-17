package camera;

import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class ReadConfig {
    File file;
    Scanner scanner;
    ArrayList<Shape3D> shapes3D = new ArrayList<Shape3D>();

    public ReadConfig(String path) {
        file = new File(path);
        try {
            scanner = new Scanner(file).useLocale(Locale.ENGLISH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Shape3D> ReadLines() {
        double x = 0, y = 0, z = 0;
        Point3D a = new Point3D(x, y, z), b = new Point3D(x, y, z), c = new Point3D(x, y, z);
        while (scanner.hasNext()) {
            for (int p = 0; p < 3; p++) {
                for (int i = 0; i < 3; i++) {
                    float r = scanner.nextFloat();
                    if (i == 0) {
                        x = r;
                    } else if (i == 1) {
                        y = r;
                    } else {
                        z = r;
                    }
                }
                if (p == 0) {
                    a = new Point3D(x, y, z);
                } else if (p == 1) {
                    b = new Point3D(x, y, z);
                } else {
                    c = new Point3D(x, y, z);
                }
            }
            ArrayList<Point3D> points = new ArrayList<>();
            points.add(a);
            points.add(b);
            points.add(c);
            String clr = scanner.next();
            Color color = Color.web(clr);
            shapes3D.add(new Shape3D(points, color));
        }
        return shapes3D;
    }

}
