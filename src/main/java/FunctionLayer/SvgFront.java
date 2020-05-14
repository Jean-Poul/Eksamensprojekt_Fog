package FunctionLayer;

public class SvgFront {
    //##########################################################
    //The class needs following information from database/carportCalculation.
    //##########################################################
    CarportCalculation c;
    {
        try {
            c = new CarportCalculation(1); //Henter dummy forespørgsel fra database igennem carportcalc
        } catch (LoginSampleException e) {
            e.printStackTrace();
        }
    }

    //##########################################################
    //Variables for SvgFront.java
    //##########################################################
    private double carportWidth = c.getCarportWidth();
    private double carportLength = c.getCarportLength();
    private double carportX = 0;
    private double carportY = 0;

    private double beamlength = 210;
    private double roofHeigt = 90.0;
    private double carportHeight = roofHeigt+beamlength+5;

    private double noOfRafts = c.getNoOfRafts();
    private double raftDistance = c.getAvgRaftDistance();
    private double raftLength = c.getCarportWidth();
    private double raftWidth = 4.5;

    private double shedLength = c.getShedWidth();
    private double shedWidth = c.getShedLength();

    private double noOfLaths = c.getNoOfLaths();
    private double lathLength = 4.5;
    private double lathWidth = c.getCarportLength();
    private double lathSpan = c.getLathSpan();

    private double noOfBeams = c.getNoOfBeams();
    private double beamHight = 10;
    private double beamWidth = 10;
    private double beamX = 35;
    private double beamY = 35;

    private double roofHeight = c.getCalcRoofHeight();
    private double roofAngle = c.getCustomerRoofAngle();
    private double roofRaftLath = c.getRaftLength();
    private double roofTopAngel = 180-(2*roofAngle);

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
    private final String headerTemplate = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"x=\"0\" y=\"0\" height=\"400\" width=\"550\" viewBox=\"0,0,600,600\" preserveAspectRatio=\"xMinYMin\"> <defs>\n" +
            "<marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\">\n" +
            "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
            "</marker>\n" +
            "<marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\">\n" +
            "<path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
            "</marker>\n" +
            "</defs>";
    private final String rectTemplate = "<rect transform=\"translate(100,200)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #000000\" />";
    private final String beamTemplate = "<rect transform=\"translate(100,200)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String lineTemplate = "<line transform=\"translate(100,200)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000;\n" +
            "marker-start: url(#beginArrow);\n"+"marker-end: url(#endArrow);\" />";
    private final String lineNoArrowTemplate = "<line transform=\"translate(100,200)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000;\" />";
    private final String raftTemplate = "<line transform=\"translate(100,200)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke-width:10; stroke:#000000; stroke-linecap:round\"/>";
    private final String lowerTextTemplate = "<text transform=\"translate(100,200)\" style=\"text-anchor: middle\" x=\"%f\" y=\"%f\"> %d cm</text>";
    private final String lowerAngelTextTemplate = "<text transform=\"translate(100,200)\" style=\"text-anchor: middle\" x=\"%f\" y=\"%f\"> %d °</text>";

    //##########################################################
    //constructors
    //##########################################################
    public SvgFront() {
        svgFront.append(String.format(headerTemplate));
    }

    //##########################################################
    //Method for StringBuilder
    //##########################################################
    public void addCarportFront() {

        //Beam
        svgFront.append(String.format(beamTemplate, carportX, carportY, carportHeight, beamWidth));
        svgFront.append(String.format(beamTemplate, carportWidth, carportY, carportHeight, beamWidth));

        //Raft
        svgFront.append(String.format(raftTemplate, carportX, carportX+5, (raftLength/2)+5, 0-roofHeight));
        svgFront.append(String.format(raftTemplate, (raftLength/2)+5, 0-roofHeight, carportWidth+10, carportY+5));

        //Lath
        svgFront.append(String.format(rectTemplate, carportX, carportY, beamWidth, carportWidth+10));

        //Leftside arrow
        svgFront.append(String.format(lineTemplate, arrowLineX1-30, arrowLineY1=0, arrowLineX2=-30, arrowLineY2=(carportHeight)));
        svgFront.append(String.format(lowerTextTemplate, x=-33, y=(carportHeight /2), text= 210));

        //Leftside raft arrow
        svgFront.append(String.format(lineTemplate, arrowLineX1=0, arrowLineY1-30, raftLength/2, (-roofHeight)-30));
        svgFront.append(String.format(lowerTextTemplate, raftLength/4, -roofHeight, text= (int) raftLength/2));

        //Roof arrow
        svgFront.append(String.format(lineTemplate, (carportWidth /2)+5, (arrowLineY1=0)-roofHeight, (carportWidth /2)+5, arrowLineY2=0.0));
        svgFront.append(String.format(lowerAngelTextTemplate, x=90, y=-6 ,text= (int)roofAngle));
        svgFront.append(String.format(lowerAngelTextTemplate, carportWidth-80, y=-6 ,text= (int)roofAngle));
        svgFront.append(String.format(lowerTextTemplate, (carportWidth/2)+2, y=-((roofHeight/2)+2) ,text= (int)roofHeight));
        svgFront.append(String.format(lowerAngelTextTemplate, (carportWidth/2)-5, y=-(((roofHeight/4)*3)-5) ,text= (int) roofTopAngel));
        //Bottom Line
        svgFront.append(String.format(lineNoArrowTemplate, arrowLineX1=-30, arrowLineY1=carportHeight, arrowLineX2=carportWidth+30,arrowLineY2=carportHeight));
        svgFront.append(String.format(lineTemplate, arrowLineX1=0, arrowLineY1=carportHeight+30, arrowLineX2=carportWidth+10,arrowLineY2=carportHeight+30));
        svgFront.append(String.format(lowerTextTemplate, carportWidth/2, y=carportHeight+25 ,text= (int)carportWidth));
    }

    //##########################################################
    //Getters/Setters/toString()
    //##########################################################
    @Override
    public String toString() {
        String res = svgFront.toString().replace(",",".");
        res = res.replace("translate(100.200)","translate(100,200)");
        res = res.replace("M0.0 L12.6 L0.12 L0.0","M0,0 L12,6 L0,12 L0,0");
        res = res.replace("M0.6 L12.0 L12.12 L0.6","M0,6 L12,0 L12,12 L0,6");
        res = res.replace("0.0.600.600","0,0,600,600");
        return res + "</svg>" ;
    }
}