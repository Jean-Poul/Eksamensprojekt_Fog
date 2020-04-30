package FunctionLayer;

/**
 * Class for instantiating instances of request measurements for carport length
 */
public class CarportLength {

    private int carportLengthOptions;

    /**
     * Constructor
     * @param carportLengthOptions
     */
    public CarportLength(int carportLengthOptions) {
        this.carportLengthOptions = carportLengthOptions;
    }

    //Getters and setters
    public int getCarportLengthOptions() {
        return carportLengthOptions;
    }

    public void setCarportLengthOptions(int carportLengthOptions) {
        this.carportLengthOptions = carportLengthOptions;
    }
}
