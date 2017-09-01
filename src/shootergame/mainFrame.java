package shootergame;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mainFrame extends JFrame {
    JFrame frame = this;
    static Image icon = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"/sprites/icon.png");
    public mainFrame() {
                Container container = frame.getContentPane();
                Dimension startd = new Dimension(800,450);
                container.setPreferredSize(startd);
                frame.setLocation(200, 200);
                frame.pack();
                frame.setIconImage(icon);
                frame.setResizable(true);
                frame.setTitle("Shooter Game");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.add(new mainPanel(frame));
                Robot robot = null;
                try {
                    robot = new Robot();
                } catch (AWTException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
    	}
	public static void main(String[] args) {
                new mainFrame();
        } 
}
        
