package shootergame;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class mouseWheelListener implements MouseWheelListener {
    mainPanel panelClass;
    public mouseWheelListener(mainPanel newPanel) {
        panelClass = newPanel;
        panelClass.panel.addMouseWheelListener(this);
    }
    public void mouseWheelMoved(MouseWheelEvent e) {
        int count = e.getWheelRotation();
        panelClass.wheelScroll(count);
    }
}
