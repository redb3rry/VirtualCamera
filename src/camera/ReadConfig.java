package camera;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ReadConfig {
    File file;
    Scanner scanner;
    ArrayList<Line3D> lines3D = new ArrayList<Line3D>();

    public ReadConfig(String path) {
        file = new File(path);
        try {
            scanner = new Scanner(file).useLocale(Locale.ENGLISH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Line3D> ReadLines(){
        double x=0, y=0, z=0;
        Point3D a = new Point3D(x,y,z), b = new Point3D(x,y,z);
        while(scanner.hasNext()){
            for(int p = 0; p < 2; p++) {
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
                if(p == 0){
                    a = new Point3D(x,y,z);
                } else{
                    b = new Point3D(x,y,z);
                }
            }
            lines3D.add(new Line3D(a,b));
        }

        return lines3D;
    }

}
