package ch.k42.rpi.transport.gui;

import ch.k42.rpi.transport.api.ListItem;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/12/13
 * Time: 4:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class JPanelRenderer extends JPanel implements ListCellRenderer<JPanel> {

    public JPanelRenderer() {
        //setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList jList, JPanel item, int index, boolean b, boolean b2) {
        return item;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
