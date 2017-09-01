package shootergame;

import java.util.logging.Level;
import java.util.logging.Logger;

public class door {
    private float xpos, ypos, backgroundXpos, backgroundYpos, playerXpos, playerYpos;
    private String location;
    private int backgroundNum, nextDoor;
    private int animation = 0; 
    public door(float newXpos, float newYpos, float newBackgroundXpos, float newBackgroundYpos, float newPlayerXpos, float newPlayerYpos, String newLocation, int newBackgroundNum, int newNextDoor, int newAnimation) {
        xpos = newXpos;
        ypos = newYpos;
        location = newLocation;
        backgroundXpos = newBackgroundXpos;
        backgroundYpos = newBackgroundYpos;
        playerXpos = newPlayerXpos;
        playerYpos = newPlayerYpos;
        backgroundNum = newBackgroundNum;
        animation = newAnimation;
        nextDoor = newNextDoor;
    }
    public void move() {
        xpos= xpos + background.changex;
        ypos = ypos + background.changey;
    }
    public void incrementAnimation() {
        if (animation < 12) {
            animation++;
        }
    }
    public void decrementAnimation() {
        if (animation > 0) {
            animation--;
        }
    }
    public float getXpos() {
        return xpos;
    }
    public float getYpos() {
        return ypos;
    }
    public String getLocation() {
        return location;
    }
    public float getBackgroundXpos() {
        return backgroundXpos;
    }
    public float getBackgroundYpos() {
        return backgroundYpos;
    }
    public float getPlayerXpos() {
        return playerXpos;
    }
    public float getPlayerYpos() {
        return playerYpos;
    }
    public int getBackgroundNum() {
        return backgroundNum;
    }
    public int getAnimation() {
        return animation;
    }
    public int getNextDoor() {
        return nextDoor;
    }
}
