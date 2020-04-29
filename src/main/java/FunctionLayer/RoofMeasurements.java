package FunctionLayer;

/**
 * Class for instantiating instances of request measurements for a roof
 */

public class RoofMeasurements {

    private int id;
    private String roofType;
    private String roofFlatType;
    private String raisedRoofType;
    private int roofDegree;

    /**
     *
     * @param id
     * @param roofType
     * @param roofFlatType
     * @param raisedRoofType
     * @param roofDegree
     */
    public RoofMeasurements(int id, String roofType, String roofFlatType, String raisedRoofType, int roofDegree) {
        this.id = id;
        this.roofType = roofType;
        this.roofFlatType = roofFlatType;
        this.raisedRoofType = raisedRoofType;
        this.roofDegree = roofDegree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public String getRoofFlatType() {
        return roofFlatType;
    }

    public void setRoofFlatType(String roofFlatType) {
        this.roofFlatType = roofFlatType;
    }

    public String getRaisedRoofType() {
        return raisedRoofType;
    }

    public void setRaisedRoofType(String raisedRoofType) {
        this.raisedRoofType = raisedRoofType;
    }

    public int getRoofDegree() {
        return roofDegree;
    }

    public void setRoofDegree(int roofDegree) {
        this.roofDegree = roofDegree;
    }
}
