package ch.k42.rpi.transport.api;

import ch.k42.rpi.transport.api.model.LineNumber;
import ch.k42.rpi.transport.api.model.Stationboard;
import ch.k42.rpi.transport.api.model.StationboardEntry;
import ch.k42.rpi.transport.api.model.Transportations;
import ch.k42.rpi.transport.gui.ListItemListModel;
import ch.k42.rpi.transport.gui.Main;
import ch.k42.rpi.transport.minions.RPITSettings;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/12/13
 * Time: 10:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class TimetableUpdater implements Runnable {

    private static final String FORMAT_DATE_ISO="yyyy-MM-dd'T'HH:mm:ssZ";
    private static final long MAX_OFFSET_MILLIS = 1000*60*99; // 2h



    private static int failCount = 0;


    private Main ui;
    private long offsetInMillis;
    private int size;
    private String station;
    private Transportations[] transportations;

    public TimetableUpdater(Main ui,long offsetInMillis,int size) {
        this.ui = ui;
        this.offsetInMillis = offsetInMillis;
        this.size = size;
        station = RPITSettings.getStation();
        transportations = RPITSettings.getTransportations();
    }

    @Override
    public void run() {
        Stationboard stationboard = null;
        Date now = new Date();
        try {
            stationboard = RestTOD.getStationboardByLocation(station, size + 20,transportations); // add a few more to have at least enough to sort some out
            ui.updateStatus("API OK.");
            failCount=0;
            System.out.println("Updated: " + stationboard); //TODO do stuff with stationboard!

            List<ListItem> listItems = new ArrayList<ListItem>(40);
            ListItem item;

            DateFormat isoDate = new SimpleDateFormat(FORMAT_DATE_ISO);
            for(StationboardEntry e : stationboard){

                try {
                    if(e==null)             continue;
                    if(e.getStop()==null)   continue;

                    Date departure = isoDate.parse(e.getStop().getDeparture());
                    //System.out.println("Departure: " + departure);
                    if(!isInRange(now,departure,MAX_OFFSET_MILLIS)){
                        continue; // no need to add, to far away
                    }
                    if(isInRange(now,departure,offsetInMillis)){
                        continue; // no need to add, already gone
                    }

                    String station = e.getStop().getStation().getName();
                    //System.out.println("Station: "+station);
                    if(station==null)       continue; // error, no need to parse

                    String destination = e.getTo();
                    if(destination==null){
                        //System.out.println("dest null?");
                        continue; // error, no need to proceed
                    }
                    //System.out.println("Destination: " + destination);
                    LineNumber number = LineNumber.getByNumberAndCategory(Integer.parseInt(e.getNumber()),e.getCategoryCode()); // set cool icon!
                    //System.out.println("adding item");
                    item = new ListItem(departure,station,destination,number);
                    listItems.add(item);
                    if(listItems.size()>=size) break; // our list is big enough
                } catch (ParseException e1) {
                    System.err.println("Can't read departure: " + e1.getMessage());
                } catch (NumberFormatException e1) {
                    System.err.println("Can't read Line Number '"+e.getNumber()+"': " + e1.getMessage());
                }
            }

            Collections.sort(listItems);        // Sort the list

            // update listmodel
            System.out.println("Refreshing list.");
            ui.getListModel().setList(listItems);

        } catch (Exception e) {
            System.err.println("Error updating: " + e.getMessage());
            failCount++;
            ui.updateStatus("API fail. (" + (failCount>10000 ? "many times" : failCount) + ")");
        }
    }

    private boolean isInRange(Date a, Date b, long timeInMillis){
        return (b.getTime()-a.getTime())<timeInMillis;
    }

    public static String demoJson(){
        Path file = Paths.get("jsonDemo.txt");
        String str = null;
        try {
            str = new String(Files.readAllBytes(file));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return str;
    }

}
