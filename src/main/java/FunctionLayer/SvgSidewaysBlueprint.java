package FunctionLayer;

public class SvgSidewaysBlueprint {
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
    //Sætter carport længden
    private double carportLength = c.getCarportLength();
    private double carportX = 0;
    private double carportY = 0;

    //Spær
    private double noOfRafts = c.getNoOfRafts();
    private double raftDistance = c.getAvgRaftDistance();
    private double raftHeight = 87.5;
    private double raftWidth = 4.5;
    private double raftX = 0;
    private double raftY = 0;

    //Taghøjde
    private double roofHeigt = 90.0;
    private int pitch = c.getCustomerRoofAngle();

    //Tagtop
    private double roofRidgeHeight = 7.5;
    private double roofRidgeLength = carportLength;
    private double RidgeX = 0.0;
    private double RidgeY = 0.0 ;

    //Sternbræt
    private double fasciaBoardHeight = 15.0;
    private double fasciaBoardLength = carportLength;
    private double fasciaBoardX = 0.0;
    private double fasciaBoardY = 80.0;

    //Skur
    private double shedLength = c.getShedLength();
    private double shedX = carportLength-30-shedLength;
    private double shedX2 = carportLength-30-shedLength+5;
    private double shedY = roofHeigt+15;
    private double shedCladdingWidth = 10.0;
    private double shedCladdingHeight = 200;
    private double noOfCladsSideways = shedLength/(shedCladdingWidth+5);

    //Lægter
    private double noOfLaths = (c.getNoOfLaths()/2);
    private double lathWidth = 4.5;
    private double lathLength = c.getCarportLength();
    private double lathX = 0;
    private double lathY = 0;

    //Stolper
    private double beamlength = 210;
    private double beamWidth = 10;
    private double beamX = 0.0;
    private double beamY = 95.0;


    private double carportHeight = (roofHeigt+beamlength+5);

    //##########################################################
    //Variables for Svg.java
    //##########################################################
    private double width;
    private double height;
    private double x;
    private double y;
    private int text;
    private StringBuilder svgSidewaysBlueprint = new StringBuilder();

    //##########################################################
    //Templates for generation svg drawing using StringBuilder.
    //##########################################################
    private final String headerTemplate         = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0\" y=\"0\" height=\"400\" width=\"550\" viewBox=\"0,0,600,600\" preserveAspectRatio=\"xMinYMin\"> <defs>\n" +
            "<marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\">\n" +
            "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" + "</marker>\n" + "<marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\">\n" +
            "<path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" + "</marker>\n" + "</defs>";
    private final String rectTemplate           = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String rectTemplateRoof       = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String rectTemplateShed       = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String rectTemplateShed2      = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String rectTemplatelaths      = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String lineTemplate           = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000;\n" + "marker-start: url(#beginArrow);\n"+"marker-end: url(#endArrow);\" />";
    private final String lineNoArrowTemplate    = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String lowerTextTemplate      = "<text transform=\"translate(100,100)\" style=\"text-anchor: middle\" x=\"%f\" y=\"%f\"> %d cm</text>";


    //##########################################################
    //constructors
    //##########################################################
    public SvgSidewaysBlueprint() {
        svgSidewaysBlueprint.append(String.format(headerTemplate));
    }


    //##########################################################
    //Methods for StringBuilder
    //##########################################################

    //Roof builder
    public void addRoof(){
        //tjekker om der er tag (hældning på over 0 grader)
        if (pitch != 0){
            //rafters
            for (int i=0; i <noOfRafts; i++) {
                raftX=raftX+raftDistance;
                svgSidewaysBlueprint.append(String.format(rectTemplateRoof, (raftX-2.5), (raftY+2.5), raftHeight, raftWidth));
            }

            //laths  / lægter
            for (int i=0; i <noOfLaths; i++) {
                lathY=lathY+(roofHeigt/noOfLaths);
                svgSidewaysBlueprint.append(String.format(rectTemplatelaths, (lathX), (lathY), lathWidth, lathLength));
            }

            //Roofridge
            svgSidewaysBlueprint.append(String.format(rectTemplateRoof, RidgeX, (RidgeY+2.5), roofRidgeHeight, roofRidgeLength));
            //Windwagoo / vindskede
            svgSidewaysBlueprint.append(String.format(rectTemplateRoof, (carportX-5), carportY, (roofHeigt-5), 10.0));
            svgSidewaysBlueprint.append(String.format(rectTemplateRoof, (carportLength-5), carportY, (roofHeigt-5), 10.0));
            //waterboard @ windwagoo / vindskede
            svgSidewaysBlueprint.append(String.format(rectTemplateRoof, (carportX-5), (carportY+70), 2.5, 10.0));
            svgSidewaysBlueprint.append(String.format(rectTemplateRoof, (carportLength-5), (carportY+70), 2.5, 10.0));
        }
    }

    //carport builder
    public void addCarport(){

        //BEAMS - STOLPER
        svgSidewaysBlueprint.append(String.format(rectTemplate, (beamX+80), (beamY), beamlength, beamWidth));
        svgSidewaysBlueprint.append(String.format(rectTemplate, (carportLength-40), (beamY), beamlength, beamWidth));

        //Tegner højre stolpe
        if (shedLength>0){
            svgSidewaysBlueprint.append(String.format(rectTemplate, (carportLength-shedLength-30), (beamY), beamlength, beamWidth));
            svgSidewaysBlueprint.append(String.format(rectTemplate, ((carportLength/2)-beamWidth/2), (beamY), beamlength, beamWidth));
        }

        // tegner rem
        svgSidewaysBlueprint.append(String.format(rectTemplate, (carportX+30), (carportY+roofHeigt), 19.5, carportLength-60));
        if (shedLength>0) {
            svgSidewaysBlueprint.append(String.format(rectTemplate, (carportLength - shedLength - 30+(beamWidth/2)), (carportY + roofHeigt), 19.5, (shedLength-(beamWidth/2))));
        }

        //Skur beklædning
        if (shedLength>0) {
            //tegner bagbræt!
            svgSidewaysBlueprint.append(String.format(rectTemplateShed2, shedX2, shedY, shedCladdingHeight, shedCladdingWidth  ));
            for (int i=0; i <(noOfCladsSideways-1); i++) {
                shedX2=shedX2+(shedCladdingWidth+5);
                svgSidewaysBlueprint.append(String.format(rectTemplateShed2, shedX2, shedY, shedCladdingHeight, shedCladdingWidth));
            }

            //tegner det forreste bræt
            svgSidewaysBlueprint.append(String.format(rectTemplateShed, shedX, shedY, shedCladdingHeight, shedCladdingWidth  ));
            for (int i=0; i <(noOfCladsSideways-1); i++) {
                shedX=shedX+(shedCladdingWidth+5);
                svgSidewaysBlueprint.append(String.format(rectTemplateShed, shedX, shedY, shedCladdingHeight, shedCladdingWidth));
            }
        }
    }

    //Line & measurements builder
    public void addLines(){

        //Horizontal line
        svgSidewaysBlueprint.append(String.format(lineNoArrowTemplate, carportX-15,(carportHeight),carportLength+15,(carportHeight)));

        //Diagonal line left
        svgSidewaysBlueprint.append(String.format(lineNoArrowTemplate, carportX,(roofHeigt+15),carportX,(carportHeight+15)));

        //Diagonal line right
        svgSidewaysBlueprint.append(String.format(lineNoArrowTemplate, carportLength,(roofHeigt+15),carportLength,(carportHeight+15)));

        //Arrows & measurements
        //Height
            //Tegner pil og tekst til toppen af taget hvis der er tag (hældning !=0)
            if (pitch != 0){
                svgSidewaysBlueprint.append(String.format(lineTemplate, (carportX-70),0.0,(carportX-70),carportHeight));
                svgSidewaysBlueprint.append(String.format(lowerTextTemplate, x=(carportX-73), y=(carportHeight/2), text= (int) carportHeight));
            }
            //Tegner pil og tekst op til bunden af rem
            svgSidewaysBlueprint.append(String.format(lineTemplate,(carportX-35), (roofHeigt+5), (carportX-35),carportHeight));
            svgSidewaysBlueprint.append(String.format(lowerTextTemplate, x=(carportX-38), y=(roofHeigt+(beamlength/2)), text= (int) beamlength));

        //Width
            //Left to right
            svgSidewaysBlueprint.append(String.format(lineTemplate,(carportX), carportHeight+30, carportLength,carportHeight+30));
            svgSidewaysBlueprint.append(String.format(lowerTextTemplate, x=(carportLength/2), y=(carportHeight+50), text= (int) carportLength));
            //Leftside  -> left beam
            svgSidewaysBlueprint.append(String.format(lineTemplate,(carportX), carportHeight+60, (carportX+80),carportHeight+60));
            svgSidewaysBlueprint.append(String.format(lowerTextTemplate, x=carportX+40, y=(carportHeight+80), text= 80));

            //Shed right beam ----> right side
            svgSidewaysBlueprint.append(String.format(lineTemplate,(carportLength-30.0), (carportHeight+60), carportLength,(carportHeight+60)));
            svgSidewaysBlueprint.append(String.format(lowerTextTemplate, x=(carportLength-15), y=(carportHeight+80), text= 30));

            //tjekker om der er skur eller ej
            if (shedLength>0) {
                //shed left beam to right beam <--->
                svgSidewaysBlueprint.append(String.format(lineTemplate,(carportLength-shedLength-30), (carportHeight+90), (carportLength-30),(carportHeight+90)));
                svgSidewaysBlueprint.append(String.format(lowerTextTemplate, x=carportLength-(shedLength/2)-30, y=(carportHeight+110), text= (int) shedLength));

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

            //fascia board  // Sternbræt
            svgSidewaysBlueprint.append(String.format(rectTemplate, fasciaBoardX, fasciaBoardY, fasciaBoardHeight, fasciaBoardLength));
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

    public int getText() {
        return text;
    }

    @Override
    public String toString() {

        String res = svgSidewaysBlueprint.toString().replace(",",".");
        res = res.replace("translate(100.100)","translate(100,100)");
        res = res.replace("M0.0 L12.6 L0.12 L0.0","M0,0 L12,6 L0,12 L0,0");
        res = res.replace("M0.6 L12.0 L12.12 L0.6","M0,6 L12,0 L12,12 L0,6");
        res = res.replace("0.0.600.600","0,0,600,600");

        return res + "</svg>" ;
    }
}
