package FunctionLayer.Measurements;

/**
 * Class for instantiating instances of request options for a flat roof
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class RoofFlat {

    private String roofFlatOptions;

    /**
     * Constructor for flat roof
     *
     * @param roofFlatOptions flat roof option
     */
    public RoofFlat(String roofFlatOptions) {
        this.roofFlatOptions = roofFlatOptions;
    }

    // Getters and setters
    public String getRoofFlatOptions() {
        return roofFlatOptions;
    }

    public void setRoofFlatOptions(String roofFlatOptions) {
        this.roofFlatOptions = roofFlatOptions;
    }
}
