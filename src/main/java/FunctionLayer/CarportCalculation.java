package FunctionLayer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Indholder diverse metoder til beregning af carport størrelse, stykliste og priser
 * @author Alle
 */

public class CarportCalculation {

    private double carportLength;
    private double carportWidth;
    private int customerRoofAngle;
    private int calcAngle;
    private double calcRaftLength;
    private double calcRoofHeight;

    DecimalFormat df = new DecimalFormat("#.##");

    /*
    Hashmap "angleAndFactor" constructor og "populateValidAngles" skal rettes eller slettes hvis vinklerne hentes i DB
     */

    //Indholder hældningsvinklen og den tilsvarende faktor der skal ganges med.
    HashMap<Integer, Double> angleAndFactor = new HashMap<Integer, Double>();

    public  CarportCalculation(){
    populateAngleAndFactor();

    /*
    ########################
    ###   TESTEKSEMPEL!  ###
    ########################
     */

    carportLength = 7.3;
    carportWidth = 3.6;
    customerRoofAngle = 30;

    System.out.println("Lad os lave et test eksempel:");
    System.out.println("Kunden vælger en carport på: 3,6 x 7,3 m med vinkel 30");
    System.out.println("Vi forventer 9 stk. spær på 1,85 m, Taghøjde på 1.04 m, ");

    calcRoofAngle(customerRoofAngle);
    calcRaftLength(carportWidth,customerRoofAngle,calcAngle);
    calcRoofHeight(customerRoofAngle,carportWidth);

    System.out.println("Systemet udregner spærlængde: " + df.format(calcRaftLength));
    System.out.println("Systemet udregner antal spær: (TBD)");
    System.out.println("Systemet udregner højde: " + df.format(calcRoofHeight));
    }

    /**
     * Sætter hældningsfaktor iht. dokumentationen (Skal sikkert hentes fra DB).
     */
    private void populateAngleAndFactor(){
        angleAndFactor.put(15,1.0);
        angleAndFactor.put(20,0.97);
        angleAndFactor.put(25,0.94);
        angleAndFactor.put(30,0.89);
        angleAndFactor.put(35,0.84);
        angleAndFactor.put(40,0.79);
        angleAndFactor.put(45,0.72);
    }

    /**
     * Beregner vinklen i tagets kip. Skal bruges til at bestemme tagets højde
     *
     * @param customerRoofAngle Kundens valgte hældning
     */
    private void calcRoofAngle(int customerRoofAngle){
        int triangleAngleSum = 180;
        int calcAngle = triangleAngleSum - (customerRoofAngle * 2);
        this.calcAngle = calcAngle;
    }

    /**
     * Beregner spærrets længde
     *
     * @param carportWidth Kundens valgte bredde
     * @param customerRoofAngle Kundens valgte hældning
     * @param calculatedRoofAngle Den beregnede vinkel i tagets kip fra calcRoofAngle()
     */
    private void calcRaftLength(double carportWidth, int customerRoofAngle, int calculatedRoofAngle){

        double custRoofAngleRadian = Math.toRadians(customerRoofAngle);
        double calcRoofAngleRadian = Math.toRadians(calculatedRoofAngle);

        double calcRaftLength = (carportWidth * Math.sin(custRoofAngleRadian)) / (Math.sin(calcRoofAngleRadian));

        calcRaftLength = calcRaftLength * angleAndFactor.get(customerRoofAngle);

        this.calcRaftLength = calcRaftLength;
    }

//      TBD. Skal vælge en spærafstand.
//    private int calcNoOfRafts(){
//
//    }

    /**
     * Udregner tagkonstruktionens højde
     *
     * @param customerRoofAngle Kundens valgte hældning på taget
     * @param carportWidth Kundens valgte bredde på carporten
     */
    private void calcRoofHeight(int customerRoofAngle, double carportWidth){
        double custRoofAngleRadian = Math.toRadians(customerRoofAngle);
        double calcRoofHeight = (Math.tan(custRoofAngleRadian) * (carportWidth/2));
        this.calcRoofHeight = calcRoofHeight;
    }

//      TBD. Skal tage højde for let eller tungt tag.
//    private double calcNoOfBeams(){
//
//    }


}
