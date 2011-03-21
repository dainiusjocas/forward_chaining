package forwardchaining;

import java.util.Scanner;

/**
 * This class is used for running the example application of Forward Chaining.
 * @author Dainius Jocas, VU MIF, PS#3, 3rd year
 */
public class Main {

    /**
     * main method of the program
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Program that demonstrates the way that forward " +
                "chaining works starts to work:\n");
        String fileURI = args[0];
        try{
            DataCollector dc;
            if (fileURI.contains(".xml")) {
                dc = new DataCollectorFromXML(fileURI);
                System.out.println("Data for the program has been taken from " +
                    fileURI + " file.\n");
            } else if (fileURI.length() > 0){
                dc = new DataCollectorFromPlainTextFiles(fileURI);
                System.out.println("Data for the program has been taken from " +
                    fileURI + " file.\n");
            } else {
                dc = new DataCollectorFromCommandLine();
            }
            System.out.println("If you want to do forward chaining press f; if"
                    + " you want to do backward chaining press b ");
            Scanner in = new Scanner(System.in);
            DerivationMachine dm;
            char fOrB = in.nextLine().charAt(0);
            if (('F' == fOrB) || ('f' == fOrB)) {
                dm = new ForwardChaining(dc);
            } else {
                dm = new BackwardChaining(dc);
            }
            dm.showData();
            if (dm.doChaining()) {
                System.out.println("GOAL IS REACHED!\n" + "Final list of facts is: "
                    + dm.getListOfFacts());
                System.out.println("Production system: " + dm.getCurrentProductionSystem());
            } else {
                System.out.println("The goal is not reached.");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        System.out.println("\nEnd of the program!");
    }

}
