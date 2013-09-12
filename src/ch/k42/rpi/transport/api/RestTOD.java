package ch.k42.rpi.transport.api;

import ch.k42.rpi.transport.api.model.StationboardDTO;
import ch.k42.rpi.transport.api.model.Transportations;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/13/13
 * Time: 1:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class RestTOD {

    private static final String REST_TRANSPORT_STATIONBOARD_URL = "http://transport.opendata.ch/v1/stationboard";


    public static final String STATION_SIHLQUAI_HB = "008591368";
    public static final String STATION_MUSEUM_GESTALTUNG = "008591282";

    public static StationboardDTO getStationboard(String id,int limit,Transportations type) throws Exception {
        // Build arguments
        String[] params = {"limit","id","transportations[]"};
        String[] args = new String[3];
        args[0] = Integer.toString(limit);
        args[1] = id;
        args[2] = type.getType();

        // Do request
        String http = httpGET(REST_TRANSPORT_STATIONBOARD_URL, params, args);

        // parse JSON
        Gson gson = new Gson();
        StationboardDTO stationboard = gson.fromJson(http,StationboardDTO.class);

        return stationboard;
    }

    private static String httpGET(String url, String[] paramName,String[] paramVal) throws Exception {
        // error catching for safety
        if(paramName.length!=paramVal.length){
            throw new IllegalArgumentException("Argument count not equals parameter count!");
        }
        if(paramName.length<1){
            throw new IllegalArgumentException("Too few arguments. Need at least one!");
        }

        // build URL string
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(url)
                .append('?');
        for(int i=0;i<paramName.length-1;i++){
            urlBuilder.append(paramName[i])
                    .append('=')
                    .append(paramVal[i])
                    .append('&');
        }
        urlBuilder.append(paramName[paramName.length-1])
                .append('=')
                .append(paramVal[paramName.length-1]);
        //System.out.println(urlBuilder);
        URL obj = new URL(urlBuilder.toString());

        //Open up our connection
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        if(responseCode<200||responseCode>299){ // HTTP Response code NOT ok
            throw new Exception("HTTP Response code not OK: " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}