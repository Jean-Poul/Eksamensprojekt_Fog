package FunctionLayer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Indholder diverse metoder til beregning af carport størrelse, stykliste og priser
 *
 * @author Alle
 */

public class CarportCalculation {

    //Nedestående FINALS er formodninger om standardafstande v. lægter.
    private static final int BOTTOM_LATHSPAN = 35;
    private static final int BOTTOM_LATHS = 2;
    private static final double TOP_LATH = 3;
    private static final double AVG_LATH_SPAN = 30;

    DecimalFormat df = new DecimalFormat("#.##");

    //Input fra forespørgselssiden(.jsp)
    private double carportLength;
    private double carportWidth;
    private int customerRoofAngle;

    //Kalkuleret kip-vinkel, spær- længde, afstand, antal og dimension samt taghøjde.
    private int calcAngle;
    private double calcRaftLength;
    private double noOfRafts;
    private double raftDistance;
    private String raftDimension;
    private double calcRoofHeight;

    //Lægter (På tværs af spær)
    private int noOfLaths;
    private double lathSpan;

    //Returneres fra database - skal sletts når queries kører!
    private String raftDimAndDist = "45 x 120 1.2";

    /*
    ######################################################
    Hashmap "angleAndFactor" constructor og "populateValidAngles" skal rettes eller slettes hvis vinklerne hentes i DB
    ######################################################
     */

    //Indholder hældningsvinklen og den tilsvarende faktor der skal ganges med - hentet fra databasen.
    HashMap<Integer, Double> angleAndFactor = new HashMap<Integer, Double>();

    public CarportCalculation() {

        //Skal sikkert slettes når hashmappet hentes fra databasen!
        populateAngleAndFactor();
    /*
    ########################
    ###   TESTEKSEMPEL!  ###
    ########################
     */

        carportLength = 720;
        carportWidth = 360;
        customerRoofAngle = 30;

        System.out.println("Lad os lave et test eksempel:");
        System.out.println("Kunden vælger en carport på: 3,6 x 7,3 m med vinkel 30");
        System.out.println("Vi forventer 6 stk. spær á 45x120 på 184,98 cm, Taghøjde på 103,92 cm");

        calcRoofAngle(customerRoofAngle);
        calcRaftLength(carportWidth, customerRoofAngle, calcAngle);
        calcRoofHeight(customerRoofAngle, carportWidth);
        getRaftDimension(raftDimAndDist);
        getRaftDistance(raftDimAndDist);
        noOfRafts(carportLength, raftDistance);
        calcRoofLaths(calcRaftLength);

        System.out.println("Systemet udregner antal spær: " + noOfRafts + " stk");
        System.out.println("Systemet udregner spærdimension " + raftDimension + " mm");
        System.out.println("Systemet udregner spærlængde: " + df.format(calcRaftLength) + " cm");
        System.out.println("Systemet udregner spærafstand: " + raftDistance + " cm");
        System.out.println("Systemet udregner højde: " + df.format(calcRoofHeight) + " cm");
        System.out.println("Systemet udregner antal lægter " + noOfLaths);
        System.out.println("Systemet udregner lægteafstand: " + lathSpan);
        System.out.println("Systemet udregner lægtelængde: " + carportLength);
    }

    /**
     * Sætter hældningsfaktor iht. dokumentationen (Skal sikkert hentes fra DB).
     */
    private void populateAngleAndFactor() {
        angleAndFactor.put(15, 1.0);
        angleAndFactor.put(20, 0.97);
        angleAndFactor.put(25, 0.94);
        angleAndFactor.put(30, 0.89);
        angleAndFactor.put(35, 0.84);
        angleAndFactor.put(40, 0.79);
        angleAndFactor.put(45, 0.72);
    }

    /**
     * Beregner vinklen i tagets kip. Skal bruges til at bestemme tagets højde
     *
     * @param customerRoofAngle Kundens valgte hældning
     */
    private void calcRoofAngle(int customerRoofAngle) {
        int triangleAngleSum = 180;
        int calcAngle = triangleAngleSum - (customerRoofAngle * 2);
        this.calcAngle = calcAngle;
    }

    /**
     * Beregner spærrets længde
     *
     * @param carportWidth        Kundens valgte bredde
     * @param customerRoofAngle   Kundens valgte hældning
     * @param calculatedRoofAngle Den beregnede vinkel i tagets kip fra calcRoofAngle()
     */
    private void calcRaftLength(double carportWidth, int customerRoofAngle, int calculatedRoofAngle) {

        double custRoofAngleRadian = Math.toRadians(customerRoofAngle);
        double calcRoofAngleRadian = Math.toRadians(calculatedRoofAngle);
        double calcRaftLength = (carportWidth * Math.sin(custRoofAngleRadian)) / (Math.sin(calcRoofAngleRadian));
        calcRaftLength = calcRaftLength * angleAndFactor.get(customerRoofAngle);
        this.calcRaftLength = calcRaftLength;
    }

    private void getRaftDistance(String s) {

        String raftDistance = s.substring(s.length() - 3);
        double raftDist = Double.parseDouble(raftDistance) * 100;
        this.raftDistance = raftDist;
    }

    private void getRaftDimension(String s) {
        String raftDim = s.substring(0, 8);
        this.raftDimension = raftDim;
    }

    private void noOfRafts(double carportLength, double raftDistance) {
        double noOfRafts = Math.round(carportLength / raftDistance);
        this.noOfRafts = noOfRafts;
    }

    /**
     * Udregner tagkonstruktionens højde
     *
     * @param customerRoofAngle Kundens valgte hældning på taget
     * @param carportWidth      Kundens valgte bredde på carporten
     */
    private void calcRoofHeight(int customerRoofAngle, double carportWidth) {
        double custRoofAngleRadian = Math.toRadians(customerRoofAngle);
        double calcRoofHeight = (Math.tan(custRoofAngleRadian) * (carportWidth / 2));
        this.calcRoofHeight = calcRoofHeight;
    }

    private void calcRoofLaths(double calcRaftLength) {
        int bottomLathSpan = BOTTOM_LATHSPAN;
        int bottomLaths = BOTTOM_LATHS;
        double topLathSpan = TOP_LATH;
        double avgLathSpan = AVG_LATH_SPAN;
        double calcLaths = Math.round((calcRaftLength - bottomLathSpan - topLathSpan) / avgLathSpan);
        System.out.println(calcLaths);
        double calcLathSpan = (calcRaftLength - bottomLathSpan - topLathSpan) / calcLaths;
        int actualLaths = (int) ((calcLaths + bottomLaths) * 2);

        this.noOfLaths = actualLaths;
        this.lathSpan = calcLathSpan;
    }
}