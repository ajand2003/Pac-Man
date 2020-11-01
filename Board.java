import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Board extends JPanel {
    private int nextDirection = 0;
    private int pacChomp = 0;
    private final int MAXBOARDX = 1000;
    private final int MAXBOARDY = 500;
    private final int TILE = 10;
    private static int pacX = 475;
    private static int pacY = 595;
    private static int velocity = 5;
    private static int pacDirection = 0;
    private static ArrayList<Wall> listOfWalls = new ArrayList<Wall>();
    private static ArrayList<Point> intersectionPoints;
    private static ArrayList<Point> listOfPellets;
    private static ArrayList<Point> megaPelletPositions;
    private Timer t;
    private Blinky bli;
    private int bliX;   //probably need to implement better OOPS for this
    private int bliY;
    private Clyde cly;
    private Pinky pink;
    private Inky ink;
    private int timer;
    private int score;
    private boolean fright = false;
    private int gameSpeed = 30;

    private JLabel pinkLabel;
    private BufferedImage pinky;
    private BufferedImage blinky;
    private BufferedImage clyde;
    private BufferedImage inky;
    private int counter = 3;
    private boolean gameover;
    private JLabel gameScore;
    private BufferedImage pacMan;


    private JLabel gameStatus;

    public Board(ArrayList<Point> intersectionPoints, ArrayList<Wall> listOfWalls, ArrayList<Point> pellets, ArrayList<Point> megaPelletsPositions) {

        //create a for loop that iterates through every 10 position and check if that tile is a 0 or 1 to determine if pac man can travel there
        // need some way to set a tile in the board and set it to false--
        try {
            // BufferedImage pinky =  ImageIO.read(new File("src/pinky_down.png"));

            this.blinky = ImageIO.read(new File("src/blinky_up.png"));
            this.pinky = ImageIO.read(new File("src/pinky_left.png"));
            this.clyde = ImageIO.read(new File("src/clyde_down.png"));
            this.inky = ImageIO.read(new File("src/inky_right.png"));


            //BufferedImage inky = ImageIO.read(new File("src/inky_down.png"));
            // BufferedImage clyde = ImageIO.read(new File("src/clyde_down.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(pacX + " , " + pacY);
        int[][] wallCollisionCheck;
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.black);
        PacMan pac = new PacMan(pacX, pacY);
        addKeyListener(new Movement());
        this.megaPelletPositions = megaPelletsPositions;
        this.listOfPellets = pellets;
        this.setFocusable(true);
        this.listOfWalls = listOfWalls;
        this.intersectionPoints = intersectionPoints;
        //  t = new Timer(7, run());
        bli = new Blinky(375, 445, 3);
        pink = new Pinky(395, 445, 3);
        cly = new Clyde(415, 445, 3);
        ink = new Inky(435, 445, 3);
        Font font = new Font("Arial", Font.ITALIC, 20);
        gameStatus = new JLabel();
        gameStatus.setFont(font);
        gameStatus.setForeground(Color.red);
        gameStatus.setText("Lives: " + counter);

        gameScore = new JLabel();
        gameScore.setFont(font);
        gameScore.setForeground(Color.green);
        gameScore.setText("Score: " + score);
        //gameStatus.setVisible(true);
        add(gameStatus, BorderLayout.SOUTH);
        add(gameScore);


    }
    public boolean isWinner() {
        return megaPelletPositions.isEmpty() && listOfPellets.isEmpty();
    }

    public void gameReset() {
        counter--;
        gameover = counter == 0;

        unfrightenGhost();
        bli.setFright(false);
        ink.setFright(false);
        pink.setFright(false);
        cly.setFright(false);
        timer = 0;
        this.gameStatus.setText("Lives: " + counter);
        pacX = 475;
        pacY = 595;
        bli.setxPos(375);
        bli.setyPos(445);
        bli.setDirection(3);
        pink.setxPos(395);
        pink.setyPos(445);
        pink.setDirection(3);
        cly.setxPos(415);
        cly.setyPos(445);
        cly.setDirection(3);
        ink.setxPos(435);
        ink.setyPos(445);
        ink.setDirection(3);
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }
    }

    public void unfrightenGhost() {
        try {
            this.blinky = ImageIO.read(new File("src/blinky_up.png"));
            this.pinky = ImageIO.read(new File("src/pinky_left.png"));
            this.inky = ImageIO.read(new File("src/inky_right.png"));
            this.clyde = ImageIO.read(new File("src/clyde_down.png"));
        } catch (Exception e) {

        }
    }
    public void checkPacEat() {
        for (int i = 0; i < listOfPellets.size(); i++) {
            if (pacX <= listOfPellets.get(i).x && listOfPellets.get(i).x <= pacX + 20 && pacY <= listOfPellets.get(i).y && listOfPellets.get(i).y <= pacY + 20) {
                listOfPellets.remove(i);
                score = score + 10;
                break;
            }
        }
    }

    public boolean checkCollisionWithGhost(Blinky bli, Inky ink, Clyde cly, Pinky pink) {   //need to check logic for this
        if (pacX <= bli.getxPos() && bli.getxPos() <= pacX + 20 && pacY <= bli.getyPos() && pacY + 20 >= bli.getyPos()) {
            if (bli.isFright()) {
                bli.returnHome();

            } else {
                gameReset();

            }
            return true;
        }
        if (pacX <= cly.getxPos() && cly.getxPos() <= pacX + 20 && pacY <= cly.getyPos() && pacY + 20 >= cly.getyPos()) {
            if (cly.isFright()) {
                cly.returnHome();

            } else {
                gameReset();

            }
            return true;
        }
        if (pacX <= pink.getxPos() && pink.getxPos() <= pacX + 20 && pacY <= pink.getyPos() && pacY + 20 >= pink.getyPos()) {
            if (pink.isFright()) {
                pink.returnHome();

            } else {
                gameReset();

            }
            return true;
        }
        if (pacX <= ink.getxPos() && ink.getxPos() <= pacX + 20 && pacY <= ink.getyPos() && pacY + 20 >= ink.getyPos()) {
            if (ink.isFright()) {
                ink.returnHome();

            } else {
                gameReset();

            }
            return true;
        }
        return false;
    }


    public void paintComponent(Graphics g) {  //how can i draw this without throwing exceptions
        super.paintComponent(g);


        if (gameover) {
            gameStatus.setText(" GAMEOVER ");
        }
        else if(isWinner()) {
            gameStatus.setText(" OH YEAH HOOOOOOOADDDROGGG ");
        }
        else {
            gameScore.setText("Score: " + score);
            setPreferredSize(new Dimension(800, 800));
            setBackground(Color.black);
            g.setColor(Color.blue);
            //for loops draws the outierer boxes, reading from textfile wall positions in the main method
            for (int i = 0; i < listOfWalls.size(); i++) {
                g.setColor(Color.blue);
                g.fillRect(listOfWalls.get(i).getxPosition(), listOfWalls.get(i).getyPosition(),
                        listOfWalls.get(i).getWidth(), listOfWalls.get(i).getHeight());

            }
            for (int i = 0; i < megaPelletPositions.size(); i++) {
                g.setColor(Color.GREEN);
                g.fillOval(megaPelletPositions.get(i).x, megaPelletPositions.get(i).y, 15, 15);
            }
       /* for(int i = 0; i < intersectionPoints.size(); i++) {
            g.setColor(Color.green); // not important to draw, only for reference to see where intersection points are relative to the ghosts and pacman
            g.fillRect(intersectionPoints.get(i).x, intersectionPoints.get(i).y, 20,20);
        }*/

            for (int i = 0; i < listOfPellets.size(); i++) {
                g.setColor(Color.yellow);
                g.fillOval(listOfPellets.get(i).x, listOfPellets.get(i).y, 10, 10);
            }
            //drawing blinky
            g.drawImage(blinky, bli.getxPos(), bli.getyPos(), 20, 20, null);


            //drawing pacman
            g.drawImage(pacMan,pacX,pacY,20,20,null);

            //drawing pinky
            g.drawImage(pinky, pink.getxPos(), pink.getyPos(), 20, 20, null);

            //drawing clyde
            g.drawImage(clyde, cly.getxPos(), cly.getyPos(), 20, 20, null);

            //drawing inky
            g.drawImage(inky, ink.getxPos(), ink.getyPos(), 20, 20, null);
        }
    }



    public boolean checkCollisionMegaPellet() {
        for (int i = 0; i < megaPelletPositions.size(); i++) {
            if (pacX <= megaPelletPositions.get(i).x && megaPelletPositions.get(i).x <= pacX + 20 && pacY <= megaPelletPositions.get(i).y && megaPelletPositions.get(i).y <= pacY + 20) {
                megaPelletPositions.remove(i);
                score = score + 50;
                fright = true;
                timer = 0;

                bli.setFright(true);
                cly.setFright(true);
                ink.setFright(true);
                pink.setFright(true);
                return true;
            }
        }
        return false;
    }




    public static boolean checkCollision(Wall w1, int x, int y, int direction) {
        return w1.validRange(x, y, direction);
    }

    public void run() {
        while (true) {
            checkCollisionMegaPellet();
            updatePacLocation();
            checkPacEat();
            checkCollisionWithGhost(bli, ink, cly, pink);
            checkBoundaries();


            if (fright && timer < 300) {
                try {
                    if (bli.isFright()) {
                        bli.frighten(listOfWalls, intersectionPoints);
                        this.blinky = ImageIO.read(new File("src/ghost_feared.png"));
                    } else {
                        bli.chase(pacX, pacY, listOfWalls, intersectionPoints);
                        this.blinky = ImageIO.read(new File("src/blinky_up.png"));
                    }
                    if (pink.isFright()) {
                        pink.frighten(listOfWalls, intersectionPoints);
                        this.pinky = ImageIO.read(new File("src/ghost_feared.png"));
                    } else {
                        pink.chase(pacX, pacY, listOfWalls, intersectionPoints, pacDirection);
                        this.pinky = ImageIO.read(new File("src/pinky_left.png"));
                    }
                    if (ink.isFright()) {
                        ink.frighten(listOfWalls, intersectionPoints);
                        this.inky = ImageIO.read(new File("src/ghost_feared.png"));
                    } else {
                        ink.chase(pacX, pacY, pacDirection, listOfWalls, intersectionPoints, bli);
                        this.inky = ImageIO.read(new File("src/inky_right.png"));
                    }
                    if (cly.isFright()) {
                        cly.frighten(listOfWalls, intersectionPoints);
                        this.clyde = ImageIO.read(new File("src/ghost_feared.png"));
                    } else {
                        cly.chase(pacX, pacY, listOfWalls, intersectionPoints);
                        this.clyde = ImageIO.read(new File("src/clyde_down.png"));
                    }
                } catch (Exception e) {

                }
            }

            if (fright && (timer >= 300)) {
                timer = 0;
                fright = false;
                bli.setFright(false);
                cly.setFright(false);
                ink.setFright(false);
                pink.setFright(false);
                try {
                    this.blinky = ImageIO.read(new File("src/blinky_up.png"));
                    this.pinky = ImageIO.read(new File("src/pinky_left.png"));
                    this.clyde = ImageIO.read(new File("src/clyde_down.png"));
                    this.inky = ImageIO.read(new File("src/inky_right.png"));

                } catch (Exception e) {

                }
            }
            if (timer >= 500 && !fright) {
                bli.scatter(listOfWalls, intersectionPoints);
                cly.scatter(listOfWalls, intersectionPoints);
                pink.scatter(listOfWalls, intersectionPoints);
                ink.scatter(listOfWalls, intersectionPoints);
                repaint();
                if (timer >= 901) {
                    timer = 0;
                }
            } else if (!fright && timer < 500) {
                bli.chase(pacX, pacY, listOfWalls, intersectionPoints);
                pink.chase(pacX, pacY, listOfWalls, intersectionPoints, pacDirection);
                cly.chase(pacX, pacY, listOfWalls, intersectionPoints);
                ink.chase(pacX, pacY, pacDirection, listOfWalls, intersectionPoints, bli);
            }
            repaint();
            if (gameover || isWinner()) {
                break;
            }

            try {
                Thread.sleep(gameSpeed);
                pacChomp++;
                if(pacChomp < 5) {
                        pacMan = ImageIO.read(new File("src/pac_close.png"));


                }
                else if(pacChomp < 10 && pacChomp >= 5) {
                    if(pacDirection ==0) pacMan = ImageIO.read(new File("src/pac_up.png"));
                    if(pacDirection ==1) pacMan = ImageIO.read(new File("src/pac_down.png"));
                    if(pacDirection ==2) pacMan = ImageIO.read(new File("src/pac_right.png"));
                    if(pacDirection ==3) pacMan = ImageIO.read(new File("src/pac_left.png"));


                }
                else {
                    if(pacDirection ==0) pacMan = ImageIO.read(new File("src/pac_up_full.png"));
                    if(pacDirection ==1) pacMan = ImageIO.read(new File("src/pac_down_full.png"));
                    if(pacDirection ==2) pacMan = ImageIO.read(new File("src/pac_right_full.png"));
                    if(pacDirection ==3) pacMan = ImageIO.read(new File("src/pac_left_full.png"));
                    pacChomp = 0;
                }
                timer++;
            } catch (Exception e) {

            }
        }
    }

    private void checkBoundaries() {
        if (pacX <= 5 && pacY == 415 && pacDirection == 3) {
            pacX = 845;
            pacY = 415;
        }
        if (pacX >= 845 && pacY == 415 && pacDirection == 2) {
            pacX = 5;
            pacY = 415;
        }
        if (bli.getxPos() <= 5 && bli.getyPos() == 415 && bli.getDirection() == 3) {
            bli.setxPos(845);
            bli.setyPos(415);
        }
        if (bli.getxPos() == 845 && bli.getyPos() == 415 && bli.getDirection() == 2) {
            bli.setxPos(5);
            bli.setyPos(415);
        }
        if (pink.getxPos() <= 5 && pink.getyPos() == 415 && pink.getDirection() == 3) {
            pink.setxPos(845);
            pink.setyPos(415);
        }
        if (pink.getxPos() == 845 && pink.getyPos() == 415 && pink.getDirection() == 2) {
            pink.setxPos(5);
            pink.setyPos(415);
        }
        if (cly.getxPos() <= 5 && cly.getyPos() == 415 && cly.getDirection() == 3) {
            cly.setxPos(845);
            cly.setyPos(415);
        }
        if (cly.getxPos() == 845 && cly.getyPos() == 415 && cly.getDirection() == 2) {
            cly.setxPos(5);
            cly.setyPos(415);
        }
        if (ink.getxPos() <= 5 && ink.getyPos() == 415 && ink.getDirection() == 3) {
            ink.setxPos(845);
            ink.setyPos(415);
        }
        if (ink.getxPos() == 845 && ink.getyPos() == 415 && ink.getDirection() == 2) {
            ink.setxPos(5);
            ink.setyPos(415);
        }
    }

    public static ArrayList<Wall> getListOfWalls() {
        return listOfWalls;
    }

    public static void setListOfWalls(ArrayList<Wall> listOfWalls) {
        Board.listOfWalls = listOfWalls;
    }

    public static ArrayList<Point> getIntersectionPoints() {
        return intersectionPoints;
    }

    public static void setIntersectionPoints(ArrayList<Point> intersectionPoints) {
        Board.intersectionPoints = intersectionPoints;
    }

    public void updatePacLocation() {
        velocity = 5;
        boolean isIntersection;
        if ((nextDirection == 0 && pacDirection == 1) ||
                (nextDirection == 1 && pacDirection == 0) || (nextDirection == 2 && pacDirection == 3) ||
                (nextDirection == 3 && pacDirection == 2)) {
            pacDirection = nextDirection;
        }
        for (Point p : intersectionPoints) {
            if (pacX == p.x && pacY == p.y) {
                pacDirection = nextDirection;
                break;
            }
        }

        for (int i = 0; i < listOfWalls.size(); i++) {
            if (!checkCollision(listOfWalls.get(i), pacX, pacY, pacDirection)) {
                velocity = 0;
                break;
            }
        }

        if (pacDirection == 0) {   //0 = up
            pacY -= velocity;
        } else if (pacDirection == 1) { //1 = down
            pacY += velocity;
        } else if (pacDirection == 2) { // 2 = right
            pacX += velocity;
        } else if (pacDirection == 3) { //3 = left
            pacX -= velocity;
        }
    }

    private class Movement implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                nextDirection = 3;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                nextDirection = 2;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                nextDirection = 1;
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                nextDirection = 0;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) { }
    }
}

