package ch.k42.rpi.transport.api.model;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/12/13
 * Time: 11:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class StopDTO {
    private StationDTO station;
    private String name;
    private String category;
    private int number;
    private String operator;
    private String to;

    public StationDTO getStation() {
        return station;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getNumber() {
        return number;
    }

    public String getOperator() {
        return operator;
    }

    public String getTo() {
        return to;
    }
}
