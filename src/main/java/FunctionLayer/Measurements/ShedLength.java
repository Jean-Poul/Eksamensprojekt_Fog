package FunctionLayer.Measurements;

/**
 * Class for instantiating instances of request measurements for shed length
 */
public class ShedLength {

    private int shedLengthOption;

    /**
     * Constructor
     *
     * @param shedLengthOption
     */
    public ShedLength(int shedLengthOption) {
        this.shedLengthOption = shedLengthOption;
    }

    // Getters and setters
    public int getShedLengthOption() {
        return shedLengthOption;
    }

    public void setShedLengthOption(int shedLengthOption) {
        this.shedLengthOption = shedLengthOption;
    }
}
