package shootergame;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mainPanel extends JPanel implements ActionListener {
        JFrame frame;
        JPanel panel = this;
        private Timer timer;
        Toolkit t = Toolkit.getDefaultToolkit();
        PointerInfo p = MouseInfo.getPointerInfo();
        Point mouseLocation = p.getLocation();
        Point mouseCentre = new Point(500,500);
        Point hotSpot = new Point(0,0);  
        public static int weaponNum = 0;
        public static int countdown = 0;
        public static int backgroundNum = 0;    
        private int second, minute, hour, day, timeLeft, lifeDecrease, lifeCount, spaceNum, doorNum, currentDoor, doorCount = 0;
        private int numRectangles = 4;
        private float xscale, yscale, eXpos, eYpos;
        public static String currentComponent = "mainBoard";
        public static boolean playerMoves = true;
        public static boolean gameOver = false;
        private boolean designView = false;
        private boolean nearDoor = false;
        private boolean doorClosing = false, doorOpening = false;
        public static double mouseAngle;
        private Rectangle north, east, south, west, spaceArray[], playerMovementRectangle, moveNorth, moveEast, moveSouth, moveWest;
        public ArrayList bulletArray = new ArrayList();
        health healthBar = new health();
        life lifeBar = new life();
        armour armourBar = new armour();
        weapons weapon = new weapons();
        background background = new background();
        player player = new player(background);
        mouseListener mouseListener = new mouseListener(this);
        mouseWheelListener mouseWheelListener = new mouseWheelListener(this);
        private float mouseProximityDiameter = 200F;
               
        //images
        
        Image healthBarImage = t.getImage(healthBar.getEmptyPath());
        Image healthTextImage = t.getImage(healthBar.getTextPath()); 
        Image leftHealthImage = t.getImage(healthBar.getLeftPath());
        Image rightHealthImage = t.getImage(healthBar.getRightPath());
        Image midHealthImage = t.getImage(healthBar.getMidPath());
        Image lifeBarImage = t.getImage(lifeBar.getEmptyPath());
        Image lifeTextImage = t.getImage(lifeBar.getTextPath()); 
        Image leftLifeImage = t.getImage(lifeBar.getLeftPath());
        Image rightLifeImage = t.getImage(lifeBar.getRightPath());
        Image midLifeImage = t.getImage(lifeBar.getMidPath());
        Image armourBarImage = t.getImage(armourBar.getEmptyPath());
        Image nextArmourBarImage = t.getImage(armourBar.getNextEmptyPath());
        Image leftArmourImage = t.getImage(armourBar.getLeftPath());
        Image midArmourImage = t.getImage(armourBar.getMidPath());
        Image secondEmptyImage = t.getImage(armourBar.getSecondEmptyPath());
        Image secondLeftImage = t.getImage(armourBar.getSecondLeftPath());
        Image rightArmourImage = t.getImage(armourBar.getRightPath());
        Image nextSecondImage = t.getImage(armourBar.getNextSecondPath());
        Image secondEndImage = t.getImage(armourBar.getSecondEndPath());
        Image emptyWeapon = t.getImage(System.getProperty("user.dir")+"/sprites/weapons/bullets/empty"+weapon.getWeapon(weaponNum)+".png");
        Image ammoWeapon = t.getImage(System.getProperty("user.dir")+"/sprites/weapons/bullets/ammo"+weapon.getWeapon(weaponNum)+".png");
        Image imageWeapon = t.getImage(System.getProperty("user.dir")+"/sprites/weapons/"+weapon.getWeapon(weaponNum)+".png");
        Image backgroundImage = t.getImage(background.getPath(backgroundNum));
        Image mainBackground = t.getImage(System.getProperty("user.dir")+"/sprites/background/background.png");
        Image doorImage;
        Image bulletImage;
        Image eKey = t.getImage(System.getProperty("user.dir")+"/sprites/hud/eKey.gif");
        Image playerLegs = t.getImage(player.getLegPath());
        Image playerTorso = t.getImage(player.getTorsoPath());
        Image guiBackgroundImage = t.getImage(System.getProperty("user.dir")+"/sprites/icon.png");
        Image testArrow = t.getImage(System.getProperty("user.dir")+"/sprites/testArrow.png");
        Image crosshair = t.getImage(System.getProperty("user.dir")+"/sprites/hud/crosshair.png");
        Shape mouseProximity = new Ellipse2D.Float(newXpos(player.getXpos())+getCentrex(playerLegs)-newXscale(mouseProximityDiameter)/2, newYpos(player.getYpos())+getCentrey(playerLegs)-newYscale(mouseProximityDiameter)/2, newXscale(mouseProximityDiameter), newYscale(mouseProximityDiameter));
        public mainPanel(JFrame usedFrame) {
                frame = usedFrame;
                addKeyListener(new TAdapter());
                setSize(800,450);
        	setFocusable(true);
                requestFocusInWindow();
        	setDoubleBuffered(true);
        	setVisible(true);
                timer = new Timer(5, this);
        	timer.start(); 
                Cursor c = t.createCustomCursor(crosshair, hotSpot, "crosshair");
                panel.setCursor(c);
                xscale = getWidth()/800F;
                yscale = getHeight()/450F;
                second = 36;
                minute = 1;
                hour = 0; 
                day = 0;
                lifeCount = 0;
                timeLeft = (second+(minute*60)+(hour*60*60)+(day*24*60*60))*200;
                lifeDecrease = (int)Math.ceil(timeLeft/96);
                north = new Rectangle ((int) background.getXpos()-10, (int) background.getYpos(), backgroundImage.getWidth(panel)+20, 50);
                east = new Rectangle ((int) background.getXpos()+backgroundImage.getWidth(panel)-7, (int) background.getYpos()-10, 50, backgroundImage.getHeight(panel)+20);
                south = new Rectangle ((int) background.getXpos()-10, (int) background.getYpos()+backgroundImage.getHeight(panel)-2, backgroundImage.getWidth(panel)+20, 50);
                west = new Rectangle ((int) background.getXpos()-43, (int) background.getYpos()-10, 50, backgroundImage.getHeight(panel)+20);
                playerMovementRectangle = new Rectangle ((int) (player.getXpos()+16)-weapon.getWeaponDifference(weaponNum), (int) player.getYpos()+45, 35+weapon.getWeaponDifference(weaponNum)*2, 11);
                numRectangles = 4+background.getNumSpaces(backgroundNum); //four boundary rectangles plus any in the background image
                spaceArray = new Rectangle[numRectangles]; 
                spaceArray[0] = north;
                spaceArray[1] = east;
                spaceArray[2] = south;
                spaceArray[3] = west;
                for (int count=0; count<background.getNumSpaces(backgroundNum); count++) {
                    spaceArray[count+4] = background.getSpace(backgroundNum, count);
                } 
                lifeBar.setLife(96); //glitch occurs if this line isn't here KEEP IN
        }
        public void paint(Graphics g) {
                super.paint(g);
        	Graphics2D g2 = (Graphics2D) g;
                AffineTransform originalTransform = g2.getTransform();
                
                g2.drawImage(mainBackground,newXpos(0),newYpos(0),newXscale(1000),newYscale(600),panel);
                
                //gui draw
                
                if (currentComponent == "mainGui") {
                    g2.drawImage(guiBackgroundImage,0,0,panel.getWidth(),panel.getHeight(),panel);
                } else {
                    
                //main game draw                   
                    
                if (backgroundNum == 2) {
                    g2.drawImage(backgroundImage,newXpos(100),newYpos(70),newXscale(600),newYscale(350),panel);
                } else {
                    g2.drawImage(backgroundImage,newXpos(background.getXpos()),newYpos(background.getYpos()),newXscale(backgroundImage.getWidth(panel)),newYscale(backgroundImage.getHeight(panel)),panel);
                }
                for(int count = 0; count<(background.getNumDoors(backgroundNum)); count++) {
                    doorImage = t.getImage(System.getProperty("user.dir")+"/sprites/background/doors/"+background.getDoor(backgroundNum, count).getLocation()+"door"+background.getDoor(backgroundNum, count).getAnimation()+".png");
                    g2.drawImage(doorImage,newXpos(background.getDoor(backgroundNum, count).getXpos()),newYpos(background.getDoor(backgroundNum, count).getYpos()),newXscale(doorImage.getWidth(panel)),newYscale(doorImage.getHeight(panel)),panel);
                }
                g2.drawImage(playerLegs,newXpos(player.getXpos()),newYpos(player.getYpos()),newXscale(playerLegs.getWidth(panel)),newYscale(playerLegs.getHeight(panel)),panel);
                g2.drawImage(playerTorso,newXpos(player.getXpos()),newYpos(player.getYpos()),newXscale(playerTorso.getWidth(panel)),newYscale(playerTorso.getHeight(panel)),panel);
                ArrayList bullets = getBullets();
                for (int i = 0; i < bullets.size(); i++ ) {
                    bullet b = (bullet) bullets.get(i);
                    bulletImage = t.getImage(b.getPath());
                    Rectangle bulletRectangle = new Rectangle((int) b.getXpos(), (int) b.getYpos(), bulletImage.getWidth(panel), bulletImage.getHeight(panel));
                    for(int count=0; count<numRectangles; count++) {
                        if (spaceArray[count].intersects(bulletRectangle) && bullets.size() > 0) {
                            bullets.remove(i);
                        } 
                    }
                    g2.rotate(b.getBulletAngle()-Math.PI/2, newXpos(b.getXpos())+newXscale(6), newYpos(b.getYpos())+newYscale(2));
                    g2.drawImage(bulletImage,newXpos(b.getXpos()),newYpos(b.getYpos()),newXscale(bulletImage.getWidth(panel)),newYscale(bulletImage.getHeight(panel)),panel);
                    g2.setTransform(originalTransform);
                }
                                
                //hud draw
  
                if (nearDoor == true) {
                    g2.drawImage(eKey,newXpos(eXpos),newYpos(eYpos),newXscale(20),newYscale(20),panel);
                }
                g2.drawImage(healthBarImage,newXpos(healthBar.getEmptyXpos()),newYpos(healthBar.getEmptyYpos()),newXscale(healthBarImage.getWidth(panel)),newYscale(healthBarImage.getHeight(panel)),panel);
                if(healthBar.getHealth()>0) {
                    g2.drawImage(leftHealthImage,newXpos(healthBar.getLeftXpos()),newYpos(healthBar.getLeftYpos()),newXscale(leftHealthImage.getWidth(panel)),newYscale(leftHealthImage.getHeight(panel)),panel);
                    for(int count=0; count<(healthBar.getHealth()-1); count++) {
                        g2.drawImage(midHealthImage,newXpos(healthBar.getMidXpos()+(count*16)),newYpos(healthBar.getMidYpos()),newXscale(midHealthImage.getWidth(panel)),newYscale(midHealthImage.getHeight(panel)),panel);
                    }
                    if(healthBar.getHealth()>9) {
                        g2.drawImage(rightHealthImage,newXpos(healthBar.getRightXpos()),newYpos(healthBar.getRightYpos()),newXscale(rightHealthImage.getWidth(panel)),newYscale(rightHealthImage.getHeight(panel)),panel);
                    }
                }
                g2.drawImage(healthTextImage,newXpos(healthBar.getTextXpos()),newYpos(healthBar.getTextYpos()),newXscale(healthTextImage.getWidth(panel)),newYscale(healthTextImage.getHeight(panel)),panel);            
                g2.drawImage(lifeBarImage,newXpos(lifeBar.getEmptyXpos()),newYpos(lifeBar.getEmptyYpos()),newXscale(lifeBarImage.getWidth(panel)),newYscale(lifeBarImage.getHeight(panel)),panel);
                if(lifeBar.getLife()>0) {
                    g2.drawImage(leftLifeImage,newXpos(lifeBar.getLeftXpos()),newYpos(lifeBar.getLeftYpos()),newXscale(leftLifeImage.getWidth(panel)),newYscale(leftLifeImage.getHeight(panel)),panel);
                    for(int count=0; count<(lifeBar.getLife()-1); count++) {
                        g2.drawImage(midLifeImage,newXpos(lifeBar.getMidXpos()+(count*8)),newYpos(lifeBar.getMidYpos()),newXscale(midLifeImage.getWidth(panel)),newYscale(midLifeImage.getHeight(panel)),panel);
                    }
                    if(lifeBar.getLife()>95) {
                        g2.drawImage(rightLifeImage,newXpos(lifeBar.getRightXpos()),newYpos(lifeBar.getRightYpos()),newXscale(rightLifeImage.getWidth(panel)),newYscale(rightLifeImage.getHeight(panel)),panel);
                    }
                }
                g2.drawImage(lifeTextImage,newXpos(lifeBar.getTextXpos()),newYpos(lifeBar.getTextYpos()),newXscale(lifeTextImage.getWidth(panel)),newYscale(lifeTextImage.getHeight(panel)),panel);            
                g2.drawImage(armourBarImage,newXpos(armourBar.getEmptyXpos()),newYpos(armourBar.getEmptyYpos()),newXscale(armourBarImage.getWidth(panel)),newYscale(armourBarImage.getHeight(panel)),panel);             
                if(armourBar.getMaxArmour()>3) {
                        for(int count=0; count<(armourBar.getMaxArmour()-3)&&count<17; count++) {
                            g2.drawImage(nextArmourBarImage,newXpos(armourBar.getNextXpos()+count*8),newYpos(armourBar.getNextYpos()),newXscale(nextArmourBarImage.getWidth(panel)),newYscale(nextArmourBarImage.getHeight(panel)),panel);
                        }
                }
                if(armourBar.getMaxArmour()>20) {
                    g2.drawImage(secondEmptyImage,newXpos(armourBar.getSecondEmptyXpos()),newYpos(armourBar.getSecondEmptyYpos()),newXscale(secondEmptyImage.getWidth(panel)),newYscale(secondEmptyImage.getHeight(panel)),panel);
                }
                if(armourBar.getMaxArmour()>21) {
                        for(int count=0; count<(armourBar.getMaxArmour()-21)&&count<18; count++) {
                            g2.drawImage(nextSecondImage,newXpos((armourBar.getNextSecondXpos())+count*8),newYpos(armourBar.getNextSecondYpos()),newXscale(nextSecondImage.getWidth(panel)),newYscale(nextSecondImage.getHeight(panel)),panel);
                        }
                }
                if(armourBar.getMaxArmour()>39) {
                    g2.drawImage(secondEndImage,newXpos(armourBar.getSecondEndXpos()),newYpos(armourBar.getSecondEndYpos()),newXscale(secondEndImage.getWidth(panel)),newYscale(secondEndImage.getHeight(panel)),panel);
                }
                if(armourBar.getArmour()>0) {
                    g2.drawImage(leftArmourImage,newXpos(armourBar.getLeftXpos()),newYpos(armourBar.getLeftYpos()),newXscale(leftArmourImage.getWidth(panel)),newYscale(leftArmourImage.getHeight(panel)),panel);
                }
                if (armourBar.getArmour()>1) {
                    for(int count=0; count<(armourBar.getArmour()-1)&&count<18; count++) {
                        g2.drawImage(midArmourImage,newXpos(armourBar.getMidXpos()+(count*8)),newYpos(armourBar.getMidYpos()),newXscale(midArmourImage.getWidth(panel)),newYscale(midArmourImage.getHeight(panel)),panel);
                    }
                }  
                if (armourBar.getArmour()==armourBar.getMaxArmour() && armourBar.getMaxArmour()<=20) {
                    g2.drawImage(rightArmourImage,newXpos(armourBar.getRightXpos()+8*(armourBar.getMaxArmour()-3)),newYpos(armourBar.getRightYpos()),newXscale(rightArmourImage.getWidth(panel)),newYscale(rightArmourImage.getHeight(panel)),panel); 
                }
                if(armourBar.getArmour()>=20) {
                    g2.drawImage(rightArmourImage,newXpos(armourBar.getRightXpos()+8*17),newYpos(armourBar.getRightYpos()),newXscale(rightArmourImage.getWidth(panel)),newYscale(rightArmourImage.getHeight(panel)),panel);                  
                }   
                if(armourBar.getArmour()>20) {
                    g2.drawImage(leftArmourImage,newXpos(armourBar.getLeftXpos()),newYpos(armourBar.getLeftYpos()+9),newXscale(leftArmourImage.getWidth(panel)),newYscale(leftArmourImage.getHeight(panel)),panel);
                    for(int count=0; count<(armourBar.getArmour()-21)&&count<18; count++) {
                        g2.drawImage(midArmourImage,newXpos(armourBar.getMidXpos()+(count*8)),newYpos(armourBar.getMidYpos()+9),newXscale(midArmourImage.getWidth(panel)),newYscale(midArmourImage.getHeight(panel)),panel);
                    }
                }
                if((armourBar.getArmour()==armourBar.getMaxArmour()) && armourBar.getArmour()>20 && armourBar.getArmour()<41) {
                    g2.drawImage(rightArmourImage,newXpos(armourBar.getMidXpos()+8*(armourBar.getArmour()-22)),newYpos(armourBar.getRightYpos()+9),newXscale(rightArmourImage.getWidth(panel)),newYscale(rightArmourImage.getHeight(panel)),panel);                  
                }
                if (armourBar.getArmour()==armourBar.getMaxArmour() && armourBar.getArmour()==21) {
                    g2.drawImage(secondLeftImage,newXpos(armourBar.getLeftXpos()),newYpos(armourBar.getLeftYpos()+9),newXscale(secondLeftImage.getWidth(panel)),newYscale(secondLeftImage.getHeight(panel)),panel);
                }
                /*
                emptyXpos = 788;
                emptyYpos = 442;
                ammoXpos = 787;
                ammoYpos = 441;
                imageXpos = 770; now based off the emptyXpos
                imageYpos = 442;
                */
                for(int count=0; count<weapon.getClipSize(weaponNum); count++) {
                    g2.drawImage(emptyWeapon,newXpos(788)-newXscale(emptyWeapon.getWidth(panel)),newYpos(442-emptyWeapon.getHeight(panel)-count*(emptyWeapon.getHeight(panel)-1)),newXscale(emptyWeapon.getWidth(panel)),newYscale(emptyWeapon.getHeight(panel)),panel);             
                }
                for(int count=0; count<weapon.getBulletsInClip(weaponNum); count++) {
                    g2.drawImage(ammoWeapon,newXpos(787)-newXscale(ammoWeapon.getWidth(panel)),newYpos(441-ammoWeapon.getHeight(panel)-count*(ammoWeapon.getHeight(panel)+1)),newXscale(ammoWeapon.getWidth(panel)),newYscale(ammoWeapon.getHeight(panel)),panel);             
                }    
                g2.drawImage(imageWeapon,newXpos(780)-newXscale(emptyWeapon.getWidth(panel))-newXscale(imageWeapon.getWidth(panel)),newYpos(442)-newYscale(imageWeapon.getHeight(panel)),newXscale(imageWeapon.getWidth(panel)),newYscale(imageWeapon.getHeight(panel)),panel);             
                g2.drawImage(t.getImage(System.getProperty("user.dir")+"/sprites/hud/colon.png"),newXpos(711),newYpos(27),newXscale(2),newYscale(6),panel);
                g2.drawImage(t.getImage(System.getProperty("user.dir")+"/sprites/hud/colon.png"),newXpos(731),newYpos(27),newXscale(2),newYscale(6),panel);   
                g2.drawImage(t.getImage(System.getProperty("user.dir")+"/sprites/hud/colon.png"),newXpos(751),newYpos(27),newXscale(2),newYscale(6),panel);   
                g2.drawImage(t.getImage(System.getProperty("user.dir")+"/sprites/hud/"+second+".png"),newXpos(755),newYpos(23),newXscale(14),newYscale(10),panel);            
                g2.drawImage(t.getImage(System.getProperty("user.dir")+"/sprites/hud/"+minute+".png"),newXpos(735),newYpos(23),newXscale(14),newYscale(10),panel);            
                g2.drawImage(t.getImage(System.getProperty("user.dir")+"/sprites/hud/"+hour+".png"),newXpos(715),newYpos(23),newXscale(14),newYscale(10),panel);            
                g2.drawImage(t.getImage(System.getProperty("user.dir")+"/sprites/hud/"+day+".png"),newXpos(695),newYpos(23),newXscale(14),newYscale(10),panel); 
                if (designView == true) {
                    g2.draw(mouseProximity);
                    g2.draw(new Rectangle((int) (newXpos(player.getXpos())+getCentrex(playerLegs)), (int) (newYpos(player.getYpos())+getCentrey(playerLegs)), newXscale(100), newYscale(100)));
                    g2.draw(new Rectangle(mouseCentre.x, mouseCentre.y, newXscale(20), newYscale(20)));
                    g2.draw(playerMovementRectangle);
                    for (int count=0; count<numRectangles; count++) {
                        g2.draw(spaceArray[count]);
                    }
                    g2.rotate(mouseAngle, newXpos(400), newYpos(225));
                    g2.drawImage(testArrow, newXpos(384), newYpos(209), newXscale(testArrow.getWidth(panel)),newYscale(testArrow.getHeight(panel)),panel);                           
                }
                }
                
                g2.finalize();
        }
        public ArrayList getBullets() {
            return bulletArray;
        }
        public void actionPerformed(ActionEvent e) {
            if((second != 0 || minute != 0 || hour != 0 || day != 0) && currentComponent == "mainBoard") { //check if counter is zero
                gameOver = false;
                countdown++;
                lifeBarDecrease();
                if(countdown >= 200) {
                    secondDecrease();
                    countdown = 0;
                }
            } else if (second == 0 && minute == 0 && hour == 0 && day == 0){ // if the counter is at zero, life = 0
                lifeBar.setLife(0);
                gameOver = true;
            } 
            if (gameOver == false) {
                //check if player is near doors
                if(background.getNumDoors(backgroundNum) > 0) {
                    for(int count=0; count<background.getNumDoors(backgroundNum); count++) {
                        if (background.getDoor(backgroundNum, count).getLocation() == "n") {
                            if (player.getXpos()+16 > (background.getDoor(backgroundNum, count).getXpos()-10) && player.getXpos()+16 < (background.getDoor(backgroundNum, count).getXpos()+20) && player.getYpos()+10 > background.getDoor(backgroundNum, count).getYpos() && player.getYpos()+10 < (background.getDoor(backgroundNum, count).getYpos()+20)) {
                                eXpos = background.getDoor(backgroundNum, count).getXpos()+50;
                                eYpos = background.getDoor(backgroundNum, count).getYpos();                           
                                doorNum = count;
                                nearDoor = true;
                            } else if (doorNum == count) {
                                nearDoor = false;
                            }
                        } else if (background.getDoor(backgroundNum, count).getLocation() == "w") {
                            if (player.getXpos()+16 > background.getDoor(backgroundNum, count).getXpos() && player.getXpos()+16 < (background.getDoor(backgroundNum, count).getXpos()+20) && player.getYpos()+10 > (background.getDoor(backgroundNum, count).getYpos()-5) && player.getYpos()+10 < (background.getDoor(backgroundNum, count).getYpos()+25)) {
                                eXpos = background.getDoor(backgroundNum, count).getXpos()-25;
                                eYpos = background.getDoor(backgroundNum, count).getYpos()+25;  
                                doorNum = count;
                                nearDoor = true;
                            } else if (doorNum == count) {
                                nearDoor = false;
                            }                   
                        } else if (background.getDoor(backgroundNum, count).getLocation() == "e") {
                            if (player.getXpos()+16 > (background.getDoor(backgroundNum, count).getXpos()-50) && player.getXpos()+16 < (background.getDoor(backgroundNum, count).getXpos()-20) && player.getYpos()+10 > (background.getDoor(backgroundNum, count).getYpos()-2) && player.getYpos()+10 < (background.getDoor(backgroundNum, count).getYpos()+18)) {
                                eXpos = background.getDoor(backgroundNum, count).getXpos()+15;
                                eYpos = background.getDoor(backgroundNum, count).getYpos()+25;  
                                doorNum = count;
                                nearDoor = true;
                            } else if (doorNum == count) {
                                nearDoor = false;
                            }
                        }
                    }
                }
                if (doorOpening == true) {
                    if (doorCount == 0) {
                        background.getDoor(backgroundNum, currentDoor).incrementAnimation();
                    } else if (doorCount > 2) {
                        doorCount = -1;
                    }
                    doorCount++;
                    if (background.getDoor(backgroundNum, currentDoor).getAnimation() == 12) {
                        changeDoor(currentDoor);
                    }
                }
                if (doorClosing == true) {
                    if (doorCount == 0) {
                        background.getDoor(backgroundNum, currentDoor).decrementAnimation();
                    } else if (doorCount > 2) {
                        doorCount = -1;
                    }
                    doorCount++;
                    if (background.getDoor(backgroundNum, currentDoor).getAnimation() == 0) {
                        doorClosing = false;
                    }
                }
                player.move();
                background.move();
                for(int count = 0; count<(background.getNumDoors(backgroundNum)); count++) {
                    background.getDoor(backgroundNum, count).move();
                }
                xscale = getWidth()/800F;
                yscale = getHeight()/450F;
                emptyWeapon = t.getImage(System.getProperty("user.dir")+"/sprites/weapons/bullets/empty"+weapon.getWeapon(weaponNum)+".png");
                ammoWeapon = t.getImage(System.getProperty("user.dir")+"/sprites/weapons/bullets/ammo"+weapon.getWeapon(weaponNum)+".png");
                imageWeapon = t.getImage(System.getProperty("user.dir")+"/sprites/weapons/"+weapon.getWeapon(weaponNum)+".png");
                playerLegs = t.getImage(player.getLegPath());
                playerTorso = t.getImage(player.getTorsoPath());
                north = new Rectangle ((int) background.getXpos()-10, (int) background.getYpos(), backgroundImage.getWidth(panel)+20, 50);
                east = new Rectangle ((int) background.getXpos()+backgroundImage.getWidth(panel)-7, (int) background.getYpos()-10, 50, backgroundImage.getHeight(panel)+20);
                south = new Rectangle ((int) background.getXpos()-10, (int) background.getYpos()+backgroundImage.getHeight(panel)-2, backgroundImage.getWidth(panel)+20, 50);
                west = new Rectangle ((int) background.getXpos()-43, (int) background.getYpos()-10, 50, backgroundImage.getHeight(panel)+20);
                playerMovementRectangle = new Rectangle ((int) (player.getXpos()+16)-weapon.getWeaponDifference(weaponNum), (int) player.getYpos()+45, 35+weapon.getWeaponDifference(weaponNum)*2, 11);
                numRectangles = 4+background.getNumSpaces(backgroundNum); //four boundary rectangles plus any in the background image
                spaceArray = new Rectangle[numRectangles]; //glitch occurs if this line isn't here KEEP IN
                spaceArray[0] = north;
                spaceArray[1] = east;
                spaceArray[2] = south;
                spaceArray[3] = west;
                for (int count=0; count<background.getNumSpaces(backgroundNum); count++) {
                    background.spaceMove(backgroundNum, count);
                    spaceArray[count+4] = background.getSpace(backgroundNum, count);
                } 
                //mouse stuff
                PointerInfo p = MouseInfo.getPointerInfo();
                Point mouseLocation = p.getLocation();
                mouseLocation = new Point((int) mouseLocation.x-frame.getLocationOnScreen().x,(int) mouseLocation.y-frame.getLocationOnScreen().y); //mouse location on game screen
                mouseCentre.x = mouseLocation.x + 8; //find the xpos and ypos of the centre of the cursor
                mouseCentre.y = mouseLocation.y - 16;
                if (mouseCentre.x >= (newXpos(player.getXpos())+getCentrex(playerLegs)) && mouseCentre.y < (newYpos(player.getYpos())+getCentrey(playerLegs))) { //upper right quadrant
                    mouseAngle = Math.atan((mouseCentre.x - (newXpos(player.getXpos())+getCentrex(playerLegs))) / ((newYpos(player.getYpos())+getCentrey(playerLegs)) - mouseCentre.y));
                } else if (mouseCentre.x >= (newXpos(player.getXpos())+getCentrex(playerLegs)) && mouseCentre.y >= (newYpos(player.getYpos())+getCentrey(playerLegs))) { //lower right quadrant
                    mouseAngle = (Math.PI/2) + Math.atan((mouseCentre.y - (newYpos(player.getYpos())+getCentrey(playerLegs))) / (mouseCentre.x - (newXpos(player.getXpos())+getCentrex(playerLegs))));
                } else if (mouseCentre.x < (newXpos(player.getXpos())+getCentrex(playerLegs)) && mouseCentre.y >= (newYpos(player.getYpos())+getCentrey(playerLegs))) { //lower left quadrant
                    mouseAngle = Math.PI + Math.atan(((newXpos(player.getXpos())+getCentrex(playerLegs)) - mouseCentre.x) / (mouseCentre.y - (newYpos(player.getYpos())+getCentrey(playerLegs))));
                } else if (mouseCentre.x < (newXpos(player.getXpos())+getCentrex(playerLegs)) && mouseCentre.y < (newYpos(player.getYpos())+getCentrey(playerLegs))) { //upper left quadrant
                    mouseAngle = ((3*Math.PI)/2) + Math.atan(((newYpos(player.getYpos())+getCentrey(playerLegs)) - mouseCentre.y) / ((newXpos(player.getXpos())+getCentrex(playerLegs)) - mouseCentre.x));
                }
                if (mouseAngle >= (15*Math.PI/8) || mouseAngle < (Math.PI/8)) {
                    player.torsoDirection = 0;
                } else if (mouseAngle >= (Math.PI/8) && mouseAngle < (3*Math.PI/8)) {
                    player.torsoDirection = 1;
                } else if (mouseAngle >= (3*Math.PI/8) && mouseAngle < (5*Math.PI/8)) {
                    player.torsoDirection = 2;
                } else if (mouseAngle >= (5*Math.PI/8) && mouseAngle < (7*Math.PI/8)) {
                    player.torsoDirection = 3;
                } else if (mouseAngle >= (7*Math.PI/8) && mouseAngle < (9*Math.PI/8)) {
                    player.torsoDirection = 4;
                } else if (mouseAngle >= (9*Math.PI/8) && mouseAngle < (11*Math.PI/8)) {
                    player.torsoDirection = 5;
                } else if (mouseAngle >= (11*Math.PI/8) && mouseAngle < (13*Math.PI/8)) {
                    player.torsoDirection = 6;
                }  else if (mouseAngle >= (13*Math.PI/8) && mouseAngle < (15*Math.PI/8)) {
                    player.torsoDirection = 7;
                }
                mouseProximity = new Ellipse2D.Float(newXpos(player.getXpos())+getCentrex(playerLegs)-newXscale(mouseProximityDiameter)/2, newYpos(player.getYpos())+getCentrey(playerLegs)-newYscale(mouseProximityDiameter)/2, newXscale(mouseProximityDiameter), newYscale(mouseProximityDiameter));
                canPlayerMove();
                if (mouseProximity.contains(mouseCentre) == true) {

                } else {

                }
            }
            //end mouse stuff
            ArrayList bullets = getBullets();
            for (int i = 0; i < bullets.size(); i++ ) {
                bullet b = (bullet) bullets.get(i);
                b.move();
            }
            repaint();
    	}
        public void canPlayerMove() {
            moveNorth  = new Rectangle(playerMovementRectangle.x, playerMovementRectangle.y-2, playerMovementRectangle.width, playerMovementRectangle.height);
            moveEast  = new Rectangle(playerMovementRectangle.x+2, playerMovementRectangle.y, playerMovementRectangle.width, playerMovementRectangle.height);
            moveSouth  = new Rectangle(playerMovementRectangle.x, playerMovementRectangle.y+2, playerMovementRectangle.width, playerMovementRectangle.height);
            moveWest  = new Rectangle(playerMovementRectangle.x-2, playerMovementRectangle.y, playerMovementRectangle.width, playerMovementRectangle.height);
            for(int count=0; count<numRectangles; count++) {
                if (spaceArray[count].intersects(moveNorth)) {
                    player.moveUp = false;
                    spaceNum = count;
                } else if (spaceNum == count) {
                    player.moveUp = true;
                }
                if (spaceArray[count].intersects(moveEast)) {
                    player.moveRight = false;
                    spaceNum = count;
                } else if (spaceNum == count) {
                    player.moveRight = true;
                }
                if (spaceArray[count].intersects(moveSouth)) {
                    player.moveDown = false;
                    spaceNum = count;
                } else if (spaceNum == count) {
                    player.moveDown = true;
                }
                if (spaceArray[count].intersects(moveWest)) {
                    player.moveLeft = false;
                    spaceNum = count;
                } else if (spaceNum == count) {
                    player.moveLeft = true;
                }
            }            
        }
        public int newXpos(float oldx) { // repositions the images depending on frame size
            float newx = oldx*xscale;
            return (int)Math.floor(newx);
        }
        public int newYpos(float oldy) {
            float newy = oldy*yscale;
            return (int)Math.floor(newy);
        }
        public int newXscale(float oldx) { // resizes the images depending on frame size
            float newx = oldx*xscale;
            return (int)Math.ceil(newx);
        }
        public int newYscale(float oldy) {
            float newy = oldy*yscale;
            return (int)Math.ceil(newy);
        }
        private float getCentrex(Image image) {
            float xpos = newXscale(image.getWidth(panel))/2;
            return xpos;
        }
        private float getCentrey(Image image) {
            float ypos = newYscale(image.getHeight(panel))/2;
            return ypos;
        }
        private void secondDecrease() {
            second--;
            if(second < 0) {
                second = 59;
                minuteDecrease();
            }
        }
        private void minuteDecrease() {
            minute--;
            if(minute < 0) {
                minute = 59;
                hourDecrease();     
            }
        }
        private void hourDecrease() {
            hour--;
            if(hour < 0) {
                hour = 23;
                day--;
            }
        }
        public void lifeBarDecrease() {
                lifeCount++;
                if(lifeCount >= lifeDecrease) {
                    lifeBar.lifeDecrease();
                    lifeCount = 0;
                }
        }
        public void pause() {
            if (currentComponent == "mainBoard") {
                currentComponent = "mainGui";
            } else {
                currentComponent = "mainBoard";
            }
        }
        public void fire() {
            if (weapon.getBulletsInClip(weaponNum) > 0) {
                if (weapon.getWeapon(weaponNum) == "BaconShotgun") {
                    bulletArray.add(new bullet(weapon.getWeapon(weaponNum), player.getXpos()+30, player.getYpos()+35, mouseAngle+0.1, weapon.getBulletSpeedMultiplier(weaponNum)));
                    bulletArray.add(new bullet(weapon.getWeapon(weaponNum), player.getXpos()+30, player.getYpos()+35, mouseAngle-0.1, weapon.getBulletSpeedMultiplier(weaponNum)));
                    weapon.bulletDecrease(weaponNum, 2);
                } else {
                    bulletArray.add(new bullet(weapon.getWeapon(weaponNum), player.getXpos()+30, player.getYpos()+35, mouseAngle, weapon.getBulletSpeedMultiplier(weaponNum)));
                    weapon.bulletDecrease(weaponNum, 1);
                }
            }
        }
        public void wheelScroll(int count) {
            if (count < 0) {
                if (weaponNum < weapon.getNumWeapons()-1) {
                    weaponNum++;
                } else {
                    weaponNum = 0;
                }
            } else if (count > 0) { 
                if (weaponNum > 0) {
                    weaponNum--;
                } else {
                    weaponNum = weapon.getNumWeapons()-1;
                }
            }
        }
        public void changeDoor(int newDoorNum) {
            background.setXpos(background.getDoor(backgroundNum, newDoorNum).getBackgroundXpos()); //set new background's position
            background.setYpos(background.getDoor(backgroundNum, newDoorNum).getBackgroundYpos());
            player.setXpos(background.getDoor(backgroundNum, newDoorNum).getPlayerXpos()); //set player's new position
            player.setYpos(background.getDoor(backgroundNum, newDoorNum).getPlayerYpos());
            currentDoor = background.getDoor(backgroundNum, newDoorNum).getNextDoor();
            backgroundNum = background.getDoor(backgroundNum, newDoorNum).getBackgroundNum(); //get the new background's unique number
            backgroundImage = t.getImage(background.getPath(backgroundNum)); //set the new background's path
            doorOpening = false;
            doorClosing = true;
            // check if background or player moves
            if(backgroundImage.getHeight(panel)<=450 && backgroundImage.getWidth(panel)<=800) {
                playerMoves = true;
            } else {
                playerMoves = false;
            }
            nearDoor = false;
        }
        private class TAdapter extends KeyAdapter {
                public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();
                    if (key == KeyEvent.VK_ESCAPE) {
                        pause();
                    }
                    if (gameOver == false && currentComponent == "mainBoard") {
                        if (key == KeyEvent.VK_R) {
                            weapon.reload(weaponNum);
                        }
                        if (key == KeyEvent.VK_E && nearDoor == true) {
                            currentDoor = doorNum;
                            doorCount = 0;
                            if (doorOpening == true) {
                                doorOpening = false;
                                doorClosing = true;
                            } else {
                                doorClosing = false;
                                doorOpening = true;
                            }
                        }    
                        if (playerMoves == false) {
                            background.keyPressed(e);
                        } else { 
                            player.keyPressed(e);
                        }
                    }
                }
                public void keyReleased(KeyEvent e) {
                    if (playerMoves == false) {
                        background.keyReleased(e);
                    } else { 
                        player.keyReleased(e);
                    }
                }
    }
}
