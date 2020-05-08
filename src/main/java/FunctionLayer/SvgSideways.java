package FunctionLayer;

import java.sql.SQLException;

public class SvgSideways {
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

    //private double carportWidth = c.getCarportWidth();
    private double carportLength = c.getCarportLength();


    private double carportX = 0;
    private double carportY = 0;

    //Spær
    private double noOfRafts = c.getNoOfRafts();
    private double raftDistance = c.getAvgRaftDistance();
    private double raftHeight = 87.5;
    private double raftWidth = 4.5; //Statisk lige nu
    private double raftX = 0;
    private double raftY = 0;

    //Taghøjde
    private double roofHeigt = 90.0;
    private double roofLength;
    private double roofX;
    private double roofY;

    //Tagtop
    private double roofRidgeHeight = 10.0;
    private double roofRidgeLength = carportLength;
    private double RidgeX = 0.0;
    private double RidgeY = 0.0 ;


    //REM
    private double roofBargeHeigt;
    private double roofBargeLength;
    private double roofBargeX;
    private double roofBargeY;

    //Sternbræt
    private double fasciaBoardHeight = 15.0;
    private double fasciaBoardLength = carportLength;
    private double fasciaBoardX = 0.0;
    private double fasciaBoardY = 80.0;

    //Skur
    private double shedLength = c.getShedLength();
    private double shedWidth = c.getShedWidth();
    private double shedX = carportLength-30-shedLength; //Statisk lige nu
    private double shedX2 = carportLength-30-shedLength+5;
    private double shedY = roofHeigt+15; //Statisk lige nu
    private double shedCladdingWidth = 10.0;   // c.getcladdingwidth
    private double shedCladdingHeight = 200;
    private double noOfCladsSideways = shedLength/(shedCladdingWidth+5);



    //Lægter
    private double noOfLaths = (c.getNoOfLaths()/2);
    private double lathWidth = 4.5; //Statisk lige nu
    private double lathLength = c.getCarportLength();
    private double lathSpan = c.getLathSpan();
    private double lathX = 0;
    private double lathY = 0; //Statisk lige nu

    //Stolper
    private double noOfBeams = c.getNoOfBeams(); //Is this including shed beams?
    private double beamDistance = 200 ; //Need calculation
    private double beamlength = 210;

    private double beamWidth = 10;
    private double beamX = 0.0;
    private double beamY = 95.0;

    //Tagsten
    private double numberOfRoofTiles;
    private double roofTilesX1;
    private double roofTilesX2;
    private double roofTilesY1;
    private double roofTilesY2;

    //pile
    private double arrowLineX1;
    private double arrowLineX2;
    private double arrowLineY1;
    private double arrowLineY2;

    private double carportHeight = (roofHeigt+beamlength+5);
    private int carportHeightInt = (int) carportHeight;

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
    private final String headerTemplate = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0\" y=\"0\" height=\"400\" width=\"550\" viewBox=\"0,0,600,600\" preserveAspectRatio=\"xMinYMin\"> <defs>\n" +
            "<marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\">\n" +
            "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
            "</marker>\n" +
            "<marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\">\n" +
            "<path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
            "</marker>\n" +
            "</defs>";
    private final String rectTemplate = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #f58f00\" />";  //#f5c542
    private final String rectTemplateRoof = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #f58f00\" />"; //f27d0f
    private final String rectTemplateShed = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #f58f00\" />";
    private final String rectTemplateShed2 = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #e88700\" />";
    private final String rectTemplatelaths = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #f5e400\" />";

    private final String lineTemplate = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000;\n" +
            "marker-start: url(#beginArrow);\n"+"marker-end: url(#endArrow);\" />";
    private final String lineNoArrowTemplate = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String dotLineTemplate = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />";
    private final String lowerTextTemplate = "<text transform=\"translate(100,100)\" style=\"text-anchor: middle\" x=\"%f\" y=\"%f\"> %d cm</text>";
    private final String upperTextTemplate = "<text style=\"text-anchor: middle\" transform=\"translate(%f,%f) rotate(-90)\"> %d cm</text>\n";
    private final String roofTileTemplate1 = "<path d=\"M 350 350 Q 362.5 360 375 350\" style=\"stroke:#000000; fill: #ffffff\"/>";
    private final String roofTileTemplate2 = "<path d=\"M 350 370 Q 362.5 380 375 370\" style=\"stroke:#000000; fill: #ffffff\"/>";

    //##########################################################
    //constructors
    //##########################################################
    public SvgSideways() {
        svgSideways.append(String.format(headerTemplate));
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

    //Roof builder

    public void addRoof(){


        //rafters
        for (int i=0; i <noOfRafts; i++) {
            raftX=raftX+raftDistance;
            svgSideways.append(String.format(rectTemplateRoof, (raftX-2.5), (raftY+2.5), raftHeight, raftWidth));
        }
        //Roofridge
        svgSideways.append(String.format(rectTemplateRoof, RidgeX, (RidgeY+2.5), roofRidgeHeight, roofRidgeLength));

        //laths
        for (int i=0; i <noOfLaths; i++) {
            lathY=lathY+(roofHeigt/noOfLaths);
            svgSideways.append(String.format(rectTemplatelaths, (lathX), (lathY), lathWidth, lathLength));
        }

        //Windwagoo
        svgSideways.append(String.format(rectTemplateRoof, (carportX-5), carportY, (roofHeigt-5), 10.0));
        svgSideways.append(String.format(rectTemplateRoof, (carportLength-5), carportY, (roofHeigt-5), 10.0));
        //waterboard @ windwagoo
        svgSideways.append(String.format(rectTemplateRoof, (carportX-5), (carportY+70), 2.5, 10.0));
        svgSideways.append(String.format(rectTemplateRoof, (carportLength-5), (carportY+70), 2.5, 10.0));

        //fascia board  // Sternbræt
        svgSideways.append(String.format(rectTemplateRoof, fasciaBoardX, fasciaBoardY, fasciaBoardHeight, fasciaBoardLength));

    }


    //carport builder
    public void addCarport(){


        System.out.println(noOfBeams);
        System.out.println(noOfRafts);

        //BEAMS - STOLPER
        svgSideways.append(String.format(rectTemplate, (beamX+80), (beamY), beamlength, beamWidth));
        svgSideways.append(String.format(rectTemplate, (carportLength-40), (beamY), beamlength, beamWidth));

        if (shedLength>0){
            svgSideways.append(String.format(rectTemplate, (carportLength-shedLength-30), (beamY), beamlength, beamWidth));
            svgSideways.append(String.format(rectTemplate, ((carportLength/2)-beamWidth/2), (beamY), beamlength, beamWidth));
        }

        // rem?? skal ændres til rem højde!
        svgSideways.append(String.format(rectTemplate, (carportX+30), (carportY+roofHeigt), 19.5, carportLength-60));
        if (shedLength>0) {
            svgSideways.append(String.format(rectTemplate, (carportLength - shedLength - 30+(beamWidth/2)), (carportY + roofHeigt), 19.5, (shedLength-(beamWidth/2))));
        }

        //Skur beklædning
        if (shedLength>0) {
            //bagbræt!
            svgSideways.append(String.format(rectTemplateShed2, shedX2, shedY, shedCladdingHeight, shedCladdingWidth  ));
            for (int i=0; i <(noOfCladsSideways-1); i++) {
                shedX2=shedX2+(shedCladdingWidth+5);
                svgSideways.append(String.format(rectTemplateShed2, shedX2, shedY, shedCladdingHeight, shedCladdingWidth));
            }
            svgSideways.append(String.format(rectTemplateShed, shedX, shedY, shedCladdingHeight, shedCladdingWidth  ));
            for (int i=0; i <(noOfCladsSideways-1); i++) {
                shedX=shedX+(shedCladdingWidth+5);
                svgSideways.append(String.format(rectTemplateShed, shedX, shedY, shedCladdingHeight, shedCladdingWidth));
            }



        }
    }

    public void addLines(){

        //Horizontal line
        svgSideways.append(String.format(lineNoArrowTemplate, carportX-15,(carportHeight),carportLength+15,(carportHeight)));

        //Diagonal line left
        svgSideways.append(String.format(lineNoArrowTemplate, carportX,(roofHeigt+15),carportX,(carportHeight+15)));

        //Diagonal line right
        svgSideways.append(String.format(lineNoArrowTemplate, carportLength,(roofHeigt+15),carportLength,(carportHeight+15)));



        //Arrows & measurements
        //Height
        svgSideways.append(String.format(lineTemplate, (carportX-70),0.0,(carportX-70),carportHeight));
        svgSideways.append(String.format(lowerTextTemplate, x=(carportX-73), y=(carportHeight/2), text= (int) carportHeight));

        svgSideways.append(String.format(lineTemplate,(carportX-35), (roofHeigt+5), (carportX-35),carportHeight));
        svgSideways.append(String.format(lowerTextTemplate, x=(carportX-38), y=(roofHeigt+(beamlength/2)), text= (int) beamlength));


        //Width
        //Leftside  -> left beam
        svgSideways.append(String.format(lineTemplate,(carportX), carportHeight+40, (carportX+80),carportHeight+40));
        svgSideways.append(String.format(lowerTextTemplate, x=carportX+40, y=(carportHeight+60), text= 80));
        //Shed right beam ----> right side
        svgSideways.append(String.format(lineTemplate,(carportLength-30.0), (carportHeight+40), carportLength,(carportHeight+40)));
        svgSideways.append(String.format(lowerTextTemplate, x=(carportLength-15), y=(carportHeight+60), text= 30));

        //Left to right
        svgSideways.append(String.format(lineTemplate,(carportX), carportHeight+75, carportLength,carportHeight+75));
        svgSideways.append(String.format(lowerTextTemplate, x=(carportLength/2), y=(carportHeight+95), text= (int) carportLength));

        if (shedLength>0) {
            //shed left beam to right beam <--->
            svgSideways.append(String.format(lineTemplate,(carportLength-shedLength-30), (carportHeight+40), (carportLength-30),(carportHeight+40)));
            svgSideways.append(String.format(lowerTextTemplate, x=carportLength-(shedLength/2)-30, y=(carportHeight+60), text= (int) shedLength));
            //left shed beam to Middle Beam
            svgSideways.append(String.format(lineTemplate,(carportLength-shedLength-30), (carportHeight+40), ((carportLength/2)-beamWidth/2),(carportHeight+40)));

                if ((carportLength-shedLength-30) > ((carportLength/2)-beamWidth/2)){
                    svgSideways.append(String.format(lowerTextTemplate,
                            x=(((carportLength/2)-beamWidth/2) + (((carportLength-shedLength-30) - ((carportLength/2)-beamWidth/2)))/2),
                            y=(carportHeight+60),
                            text= (int) ((carportLength-shedLength-30) - ((carportLength/2)-beamWidth/2))));
                }
                if ((carportLength-shedLength-30) < ((carportLength/2)-beamWidth/2)){
                    svgSideways.append(String.format(lowerTextTemplate,
                            x=(((carportLength-shedLength-30) + ((((carportLength/2)-beamWidth/2) -(carportLength-shedLength-30) )/2))),
                            y=(carportHeight+60),
                            text= (int) (((carportLength/2)-beamWidth/2) -(carportLength-shedLength-30) )));

                }

            //left beam to middle beam
            svgSideways.append(String.format(lineTemplate,(carportX+80), (carportHeight+40), ((carportLength/2)-beamWidth/2),(carportHeight+40)));
            svgSideways.append(String.format(lowerTextTemplate,
                    x=(carportX+80) + Math.abs(((((carportLength/2)-beamWidth/2)-(carportX+80))/2)),
                    y=(carportHeight+60),
                    text= (int) Math.abs((((carportLength/2)-beamWidth/2)-(carportX+80)))));
        }




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
        res = res.replace("0.0.600.600","0,0,600,600");
        //String res = svgSideways.toString().replace("[^(100),(100)]+,",".");
        return res + "</svg>" ;
        //return svgSideways.toString() + "</svg>" ;
    }
}
