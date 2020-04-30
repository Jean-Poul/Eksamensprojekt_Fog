package FunctionLayer;

/**
 * Class for instantiating instances of request options for a flat roof
 */
public class RoofFlat {

    private String roofFlatOptions;

    /**
     * Constructor
     * @param roofFlatOptions
     */
    public RoofFlat(String roofFlatOptions) {
        this.roofFlatOptions = roofFlatOptions;
    }

    //Getters and setters
    public String getRoofFlatOptions() {
        return roofFlatOptions;
    }

    public void setRoofFlatOptions(String roofFlatOptions) {
        this.roofFlatOptions = roofFlatOptions;
    }
}
