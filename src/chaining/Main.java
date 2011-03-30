package chaining;

import derivation_machine.ForwardChaining;
import derivation_machine.DerivationMachine;
import derivation_machine.BackwardChaining;
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
        System.out.println("Program that demonstrates the way that robot "
                + "thinks starts to work:\n");
        Robot myRobot = new Robot();
        if (myRobot.isThisSystemCorrect(args[0])) {
            System.out.println("\nRobot says that the system is correct.");
        } else {
            System.out.println("\nRobot says that this system is ambiguous.");
        }
        System.out.println("\nEnd of the program!");
    }

}
