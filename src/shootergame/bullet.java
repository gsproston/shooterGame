package shootergame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class bullet {
    private float xpos, ypos, changex, changey;
    private String path, currentWeapon;
    private double secondAngle, thirdAngle, bulletAngle;
    private int bulletSpeedMultiplier;
    public bullet(String weapon, float newXpos, float newYpos, double angle, int bulletSpeed) {
        xpos = newXpos;
        ypos = newYpos;
        bulletAngle = angle;
        currentWeapon = weapon;
        bulletSpeedMultiplier = bulletSpeed;
        path = System.getProperty("user.dir")+"/sprites/weapons/bullets/ammo"+currentWeapon+".png";
    }
    public void move() {
        if (bulletAngle < Math.PI/2) {
            secondAngle = Math.PI/2 - bulletAngle;
            changex = (float) Math.sin(bulletAngle);
            changey = (float) -(Math.sin(secondAngle));
        } else if (bulletAngle >= Math.PI/2 && bulletAngle < Math.PI) {
            secondAngle = Math.PI - bulletAngle;
            thirdAngle = bulletAngle - Math.PI/2;
            changex = (float) Math.sin(secondAngle);
            changey = (float) Math.sin(thirdAngle);
        } else if (bulletAngle >= Math.PI && bulletAngle < 3*Math.PI/2) {
            secondAngle = 3*Math.PI/2 - bulletAngle;
            thirdAngle = bulletAngle - Math.PI;
            changex = (float) -(Math.sin(thirdAngle));
            changey = (float) Math.sin(secondAngle);
        } else if (bulletAngle >= 3*Math.PI/2) {
            secondAngle = 2*Math.PI - bulletAngle;
            thirdAngle = bulletAngle - 3*Math.PI/2;
            changex = (float) -(Math.sin(secondAngle));
            changey = (float) -(Math.sin(thirdAngle));
        }
        xpos = xpos + changex*bulletSpeedMultiplier;
        ypos = ypos + changey*bulletSpeedMultiplier;
    }
    public float getXpos() {
        return xpos;
    }
    public float getYpos() {
        return ypos;
    }
    public String getPath() {
        return path;
    }
    public double getBulletAngle() {
        return bulletAngle;
    }
}
