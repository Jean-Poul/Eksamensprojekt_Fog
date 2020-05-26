package FunctionLayer.Measurements;

/**
 * Class for instantiating instances of request measurements for shed width
 */
public class ShedWidth {

    private int shedWidthOption;

    /**
     * Constructor
     *
     * @param shedWidthOption
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
