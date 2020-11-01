



import javax.swing.*;





public class PacMan extends JPanel {

    private int pacX = 100;
    private int pacY = 100; //probably need to restrict certain values, such as hitting walls, or hitting the coordinates of ghost
    // how to implement shortest path algo? How to change and keep the ghost moving based on the change in PacMan's coordinates?
    //in case the ghost finds the path, and the movement is restricted by the walls,
    private int speed = 5;

    public PacMan(int pacX, int pacY) {
        this.setFocusable(true);
    }


    public int getPacX() {
        return pacX;
    }

    public void setPacX(int pacX) {
        this.pacX = pacX;
    }

    public int getPacY() {
        return pacY;
    }

    public void setPacY(int pacY) {
        this.pacY = pacY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
