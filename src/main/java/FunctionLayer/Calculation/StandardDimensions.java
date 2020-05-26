package FunctionLayer.Calculation;

public class StandardDimensions {

    private int standard_dimensions_id;
    private int bottom_lathspan;
    private int bottom_laths;
    private double top_lath_gap;
    private double avg_lath_span;
    private double roof_tile_length;
    private double roof_tile_width;
    private double roof_trapez_length;
    private double roof_trapez_width;
    private String shed_claddeing_board_dim;
    private String beam_dimension_heavy;
    private String beam_dimension_light;

    /**
     * Constructor for admin
     * @param standard_dimensions_id
     * @param bottom_lathspan
     * @param bottom_laths
     * @param top_lath_gap
     * @param avg_lath_span
     * @param roof_tile_length
     * @param roof_tile_width
     * @param roof_trapez_length
     * @param roof_trapez_width
     * @param shed_claddeing_board_dim
     * @param beam_dimension_heavy
     * @param beam_dimension_light
     */
    public StandardDimensions(int standard_dimensions_id, int bottom_lathspan, int bottom_laths, double top_lath_gap, double avg_lath_span, double roof_tile_length, double roof_tile_width, double roof_trapez_length, double roof_trapez_width, String shed_claddeing_board_dim, String beam_dimension_heavy, String beam_dimension_light) {
        this.standard_dimensions_id = standard_dimensions_id;
        this.bottom_lathspan = bottom_lathspan;
        this.bottom_laths = bottom_laths;
        this.top_lath_gap = top_lath_gap;
        this.avg_lath_span = avg_lath_span;
        this.roof_tile_length = roof_tile_length;
        this.roof_tile_width = roof_tile_width;
        this.roof_trapez_length = roof_trapez_length;
        this.roof_trapez_width = roof_trapez_width;
        this.shed_claddeing_board_dim = shed_claddeing_board_dim;
        this.beam_dimension_heavy = beam_dimension_heavy;
        this.beam_dimension_light = beam_dimension_light;
    }

    /**
     * Constructor
     * @param bottom_lathspan
     * @param bottom_laths
     * @param top_lath_gap
     * @param avg_lath_span
     * @param roof_tile_length
     * @param roof_tile_width
     * @param roof_trapez_length
     * @param roof_trapez_width
     * @param shed_claddeing_board_dim
     * @param beam_dimension_heavy
     * @param beam_dimension_light
     */
    public StandardDimensions(int bottom_lathspan, int bottom_laths, double top_lath_gap, double avg_lath_span, double roof_tile_length, double roof_tile_width, double roof_trapez_length, double roof_trapez_width, String shed_claddeing_board_dim, String beam_dimension_heavy, String beam_dimension_light) {
        this.bottom_lathspan = bottom_lathspan;
        this.bottom_laths = bottom_laths;
        this.top_lath_gap = top_lath_gap;
        this.avg_lath_span = avg_lath_span;
        this.roof_tile_length = roof_tile_length;
        this.roof_tile_width = roof_tile_width;
        this.roof_trapez_length = roof_trapez_length;
        this.roof_trapez_width = roof_trapez_width;
        this.shed_claddeing_board_dim = shed_claddeing_board_dim;
        this.beam_dimension_heavy = beam_dimension_heavy;
        this.beam_dimension_light = beam_dimension_light;
    }

    // Getter and Setter
    public int getStandard_dimensions_id() { return standard_dimensions_id; }

    public void setStandard_dimensions_id(int standard_dimensions_id) { this.standard_dimensions_id = standard_dimensions_id; }

    public int getBottom_lathspan() { return bottom_lathspan; }

    public void setBottom_lathspan(int bottom_lathspan) { this.bottom_lathspan = bottom_lathspan; }

    public int getBottom_laths() { return bottom_laths; }

    public void setBottom_laths(int bottom_laths) { this.bottom_laths = bottom_laths; }

    public double getTop_lath_gap() { return top_lath_gap; }

    public void setTop_lath_gap(double top_lath_gap) { this.top_lath_gap = top_lath_gap; }

    public double getAvg_lath_span() { return avg_lath_span; }

    public void setAvg_lath_span(double avg_lath_span) { this.avg_lath_span = avg_lath_span; }

    public double getRoof_tile_length() { return roof_tile_length; }

    public void setRoof_tile_length(double roof_tile_length) { this.roof_tile_length = roof_tile_length; }

    public double getRoof_tile_width() { return roof_tile_width; }

    public void setRoof_tile_width(double roof_tile_width) { this.roof_tile_width = roof_tile_width; }

    public double getRoof_trapez_length() { return roof_trapez_length; }

    public void setRoof_trapez_length(double roof_trapez_length) { this.roof_trapez_length = roof_trapez_length; }

    public double getRoof_trapez_width() { return roof_trapez_width; }

    public void setRoof_trapez_width(double roof_trapez_width) { this.roof_trapez_width = roof_trapez_width; }

    public String getShed_claddeing_board_dim() { return shed_claddeing_board_dim; }

    public void setShed_claddeing_board_dim(String shed_claddeing_board_dim) { this.shed_claddeing_board_dim = shed_claddeing_board_dim; }

    public String getBeam_dimension_heavy() { return beam_dimension_heavy; }

    public void setBeam_dimension_heavy(String beam_dimension_heavy) { this.beam_dimension_heavy = beam_dimension_heavy; }

    public String getBeam_dimension_light() { return beam_dimension_light; }

    public void setBeam_dimension_light(String beam_dimension_light) { this.beam_dimension_light = beam_dimension_light; }
}
