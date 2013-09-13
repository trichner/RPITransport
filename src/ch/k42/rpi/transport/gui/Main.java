package ch.k42.rpi.transport.gui;

import ch.k42.rpi.transport.api.model.LineNumber;
import ch.k42.rpi.transport.api.ListItem;
import ch.k42.rpi.transport.api.TimetableUpdater;
import ch.k42.rpi.transport.minions.RPITSettings;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
    private JFrame mainFrame;
    private DefaultListModel<ListItem> listModel;
    private ScheduledThreadPoolExecutor executor;





    public static void main(String[] args) {
        Main main = new Main();
        main.initialize();
    }

    public void updateStatus(final String status){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                lblStatus.setText(status);
            }
        });
    }

    private void initialize(){
        System.out.println("Initializing...");
        initializeUI();
        System.out.println("UI online.");
        initializeAPI();
        System.out.println("API online.");
        System.out.println("All systems nominal.");

    }


    private void initializeUI(){
        mainFrame = new JFrame("Main");
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);

        if(mainFrame.getGraphicsConfiguration().getDevice().isFullScreenSupported()){ // fullscreen? (I have no idea how this works...)
            // go fullscreeeen!
            Window w = SwingUtilities.windowForComponent(mainPanel);
            if (w instanceof JFrame) {
                JFrame frame = (JFrame) w;
                frame.dispose();
                frame.setUndecorated(true);
                frame.getGraphicsConfiguration().getDevice().setFullScreenWindow(w);
                frame.setContentPane(mainPanel);
                frame.revalidate();
                frame.repaint();
                frame.setVisible(true);
            }
        }
    }

    private void initializeAPI() {
        // we should be able to go in peace
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                shutdownAPI();
            }
        });

        // fire up everything
        executor = new ScheduledThreadPoolExecutor(1);
        long offset = RPITSettings.getOffsetInMinutes()*60*1000;
        long updateInterval = RPITSettings.getUpdateIntervallInSeconds();
        int maxListSize = RPITSettings.getNumberOfEntries();
        executor.scheduleAtFixedRate(new TimetableUpdater(this, offset,maxListSize),1,updateInterval, TimeUnit.SECONDS);
    }

    private void shutdownAPI() {
        System.out.println("Shutting down safely.");
        executor.shutdownNow();
        RPITSettings.store();
    }


    private void createUIComponents() {
        listModel = new DefaultListModel<ListItem>();
        addDummyItems();
        listPanels = new JList(listModel);
        listPanels.setAlignmentY(JList.CENTER_ALIGNMENT);
        listPanels.setCellRenderer(new JPanelRenderer());
    }

    public DefaultListModel<ListItem> getListModel() {
        return listModel;
    }

    private void addDummyItems(){
        ListItem item1 = new ListItem(new Date(System.currentTimeMillis()+100000),"Sihlquai","Altstetten", LineNumber.TRAM_1);
        ListItem item2 = new ListItem(new Date(System.currentTimeMillis()+200000),"Sihlquai","Triemli", LineNumber.TRAM_2);
        ListItem item3 = new ListItem(new Date(System.currentTimeMillis()+1000000),"Museum","Triemli", LineNumber.TRAM_2);
        listModel.addElement(item1);
        listModel.addElement(item2);
        listModel.addElement(item3);
    }
}
