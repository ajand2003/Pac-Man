import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Pinky {
    private int xPos;
    private int yPos;
    private int direction;
    private final int SCATTERX = 825;
    private final int SCATTERY = 25;
    private final int HOMEX = 375;
    private final int HOMEY = 445;
    private boolean fright;

    public Pinky(int xPos, int yPos, int pinkDirection) {
        this.direction = pinkDirection;
        this.xPos = xPos;
        this.yPos = yPos;
        this.fright =false;
    }

    public boolean isFright() {
        return fright;
    }

    public void setFright(boolean fright) {
        this.fright = fright;
    }

    public void scatter(ArrayList<Wall> listOfWalls, ArrayList<Point> intersections) {
        final int MAXTILE = 160;
        double minDistance = 5000000000.0; //no distance is greater than this 4head
        double upDistance = 500000000.0;
        double downDistance = 5000000.0;
        double leftDistance = 50000000.0;
        double rightDistance = 5000000.0;
        boolean isIntersection = checkIntersection(intersections);
        boolean isRightCollision = checkWallCollision(listOfWalls, 2);
        boolean isLeftCollision = checkWallCollision(listOfWalls, 3);
        boolean isUpCollision = checkWallCollision(listOfWalls, 0);
        boolean isDownCollision = checkWallCollision(listOfWalls, 1);

        if (isIntersection) {
            if (!isRightCollision && this.direction != 3) {
                rightDistance = Math.pow((xPos + 20) - SCATTERX, 2) + Math.pow(yPos - SCATTERY, 2);
            }
            if (!isLeftCollision && this.direction != 2) {
                leftDistance = Math.pow((xPos - 20) - SCATTERX, 2) + Math.pow(yPos - SCATTERY, 2);
            }
            if (!isUpCollision && this.direction != 1) {
                upDistance = Math.pow((xPos) - SCATTERX, 2) + Math.pow((yPos - 20) - SCATTERY, 2);
            }
            if (!isDownCollision && this.direction != 0) {
                downDistance = Math.pow((xPos) - SCATTERX, 2) + Math.pow((yPos + 20) - SCATTERY, 2);
            }
            if (upDistance <= downDistance && upDistance <= leftDistance && upDistance <= rightDistance) {
                this.direction = 0;
            } else if (downDistance <= upDistance && downDistance <= leftDistance && downDistance <= rightDistance) {
                this.direction = 1;
            } else if (rightDistance <= leftDistance && rightDistance <= downDistance && rightDistance <= upDistance) {
                this.direction = 2;
            } else if (leftDistance <= rightDistance && leftDistance <= upDistance && leftDistance <= downDistance) {
                this.direction = 3;
            }


        }

        if (this.direction == 0) {
            yPos = yPos - 5;
        }
        if (this.direction == 1) {
            yPos = yPos + 5;
        }
        if (this.direction == 2) {
            xPos = xPos + 5;
        }
        if (this.direction == 3) {
            xPos = xPos - 5;
        }


    }

    public boolean checkWallCollision(ArrayList<Wall> wallList, int direction) {
        boolean isWallCollision = false;
        for (Wall w1 : wallList) {
            isWallCollision = !w1.validRange(xPos, yPos, direction);
            if (isWallCollision)
                return true;
        }
        return isWallCollision;
    }

    public boolean checkIntersection(ArrayList<Point> intersections) {
        for (Point p : intersections) {
            if (xPos == p.x && yPos == p.y) {
                return true;
            }
        }
        return false;
    }

    public void chase(int targetX, int targetY, ArrayList<Wall> listOfWalls, ArrayList<Point> intersections, int pacDirection) {
        double minDistance = 5000000000.0; //no distance is greater than this 4head
        double upDistance = 500000000.0;
        double downDistance = 5000000.0;
        double leftDistance = 50000000.0;
        double rightDistance = 5000000.0;
        boolean isIntersection = checkIntersection(intersections);
        boolean isRightCollision = checkWallCollision(listOfWalls, 2);
        boolean isLeftCollision = checkWallCollision(listOfWalls, 3);
        boolean isUpCollision = checkWallCollision(listOfWalls, 0);
        boolean isDownCollision = checkWallCollision(listOfWalls, 1);

        if (pacDirection == 0) {
            targetY = targetY - 80;
        }
        if (pacDirection == 1) {
            targetY = targetY + 80;
        }
        if (pacDirection == 2) {
            targetX = targetX + 80;
        }
        if (pacDirection == 3) {
            targetX = targetX - 80;
        }

        if (isIntersection) {
            if (!isRightCollision && this.direction != 3) {
                rightDistance = Math.pow((xPos + 20) - targetX, 2) + Math.pow(yPos - targetY, 2);
            }
            if (!isLeftCollision && this.direction != 2) {
                leftDistance = Math.pow((xPos - 20) - targetX, 2) + Math.pow(yPos - targetY, 2);
            }
            if (!isUpCollision && this.direction != 1) {
                upDistance = Math.pow((xPos) - targetX, 2) + Math.pow((yPos - 20) - targetY, 2);
            }
            if (!isDownCollision && this.direction != 0) {
                downDistance = Math.pow((xPos) - targetX, 2) + Math.pow((yPos + 20) - targetY, 2);
            }
            if (upDistance <= downDistance && upDistance <= leftDistance && upDistance <= rightDistance) {
                this.direction = 0;
            } else if (downDistance <= upDistance && downDistance <= leftDistance && downDistance <= rightDistance) {
                this.direction = 1;
            } else if (rightDistance <= leftDistance && rightDistance <= downDistance && rightDistance <= upDistance) {
                this.direction = 2;
            } else if (leftDistance <= rightDistance && leftDistance <= upDistance && leftDistance <= downDistance) {
                this.direction = 3;
            }


        }

        if (this.direction == 0) {
            yPos = yPos - 5;
        }
        if (this.direction == 1) {
            yPos = yPos + 5;
        }
        if (this.direction == 2) {
            xPos = xPos + 5;
        }
        if (this.direction == 3) {
            xPos = xPos - 5;
        }


    }


    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }


    public void frighten(ArrayList<Wall> listOfWalls, ArrayList<Point> intersections) {    //calls method if pacman hits the mega pellet--- causes ghost movement to be randomized based on setting the random tile at a random position
        Random r = new Random();
        int targetX = r.nextInt(900);
        int targetY = r.nextInt(900);
        double minDistance = 5000000000.0; //no distance is greater than this 4head
        double upDistance = 500000000.0;
        double downDistance = 5000000.0;
        double leftDistance = 50000000.0;
        double rightDistance = 5000000.0;

        boolean isIntersection = checkIntersection(intersections);
        boolean isRightCollision = checkWallCollision(listOfWalls, 2);
        boolean isLeftCollision = checkWallCollision(listOfWalls, 3);
        boolean isUpCollision = checkWallCollision(listOfWalls, 0);
        boolean isDownCollision = checkWallCollision(listOfWalls, 1);

        if (isIntersection) {

            if (!isRightCollision && this.direction != 3) {
                rightDistance = Math.pow((xPos + 20) - targetX, 2) + Math.pow(yPos - targetY, 2);
            }
            if (!isLeftCollision && this.direction != 2) {
                leftDistance = Math.pow((xPos - 20) - targetX, 2) + Math.pow(yPos - targetY, 2);
            }
            if (!isUpCollision && this.direction != 1) {
                upDistance = Math.pow((xPos) - targetX, 2) + Math.pow((yPos - 20) - targetY, 2);
            }
            if (!isDownCollision && this.direction != 0) {
                downDistance = Math.pow((xPos) - targetX, 2) + Math.pow((yPos + 20) - targetY, 2);
            }
            if (upDistance <= downDistance && upDistance <= leftDistance && upDistance <= rightDistance) {
                this.direction = 0;
            } else if (downDistance <= upDistance && downDistance <= leftDistance && downDistance <= rightDistance) {
                this.direction = 1;
            } else if (rightDistance <= leftDistance && rightDistance <= downDistance && rightDistance <= upDistance) {
                this.direction = 2;
            } else if (leftDistance <= rightDistance && leftDistance <= upDistance && leftDistance <= downDistance) {
                this.direction = 3;
            }



        }

        if(this.direction == 0) {
            yPos = yPos - 5;
        }
        if(this.direction ==1) {
            yPos = yPos + 5;
        }
        if(this.direction ==2) {
            xPos = xPos + 5;
        }
        if(this.direction == 3) {
            xPos = xPos -5;
        }
    }

    public void returnHome() {
        setxPos(375);
        setyPos(445);
        this.fright = false;

    }

}
