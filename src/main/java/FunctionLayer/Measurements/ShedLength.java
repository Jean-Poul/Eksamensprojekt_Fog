package FunctionLayer.Measurements;

/**
 * Class for instantiating instances of request measurements for shed length
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class ShedLength {

    private int shedLengthOption;

    /**
     * Constructor for shed length
     *
     * @param shedLengthOption shed length option
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
