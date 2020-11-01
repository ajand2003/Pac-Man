

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Wall> wallPositions = new ArrayList<Wall>();
        ArrayList<Point> megaPelletsPositions = new ArrayList<Point>();
        ArrayList<Point> intersectionPositions = new ArrayList<Point>();
        ArrayList<Point> listOfPellets = new ArrayList<Point>();
        Scanner scanIntersections = new Scanner(new File("src/Intersection"));
        Scanner scan = new Scanner(new File("src/wallPositions"));
        Scanner scanPellets = new Scanner(new File("src/Pellets"));
        Scanner scanMegaPellets = new Scanner(new File("src/MegaPellets"));
        while(scanMegaPellets.hasNext()) {
            int x = Integer.parseInt(scanMegaPellets.next());
            int y = Integer.parseInt(scanMegaPellets.next());
            Point p = new Point(x,y);
            megaPelletsPositions.add(p);

        }
        while(scanPellets.hasNext()) {
            int x = Integer.parseInt(scanPellets.next());
            int y = Integer.parseInt(scanPellets.next());
            Point p = new Point(x,y);
            listOfPellets.add(p);

        }
        while (scan.hasNext()) {
            for (int i = 0; i < 4; i++) {
                if (!scan.hasNext()) {
                    break;
                }
                int x = Integer.parseInt(scan.next());
                int y = Integer.parseInt(scan.next());
                int width = Integer.parseInt(scan.next());
                int height = Integer.parseInt(scan.next());
                Wall w1 = new Wall(x, y, width, height);
                wallPositions.add(w1);


            }
        }
        scan.close();

        while (scanIntersections.hasNext()) {
            for (int i = 0; i < 4; i++) {
                if (!scanIntersections.hasNext()) {
                    break;
                }
                int x = Integer.parseInt(scanIntersections.next());
                int y = Integer.parseInt(scanIntersections.next());
                Point point = new Point(x, y);
                intersectionPositions.add(point);
            }
        }
        scanIntersections.close();

        Board board = new Board(intersectionPositions, wallPositions, listOfPellets, megaPelletsPositions);
        JFrame frame = new JFrame("Pac-man");
        frame.setSize(new Dimension(820, 800));
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(board);
        frame.setVisible(true);


        board.run();


    }
}

