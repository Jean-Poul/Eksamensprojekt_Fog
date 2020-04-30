package FunctionLayer;

public class SvgSideways {

    private double width;
    private double height;
    private String viewbox;
    private double x;
    private double y;

    private double x1;
    private double y1;
    private double x2;
    private double y2;

    private int text;

    private StringBuilder svgSideways = new StringBuilder();

    private final String headerTemplate = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" viewBox=\"%s\" preserveAspectRatio=\"xMinYMin\">";
    private final String rectTemplate = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String lineTemplate = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000;\n" +
            "marker-start: url(#beginArrow);\n"+"marker-end: url(#endArrow);\" />";
    private final String dotLineTemplate = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />";
    private final String lowerTextTemplate = "<text transform=\"translate(100,100)\" style=\"text-anchor: middle\" x=\"%f\" y=\"%f\"> %d cm</text>";
    private final String upperTextTemplate = "<text style=\"text-anchor: middle\" transform=\"translate(%f,%f) rotate(-90)\">600 cm</text>\n";

    public SvgSideways(double x, double y, double width, double height, String viewbox) {
        this.width = width;
        this.height = height;
        this.viewbox = viewbox;
        this.x = x;
        this.y = y;
        svgSideways.append(String.format(headerTemplate, x, y, height, width, viewbox));
    }

    public SvgSideways(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.x1 = y1;
        this.x1 = x2;
        this.x1 = y2;
    }

    public SvgSideways(double x, double y, int text){
        this.x = x;
        this.y = y;
        this.text = text;
    }

    public void addRect(double x, double y, double height, double width){
        svgSideways.append(String.format(rectTemplate, x, y, height, width));
    }

    public void addLine(double x1, double y1, double x2, double y2){
        svgSideways.append(String.format(lineTemplate, x1, y1, x2, y2));
    }

    public void addDotLine(double x1, double y1, double x2, double y2){
        svgSideways.append(String.format(dotLineTemplate, x1, y1, x2, y2));
    }

    public void addLowerText(double x, double y, int text){
        svgSideways.append(String.format(lowerTextTemplate, x, y, text));
    }

    public void addUpperText(double x, double y, int text){
        svgSideways.append(String.format(upperTextTemplate, x, y, text));
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getViewbox() {
        return viewbox;
    }

    public void setViewbox(String viewbox) {
        this.viewbox = viewbox;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public int getText() {
        return text;
    }

    public void setint(int text) {
        this.text = text;
    }

    @Override
    public String toString() {


        return svgSideways.toString() + "</svg>" ;
    }
}
