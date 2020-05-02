package FunctionLayer;

public class BeamDimensionLight {

    private String beamDimensionLight;
    private double beamSpacingLight;

    /**
     * Constructor
     * @param beamDimensionLight
     * @param beamSpacingLight
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
