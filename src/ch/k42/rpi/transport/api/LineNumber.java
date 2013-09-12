package ch.k42.rpi.transport.api;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/12/13
 * Time: 3:49 PM
 * To change this template use File | Settings | File Templates.
 */
public enum LineNumber {
    TRAM_1("tram_1.png"),
    TRAM_2("tram_2.png"),
    TRAM_3("tram_3.png"),
    TRAM_4("tram_4.png"),
    TRAM_5("tram_5.png"),
    TRAM_6("tram_6.png"),
    TRAM_7("tram_7.png"),
    TRAM_8("tram_8.png"),
    TRAM_9("tram_9.png"),
    TRAM_10("tram_10.png"),
    TRAM_11("tram_11.png"),
    TRAM_12("tram_12.png"),
    TRAM_13("tram_13.png"),
    TRAM_14("tram_14.png"),
    TRAM_15("tram_15.png"),
    TRAM_16("tram_16.png"),
    TRAM_17("tram_17.png"),
    NONE("none.png");
    private String imageFileName;
    private LineNumber(String imageFileName){
        this.imageFileName = imageFileName;
    }
}
