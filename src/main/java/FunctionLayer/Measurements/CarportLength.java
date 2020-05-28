package FunctionLayer.Measurements;

/**
 * Class for instantiating instances of request measurements for carport length
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class CarportLength {

    private int carportLengthOptions;

    /**
     * Constructor for carport length
     *
     * @param carportLengthOptions carport length option
     */
    public CarportLength(int carportLengthOptions) {
        this.carportLengthOptions = carportLengthOptions;
    }

    // Getters and setters
    public int getCarportLengthOptions() {
        return carportLengthOptions;
    }

    public void setCarportLengthOptions(int carportLengthOptions) {
        this.carportLengthOptions = carportLengthOptions;
    }
}
