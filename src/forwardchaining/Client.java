package forwardchaining;

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
            DataCollector dc = new DataCollectorFromCommandLine();            
            Facts facts = new Facts(dc.collectFacts());
            String goal = dc.collectGoal();
            ListOfImplications loi =
                    new ListOfImplications(dc.collectImplications());
            System.out.println("Data for the program has been taken from " +
                    fileURI + " file.\n");
            DerivationMachine dm = new DerivationMachine(loi, facts, goal);
            dm.showData();
            if (dm.doForwardChaining()) {
                System.out.println("GOAL IS REACHED!\n" + "Final list of facts is: "
                    + dm.getListOfFacts());
                System.out.println("Production System: " + dm.getCurrentProductionSystem());
            } else {
                System.out.println("The goal is not reached.");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        System.out.println("\nEnd of the program!");
    }
    
}
