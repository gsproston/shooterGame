package shootergame;

import java.awt.*;
import java.awt.event.KeyEvent;

public class player {
    private float xpos, ypos, changex, changey;
    private String legPath, torsoPath, character;
    private float timex = 1F, timey = 1F, speedMultiplier = 1F;
    public static int legDirection, torsoDirection;
    public static boolean moveUp, moveLeft, moveRight, moveDown;
    background background = new background();
    public player(background playerBackground) {
        moveUp = true;
        moveLeft = true;
        moveRight = true;
        moveDown = true;
        this.background = playerBackground;
        legDirection = 3;
        torsoDirection = 3;
        character = "player";
        legPath = System.getProperty("user.dir")+"/sprites/player/legs/"+character+"Legs"+legDirection+".png";
        torsoPath = System.getProperty("user.dir")+"/sprites/player/torso/"+character+"Torso"+torsoDirection+".png";
        xpos = 300F;
        ypos = 200F;
    }
    public void move() {
        if (mainPanel.playerMoves == true) {
            if (mainPanel.backgroundNum != 2) {
            if ((moveUp == false && changey < 0) || (moveDown == false && changey > 0)) {
                changey = 0;
            }
            if ((moveLeft == false && changex < 0) || (moveRight == false && changex > 0)) {
                changex = 0;
            }
        }
        if (changey<0) {
            if (changex>0) {
                legDirection = 1;
            } else if (changex<0) {
                legDirection = 7;
            } else {
                legDirection = 0;
            }
        } else if (changey>0) {
            if (changex>0) {
                legDirection = 3;
            } else if (changex<0) {
                legDirection = 5;
            } else {
                legDirection = 4;
            }
        } else {
            if (changex>0) {
                legDirection = 2;
            } else if (changex<0) {
                legDirection = 6;
            } else {
                legDirection = torsoDirection;
            }   
        }
        }
        legPath = System.getProperty("user.dir")+"/sprites/player/legs/"+character+"Legs"+legDirection+".png";
        torsoPath = System.getProperty("user.dir")+"/sprites/player/torso/"+character+"Torso"+torsoDirection+".png";
        xpos = xpos+changex*speedMultiplier;
        ypos = ypos+changey*speedMultiplier;
    }
    public String getLegPath() {
        return legPath;
    }
    public String getTorsoPath() {
        return torsoPath;
    }
    public float getXpos() {
        return xpos;
    }
    public float getYpos() {
        return ypos;
    }
    public int getLegDirection() {
        return legDirection;
    }
    public int getTorsoDirection() {
        return torsoDirection;
    }
    public void setLegDirection(int newDirection) {
        legDirection = newDirection;
    }
    public void setTorsoDirection(int newDirection) {
        torsoDirection = newDirection;
    }
    public void setXpos(float newXpos) {
        xpos = newXpos;
    }
    public void setYpos(float newYpos) {
        ypos = newYpos;
    }
     public void w() {
        changey = -0.6F;
    }
    public void a() {
        changex = -0.6F;
    }
    public void s() {
        changey = 0.6F;
    }
    public void d() {
        changex = 0.6F;   
    }
    public void keyPressed(KeyEvent e) { //once key is pressed, do action
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SHIFT) {
            speedMultiplier = 2;
        }
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
        if (key == KeyEvent.VK_1) {
            character = "player";
        }
        if (key == KeyEvent.VK_2) {
            character = "snups";
        }
        if (key == KeyEvent.VK_3) {
            character = "pig";
        }
        if (key == KeyEvent.VK_4) {
            character = "deadPool";
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SHIFT) {
            speedMultiplier = 1;
        }
        if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) || (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)) {
            changey = 0;
        }
        if ((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) || (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)) {
            changex = 0;
        }
    }
}

