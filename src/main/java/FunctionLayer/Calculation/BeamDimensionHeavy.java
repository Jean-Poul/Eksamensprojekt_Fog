package FunctionLayer.Calculation;

/**
 * Creates a object for beam dimension heavy
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class BeamDimensionHeavy {
    private String beamDimensionHeavy;
    private double beamSpacingHeavy;

    /**
     * Constructor
     * @param beamDimensionHeavy beam dimension heavy
     * @param beamSpacingHeavy beam dimension light
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
