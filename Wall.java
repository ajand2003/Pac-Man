

public class Wall {
    private int xPosition;
    private int yPosition;
    private int width;
    private int height;

    // Checks for this


    public Wall(int xPosition, int yPosition, int width, int height) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public String toString() {
        return " x: " + this.xPosition + " y: " + this.yPosition + " = width: " + width + " height: " + height;
    }

    public boolean validRange(int x, int y, int direction) {
        if(direction == 0 && ((x >= xPosition && x <= xPosition + width) || (x + 20 >= xPosition && x + 20 <= xPosition + width))
                && y >= yPosition && y <= yPosition + 15) {
           // System.out.println("Collision " + xPosition + " " + yPosition);
            return false;
        }
        else if(direction == 1 && ((x >= xPosition && x <= xPosition + width) || (x + 20 >= xPosition && x + 20 <= xPosition + width))
                && y <= yPosition && y >= yPosition - 25) {
            //System.out.println("Collision " + xPosition + " " + yPosition);
            return false;
        }
        else if(direction == 2 && ((y >= yPosition && y <= yPosition + height) || (y + 20 >= yPosition && y + 20 <= yPosition + height))
                && x >= xPosition - 25 && x <= xPosition) {
            //System.out.println("Collision " + xPosition + " " + yPosition);
            return false;
        }
        else if(direction == 3 && ((y >= yPosition && y <= yPosition + height) || (y + 20 >= yPosition && y + 20 <= yPosition + height))
                && x <= xPosition + 15 && x >= xPosition) {
            //System.out.println("Collision " + xPosition + " " + yPosition);
            return false;
        }
        return true;
    }
}
