package ch.k42.rpi.transport.minions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/13/13
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class RPITSettings {

    //==== Constants
    private static final String settingsfile = "rpitransport.properties";

    private static RPITSettings _instance = new RPITSettings();

    private Settings settings;

    private RPITSettings(){
        load();
    }

    private boolean load(){
        Gson gson = new Gson();
        try {
            settings = gson.fromJson(new String(Files.readAllBytes(Paths.get(settingsfile))),Settings.class);
            return true;
        } catch (IOException e) {
            settings = new Settings(); // couldn't read config file? use default
            return false;
        }
    }

    public static boolean reload(){
        return _instance.load();
    }

    public static boolean store(){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String file = gson.toJson(_instance.settings);
            Files.write(Paths.get(settingsfile),file.getBytes(), StandardOpenOption.CREATE);
            return true;
        } catch (IOException e) {
            System.err.println("Can't write settings file: " + settingsfile);
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static String getAPIUrl(){
        String url=_instance.settings.getUrlAPI();
        if(url==null){
            url = Settings.default_urlAPI;
            _instance.settings.setUrlAPI(url);
        }
        return url;
    }

    public static String getStation(){
        String station=_instance.settings.getStation();
        if(station==null){
            station = Settings.default_location;
            _instance.settings.setStation(station);
        }
        return station;
    }

    public static int getNumberOfEntries(){
        Integer numberOfEntries= _instance.settings.getNumberOfEntries();
        if(numberOfEntries==null){
            numberOfEntries = Settings.default_nuberOfEntries;
            _instance.settings.setNumberOfEntries(numberOfEntries);
        }
        return numberOfEntries.intValue();
    }

    public static int getUpdateIntervallInSeconds(){
        Integer uinterval = _instance.settings.getUpdateIntervallInSeconds();
        if(uinterval==null){
            uinterval = Settings.default_updateIntervallInSeconds;
            _instance.settings.setUpdateIntervallInSeconds(uinterval);
        }
        return uinterval.intValue();
    }

    public static int getOffsetInMinutes(){
        Integer offset = _instance.settings.getOffsetInMinutes();
        if(offset==null){
            offset = Settings.default_offsetInMinutes;
            _instance.settings.setOffsetInMinutes(offset);
        }
        return offset.intValue();
    }


}
