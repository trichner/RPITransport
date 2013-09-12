package ch.k42.rpi.transport.api;

import ch.k42.rpi.transport.api.model.StationboardDTO;
import ch.k42.rpi.transport.api.model.Transportations;
import ch.k42.rpi.transport.gui.Main;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/12/13
 * Time: 10:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class TimetableUpdater implements Runnable {


    private static String demo = "{\n \"key1\": \"value1\",\n \"key2\": \"value2\",\n  \"key3\": \"value3\" \n   }";


    @Override
    public void run() {
        StationboardDTO stationboard = null;
        try {
            stationboard = RestTOD.getStationboard(RestTOD.STATION_SIHLQUAI_HB, 10, Transportations.TRAMWAY_UNDERGROUND);
            Main.updateStatus("API OK." + stationboard);

            System.out.println(stationboard); //TODO do stuff with stationboard!

        } catch (Exception e) {
            Main.updateStatus("API fail.");
        }
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
