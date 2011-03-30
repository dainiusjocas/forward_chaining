package samples;

import chaining.BackwardChaining;
import chaining.Chaining;
import chaining.DataCollector;
import chaining.DataCollectorFromXML;
import java.util.ArrayList;

/**
 * Sample application of Forward chaining
 * @author Dainius Jocas, VU MIF, PS#3, 3rd year
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("Program that demonstrates how forward " +
                "chaining works started to work:\n");
        String fileURI = "src/xml/4_9_implications.xml";
        try{
            DataCollector dc = new DataCollectorFromXML(fileURI);
            System.out.println("Data for the program has been taken from " +
                    fileURI + " file.\n");
            Chaining dm = new BackwardChaining(dc);
            if (dm.doChaining(dc.collectGoal())) {
                System.out.println("\nThe goal is reached!\n" + dm.getProductionSystem());
            } else {
                System.out.println("\nThe goal is not reached.");
            }
        } catch (Exception e) {
            System.out.println("Internal error");
        }
        System.out.println("\nEnd of the program!");
    }
    
}
