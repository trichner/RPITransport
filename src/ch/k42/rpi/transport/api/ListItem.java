package ch.k42.rpi.transport.api;

import ch.k42.rpi.transport.api.model.LineNumber;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/12/13
 * Time: 3:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListItem implements Comparable<ListItem> {

    private static final int WIDTH_STATION = 600;
    private static final int WIDTH_DESTINATION = 600;
    private static final int WIDTH_DEPARTSIN = 60;
    private static final int WIDTH_LINENO = 150;
    private static final int HEIGHT = 100;

    private Date departure;
    private String station;
    private String destination;
    private LineNumber number;

    private JPanel panel;
    private JLabel lblLineNo;
    private JLabel lblStation;
    private JLabel lblDestination;
    private JLabel lblDepartsIn;

    public ListItem(Date departure, String station, String destination, LineNumber number){
        this.departure = departure;
        this.station = station;
        this.destination = destination;
        this.number = number;

        setupPanel();
    }

    public boolean isOutOfDate(Date now){
        return departure.before(now);
    }

    public boolean isOutOfDate(){
        return isOutOfDate(new Date());
    }

    public JPanel getJPanel(){
        return panel;
    }

    private void setupPanel(){
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING));
        panel.setAlignmentY(JPanel.LEFT_ALIGNMENT);
        panel.setBackground(new Color(0,0x33,0x99,255)); // should be full alpha
        setupLblLineNo();
        panel.add(lblLineNo);
        setupLblStation();
        panel.add(lblStation);
        setupLblDestination();
        panel.add(lblDestination);
        setupLblDepartsIn();
        panel.add(lblDepartsIn);
    }

    private void setupLblLineNo(){
        lblLineNo = new JLabel();
        lblLineNo.setHorizontalAlignment(JLabel.LEFT);
        lblLineNo.setFont(new Font("Andale Mono", Font.BOLD, 28));
        lblLineNo.setText(number.name());
        lblLineNo.setForeground(Color.white);
        lblLineNo.setPreferredSize(new Dimension(WIDTH_LINENO, HEIGHT));
    }

    private void setupLblStation(){
        lblStation = new JLabel();
        lblStation.setHorizontalAlignment(JLabel.LEFT);
        lblStation.setFont(new Font("Verdana", Font.BOLD, 28));
        lblStation.setForeground(Color.white);
        lblStation.setText(station);
        lblStation.setPreferredSize(new Dimension(WIDTH_STATION, HEIGHT));
    }

    private void setupLblDestination(){
        lblDestination = new JLabel();
        lblDestination.setHorizontalAlignment(JLabel.LEFT);
        lblDestination.setFont(new Font("Verdana", Font.BOLD, 28));
        lblDestination.setForeground(Color.white);
        lblDestination.setText(destination);
        lblDestination.setPreferredSize(new Dimension(WIDTH_DESTINATION,HEIGHT));
    }

    private void setupLblDepartsIn(){
        lblDepartsIn = new JLabel();
        lblDepartsIn.setHorizontalTextPosition(JLabel.RIGHT);
        lblDepartsIn.setHorizontalAlignment(JLabel.RIGHT);
        lblDepartsIn.setFont(new Font("Verdana", Font.BOLD, 28));
        lblDepartsIn.setForeground(Color.white);
        lblDepartsIn.setText(String.format("%2d'",countdownInMinutes()));
        lblDepartsIn.setPreferredSize(new Dimension(WIDTH_DEPARTSIN, HEIGHT));
    }

    /**
     *  Updates the countdown and it's label
     * @param now the current time
     * @return true if it is still valid (not already departed)
     */
    public boolean update(Date now){
        int countdown = (int) (((double)(departure.getTime()-now.getTime()))/(1000.0*60.0));
        lblDepartsIn.setText(String.format("%2d'",countdown));
        if(isOutOfDate(now)){
            return false;
        }else {
            lblDepartsIn.invalidate();
            return true;
        }
    }

    private int countdownInMinutes(){
        return (int) (((double)(departure.getTime()-System.currentTimeMillis()))/(1000.0*60.0));
    }

    @Override
    public int compareTo(ListItem listItem) {
        return departure.compareTo(listItem.departure);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
