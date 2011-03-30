package samples;

import chaining.Chaining;
import chaining.DataCollector;
import chaining.DataCollectorFromCyrasTypeFile;
import chaining.ForwardChaining;

/**
 *
 * @author Dainius
 */
public class ClientForward {

    public static void main(String[] args) {
        System.out.println("Program that demonstrates how forward " +
                "chaining works started to work:\n");
        String fileURI = "src/cyras_input/9_implications.cyras";
        try{
            DataCollector dc = new DataCollectorFromCyrasTypeFile(fileURI);
            System.out.println("Data for the program has been taken from " +
                    fileURI + " file.\n");
            Chaining dm = new ForwardChaining(dc);
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
