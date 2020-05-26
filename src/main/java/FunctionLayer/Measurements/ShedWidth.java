package FunctionLayer.Measurements;

/**
 * Class for instantiating instances of request measurements for shed width
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class ShedWidth {

    private int shedWidthOption;

    /**
     * Constructor for shed width
     *
     * @param shedWidthOption shed width option
     */
    public ShedWidth(int shedWidthOption) {
        this.shedWidthOption = shedWidthOption;
    }

    // Getters and setters
    public int getShedWidthOption() {
        return shedWidthOption;
    }

    public void setShedWidthOption(int shedWidthOption) {
        this.shedWidthOption = shedWidthOption;
    }
}
