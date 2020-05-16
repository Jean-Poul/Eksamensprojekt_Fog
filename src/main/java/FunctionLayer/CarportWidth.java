package FunctionLayer;

/**
 * Class for instantiating instances of request measurements for carport width
 */
public class CarportWidth {

    private int carportWidthOption;

    /**
     * Constructor
     *
     * @param carportWidthOption
     */
    public CarportWidth(int carportWidthOption) {
        this.carportWidthOption = carportWidthOption;
    }

    // Getters and setters
    public int getCarportWidthOption() {
        return carportWidthOption;
    }

    public void setCarportWidthOption(int carportWidthOption) {
        this.carportWidthOption = carportWidthOption;
    }
}
