package FunctionLayer.Measurements;

public class RoofPitch {

    private int roof_pitch_id;
    private int pitch;
    private double factor;

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
