package ch.k42.rpi.transport.api.model;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/13/13
 * Time: 12:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class StationboardEntry {
    private Stop stop;
    private String name;
    private String category;
    private int categoryCode;
    private String number;
    private String to;
    private String subcategory;

    public Stop getStop() {
        return stop;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getNumber() {
        return number;
    }

    public String getTo() {
        return to;
    }

    public String getSubcategory() {
        return subcategory;
    }
}
