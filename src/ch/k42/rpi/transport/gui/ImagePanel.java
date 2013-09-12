package ch.k42.rpi.transport.gui;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/12/13
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel(BufferedImage image) {
        //this.image = image;
        try {
            this.image = ImageIO.read(new File("image name and path"));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters
    }

}