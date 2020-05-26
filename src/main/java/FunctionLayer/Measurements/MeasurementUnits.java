package FunctionLayer.Measurements;

/**
 * Class for instantiating instances of request measurements units
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class MeasurementUnits {

    private int measurementUnitsId;
    private int units;
    private int c_width;
    private int c_length;
    private int ts_width;
    private int ts_length;

    /**
     * Constructor for measurement units
     *
     * @param measurementUnitsId measurement units id
     * @param units units
     * @param c_width carport width
     * @param c_length carport length
     * @param ts_width shed width
     * @param ts_length shed length
     */
    public MeasurementUnits(int measurementUnitsId, int units, int c_width, int c_length, int ts_width, int ts_length) {
        this.measurementUnitsId = measurementUnitsId;
        this.units = units;
        this.c_width = c_width;
        this.c_length = c_length;
        this.ts_width = ts_width;
        this.ts_length = ts_length;
    }

    public int getMeasurementUnitsId() {
        return measurementUnitsId;
    }

    public void setMeasurementUnitsId(int measurementUnitsId) {
        this.measurementUnitsId = measurementUnitsId;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getC_width() {
        return c_width;
    }

    public void setC_width(int c_width) {
        this.c_width = c_width;
    }

    public int getC_length() {
        return c_length;
    }

    public void setC_length(int c_length) {
        this.c_length = c_length;
    }

    public int getTs_width() {
        return ts_width;
    }

    public void setTs_width(int ts_width) {
        this.ts_width = ts_width;
    }

    public int getTs_length() {
        return ts_length;
    }

    public void setTs_length(int ts_length) {
        this.ts_length = ts_length;
    }
}
