package forwardchaining;

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
        String fileURI = "src/InputData/input1.txt";//args[0];
        try{
            DataCollector dc;
            if (fileURI.contains(".xml")) {
                dc = new DataCollectorFromXML(fileURI);
            } else {
                dc = new DataCollectorFromPlainTextFiles(fileURI);
            }
//            Facts facts = new Facts(dc.collectFacts());
//            String goal = dc.collectGoal();
//            ListOfImplications loi =
//                    new ListOfImplications(dc.collectImplications());
            System.out.println("Data for the program has been taken from " +
                    fileURI + " file.\n");
            DerivationMachine dm = new ForwardChaining(dc);
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
