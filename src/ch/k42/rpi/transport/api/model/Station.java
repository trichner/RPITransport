package ch.k42.rpi.transport.api.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/12/13
 * Time: 11:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Station {
    private String id;
    private String name;
    private String score;
    private Coordinates coordinate;

    public Station() {
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

    public Coordinates getCoordinate() {
        return coordinate;
    }
}
