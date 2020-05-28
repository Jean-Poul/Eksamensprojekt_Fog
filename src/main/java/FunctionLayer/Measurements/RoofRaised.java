package FunctionLayer.Measurements;

/**
 * Class for instantiating instances of request options for a raised roof
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class RoofRaised {

    private String RoofRaisedOptions;

    /**
     * Constructor for raised roof
     *
     * @param roofRaisedOptions raised roof option
     */
    public RoofRaised(String roofRaisedOptions) {
        RoofRaisedOptions = roofRaisedOptions;
    }

    // Getters and setters
    public String getRoofRaisedOptions() {
        return RoofRaisedOptions;
    }

    public void setRoofRaisedOptions(String roofRaisedOptions) {
        RoofRaisedOptions = roofRaisedOptions;
    }
}
