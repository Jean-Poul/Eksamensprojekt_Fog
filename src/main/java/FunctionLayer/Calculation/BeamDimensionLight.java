package FunctionLayer.Calculation;

/**
 * Creates a object for beam dimension light
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class BeamDimensionLight {

    private String beamDimensionLight;
    private double beamSpacingLight;

    /**
     * Constructor
     * @param beamDimensionLight beam dimension light
     * @param beamSpacingLight beam dimension light
     */
    public BeamDimensionLight(String beamDimensionLight, double beamSpacingLight) {
        this.beamDimensionLight = beamDimensionLight;
        this.beamSpacingLight = beamSpacingLight;
    }

    // Getter and Setter
    public String getBeamDimensionLight() { return beamDimensionLight; }

    public void setBeamDimensionLight(String beamDimensionLight) { this.beamDimensionLight = beamDimensionLight; }

    public double getBeamSpacingLight() { return beamSpacingLight; }

    public void setBeamSpacingLight(double beamSpacingLight) { this.beamSpacingLight = beamSpacingLight; }
}
