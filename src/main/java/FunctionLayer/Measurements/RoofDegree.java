package FunctionLayer.Measurements;

/**
 * Class for instantiating instances of request measurements for roof degrees
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class RoofDegree {

    private int roofDegreeOption;

    /**
     * Constructor for roof degree
     *
     * @param roofDegreeOption roof degree option
     */
    public RoofDegree(int roofDegreeOption) {
        this.roofDegreeOption = roofDegreeOption;
    }

    // Getters and setters
    public int getRoofDegreeOption() {
        return roofDegreeOption;
    }

    public void setRoofDegreeOption(int roofDegreeOption) {
        this.roofDegreeOption = roofDegreeOption;
    }
}
