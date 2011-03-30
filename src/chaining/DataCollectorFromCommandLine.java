package chaining;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is for collecting data for application of production system.
 * This class implement DataCollector inteface in that way that it collects data
 * from command line.
 * @author Dainius Jocas, VU MIF, PS#3, 3rd year
 */
public class DataCollectorFromCommandLine implements DataCollector {

    /**
     * This method collects facts from the command line. Facts should be given
     * in form "a, A, v, B". One fact is a letter between commas.
     * @return ArrayList of facts
     * @throws IOException
     */
    public ArrayList<String> collectFacts() throws IOException {
        ArrayList<String> facts = new ArrayList();
        boolean goodInput = true;
        Scanner in = new Scanner(System.in);
        String lineWithFacts = "";
        System.out.println("Now You have to enter facts of the derivation system"
                + ".\nFacts can be only letters and have to be in that format: "
                + "A, b, c, D");
        System.out.println("Enter facts:");
        lineWithFacts = in.nextLine();
        goodInput = isLineWithFactsCorrect(lineWithFacts);
        while(!goodInput) {
            System.out.println("Bad input! Try enter data once again, please.");
            lineWithFacts = in.nextLine();
            goodInput = isLineWithFactsCorrect(lineWithFacts);
        }
        facts = constructArrayListOfFacts(lineWithFacts);
        return facts;
    }

    /**
     * This method from the String which has only letters, commas, and spaces
     * builds Array list of facts.
     * @param lineWithFacts
     * @return ArrayList of Strings which represents facts.
     */
    private ArrayList<String> constructArrayListOfFacts(String lineWithFacts) {
        ArrayList<String> facts = new ArrayList();
        String[] arrayOfFacts = lineWithFacts.split(",");
        for (String s: arrayOfFacts) {
            s = s.trim();
            if ((s.length() > 0) && (s.length() < 2) && (isGoalValid(s))) {
                facts.add(s);
            }
        }
        return facts;
    }

    /**
     * This method checks if line consist only letters, commas, and spaces and
     * checks if line has at least one letter.
     * @param lineWithFacts
     * @return true if facts are in right format, otherwise false
     */
    private boolean isLineWithFactsCorrect(String lineWithFacts) {
        if ((lineWithFacts.matches("^[a-zA-Z ,]+$")) &&
                (lineWithFacts.matches(".*[A-Za-z]+.*"))) {
            return true;
        }
        return false;
    }

    /**
     * Method that collects implications from command line.
     * @return ArrayList of implications, where one implication is a string of
     * format R1: A -> B
     * @throws IOException
     */
    public ArrayList<String> collectImplications() throws IOException {
        ArrayList<String> implications = new ArrayList();
        String implication = "";
        String antecedent = "";
        String consequent = "";
        int i = 1;
        System.out.println("Rule for the production system consists of two "
                + "parts: antecedent and consequent.\n Antecedent is in format"
                + " a, B, A. Consequent is one letter");
        do {
            System.out.print("Enter antecedent: ");
            antecedent = getAntecedent();
            System.out.print("Enter consequent: ");
            consequent = getConsequent();
            implication = "R" + i + ": " + antecedent + " -> " + consequent;
            implications.add(implication);
            System.out.println(implication);
        } while (doWeHaveMoreImplications());

        
        return implications;
    }

    /**
     * Takes data from console and validates it.
     * @return String which is antecedent for implication
     */
    private String getAntecedent() {
        boolean goodInput = false;
        Scanner in = new Scanner(System.in);
        String lineWithFacts = in.nextLine();
        goodInput = isLineWithFactsCorrect(lineWithFacts);
        while(!goodInput) {
            System.out.println("Bad input! Try enter data once again, please.");
            lineWithFacts = in.nextLine();
            goodInput = isLineWithFactsCorrect(lineWithFacts);
        }
        return lineWithFacts;
    }

    /**
     * Takes data from console and validates it.
     * @return string which is a consequent for implication.
     */
    private String getConsequent() {
        Scanner in = new Scanner(System.in);
        String consequent = in.nextLine();
        boolean goodInput = isGoalValid(consequent);
        /* Loop till we have a valid input */
        while(!goodInput) {
            System.out.println("Bad input! Try enter data once again, please.");
            consequent = in.nextLine();
            goodInput = isGoalValid(consequent);
        }
        return consequent;
    }

    /**
     * Method to ask user if he wants to enter more implications
     * @return
     */
    private boolean doWeHaveMoreImplications() {
        System.out.println("If you want to enter more implications then press "
                + "'y' else press 'n' and then 'ENTER'");
        Scanner in = new Scanner(System.in);
        if (('y' == (in.nextLine()).charAt(0)) || ('Y' == (in.nextLine()).charAt(0))) {
            return true;
        }
        return false;
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
