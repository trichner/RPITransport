package ch.k42.rpi.transport.minions;

/**
 * Singleton containing all Information about the device, Settings/Properties
 * @author Thomas
 *
 */
public class Settings {

    public static final String default_location= "Sihlquai";
    public static final Integer default_offsetInMinutes = 3;
    public static final Integer default_nuberOfEntries= 8;
    public static final String default_urlAPI= "http://transport.opendata.ch/v1/stationboard";
    public static final Integer default_updateIntervallInSeconds = 20;

    private String station = default_location;
    private Integer numberOfEntries = default_nuberOfEntries;
    private String urlAPI = default_urlAPI;
    private Integer updateIntervallInSeconds = default_updateIntervallInSeconds;
    private Integer offsetInMinutes = default_offsetInMinutes;

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Integer getNumberOfEntries() {
        return numberOfEntries;
    }

    public void setNumberOfEntries(Integer numberOfEntries) {
        this.numberOfEntries = numberOfEntries;
    }

    public String getUrlAPI() {
        return urlAPI;
    }

    public void setUrlAPI(String urlAPI) {
        this.urlAPI = urlAPI;
    }

    public Integer getUpdateIntervallInSeconds() {
        return updateIntervallInSeconds;
    }

    public void setUpdateIntervallInSeconds(Integer updateIntervallInSeconds) {
        this.updateIntervallInSeconds = updateIntervallInSeconds;
    }

    public Integer getOffsetInMinutes() {
        return offsetInMinutes;
    }

    public void setOffsetInMinutes(Integer offsetInMinutes) {
        this.offsetInMinutes = offsetInMinutes;
    }
}
