package ch.k42.rpi.transport.api.model;

import ch.k42.rpi.transport.api.RestTOD;

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
    public String getImageFileName(){
        return imageFileName;
    }
    public static LineNumber getByNumberAndCategory(int number,int category){
        switch (category){
            case RestTOD.CATEGORY_CODE_TRAM:
                switch (number){
                    case 1: return TRAM_1;
                    case 2: return TRAM_2;
                    case 3: return TRAM_3;
                    case 4: return TRAM_4;
                    case 5: return TRAM_5;
                    case 6: return TRAM_6;
                    case 7: return TRAM_7;
                    case 8: return TRAM_8;
                    case 9: return TRAM_9;
                    case 10: return TRAM_10;
                    case 11: return TRAM_11;
                    case 12: return TRAM_12;
                    case 13: return TRAM_13;
                    case 14: return TRAM_14;
                    case 15: return TRAM_15;
                    case 16: return TRAM_16;
                    case 17: return TRAM_17;
                    default: return NONE;
                }
            default: return NONE;

        }
    }

}
