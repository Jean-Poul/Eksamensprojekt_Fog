package FunctionLayer;

/**
 * Class for instantiating instances of request measurements for roof degrees
 */
public class RoofDegree {

    private int roofDegreeOption;

    /**
     * Constructor
     * @param roofDegreeOption
     */
    public RoofDegree(int roofDegreeOption) {
        this.roofDegreeOption = roofDegreeOption;
    }

    //Getters and setters
    public int getRoofDegreeOption() {
        return roofDegreeOption;
    }

    public void setRoofDegreeOption(int roofDegreeOption) {
        this.roofDegreeOption = roofDegreeOption;
    }
}
