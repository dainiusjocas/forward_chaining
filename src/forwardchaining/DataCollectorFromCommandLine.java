/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forwardchaining;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Dainius
 */
public class DataCollectorFromCommandLine implements DataCollector {

    public ArrayList<String> collectFacts() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<String> collectImplications() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This method is for collecting a goal for the production system from
     * command line.
     * @return String which should be one char and this char can be only a
     * letter
     * @throws IOException
     */
    public String collectGoal() throws IOException {
        String goal = "";
        boolean goodInput = false;
        Scanner in = new Scanner(System.in);
        System.out.println("Goal of the production system can be only one"
                + " letter. E.g. Z.");
        System.out.println("Enter a goal for the production system");
        goal = in.nextLine();
        goodInput = isGoalValid(goal);
        /* Loop till we have a valid input */
        while(!goodInput) {
            System.out.println("Bad input! Try enter data once again, please.");
            goal = in.nextLine();
            goodInput = isGoalValid(goal);
        }
        return goal;
    }

    /**
     * Method for checking if the goal is valid.
     * @param goal String
     * @return true if goal is valid, else - false
     */
    private boolean isGoalValid(String goal) {
        goal = goal.trim();
        if ((goal.length() == 1) &&
                (((goal.charAt(0) > 64) && (goal.charAt(0) < 91)) ||
                ((goal.charAt(0) > 96) && (goal.charAt(0) < 123)))) {
            return true;
        }
        return false;
    }

}
