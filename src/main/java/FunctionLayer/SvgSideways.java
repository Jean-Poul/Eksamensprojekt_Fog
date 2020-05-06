package FunctionLayer;

public class SvgSideways {
    //##########################################################
    //The class needs following information from database/carportCalculation.
    //##########################################################

    CarportCalculation c = new CarportCalculation();

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

    //Roof builder

    public void addRoof(){


        //rafters
        for (int i=0; i <noOfRafts; i++) {
            raftX=raftX+raftDistance;
            svgSideways.append(String.format(rectTemplate, (raftX-2.5), (raftY+2.5), raftHeight, raftWidth));
        }
        //Roofridge
        svgSideways.append(String.format(rectTemplate, RidgeX, (RidgeY+2.5), roofRidgeHeight, roofRidgeLength));

        //laths
        for (int i=0; i <noOfLaths; i++) {
            lathY=lathY+(roofHeigt/noOfLaths);
            svgSideways.append(String.format(rectTemplate, (lathX), (lathY), lathWidth, lathLength));
        }

        //Windwagoo
        svgSideways.append(String.format(rectTemplate, (carportX-5), carportY, (roofHeigt-5), 10.0));
        svgSideways.append(String.format(rectTemplate, (carportLength-5), carportY, (roofHeigt-5), 10.0));
        //waterboard @ windwagoo
        svgSideways.append(String.format(rectTemplate, (carportX-5), (carportY+70), 2.5, 10.0));
        svgSideways.append(String.format(rectTemplate, (carportLength-5), (carportY+70), 2.5, 10.0));

        //fascia board  // Sternbræt
        svgSideways.append(String.format(rectTemplate, fasciaBoardX, fasciaBoardY, fasciaBoardHeight, fasciaBoardLength));

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
            svgSideways.append(String.format(rectTemplate, shedX, shedY, shedCladdingHeight, shedCladdingWidth  ));
            for (int i=0; i <(noOfCladsSideways-1); i++) {
                shedX=shedX+(shedCladdingWidth+5);
                svgSideways.append(String.format(rectTemplate, shedX, shedY, shedCladdingHeight, shedCladdingWidth));
        }}
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
        svgSideways.append(String.format(lineTemplate, (carportX-50),0.0,(carportX-50),carportHeight));
        //svgSideways.addUpperText(90,250, 305);
        svgSideways.append(String.format(lineTemplate,(carportX-25), (roofHeigt+5), (carportX-25),carportHeight));
        //svgSideways.addUpperText(120,290, 210);

        //Width
        //Leftside  -> left beam
        svgSideways.append(String.format(lineTemplate,(carportX), carportHeight+30, (carportX+80),carportHeight+30));

        //Shed right beam ----> right side
        svgSideways.append(String.format(lineTemplate,(carportLength-30.0), (carportHeight+30), carportLength,(carportHeight+30)));

        if (shedLength>0) {
            //shed left beam to right beam <--->
            svgSideways.append(String.format(lineTemplate,(carportLength-shedLength-30), (carportHeight+30), (carportLength-30),(carportHeight+30)));
            //left shed beam to Middle Beam
            svgSideways.append(String.format(lineTemplate,(carportLength-shedLength-30), (carportHeight+30), ((carportLength/2)-beamWidth/2),(carportHeight+30)));
            //left beam to middle beam
            svgSideways.append(String.format(lineTemplate,(carportX+80), (carportHeight+30), ((carportLength/2)-beamWidth/2),(carportHeight+30)));
        } else{
            //Left to right <----> incase of no shed
            svgSideways.append(String.format(lineTemplate,(carportX), carportHeight+30, carportLength-30,carportHeight+30));

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
        //String res = svgSideways.toString().replace("[^(100),(100)]+,",".");
        return res + "</svg>" ;
        //return svgSideways.toString() + "</svg>" ;
    }
}
