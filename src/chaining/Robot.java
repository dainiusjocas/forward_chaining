package chaining;

import java.util.Date;
import java.util.Random;

/**
 * This class emulates the entity which can think in both backward and forward
 * chaining
 * @author Dainius
 */
public class Robot {
    Chaining chainingBrain;

    /**
     * Constructor with parameter
     * @param chainingMachine
     */
    public Robot(Chaining chainingMachine) {
        this.chainingBrain = chainingMachine;
    }

    /**
     * Constructor without parameters
     */
    public Robot() {};

    /**
     * This method can change the model of thinking of this robot
     * @param chainingMachine
     */
    public void changeChainingModel(Chaining chainingMachine) {
        this.chainingBrain = chainingMachine;
    }

    /**
     * Method which checks if the goal is derivable in the system of facts and
     * implications that robot has inside
     * @param goal
     * @return true if goal is derivable, otherwise - false
     */
    public boolean isThisGoalCorrect(String goal) {
        boolean result = this.chainingBrain.doChaining(goal);
        if (result) {
            System.out.println("\nThe goal is reached!\n" + this.chainingBrain.getProductionSystem());
        } else {
            System.out.println("\nThe goal is not reached.");
        }
        return result;
    }

    public boolean isThisSystemCorrect(String fileURI) {
        boolean result = false;
        try{
            DataCollector dc;
            if (fileURI.contains(".xml")) {
                dc = new DataCollectorFromXML(fileURI);
                System.out.println("Data for the program has been taken from " +
                    fileURI + " file.\n");
            } else if (fileURI.contains(".cyras")) {
                dc = new DataCollectorFromCyrasTypeFile(fileURI);
            }
            else if (fileURI.length() > 0){
                dc = new DataCollectorFromPlainTextFiles(fileURI);
                System.out.println("Data for the program has been taken from " +
                    fileURI + " file.\n");
            } else {
                dc = new DataCollectorFromCommandLine();
            }
            Date date = new Date();
            Random generator = new Random(date.getTime());
            if (1 == generator.nextInt(2)) {
                this.chainingBrain = new BackwardChaining(dc);
            } else {
                this.chainingBrain = new ForwardChaining(dc);
            }
            result = isThisGoalCorrect(dc.collectGoal());
        } catch (Exception e) {
            System.out.println("Robot failed to read from file");
        }
        return result;
    }

}
