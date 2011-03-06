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
        try{
            DataCollector dc = new DataCollector("src/InputData/input1.txt");
            Facts facts = new Facts(dc.collectFacts());
            String goal = dc.collectGoal();
            ListOfImplications loi = 
                    new ListOfImplications(dc.collectProductions());
            ForwardChaining fc = new ForwardChaining(loi, facts, goal);
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        System.out.println("\nEnd of the program!");
    }

}
