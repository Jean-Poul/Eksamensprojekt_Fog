package FunctionLayer;

import java.sql.SQLException;

public class SvgSidewaysBlueprint {
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
    private int pitch = c.getCustomerRoofAngle();
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
    //private double lathSpan = c.getLathSpan();   //overflødig pt
    private double lathX = 0;
    private double lathY = 0; //Statisk lige nu

    //Stolper
    private double noOfBeams = c.getNoOfBeams(); //Is this including shed beams?
    private double beamlength = 210;

    private double beamWidth = 10;
    private double beamX = 0.0;
    private double beamY = 95.0;

    //Tagsten
    private double roofTileHeight = 15.0;
    private double roofTileWidth = 25.0;
    private double roofTileColumns = Math.ceil(carportLength/roofTileWidth);
    private double roofTileRows = roofHeigt/roofTileHeight;
    private double roofTilesX1 = 5.0;
    private double roofTilesY1 = 0.0;
    private double roofTilesPx = roofTilesX1+(roofTileWidth/2);
    private double roofTilesPy = roofTilesY1+10.0;




    private double carportHeight = (roofHeigt+beamlength+5);

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
    private double x3;
    private double y3;
    private int text;
    private StringBuilder svgSidewaysBlueprint = new StringBuilder();

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
    private final String rectTemplate = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String rectTemplateRoof = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String rectTemplateShed = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String rectTemplateShed2 = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String rectTemplatelaths = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String rectTemplateTile = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String rectTemplateHiddenTile = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#ffffff; fill: #ffffff\" />";
    private final String lineTemplate = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000;\n" +
            "marker-start: url(#beginArrow);\n"+"marker-end: url(#endArrow);\" />";
    private final String lineNoArrowTemplate = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String dotLineTemplate = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />";
    private final String lowerTextTemplate = "<text transform=\"translate(100,100)\" style=\"text-anchor: middle\" x=\"%f\" y=\"%f\"> %d cm</text>";
    private final String upperTextTemplate = "<text style=\"text-anchor: middle\" transform=\"translate(%f,%f) rotate(-90)\"> %d cm</text>\n";
    private final String roofTileTemplate1 = "<path d=\"M 350 350 Q 362.5 360 375 350\" style=\"stroke:#000000; fill: #ffffff\"/>";
    private final String roofTileTemplate2 = "<path d=\"M 350 370 Q 362.5 380 375 370\" style=\"stroke:#000000; fill: #ffffff\"/>";
    private final String roofTileTemplate = "<path d=\"M 350 370 Q 362.5 380 375 370\" style=\"stroke:#000000; fill: #742727\"/>";

    //##########################################################
    //constructors
    //##########################################################
    public SvgSidewaysBlueprint() {
        svgSidewaysBlueprint.append(String.format(headerTemplate));
    }

    /*public SvgSidewaysBlueprint(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.x1 = y1;
        this.x1 = x2;
        this.x1 = y2;
    }

    public SvgSidewaysBlueprint(double x, double y, int text){
        this.x = x;
        this.y = y;
        this.text = text;
    }

*/


    //##########################################################
    //Methods for StringBuilder
    //##########################################################

    //Roof builder

    public void addRoof(){

        if (pitch != 0){


        //rafters
        for (int i=0; i <noOfRafts; i++) {
            raftX=raftX+raftDistance;
            svgSidewaysBlueprint.append(String.format(rectTemplateRoof, (raftX-2.5), (raftY+2.5), raftHeight, raftWidth));
        }
        //Roofridge
        svgSidewaysBlueprint.append(String.format(rectTemplateRoof, RidgeX, (RidgeY+2.5), roofRidgeHeight, roofRidgeLength));

        //laths
        for (int i=0; i <noOfLaths; i++) {
            lathY=lathY+(roofHeigt/noOfLaths);
            svgSidewaysBlueprint.append(String.format(rectTemplatelaths, (lathX), (lathY), lathWidth, lathLength));
        }

        //Windwagoo
        svgSidewaysBlueprint.append(String.format(rectTemplateRoof, (carportX-5), carportY, (roofHeigt-5), 10.0));
        svgSidewaysBlueprint.append(String.format(rectTemplateRoof, (carportLength-5), carportY, (roofHeigt-5), 10.0));
        //waterboard @ windwagoo
        svgSidewaysBlueprint.append(String.format(rectTemplateRoof, (carportX-5), (carportY+70), 2.5, 10.0));
        svgSidewaysBlueprint.append(String.format(rectTemplateRoof, (carportLength-5), (carportY+70), 2.5, 10.0));
        }


    }


    //carport builder
    public void addCarport(){


        System.out.println(noOfBeams);
        System.out.println(noOfRafts);

        //BEAMS - STOLPER
        svgSidewaysBlueprint.append(String.format(rectTemplate, (beamX+80), (beamY), beamlength, beamWidth));
        svgSidewaysBlueprint.append(String.format(rectTemplate, (carportLength-40), (beamY), beamlength, beamWidth));

        if (shedLength>0){
            svgSidewaysBlueprint.append(String.format(rectTemplate, (carportLength-shedLength-30), (beamY), beamlength, beamWidth));
            svgSidewaysBlueprint.append(String.format(rectTemplate, ((carportLength/2)-beamWidth/2), (beamY), beamlength, beamWidth));
        }

        // rem?? skal ændres til rem højde!
        svgSidewaysBlueprint.append(String.format(rectTemplate, (carportX+30), (carportY+roofHeigt), 19.5, carportLength-60));
        if (shedLength>0) {
            svgSidewaysBlueprint.append(String.format(rectTemplate, (carportLength - shedLength - 30+(beamWidth/2)), (carportY + roofHeigt), 19.5, (shedLength-(beamWidth/2))));
        }

        //Skur beklædning
        if (shedLength>0) {
            //bagbræt!
            svgSidewaysBlueprint.append(String.format(rectTemplateShed2, shedX2, shedY, shedCladdingHeight, shedCladdingWidth  ));
            for (int i=0; i <(noOfCladsSideways-1); i++) {
                shedX2=shedX2+(shedCladdingWidth+5);
                svgSidewaysBlueprint.append(String.format(rectTemplateShed2, shedX2, shedY, shedCladdingHeight, shedCladdingWidth));
            }
            svgSidewaysBlueprint.append(String.format(rectTemplateShed, shedX, shedY, shedCladdingHeight, shedCladdingWidth  ));
            for (int i=0; i <(noOfCladsSideways-1); i++) {
                shedX=shedX+(shedCladdingWidth+5);
                svgSidewaysBlueprint.append(String.format(rectTemplateShed, shedX, shedY, shedCladdingHeight, shedCladdingWidth));
            }



        }
        //fascia board  // Sternbræt
        svgSidewaysBlueprint.append(String.format(rectTemplateRoof, fasciaBoardX, fasciaBoardY, fasciaBoardHeight, fasciaBoardLength));
    }

    public void addLines(){

        //Horizontal line
        svgSidewaysBlueprint.append(String.format(lineNoArrowTemplate, carportX-15,(carportHeight),carportLength+15,(carportHeight)));

        //Diagonal line left
        svgSidewaysBlueprint.append(String.format(lineNoArrowTemplate, carportX,(roofHeigt+15),carportX,(carportHeight+15)));

        //Diagonal line right
        svgSidewaysBlueprint.append(String.format(lineNoArrowTemplate, carportLength,(roofHeigt+15),carportLength,(carportHeight+15)));



        //Arrows & measurements
        //Height
        if (pitch != 0){
            svgSidewaysBlueprint.append(String.format(lineTemplate, (carportX-70),0.0,(carportX-70),carportHeight));
            svgSidewaysBlueprint.append(String.format(lowerTextTemplate, x=(carportX-73), y=(carportHeight/2), text= (int) carportHeight));
        }


        svgSidewaysBlueprint.append(String.format(lineTemplate,(carportX-35), (roofHeigt+5), (carportX-35),carportHeight));
        svgSidewaysBlueprint.append(String.format(lowerTextTemplate, x=(carportX-38), y=(roofHeigt+(beamlength/2)), text= (int) beamlength));


        //Width
        //Leftside  -> left beam
        svgSidewaysBlueprint.append(String.format(lineTemplate,(carportX), carportHeight+30, (carportX+80),carportHeight+30));
        svgSidewaysBlueprint.append(String.format(lowerTextTemplate, x=carportX+40, y=(carportHeight+50), text= 80));
        //Shed right beam ----> right side
        svgSidewaysBlueprint.append(String.format(lineTemplate,(carportLength-30.0), (carportHeight+30), carportLength,(carportHeight+30)));
        svgSidewaysBlueprint.append(String.format(lowerTextTemplate, x=(carportLength-15), y=(carportHeight+50), text= 30));

        //Left to right
        svgSidewaysBlueprint.append(String.format(lineTemplate,(carportX), carportHeight+130, carportLength,carportHeight+130));
        svgSidewaysBlueprint.append(String.format(lowerTextTemplate, x=(carportLength/2), y=(carportHeight+150), text= (int) carportLength));

        if (shedLength>0) {
            //shed left beam to right beam <--->
            svgSidewaysBlueprint.append(String.format(lineTemplate,(carportLength-shedLength-30), (carportHeight+30), (carportLength-30),(carportHeight+30)));
            svgSidewaysBlueprint.append(String.format(lowerTextTemplate, x=carportLength-(shedLength/2)-30, y=(carportHeight+50), text= (int) shedLength));

            //left shed beam to Middle Beam
            svgSidewaysBlueprint.append(String.format(lineTemplate,(carportLength-shedLength-30), (carportHeight+90), ((carportLength/2)-beamWidth/2),(carportHeight+90)));
            //Carport left shed beam to middle beam text
                if ((carportLength-shedLength-30) > ((carportLength/2)-beamWidth/2)){
                    svgSidewaysBlueprint.append(String.format(lowerTextTemplate,
                            x=(((carportLength/2)-beamWidth/2) + (((carportLength-shedLength-30) - ((carportLength/2)-beamWidth/2)))/2),
                            y=(carportHeight+110),
                            text= (int) ((carportLength-shedLength-30) - ((carportLength/2)-beamWidth/2))));
                }
                if ((carportLength-shedLength-30) < ((carportLength/2)-beamWidth/2)){
                    svgSidewaysBlueprint.append(String.format(lowerTextTemplate,
                            x=(((carportLength-shedLength-30) + ((((carportLength/2)-beamWidth/2) -(carportLength-shedLength-30) )/2))),
                            y=(carportHeight+110),
                            text= (int) (((carportLength/2)-beamWidth/2) -(carportLength-shedLength-30) )));

                }

            //left beam to middle beam
            svgSidewaysBlueprint.append(String.format(lineTemplate,(carportX+80), (carportHeight+60), ((carportLength/2)-beamWidth/2),(carportHeight+60)));
            svgSidewaysBlueprint.append(String.format(lowerTextTemplate,
                    x=(carportX+80) + Math.abs(((((carportLength/2)-beamWidth/2)-(carportX+80))/2)),
                    y=(carportHeight+80),
                    text= (int) Math.abs((((carportLength/2)-beamWidth/2)-(carportX+80)))));
        }




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

        String res = svgSidewaysBlueprint.toString().replace(",",".");
        res = res.replace("translate(100.100)","translate(100,100)");
        res = res.replace("M0.0 L12.6 L0.12 L0.0","M0,0 L12,6 L0,12 L0,0");
        res = res.replace("M0.6 L12.0 L12.12 L0.6","M0,6 L12,0 L12,12 L0,6");
        res = res.replace("0.0.600.600","0,0,600,600");
        //String res = svgSidewaysBlueprint.toString().replace("[^(100),(100)]+,",".");
        return res + "</svg>" ;
        //return svgSidewaysBlueprint.toString() + "</svg>" ;
    }
}
