package FunctionLayer;

import java.sql.SQLException;

public class Svg {
    //##########################################################
    //The class needs following information from database/carportCalculation.
    //##########################################################

    CarportCalculation c;
    {
        try {
            c = new CarportCalculation(1); //Henter dummy forespørgsel fra database igennem carportcalc
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    private double shedLength = c.getShedLength();
    private double shedWidth = c.getShedWidth();
    private double shedX; //Statisk lige nu
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
    private double beamY = 35;

    private double roofBargeHeigt = 6.5;
    private double roofBargeWidth = carportWidth;
    private double roofBargeX = 0;
    private double roofBargeY = 0;

    private double windCrossX1;
    private double windCrossX2;
    private double windCrossY1;
    private double windCrossY2;

    private double arrowLineX1 = 0;
    private double arrowLineX2 = 0;
    private double arrowLineY1 = 0;
    private double arrowLineY2 = 0;

    private double textX = 0;
    private double textY = 0;
    private double text = 0;

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
    //private double text;
    private StringBuilder svg = new StringBuilder();

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
    private final String rectRemTemplate = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #bababa\" />";
    private final String rectShedTemplate = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #cfcfcf\" />";
    private final String lineTemplate = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000;\n" +
            "marker-start: url(#beginArrow);\n"+"marker-end: url(#endArrow);\" />";
    private final String dotLineTemplate = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />";
    private final String lowerTextTemplate = "<text transform=\"translate(100,100)\" style=\"text-anchor: middle\" x=\"%f\" y=\"%f\"> %f cm</text>";
    private final String upperTextTemplate = "<text style=\"text-anchor: middle\" transform=\"translate(%f,%f) rotate(-90)\"> %f cm</text>\n";

    //##########################################################
    //constructors
    //##########################################################
    public Svg(double x, double y, double width, double height, String viewbox) {
        this.width = width;
        this.height = height;
        this.viewbox = viewbox;
        this.x = x;
        this.y = y;
        svg.append(String.format(headerTemplate, x, y, height, width, viewbox));
    }

    public Svg(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.x1 = y1;
        this.x1 = x2;
        this.x1 = y2;
    }

    public Svg(double x, double y, double text){
        this.x = x;
        this.y = y;
        this.text = text;
    }

    //##########################################################
    //Methods for StringBuilder
    //##########################################################
    public void addCarport(){

        //Carport
        svg.append(String.format(rectTemplate, carportX, carportY, carportHeight, carportWidth));

        //Shed
        if(shedLength>0){
            svg.append(String.format(rectShedTemplate, (shedX=(carportWidth-shedWidth)-15), shedY, shedLength, shedWidth));
            svg.append(String.format(rectTemplate, shedX, shedY, beamHight, beamWidth));
            svg.append(String.format(rectTemplate, shedX=carportWidth-25, shedY, beamHight, beamWidth));
            svg.append(String.format(rectTemplate, shedX=(carportWidth-shedWidth)-15, shedY=(shedLength+5), beamHight, beamWidth));
            svg.append(String.format(rectTemplate, shedX=carportWidth-25, shedY=(shedLength+5), beamHight, beamWidth));
        }

        //Remme
        svg.append(String.format(rectRemTemplate, roofBargeX, roofBargeY=(carportHeight/noOfLaths)-1.25, roofBargeHeigt, roofBargeWidth));
        svg.append(String.format(rectRemTemplate, roofBargeX, roofBargeY=(carportHeight-(carportHeight/noOfLaths))-1.25, roofBargeHeigt, roofBargeWidth));

        //Laths
        svg.append(String.format(rectTemplate, lathX, lathY, lathLength, lathWidth));
        for (int i=0; i <noOfLaths; i++) {
            lathY=lathY+(carportHeight/noOfLaths);
            svg.append(String.format(rectTemplate, lathX, lathY, lathLength, lathWidth));
        }

        //Rafters
        svg.append(String.format(rectTemplate, raftX, raftY, raftLength+5, raftWidth));
        for (int i=0; i <noOfRafts; i++) {
            raftX=raftX+(carportWidth/noOfRafts);
            svg.append(String.format(rectTemplate, raftX, raftY, raftLength+5, raftWidth));
        }

        //Beams
        svg.append(String.format(rectTemplate, beamX=(carportWidth-raftDistance)-2.25, beamY=(carportHeight/noOfLaths)-2.25, beamHight, beamWidth));
        svg.append(String.format(rectTemplate, beamX=raftDistance-2.25, beamY=(lathSpan-3)-2.25, beamHight, beamWidth));
        svg.append(String.format(rectTemplate, beamX=(carportWidth-raftDistance)-2.25, beamY=(carportHeight-(carportHeight/noOfLaths))-2.25, beamHight, beamWidth));
        svg.append(String.format(rectTemplate, beamX=raftDistance-2.25, beamY=(carportHeight-(carportHeight/noOfLaths))-2.25, beamHight, beamWidth));
        if(shedWidth>0){
            svg.append(String.format(rectTemplate, beamX=(carportWidth/2)-2.25, beamY=(carportHeight/noOfLaths)-2.25, beamHight, beamWidth));
            svg.append(String.format(rectTemplate, beamX=(carportWidth/2)-2.25, beamY=(carportHeight-(carportHeight/noOfLaths))-2.25, beamHight, beamWidth));
        }

        //Arrows
        svg.append(String.format(lineTemplate, arrowLineX1, arrowLineY1=(carportHeight+30), arrowLineX2=(carportWidth), arrowLineY2=(carportHeight+30)));
        svg.append(String.format(lineTemplate, arrowLineX1-30, arrowLineY1=0, arrowLineX2=-30, arrowLineY2=(carportHeight)));
        svg.append(String.format(lineTemplate, arrowLineX1=(carportWidth+30), arrowLineY1=15, arrowLineX2=(carportWidth+30), arrowLineY2=(shedLength+15)));
        svg.append(String.format(lineTemplate, arrowLineX1=(carportWidth-shedWidth-15), arrowLineY1=-30, arrowLineX2=(carportWidth-15), arrowLineY2=-30));

        //Measurements Text
        svg.append(String.format(lowerTextTemplate, x=(carportWidth/2), y=(carportHeight+50), text=carportHeight));
        svg.append(String.format(lowerTextTemplate, x=-50, y=(carportHeight/2), text=carportWidth));
        svg.append(String.format(lowerTextTemplate, x=(carportWidth-(shedWidth/2)-15), y=-50, text=shedWidth));
        svg.append(String.format(lowerTextTemplate, x=(carportWidth+50), y=(shedLength/2+15), text=shedLength));

        //Wind Cross
        svg.append(String.format(dotLineTemplate, windCrossX1=(raftDistance)+2.25, windCrossY1=(lathSpan), windCrossX2=(carportWidth-raftDistance), windCrossY2=(carportHeight-lathSpan)));
        svg.append(String.format(dotLineTemplate, windCrossX1=(raftDistance)+2.25, windCrossY1=(carportHeight-lathSpan), windCrossX2=(carportWidth-raftDistance), windCrossY2=(lathSpan)));

    }

    //Skal færdiggøres. Udregning af beam afstand?
    public void addLine(double x1, double y1, double x2, double y2){
        svg.append(String.format(lineTemplate, x1, y1, x2, y2));
    }

    public void addDotLine(double x1, double y1, double x2, double y2){
        svg.append(String.format(dotLineTemplate, x1, y1, x2, y2));
    }

    public void addLowerText(double x, double y, int text){
        svg.append(String.format(lowerTextTemplate, x, y, text));
    }

    public void addUpperText(double x, double y, int text){
        svg.append(String.format(upperTextTemplate, x, y, text));
    }

    //##########################################################
    //Getters/Setters/toString()
    //##########################################################
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

    public void setY(int y) {
        this.y = y;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public double getText() {
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