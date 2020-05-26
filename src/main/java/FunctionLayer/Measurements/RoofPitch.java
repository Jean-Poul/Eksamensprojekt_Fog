package FunctionLayer.Measurements;

/**
 * Class for instantiating instances of request roof pitch
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class RoofPitch {

    private int roof_pitch_id;
    private int pitch;
    private double factor;

    /**
     * Constructor for roof pitch
     * @param roof_pitch_id roof pitch id
     * @param pitch pitch
     * @param factor factor
     */
    public RoofPitch(int roof_pitch_id, int pitch, double factor) {
        this.roof_pitch_id = roof_pitch_id;
        this.pitch = pitch;
        this.factor = factor;
    }

    public int getRoof_pitch_id() { return roof_pitch_id; }

    public void setRoof_pitch_id(int roof_pitch_id) { this.roof_pitch_id = roof_pitch_id; }

    public int getPitch() { return pitch; }

    public void setPitch(int pitch) { this.pitch = pitch; }

    public double getFactor() { return factor; }

    public void setFactor(double factor) { this.factor = factor; }
}
