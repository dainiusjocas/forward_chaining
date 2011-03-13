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
        String fileURI = args[0];//"src/InputData/input4.txt";
        try{
            DataCollector dc = new DataCollector(fileURI);
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
                System.out.println("Answer: " + dm.getCurrentPlan());
            } else {
                System.out.println("The goal is not reached.");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        System.out.println("\nEnd of the program!");
    }

}
