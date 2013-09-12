package ch.k42.rpi.transport.api.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/13/13
 * Time: 12:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class StationboardDTO implements Iterable<StationboardEntryDTO> {
    private StationDTO station;
    private StationboardEntryDTO[] stationboard;

    public StationDTO getStation() {
        return station;
    }

    public StationboardEntryDTO getEntry(int i){
        return stationboard[i];
    }

    public int size(){
        return stationboard.length;
    }



    @Override
    public String toString() {
        return "StationboardDTO{" +
                "station=" + station.getName() +
                ", stationboard=" + stationboard.length +
                '}';
    }

    @Override
    public Iterator<StationboardEntryDTO> iterator() {
        return new ArrayList<StationboardEntryDTO>(stationboard.length).iterator();  // ugly but works, packing into list first
    }
}
