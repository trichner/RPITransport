package ch.k42.rpi.transport.api.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/13/13
 * Time: 12:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class Stationboard implements Iterable<StationboardEntry> {
    private Station station;
    private StationboardEntry[] stationboard;

    public Station getStation() {
        return station;
    }

    public StationboardEntry getEntry(int i){
        return stationboard[i];
    }

    public int size(){
        return stationboard.length;
    }



    @Override
    public String toString() {
        return "station=" + station.getName() + ", results=" + stationboard.length;
    }

    @Override
    public Iterator<StationboardEntry> iterator() {
        return Arrays.asList(stationboard).iterator();  // ugly but works, packing into list first
    }
}
