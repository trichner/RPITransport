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
    private static final int WIDTH_DEPARTSIN = 80;
    private static final int WIDTH_LINENO = 150;
    private static final int HEIGHT = 100;

    private static final String font = "Arial";

    private Date departure;
    private int departsInMin;
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
        this.departsInMin = countdownInMinutes();
        setupPanel();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListItem listItem = (ListItem) o;

        if (departsInMin != listItem.departsInMin) return false;
        if (departure != null ? !departure.equals(listItem.departure) : listItem.departure != null) return false;
        if (destination != null ? !destination.equals(listItem.destination) : listItem.destination != null)
            return false;
        if (number != listItem.number) return false;
        if (station != null ? !station.equals(listItem.station) : listItem.station != null) return false;

        return true;
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
        lblLineNo.setFont(new Font(font, Font.BOLD, 28));
        lblLineNo.setText(number.name());
        lblLineNo.setForeground(Color.white);
        lblLineNo.setPreferredSize(new Dimension(WIDTH_LINENO, HEIGHT));
    }

    private void setupLblStation(){
        lblStation = new JLabel();
        lblStation.setHorizontalAlignment(JLabel.LEFT);
        lblStation.setFont(new Font(font, Font.BOLD, 28));
        lblStation.setForeground(Color.white);
        lblStation.setText(station);
        lblStation.setPreferredSize(new Dimension(WIDTH_STATION, HEIGHT));
    }

    private void setupLblDestination(){
        lblDestination = new JLabel();
        lblDestination.setHorizontalAlignment(JLabel.LEFT);
        lblDestination.setFont(new Font(font, Font.BOLD, 28));
        lblDestination.setForeground(Color.white);
        lblDestination.setText(destination);
        lblDestination.setPreferredSize(new Dimension(WIDTH_DESTINATION,HEIGHT));
    }

    private void setupLblDepartsIn(){
        lblDepartsIn = new JLabel();
        lblDepartsIn.setHorizontalTextPosition(JLabel.RIGHT);
        lblDepartsIn.setHorizontalAlignment(JLabel.RIGHT);
        lblDepartsIn.setFont(new Font(font, Font.BOLD, 28));
        lblDepartsIn.setForeground(Color.white);
        lblDepartsIn.setText(String.format("%2d'",departsInMin));
        lblDepartsIn.setPreferredSize(new Dimension(WIDTH_DEPARTSIN, HEIGHT));
    }

    private int countdownInMinutes(){
        return (int) (((double)(departure.getTime()-System.currentTimeMillis()))/(1000.0*60.0));
    }

    @Override
    public int compareTo(ListItem listItem) {
        return departure.compareTo(listItem.departure);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
