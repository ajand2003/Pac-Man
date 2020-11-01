import java.awt.*;
import java.util.ArrayList;

public interface Ghost {
    public void scatter(int x, int y);
    public void chase(int x, int y, ArrayList<Wall> walls, ArrayList<Point> intersections);


}
