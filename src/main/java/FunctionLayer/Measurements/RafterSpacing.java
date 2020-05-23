package FunctionLayer.Measurements;

public class RafterSpacing {

    private int rafter_spacing_id;
    private String category;
    private String beam_dimension;
    private double beam_spacing;
    private double rafter_length;

    public RafterSpacing(int rafter_spacing_id, String category, String beam_dimension, double beam_spacing, double rafter_length) {
        this.rafter_spacing_id = rafter_spacing_id;
        this.category = category;
        this.beam_dimension = beam_dimension;
        this.beam_spacing = beam_spacing;
        this.rafter_length = rafter_length;
    }

    public int getRafter_spacing_id() { return rafter_spacing_id; }

    public void setRafter_spacing_id(int rafter_spacing_id) { this.rafter_spacing_id = rafter_spacing_id; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getBeam_dimension() { return beam_dimension; }

    public void setBeam_dimension(String beam_dimension) { this.beam_dimension = beam_dimension; }

    public double getBeam_spacing() { return beam_spacing; }

    public void setBeam_spacing(double beam_spacing) { this.beam_spacing = beam_spacing; }

    public double getRafter_length() {
        return rafter_length;
    }

    public void setRafter_length(double rafter_length) { this.rafter_length = rafter_length; }
}
