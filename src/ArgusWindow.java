import javax.swing.*;
import java.awt.*;

public class ArgusWindow extends JPanel {

    String omniTemp = "Unavailable";

    public void updateTemp(String temp){
        omniTemp = temp;
        repaint();
        this.setDoubleBuffered(true);
    }

    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        g.drawString(omniTemp, this.getWidth()/2-30, this.getHeight()/2);
    }

}
