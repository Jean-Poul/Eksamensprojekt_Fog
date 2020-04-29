package FunctionLayer;

/**
 * Class for instantiating instances of request measurements for a shed
 */

public class ShedMeasurements {

    private int id;
    private int shedWidth;
    private int shedLength;

    /**
     *
     * @param id
     * @param shedWidth
     * @param shedLength
     */
    public ShedMeasurements(int id, int shedWidth, int shedLength) {
        this.id = id;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }
}
