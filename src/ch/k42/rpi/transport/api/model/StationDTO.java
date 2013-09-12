package ch.k42.rpi.transport.api.model;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/12/13
 * Time: 11:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class StationDTO {
    private String id;
    private String name;
    private String score;
    private CoordinatesDTO coordinate;

    public StationDTO() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public CoordinatesDTO getCoordinate() {
        return coordinate;
    }
}
