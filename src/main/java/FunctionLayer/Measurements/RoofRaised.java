package FunctionLayer.Measurements;

/**
 * Class for instantiating instances of request options for a raised roof
 */
public class RoofRaised {

    private String RoofRaisedOptions;

    /**
     * Constructor
     *
     * @param roofRaisedOptions
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
