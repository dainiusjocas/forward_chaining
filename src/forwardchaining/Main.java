/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forwardchaining;

/**
 *
 * @author dj
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Program starts to work:\n");
        String fileURI = "src/InputData/input1.txt";
        try{
            DataCollector dc = new DataCollector(fileURI);
            Facts facts = new Facts(dc.collectFacts());
            String goal = dc.collectGoal();
            ListOfImplications loi = 
                    new ListOfImplications(dc.collectProductions());
            System.out.println("Data for the program has been taken from " +
                    fileURI + " file.\n");
            DerivationMachine dm = new DerivationMachine(loi, facts, goal);
            dm.doForwardChaining();
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        System.out.println("\nEnd of the program!");
    }

}
