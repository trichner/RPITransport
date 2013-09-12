package ch.k42.rpi.transport.gui;

import ch.k42.rpi.transport.api.LineNumber;
import ch.k42.rpi.transport.api.ListItem;

import javax.swing.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/12/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    private JPanel mainPanel;
    private JList listPanels;
    private JLabel lblStatus;
    private DefaultListModel<JPanel> listModel;

    private static Main _instance = null;

    public static void main(String[] args) {
        Main main = new Main();
        main.initialize();
        _instance = main;
    }

    public static void updateStatus(final String status){
        if(_instance==null) return; // no GUI running, nothing to do

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                _instance.lblStatus.setText(status);
            }
        });
    }

    private void initialize(){
        initializeUI();
        initializeAPI();
    }


    private void initializeUI(){
        JFrame frame = new JFrame("Main");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void initializeAPI() {
    }


    private void createUIComponents() {
        listModel = new DefaultListModel<JPanel>();
        addDummyItems();
        listPanels = new JList(listModel);
        listPanels.setAlignmentY(JList.CENTER_ALIGNMENT);
        listPanels.setCellRenderer(new JPanelRenderer());
    }

    private void addDummyItems(){
        ListItem item1 = new ListItem(new Date(System.currentTimeMillis()+100000),"Sihlquai","Altstetten", LineNumber.TRAM_1);
        ListItem item2 = new ListItem(new Date(System.currentTimeMillis()+200000),"Sihlquai","Triemli", LineNumber.TRAM_2);
        ListItem item3 = new ListItem(new Date(System.currentTimeMillis()+1000000),"Museum","Triemli", LineNumber.TRAM_2);
        listModel.addElement(item1.getJPanel());
        listModel.addElement(item2.getJPanel());
        listModel.addElement(item3.getJPanel());
    }
}
