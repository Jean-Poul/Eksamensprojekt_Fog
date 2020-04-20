package FunctionLayer;

/**
 * Indholder diverse metoder til beregning af carport størrelse, stykliste og priser
 * @author Alle
 */

public class CarportCalculation {

    private double carportLength;
    private double carportWidth;
    private int customerRoofAngle;
    private int calculatedRoofAngle;

    /**
     *
     * @param customerRoofAngle Kundens valgte hældning
     * @return vinklen i kippet som anvendes til at beregne tagkonstruktionens højde
     */
    private int calcRoofAngle(int customerRoofAngle){
        int triangleAngleSum = 180;
        int calcAngle = triangleAngleSum - (customerRoofAngle * 2);
        return calcAngle;
    }

    private double calcRaftLength(double carportWidth, int customerRoofAngle, int calculatedRoofAngle){
        double raftLength = (carportWidth * Math.sin(15) / (Math.sin(calculatedRoofAngle)));
        System.out.println(raftLength);
        return raftLength;
    }
}
