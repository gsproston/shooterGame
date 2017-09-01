package shootergame;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class background {
    public float xpos, ypos;
    public static float changex, changey;
    private int numSpaces[], numDoors[];
    private door door[][];
    private String path[];
    private Rectangle space[][];
    private int numBackgrounds = 3;
    private int maxDoors = 2;
    private int maxSpaces = 0;
    background() {
        path = new String[numBackgrounds];
        numDoors = new int[numBackgrounds];
        door = new door[numBackgrounds][maxDoors];
        numSpaces = new int[numBackgrounds];
        space = new Rectangle[numBackgrounds][maxSpaces];
        changex = 0F;
        changey = 0F;
        xpos = 252F;
        ypos = 130F;
        
        //start array
        
        //room
        path[0] = System.getProperty("user.dir")+"/sprites/background/room.png";
        numDoors[0] = 2;
        door[0][0] = new door(280, 136, 252, 130, 270, 135, "n", 2, 0, 0); //to corridor
        door[0][1] = new door(252, 209, 325, 150, 384, 210, "w", 1, 0, 0); //to bathroom
        numSpaces[0] = 0;
        //bathroom
        path[1] = System.getProperty("user.dir")+"/sprites/background/bathroom.png";
        numDoors[1] = 1;
        door[1][0] = new door(442, 203, 252, 130, 249, 210, "e", 0, 1, 0); //to room
        numSpaces[1] = 0;
        //corridor
        path[2] = System.getProperty("user.dir")+"/sprites/player/snopes/egif.gif";
        numDoors[2] = 1;
        door[2][0] = new door(280, 136, 252, 130, 270, 135, "n", 0, 0, 0); //to room
        numSpaces[2] = 0;
        
        //array end
        //NB door format is doorpositions, backgroundpositions, playerpositions, location, background, nextdoor, animation
        
    }
    public void move() {
        if ((player.moveUp == false && changey > 0) || (player.moveDown == false && changey < 0)) {
            changey = 0;
        }
        if ((player.moveLeft == false && changex > 0) || (player.moveRight == false && changex < 0)) {
            changex = 0;
        }
        if (mainPanel.playerMoves == false) {
            if (changey>0) {
                if (changex<0) {
                    player.legDirection = 1;
                } else if (changex>0) {
                    player.legDirection = 7;
                } else {
                    player.legDirection = 0;
                }
            } else if (changey<0) {
                if (changex<0) {
                    player.legDirection = 3;
                } else if (changex>0) {
                    player.legDirection = 5;
                } else {
                    player.legDirection = 4;
                }
            } else {
                if (changex<0) {
                    player.legDirection = 2;
                } else if (changex>0) {
                    player.legDirection = 6;
                } else {
                    player.legDirection = player.torsoDirection;
                }   
            }
        }
        xpos = xpos + changex;
        ypos = ypos + changey;
    }
    public void spaceMove(int backgroundNum, int spaceNum) {
        space[backgroundNum][spaceNum].x = space[backgroundNum][spaceNum].x + (int) changex;
        space[backgroundNum][spaceNum].y = space[backgroundNum][spaceNum].y + (int) changey;
    }
    public String getPath(int num) {
        return path[num];
    }
    public float getXpos() {
        return xpos;
    }
    public float getYpos() {
        return ypos;
    }
    public float getChangex() {
        return changex;
    }
    public float getChangey() {
        return changey;
    }
    public int getNumDoors(int num) {
        return numDoors[num];
    }
    public door getDoor(int backgroundNum, int doorNum) {
        return door[backgroundNum][doorNum];
    }
    public int getNumSpaces(int num) {
        return numSpaces[num];
    }
    public Rectangle getSpace(int backgroundNum, int spaceNum) {
        return space[backgroundNum][spaceNum];
    }
    public void setXpos(float newXpos) {
        xpos = newXpos;
    }
    public void setYpos(float newYpos) {
        ypos = newYpos;
    }
    public void w() {
        changey = 0.6F;
    }
    public void a() {
        changex = 0.6F;
    }
    public void s() {
        changey = -0.6F;
    }
    public void d() {
        changex = -0.6F;
    }
    public void keyPressed(KeyEvent e) { //once key is pressed, do action
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            w();
        }
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            a();
        }
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            s();
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            d();
        }   
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) || (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)) {
            changey = 0;
        }
        if ((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) || (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)) {
            changex = 0;
        }
    }
}
