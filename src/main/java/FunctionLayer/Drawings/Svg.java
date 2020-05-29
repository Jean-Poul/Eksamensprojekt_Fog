package FunctionLayer.Drawings;

/**
 * Contains Constructor and method for generating topview svg drawing based off the CarportCalculation class.
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */

import FunctionLayer.Calculation.CarportCalculation;
import FunctionLayer.Exceptions.LoginSampleException;

public class Svg {

    //##########################################################
    //constructors
    //##########################################################

    CarportCalculation c;

    /**
     * Constructor for svg drawing
     * @param orderID User data (In order to reference what order ID the drawing belongs to)
     * @throws LoginSampleException LoginSampleException
     */
    public Svg(int orderID) throws LoginSampleException {

        c = new CarportCalculation(orderID);

        this.carportWidth = c.getCarportWidth();
        this.carportLength = c.getCarportLength();
        this.noOfRafts = c.getNoOfRafts();
        this.raftDistance = c.getAvgRaftDistance();
        this.raftDistance2 = c.getAvgRaftDistance();
        this.raftLength = c.getCarportWidth();
        this.shedLength = c.getShedWidth();
        this.shedWidth = c.getShedLength();
        this.noOfLaths = c.getNoOfLaths();
        this.lathWidth = c.getCarportLength();
        this.lathSpan = c.getLathSpan();
        this.noOfBeams = c.getNoOfBeams();
        this.roofBargeWidth = c.getCarportLength();

        //If else for handling viewbox size
        if(carportWidth<400 && carportLength<400) {
            svg.append(String.format(headerTemplate1));
        }else if(carportWidth<500 && carportLength>500) {
            svg.append(String.format(headerTemplate2));
        }else if(carportWidth<600 && carportLength<600) {
            svg.append(String.format(headerTemplate3));
        }else if(carportWidth<700 && carportLength<700) {
            svg.append(String.format(headerTemplate4));
        }else if(carportWidth<800 && carportLength<800) {
            svg.append(String.format(headerTemplate5));
        }
    }

    //##########################################################
    //The class needs following information from database/carportCalculation.
    //##########################################################

    private double carportWidth;
    private double carportLength;
    private double carportX = 0;
    private double carportY = 0;

    private double noOfRafts;
    private double raftDistance;
    private double raftDistance2;
    private double raftLength;
    private double raftWidth = 4.5;
    private double raftX = 0;
    private double raftY = 0;

    private double shedLength;
    private double shedWidth;
    private double shedX = 0;
    private double shedY = 0;

    private double noOfLaths;
    private double lathLength = 4.5;
    private double lathWidth;
    private double lathSpan;
    private double lathX = 0;
    private double lathY = 0;

    private double noOfBeams;
    private double beamDistance; //Need calculation
    private double beamHight = 10;
    private double beamWidth = 10;
    private double beamX = 35;
    private double beamY = 35;

    private double roofBargeHeigt = 6.5;
    private double roofBargeWidth;
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
    private int text = 0;

    private double width;
    private double height;
    private String viewbox;
    private double x=0;
    private double y=0;

    private StringBuilder svg = new StringBuilder();

    //##########################################################
    //Templates for generation svg drawing using StringBuilder.
    //##########################################################
    private final String headerTemplate1    = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"x=\"0\" y=\"0\" height=\"400\" width=\"550\" viewBox=\"0,0,550,550\" preserveAspectRatio=\"xMinYMin\"> <defs>\n" +
                                            "<marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\">\n" +
                                            "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
                                            "</marker>\n" +
                                            "<marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\">\n" +
                                            "<path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
                                            "</marker>\n" +
                                            "</defs>";
    private final String headerTemplate2    = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"x=\"0\" y=\"0\" height=\"400\" width=\"550\" viewBox=\"0,0,650,650\" preserveAspectRatio=\"xMinYMin\"> <defs>\n" +
                                            "<marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\">\n" +
                                            "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
                                            "</marker>\n" +
                                            "<marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\">\n" +
                                            "<path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
                                            "</marker>\n" +
                                            "</defs>";
    private final String headerTemplate3    = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"x=\"0\" y=\"0\" height=\"400\" width=\"550\" viewBox=\"0,0,750,750\" preserveAspectRatio=\"xMinYMin\"> <defs>\n" +
                                            "<marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\">\n" +
                                            "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
                                            "</marker>\n" +
                                            "<marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\">\n" +
                                            "<path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
                                            "</marker>\n" +
                                            "</defs>";
    private final String headerTemplate4    = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"x=\"0\" y=\"0\" height=\"400\" width=\"550\" viewBox=\"0,0,850,850\" preserveAspectRatio=\"xMinYMin\"> <defs>\n" +
                                            "<marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\">\n" +
                                            "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
                                            "</marker>\n" +
                                            "<marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\">\n" +
                                            "<path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
                                            "</marker>\n" +
                                            "</defs>";
    private final String headerTemplate5    = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"x=\"0\" y=\"0\" height=\"400\" width=\"550\" viewBox=\"0,0,950,950\" preserveAspectRatio=\"xMinYMin\"> <defs>\n" +
                                            "<marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\">\n" +
                                            "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
                                            "</marker>\n" +
                                            "<marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\">\n" +
                                            "<path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
                                            "</marker>\n" +
                                            "</defs>";

    private final String rectTemplate       = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" " +
                                              "style=\"stroke:#000000; fill: #ffffff\" />";

    private final String rectRemTemplate    = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #bababa\" />";
    private final String rectShedTemplate   = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #cfcfcf\" />";
    private final String lineTemplate       = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000;\n" +
                                                "marker-start: url(#beginArrow);\n"+"marker-end: url(#endArrow);\" />";
    private final String dotLineTemplate    = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />";
    private final String lowerTextTemplate  = "<text transform=\"translate(100,100)\" style=\"text-anchor: middle\" x=\"%f\" y=\"%f\"> %d cm</text>";

    /**
     * Draws Carport top view.
     */
    //##########################################################
    //Method for StringBuilder
    //##########################################################
    public void addCarport(){

        //Carport
        svg.append(String.format(rectTemplate, carportX, carportY, carportWidth, carportLength));

        //Shed
        if(shedWidth>0){
            if(shedLength>carportWidth-(2*(lathSpan))) {
                svg.append(String.format(rectShedTemplate, ((carportLength - shedWidth) -30), (shedY + lathSpan)-4.5, shedLength-(lathSpan-9), shedWidth));
                svg.append(String.format(rectTemplate, (carportLength-shedWidth)-30, shedY+lathSpan, beamHight, beamWidth));
                svg.append(String.format(rectTemplate, carportLength -40, lathSpan, beamHight, beamWidth));
                svg.append(String.format(rectTemplate, (carportLength -shedWidth)-30, (shedLength-(lathSpan-24)), beamHight, beamWidth));
                svg.append(String.format(rectTemplate, carportLength -40, (shedLength-(lathSpan-24)), beamHight, beamWidth));

                //Arrows
                svg.append(String.format(lineTemplate, (carportLength +35), lathSpan, (carportLength +35), shedLength));
                svg.append(String.format(lineTemplate, (carportLength -shedWidth-30), arrowLineY1-30, (carportLength -30), arrowLineY2-30));
                svg.append(String.format(lowerTextTemplate, (carportLength -(shedWidth/2)-30), y-33, text= (int) shedWidth));
                svg.append(String.format(lowerTextTemplate, (carportLength +33), shedLength/2+30, text= (int) shedLength));

                }else{
                    svg.append(String.format(rectShedTemplate, ((carportLength - shedWidth) -30), shedY + lathSpan, shedLength, shedWidth));
                    svg.append(String.format(rectTemplate, (carportLength-shedWidth)-30, shedY+lathSpan, beamHight, beamWidth));
                    svg.append(String.format(rectTemplate, carportLength -40, shedY+lathSpan, beamHight, beamWidth));
                    svg.append(String.format(rectTemplate, (carportLength -shedWidth)-30, shedY=(shedLength+lathSpan)-10, beamHight, beamWidth));
                    svg.append(String.format(rectTemplate, carportLength -40, shedY=(shedLength+lathSpan)-10, beamHight, beamWidth));

                    //Arrows
                    svg.append(String.format(lineTemplate, (carportLength +35), lathSpan, (carportLength +35), shedLength+lathSpan));
                    svg.append(String.format(lineTemplate, (carportLength -shedWidth-30), arrowLineY1-30, (carportLength -30), arrowLineY2-30));
                    svg.append(String.format(lowerTextTemplate, (carportLength -(shedWidth/2)-30), y-33, text= (int) shedWidth));
                    svg.append(String.format(lowerTextTemplate, (carportLength +33), (shedLength/2+30), text= (int) shedLength));
                }
        }

        //Remme
        svg.append(String.format(rectRemTemplate, roofBargeX, roofBargeY=(carportWidth /noOfLaths)-1.25, roofBargeHeigt, roofBargeWidth));
        svg.append(String.format(rectRemTemplate, roofBargeX, roofBargeY=(carportWidth -(carportWidth /noOfLaths))-1.25, roofBargeHeigt, roofBargeWidth));

        //Laths
        svg.append(String.format(rectTemplate, lathX, lathY, lathLength, lathWidth));
        for (int i=0; i <noOfLaths; i++) {
            lathY=lathY+(carportWidth /noOfLaths);
            svg.append(String.format(rectTemplate, lathX, lathY, lathLength, lathWidth));
        }

        //Rafters
        svg.append(String.format(rectTemplate, raftX, raftY, raftLength+5, raftWidth));
        for (int i=0; i <noOfRafts; i++) {
            raftX=raftX+(carportLength/noOfRafts);
            svg.append(String.format(rectTemplate, raftX, raftY, raftLength+5, raftWidth));
        }

        //Beams
        svg.append(String.format(rectTemplate, (carportLength -raftDistance)-2.25, (carportWidth /noOfLaths)-2.25, beamHight, beamWidth));
        svg.append(String.format(rectTemplate, raftDistance-2.25, (lathSpan-3)-2.25, beamHight, beamWidth));
        svg.append(String.format(rectTemplate, (carportLength -raftDistance)-2.25, (carportWidth -(carportWidth /noOfLaths))-2.25, beamHight, beamWidth));
        svg.append(String.format(rectTemplate, raftDistance-2.25, (carportWidth -(carportWidth /noOfLaths))-2.25, beamHight, beamWidth));
        if(shedWidth>0){
            svg.append(String.format(rectTemplate, (carportLength /2)-2.25, (carportWidth /noOfLaths)-2.25, beamHight, beamWidth));
            svg.append(String.format(rectTemplate, (carportLength /2)-2.25, (carportWidth -(carportWidth /noOfLaths))-2.25, beamHight, beamWidth));
        }

        //Lower arrow
        svg.append(String.format(lineTemplate, arrowLineX1, carportWidth+30, carportLength, carportWidth+30));
        svg.append(String.format(lowerTextTemplate, (carportLength /2), (carportWidth+45), text= (int) carportLength));

        //Leftside arrow
        svg.append(String.format(lineTemplate, arrowLineX1-30, arrowLineY1, arrowLineX2-30, carportWidth));
        svg.append(String.format(lowerTextTemplate, x-33, (carportWidth /2), text= (int) carportWidth));

        //Raft measurements
        svg.append(String.format(lineTemplate, arrowLineX1=0, arrowLineY1-30, raftDistance, arrowLineY2-30));
        svg.append(String.format(lowerTextTemplate, raftDistance/2, y-38, text= (int) raftDistance));

        //Lath measurements
        svg.append(String.format(lineTemplate, carportLength+10, lathSpan, carportLength+10, arrowLineY2=0));
        svg.append(String.format(lowerTextTemplate, carportLength+40, lathSpan/2, text= (int) lathSpan));

        //Wind Cross
        svg.append(String.format(dotLineTemplate, windCrossX1=(raftDistance)+2.25, windCrossY1=(lathSpan), windCrossX2=(carportLength -raftDistance), windCrossY2=(carportWidth -lathSpan)));
        svg.append(String.format(dotLineTemplate, windCrossX1=(raftDistance)+2.25, windCrossY1=(carportWidth -lathSpan), windCrossX2=(carportLength -raftDistance), windCrossY2=(lathSpan)));

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
        res = res.replace("0.0.550.550","0,0,550,550");
        res = res.replace("0.0.650.650","0,0,650,650");
        res = res.replace("0.0.750.750","0,0,750,750");
        res = res.replace("0.0.850.850","0,0,850,850");
        res = res.replace("0.0.950.950","0,0,950,950");
        return res + "</svg>" ;
    }

}