package FunctionLayer.Calculation;

public class BeamDimensionHeavy {
    private String beamDimensionHeavy;
    private double beamSpacingHeavy;

    /**
     * Constructor
     * @param beamDimensionHeavy
     * @param beamSpacingHeavy
     */
    public BeamDimensionHeavy(String beamDimensionHeavy, double beamSpacingHeavy) {
        this.beamDimensionHeavy = beamDimensionHeavy;
        this.beamSpacingHeavy = beamSpacingHeavy;
    }

    // Getter and Setter
    public String getBeamDimensionHeavy() { return beamDimensionHeavy; }

    public void setBeamDimensionHeavy(String beamDimensionHeavy) { this.beamDimensionHeavy = beamDimensionHeavy; }

    public double getBeamSpacingHeavy() { return beamSpacingHeavy; }

    public void setBeamSpacingHeavy(double beamSpacingHeavy) { this.beamSpacingHeavy = beamSpacingHeavy; }
}
