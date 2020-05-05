/*package FunctionLayer;

public class SvgSideways {
    //##########################################################
    //The class needs following information from database/carportCalculation.
    //##########################################################

    CarportCalculation c = new CarportCalculation();

    private double carportHeight = c.getCarportWidth();
    private double carportWidth = c.getCarportLength();
    private double carportX = 0;
    private double carportY = 0;


    private double noOfRafts = c.getNoOfRafts();
    private double raftDistance = c.getAvgRaftDistance();
    private double raftLength = c.getCarportWidth();
    private double raftWidth = 4.5; //Statisk lige nu
    private double raftX = 0;
    private double raftY = 0;

    private double roofHeigt;
    private double roofLength;
    private double roofX;
    private double roofY;

    private double roofRidgeHeight;
    private double roofRidgeLength;
    private double RidgeX;
    private double RidgeY;

    private double roofBargeHeigt;
    private double roofBargeLength;
    private double roofBargeX;
    private double roofBargeY;

    private double fasciaBoardHeight;
    private double fasciaBoardLength;
    private double fasciaBoardX;
    private double fasciaBoardY;

    private double roofBottomHeight;
    private double roofBottomLength;
    private double roofBottomLengthX;
    private double roofBottomLengthY;

    private double shedLength = c.getShedLength();
    private double shedWidth = c.getShedWidth();
    private double shedX = 465; //Statisk lige nu
    private double shedY = 15; //Statisk lige nu

    private double noOfLaths = c.getNoOfLaths();
    private double lathLength = 4.5; //Statisk lige nu
    private double lathWidth = c.getCarportLength();
    private double lathSpan = c.getLathSpan();
    private double lathX = 0;
    private double lathY = 0; //Statisk lige nu

    private double noOfBeams = c.getNoOfBeams(); //Is this including shed beams?
    private double beamDistance; //Need calculation
    private double beamHight = 10;
    private double beamWidth = 10;
    private double beamX = 35;
    private double beamY;

    private double numberOfRoofTiles;
    private double roofTilesX1;
    private double roofTilesX2;
    private double roofTilesY1;
    private double roofTilesY2;

    private double arrowLineX1;
    private double arrowLineX2;
    private double arrowLineY1;
    private double arrowLineY2;

    //##########################################################
    //Variables for Svg.java
    //##########################################################
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

    //##########################################################
    //Templates for generation svg drawing using StringBuilder.
    //##########################################################
    private final String headerTemplate = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" viewBox=\"%s\" preserveAspectRatio=\"xMinYMin\"> <defs>\n" +
            "<marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\">\n" +
            "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
            "</marker>\n" +
            "<marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\">\n" +
            "<path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
            "</marker>\n" +
            "</defs>";
    private final String rectTemplate = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String lineTemplate = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000;\n" +
            "marker-start: url(#beginArrow);\n"+"marker-end: url(#endArrow);\" />";
    private final String lineNoArrowTemplate = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String dotLineTemplate = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />";
    private final String lowerTextTemplate = "<text transform=\"translate(100,100)\" style=\"text-anchor: middle\" x=\"%f\" y=\"%f\"> %d cm</text>";
    private final String upperTextTemplate = "<text  transform=\"translate(%f,%f) rotate(-90)\" style=\"text-anchor: middle\"> %d </text>\n";
    private final String roofTileTemplate1 = "<path d=\"M 350 350 Q 362.5 360 375 350\" style=\"stroke:#000000; fill: #ffffff\"/>";
    private final String roofTileTemplate2 = "<path d=\"M 350 370 Q 362.5 380 375 370\" style=\"stroke:#000000; fill: #ffffff\"/>";

    //##########################################################
    //constructors
    //##########################################################
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

    //##########################################################
    //Methods for StringBuilder
    //##########################################################
    public void addRect(double x, double y, double height, double width){
        svgSideways.append(String.format(rectTemplate, x, y, height, width));
    }

    public void addLine(double x1, double y1, double x2, double y2){
        svgSideways.append(String.format(lineTemplate, x1, y1, x2, y2));
    }
    public void addLineNoArrow(double x1, double y1, double x2, double y2){
        svgSideways.append(String.format(lineNoArrowTemplate, x1, y1, x2, y2));
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

    //##########################################################
    //Getters/Setters/toString()
    //##########################################################
    public void addRoofTile1(){
        svgSideways.append(String.format(roofTileTemplate1));
    }

    public void addRoofTile2(){
        svgSideways.append(String.format(roofTileTemplate2));
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

        String res = svgSideways.toString().replace(",",".");
        res = res.replace("translate(100.100)","translate(100,100)");
        res = res.replace("M0.0 L12.6 L0.12 L0.0","M0,0 L12,6 L0,12 L0,0");
        res = res.replace("M0.6 L12.0 L12.12 L0.6","M0,6 L12,0 L12,12 L0,6");
        //String res = svgSideways.toString().replace("[^(100),(100)]+,",".");
        return res + "</svg>" ;
        //return svgSideways.toString() + "</svg>" ;
    }
}
*/