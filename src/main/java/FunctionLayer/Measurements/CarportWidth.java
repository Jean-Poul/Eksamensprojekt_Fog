package FunctionLayer.Measurements;

/**
 * Class for instantiating instances of request measurements for carport width
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class CarportWidth {

    private int carportWidthOption;

    /**
     * Constructor for carport width
     *
     * @param carportWidthOption carport width option
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
