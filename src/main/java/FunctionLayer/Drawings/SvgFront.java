package FunctionLayer.Drawings;

import FunctionLayer.Calculation.CarportCalculation;
import FunctionLayer.Exceptions.LoginSampleException;
<<<<<<< Updated upstream:src/main/java/FunctionLayer/Drawings/SvgFront.java

/**
 * Contains Constructor and method for generating drawing based of the CarPortCalculation class.
 *
 * @author group
 */
=======
>>>>>>> Stashed changes:src/main/java/FunctionLayer/SvgFront.java

public class SvgFront {

    //##########################################################
    //constructors
    //##########################################################

    CarportCalculation c;

    /**
     *
     * @param orderID
     * @throws LoginSampleException
     */
    public SvgFront(int orderID) throws LoginSampleException {

        c = new CarportCalculation(orderID); //Henter dummy forespørgsel fra database igennem carportcalc

        this.carportWidth = c.getCarportWidth();
        this.carportLength = c.getCarportLength();
        this.noOfRafts = c.getNoOfRafts();
        this.raftDistance = c.getAvgRaftDistance();
        this.raftLength = c.getCarportWidth();
        this.shedLength = c.getShedWidth();
        this.shedWidth = c.getShedLength();
        this.noOfLaths = c.getNoOfLaths();
        this.lathWidth = c.getCarportLength();
        this.lathSpan = c.getLathSpan();
        this.noOfBeams = c.getNoOfBeams();
        this.roofHeight = c.getCalcRoofHeight();
        this.roofAngle = c.getCustomerRoofAngle();
        this.roofRaftLath = c.getRaftLength();


        //Viewbox

        //If else for handling viewbox size
        if(carportWidth<600) {
            svgFront.append(String.format(headerTemplate1));
        }else {
            svgFront.append(String.format(headerTemplate2));
        }

    }

    //##########################################################
    //Variables for SvgFront.java
    //##########################################################
    private double carportWidth;
    private double carportLength;
    private double carportX = 0;
    private double carportY = 0;

    private double roofHeight;
    private double actualRoofHeight = 90;
    private double roofAngle;
    private double roofRaftLath;

    private double beamlength = 210;
    private double carportHeight = roofHeight+beamlength+5;

    private double noOfRafts;
    private double raftDistance;
    private double raftLength;
    private double raftWidth = 4.5;

    private double shedLength;
    private double shedWidth;

    private double noOfLaths;
    private double lathLength = 4.5;
    private double lathWidth;
    private double lathSpan;

    private double noOfBeams;
    private double beamHight = 10;
    private double beamWidth = 10;
    private double beamX = 35;
    private double beamY = 35;

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
    private double x;
    private double y;

    private StringBuilder svgFront = new StringBuilder();

    //##########################################################
    //Templates for generation svg drawing using StringBuilder.
    //##########################################################
    private final String headerTemplate1        = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"x=\"0\" y=\"0\" height=\"400\" width=\"550\" viewBox=\"0,0,600,600\" preserveAspectRatio=\"xMinYMin\"> <defs>\n" +
                                                    "<marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\">\n" +
                                                    "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
                                                    "</marker>\n" +
                                                    "<marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\">\n" +
                                                    "<path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
                                                    "</marker>\n" +
                                                    "</defs>";
    private final String headerTemplate2        = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"x=\"0\" y=\"0\" height=\"400\" width=\"550\" viewBox=\"0,0,700,700\" preserveAspectRatio=\"xMinYMin\"> <defs>\n" +
                                                    "<marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\">\n" +
                                                    "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
                                                    "</marker>\n" +
                                                    "<marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\">\n" +
                                                    "<path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
                                                    "</marker>\n" +
                                                    "</defs>";
    private final String rectTemplate           = "<rect transform=\"translate(100,200)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #000000\" />";
    private final String lathTemplate           = "<rect transform=\"translate(100,200)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #000000\" />";
    private final String beamTemplate           = "<rect transform=\"translate(100,200)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String lineTemplate           = "<line transform=\"translate(100,200)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000;\n" +
                                                    "marker-start: url(#beginArrow);\n"+"marker-end: url(#endArrow);\" />";
    private final String lineNoArrowTemplate    = "<line transform=\"translate(100,200)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000;\" />";
    private final String raftTemplate           = "<line transform=\"translate(100,200)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke-width:10; stroke:#000000; stroke-linecap:round\"/>";
    private final String lowerTextTemplate      = "<text transform=\"translate(100,200)\" style=\"text-anchor: middle\" x=\"%f\" y=\"%f\"> %d cm</text>";
    private final String lowerAngelTextTemplate = "<text transform=\"translate(100,200)\" style=\"text-anchor: middle\" x=\"%f\" y=\"%f\"> %d °</text>";

    //##########################################################
    //Method for StringBuilder
    //##########################################################

    /**
     * Draws CarportFront with templates and stringbuilder
     */
    //##########################################################
    //Method for StringBuilder
    //##########################################################
    public void addCarportFront() {

        //Beam
        svgFront.append(String.format(beamTemplate, carportX+30, carportY, carportHeight, beamWidth));
        svgFront.append(String.format(beamTemplate, carportWidth-30, carportY, carportHeight, beamWidth));

        if(roofAngle>0) {
            //Rafts
            svgFront.append(String.format(raftTemplate, carportX, carportX + 5, (raftLength / 2) + 5, 0 - actualRoofHeight));
            svgFront.append(String.format(raftTemplate, (raftLength / 2) + 5, 0 - actualRoofHeight, carportWidth + 10, carportY + 5));
            //Lath
            svgFront.append(String.format(rectTemplate, carportX, carportY, beamWidth, raftLength+10));
            }else{
                //Lath
                svgFront.append(String.format(lathTemplate, carportX, carportY, beamWidth, raftLength+10));
            }

        //Leftside arrow
        svgFront.append(String.format(lineTemplate, arrowLineX1-30, arrowLineY1=0, arrowLineX2=-30, arrowLineY2=(carportHeight)));
        svgFront.append(String.format(lowerTextTemplate, x=-33, y=(carportHeight /2), text= 210));

        //Leftside raft arrow
        svgFront.append(String.format(lineTemplate, arrowLineX1=0, arrowLineY1-30, raftLength/2, (-actualRoofHeight)-30));
        svgFront.append(String.format(lowerTextTemplate, raftLength/4, textY-90, text= (int) raftLength/2));

        //Roof arrows
        if(roofAngle>0) {
            svgFront.append(String.format(lineTemplate, (carportWidth / 2) + 5, (arrowLineY1 = 0) - actualRoofHeight, (carportWidth / 2) + 5, arrowLineY2 = 0.0));
            if(roofAngle==15){
                svgFront.append(String.format(lowerAngelTextTemplate, x = 120, y = -6, text = (int) roofAngle));
                svgFront.append(String.format(lowerAngelTextTemplate, carportWidth - 110, y = -6, text = (int) roofAngle));
                }else {
                    svgFront.append(String.format(lowerAngelTextTemplate, x = carportWidth/5, y = -6, text = (int) roofAngle));
                    svgFront.append(String.format(lowerAngelTextTemplate, carportWidth - (carportWidth/5), y = -6, text = (int) roofAngle));
                }
            svgFront.append(String.format(lowerTextTemplate, (carportWidth / 2) + 2, y = -((actualRoofHeight / 5) + 2), text = (int) roofHeight-210));
            svgFront.append(String.format(lowerAngelTextTemplate, (carportWidth / 2) - 5, y = -(((actualRoofHeight / 4) * 3)-10), text = (int) (180-(roofAngle+roofAngle))));

            }

        //Bottom Line
        svgFront.append(String.format(lineNoArrowTemplate, arrowLineX1=-30, arrowLineY1=carportHeight, arrowLineX2=carportWidth+30,arrowLineY2=carportHeight));
        svgFront.append(String.format(lineTemplate, arrowLineX1=0, arrowLineY1=carportHeight+30, arrowLineX2=carportWidth+10,arrowLineY2=carportHeight+30));
        svgFront.append(String.format(lowerTextTemplate, carportWidth/2, y=carportHeight+25 ,text= (int)carportWidth));
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
        String res = svgFront.toString().replace(",",".");
        res = res.replace("translate(100.200)","translate(100,200)");
        res = res.replace("M0.0 L12.6 L0.12 L0.0","M0,0 L12,6 L0,12 L0,0");
        res = res.replace("M0.6 L12.0 L12.12 L0.6","M0,6 L12,0 L12,12 L0,6");
        res = res.replace("0.0.600.600","0,0,600,600");
        res = res.replace("0.0.700.700","0,0,700,700");
        return res + "</svg>" ;
    }
}