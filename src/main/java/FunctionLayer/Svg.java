package FunctionLayer;

/**
 * KLASSEN HAR BRUG FOR FØLGENDE INFORMATIONER FRA DATABASEN.
 * double       - carpot width
 * double       - carpot height
 * String       - viewbox mål (Måske samme som carport størrelse?)
 * int          - x
 * int          - y
 * int          - x1
 * int          - x2
 * int          - y1
 * int          - y2
 * KAN VÆRE NEDENSTÅENDE SKAL VÆRE I DRAWING.JAVA ISTEDET?
 * int          - Skur bredde
 * int          - Skur længde
 * int          - Spærantal
 * int          - Bredde mellem spær
 * int          - lægteantal
 * int          - Bredde imellem lægter
 * int          - Antal stolper på carporten
 */

public class Svg {

    private double width;
    private double height;
    private String viewbox;
    private int x;
    private int y;

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    private int text;

    private StringBuilder svg = new StringBuilder();

    private final String headerTemplate = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"%d\" y=\"%d\" height=\"%f\" width=\"%f\" viewBox=\"%s\" preserveAspectRatio=\"xMinYMin\"> <defs>\n" +
            "<marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\">\n" +
            "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
            "</marker>\n" +
            "<marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\">\n" +
            "<path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
            "</marker>\n" +
            "</defs>";
    private final String rectTemplate = "<rect transform=\"translate(100,100)\" x=\"%d\" y=\"%d\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String lineTemplate = "<line transform=\"translate(100,100)\" x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000;\n" +
            "marker-start: url(#beginArrow);\n"+"marker-end: url(#endArrow);\" />";
    private final String dotLineTemplate = "<line transform=\"translate(100,100)\" x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />";
    private final String lowerTextTemplate = "<text transform=\"translate(100,100)\" style=\"text-anchor: middle\" x=\"%d\" y=\"%d\"> %d cm</text>";
    private final String upperTextTemplate = "<text style=\"text-anchor: middle\" transform=\"translate(%d,%d) rotate(-90)\">600 cm</text>\n";

    public Svg(int x, int y, double width, double height, String viewbox) {
        this.width = width;
        this.height = height;
        this.viewbox = viewbox;
        this.x = x;
        this.y = y;
        svg.append(String.format(headerTemplate, x, y, height, width, viewbox));
    }

    public Svg(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.x1 = y1;
        this.x1 = x2;
        this.x1 = y2;
    }

    public Svg(int x, int y, int text){
        this.x = x;
        this.y = y;
        this.text = text;
    }

    public void addRect(int x, int y, double height, double width){
        svg.append(String.format(rectTemplate, x, y, height, width));
    }

    public void addLine(int x1, int y1, int x2, int y2){
        svg.append(String.format(lineTemplate, x1, y1, x2, y2));
    }

    public void addDotLine(int x1, int y1, int x2, int y2){
        svg.append(String.format(dotLineTemplate, x1, y1, x2, y2));
    }

    public void addLowerText(int x, int y, int text){
        svg.append(String.format(lowerTextTemplate, x, y, text));
    }

    public void addUpperText(int x, int y, int text){
        svg.append(String.format(upperTextTemplate, x, y, text));
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
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
        String res = svg.toString().replace(",",".");
        res = res.replace("translate(100.100)","translate(100,100)");
        res = res.replace("M0.0 L12.6 L0.12 L0.0","M0,0 L12,6 L0,12 L0,0");
        res = res.replace("M0.6 L12.0 L12.12 L0.6","M0,6 L12,0 L12,12 L0,6");
        //String res = svg.toString().replace("[^(100),(100)]+," , ".");
        return res + "</svg>" ;
        //return svg.toString() + "</svg>" ;
    }

}
