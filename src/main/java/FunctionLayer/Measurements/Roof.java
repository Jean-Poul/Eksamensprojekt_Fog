package FunctionLayer.Measurements;

public class Roof {

    private int roof_id;
    private String roof_type;
    private String roof_category;
    private String roof_material;

    public Roof(int roof_id, String roof_type, String roof_category, String roof_material) {
        this.roof_id = roof_id;
        this.roof_type = roof_type;
        this.roof_category = roof_category;
        this.roof_material = roof_material;
    }

    public int getRoof_id() { return roof_id; }

    public void setRoof_id(int roof_id) { this.roof_id = roof_id; }

    public String getRoof_type() { return roof_type; }

    public void setRoof_type(String roof_type) { this.roof_type = roof_type; }

    public String getRoof_category() { return roof_category; }

    public void setRoof_category(String roof_category) { this.roof_category = roof_category; }

    public String getRoof_material() { return roof_material; }

    public void setRoof_material(String roof_material) { this.roof_material = roof_material; }
}
