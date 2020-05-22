package FunctionLayer.Drawings;

import FunctionLayer.Calculation.CarportCalculation;
import FunctionLayer.Exceptions.LoginSampleException;

public class SvgSideways {

    //##########################################################
    //constructor
    //##########################################################

    CarportCalculation c;
    public SvgSideways(int orderID) throws LoginSampleException {

        c = new CarportCalculation(orderID); //Henter dummy forespørgsel fra database igennem carportcalc


        this.carportLength = c.getCarportLength();
        this.noOfRafts = c.getNoOfRafts();
        this.raftDistance = c.getAvgRaftDistance();
        this.pitch = c.getCustomerRoofAngle();
        this.shedLength = c.getShedLength();
        this.noOfLaths = c.getNoOfLaths()/2;
        this.lathLength = c.getCarportLength();
        this.noOfBeams = c.getNoOfBeams();


        svgSideways.append(String.format(headerTemplate));


    }
    //Sætter carport længden
    private double carportLength;
    private double carportX = 0;
    private double carportY = 0;

    //Spær
    private double noOfRafts;
    private double raftDistance;
    private double raftHeight = 87.5;
    private double raftWidth = 4.5;
    private double raftX = 0;
    private double raftY = 0;

    //Taghøjde
    private double roofHeigt = 90.0;
    private int pitch;

    //Tagtop
    private double roofRidgeHeight = 7.5;
    private double RidgeX = 0.0;
    private double RidgeY = 0.0;

    //Sternbræt
    private double fasciaBoardHeight = 15.0;
    private double fasciaBoardX = 0.0;
    private double fasciaBoardY = 80.0;

    //Skur
    private double shedLength;
    private double shedY = roofHeigt+15;
    private double shedCladdingWidth = 10.0;
    private double shedCladdingHeight = 200;


    //Lægter
    private double noOfLaths;;
    private double lathWidth = 4.5;
    private double lathLength;
    private double lathX = 0;
    private double lathY = 0;

    //Stolper
    private double noOfBeams;
    private double beamlength = 210;
    private double beamWidth = 10;

    private double beamY = 95.0;

    //Tagsten
    private double roofTileHeight = 15.0;
    private double roofTileWidth = 25.0;
    //
    private double roofTileRows = roofHeigt/roofTileHeight;
    private double roofTilesX1 = 5.0;
    private double roofTilesY1 = 0.0;

    private double carportHeight = (roofHeigt+beamlength+5);

    //##########################################################
    //Variables for Svg.java
    //##########################################################
    private double width;
    private double height;
    private double x;
    private double y;
    private int text;
    private StringBuilder svgSideways = new StringBuilder();

    //##########################################################
    //Templates for generation svg drawing using StringBuilder.
    //##########################################################
    private final String headerTemplate         = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0\" y=\"0\" height=\"400\" width=\"900\" viewBox=\"0,0,600,600\" preserveAspectRatio=\"xMinYMin\"> <defs>\n" +
                                                "<marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\">\n" +
                                                "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" + "</marker>\n" + "<marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\">\n" +
                                                "<path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" + "</marker>\n" +"</defs>";
    private final String rectTemplate           = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #f58f00\" />";
    private final String rectTemplateRoof       = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #f58f00\" />";
    private final String rectTemplateShed       = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #f58f00\" />";
    private final String rectTemplateShed2      = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #e88700\" />";
    private final String rectTemplatelaths      = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #f5e400\" />";
    private final String rectTemplateTile       = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #742727\" />";
    private final String rectTemplateHiddenTile = "<rect transform=\"translate(100,100)\" x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#ffffff; fill: #f8f9fa\" />";
    private final String lineNoArrowTemplate    = "<line transform=\"translate(100,100)\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";





    //##########################################################
    //Methods for StringBuilder
    //##########################################################

    //Roof builder
    public void addRoof(){
        //tjekker om der er tag (hældning på over 0 grader
        if (pitch != 0){
            //rafters / lægter
            for (int i=0; i <noOfRafts; i++) {
                raftX=raftX+raftDistance;
                svgSideways.append(String.format(rectTemplateRoof, (raftX), (raftY+2.5), raftHeight, raftWidth));
            }

            //laths
            for (int i=0; i <noOfLaths; i++) {
                lathY=lathY+(roofHeigt/noOfLaths);
                svgSideways.append(String.format(rectTemplatelaths, (lathX), (lathY), lathWidth, lathLength));
            }
            //Roofridge
            svgSideways.append(String.format(rectTemplateRoof, RidgeX, (RidgeY+2.5), roofRidgeHeight, carportLength));
            //Windwagoo
            svgSideways.append(String.format(rectTemplateRoof, (carportX-5), carportY, (roofHeigt-5), 10.0));
            svgSideways.append(String.format(rectTemplateRoof, (carportLength-5), carportY, (roofHeigt-5), 10.0));
            //waterboard @ windwagoo
            svgSideways.append(String.format(rectTemplateRoof, (carportX-5), (carportY+70), 2.5, 10.0));
            svgSideways.append(String.format(rectTemplateRoof, (carportLength-5), (carportY+70), 2.5, 10.0));
        }
    }

    //carport builder
    public void addCarport(){
        double shedX =  (carportLength-30-shedLength);
        double shedX2 = (carportLength-30-shedLength+5);
        double noOfCladsSideways = shedLength/(shedCladdingWidth+5);

        //BEAMS - STOLPER
        svgSideways.append(String.format(rectTemplate, (raftDistance), (beamY), beamlength, beamWidth));
        svgSideways.append(String.format(rectTemplate, (carportLength-40), (beamY), beamlength, beamWidth));

        //checker om der er skur, og tegner derefter skuret stolper
        if (shedLength>0){
            svgSideways.append(String.format(rectTemplate, (carportLength-shedLength-30), (beamY), beamlength, beamWidth));
            svgSideways.append(String.format(rectTemplate, ((carportLength/2)-beamWidth/2), (beamY), beamlength, beamWidth));
        }

        // tegner rem
        svgSideways.append(String.format(rectTemplate, (carportX+30), (carportY+roofHeigt), 19.5, carportLength-60));
        if (shedLength>0) {
            svgSideways.append(String.format(rectTemplate, (carportLength - shedLength - 30+(beamWidth/2)), (carportY + roofHeigt), 19.5, (shedLength-(beamWidth/2))));
        }

        //Skur beklædning
        if (shedLength>0) {
            //tegner bagbræt!
            svgSideways.append(String.format(rectTemplateShed2, shedX2, shedY, shedCladdingHeight, shedCladdingWidth  ));
            for (int i=0; i <(noOfCladsSideways-1); i++) {
                shedX2=shedX2+(shedCladdingWidth+5);
                svgSideways.append(String.format(rectTemplateShed2, shedX2, shedY, shedCladdingHeight, shedCladdingWidth));
            }

            //tegner det forreste bræt
            svgSideways.append(String.format(rectTemplateShed, shedX, shedY, shedCladdingHeight, shedCladdingWidth  ));
            for (int i=0; i <(noOfCladsSideways-1); i++) {
                shedX=shedX+(shedCladdingWidth+5);
                svgSideways.append(String.format(rectTemplateShed, shedX, shedY, shedCladdingHeight, shedCladdingWidth));
            }
        }
        //fascia board  // Sternbræt
        svgSideways.append(String.format(rectTemplateRoof, fasciaBoardX, fasciaBoardY, fasciaBoardHeight, carportLength));
    }

    public void addLines(){

        //Horizontal line
        svgSideways.append(String.format(lineNoArrowTemplate, carportX-15,(carportHeight),carportLength+15,(carportHeight)));

    }

    public void addRooftiles() {

        double roofTileColumns = Math.ceil(carportLength/roofTileWidth);

        if (pitch != 0) {
            roofTilesY1 = 10;
            //looper rows igennem
            for (int iR = 0; iR < roofTileRows - 1; iR++) {

                //tegner første colonne
                svgSideways.append(String.format(rectTemplateTile, roofTilesX1, roofTilesY1, roofTileHeight, roofTileWidth));
                //tegner tagsten i x-aksens retning
                for (int iC = 0; iC < roofTileColumns - 1; iC++) {
                    roofTilesX1 = roofTilesX1 + roofTileWidth;
                    svgSideways.append(String.format(rectTemplateTile, roofTilesX1, roofTilesY1, roofTileHeight, roofTileWidth));
                }
                roofTilesX1 = 5;
                roofTilesY1 += roofTileHeight;
            }

            //Tegner vindskede og sternbræt igen for at forhindre overlap
            svgSideways.append(String.format(rectTemplateHiddenTile, (carportLength + 5.0), 0.0, roofHeigt, 25.0));
            //Windwagoo
            svgSideways.append(String.format(rectTemplateRoof, (carportX - 5), carportY, (roofHeigt - 5), 10.0));
            svgSideways.append(String.format(rectTemplateRoof, (carportLength - 5), carportY, (roofHeigt - 5), 10.0));
            //waterboard @ windwagoo
            svgSideways.append(String.format(rectTemplateRoof, (carportX - 5), (carportY + 70), 2.5, 10.0));
            svgSideways.append(String.format(rectTemplateRoof, (carportLength - 5), (carportY + 70), 2.5, 10.0));
            //fascia board  // Sternbræt
            svgSideways.append(String.format(rectTemplateRoof, fasciaBoardX, fasciaBoardY, fasciaBoardHeight, carportLength));
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

    public int getText() {
        return text;
    }

    @Override
    public String toString() {

        String res = svgSideways.toString().replace(",",".");
        res = res.replace("translate(100.100)","translate(100,100)");
        res = res.replace("M0.0 L12.6 L0.12 L0.0","M0,0 L12,6 L0,12 L0,0");
        res = res.replace("M0.6 L12.0 L12.12 L0.6","M0,6 L12,0 L12,12 L0,6");
        res = res.replace("0.0.600.600","0,0,600,600");

        return res + "</svg>" ;
    }
}
