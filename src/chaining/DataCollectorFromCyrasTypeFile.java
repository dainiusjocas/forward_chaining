package chaining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class can read cyras typed files for the chaining machines.
 * @author Dainius
 */
public class DataCollectorFromCyrasTypeFile implements DataCollector {

    public static int EMPTY_LINES_TILL_IMPLICATIONS = 1;
    public static int EMPTY_LINES_TILL_FACTS = 2;
    public static int EMPTY_LINES_TILL_GOAL = 3;
    /* In this variable data input file descriptor is loaded */
    private BufferedReader bufferedReader;

    /**
     * Constructor which loads Cyras type file with input data.
     * @param fileURI
     */
    public DataCollectorFromCyrasTypeFile(String fileURI) {
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(fileURI)));
            bufferedReader.mark(1);
        } catch (Exception e) {
            System.out.println("Some general error occurred.");
        }
    }

    /**
     * Method that collects facts from the Cyras type files.
     * @return ArrayList where one member is one fact/
     * @throws IOException
     */
    public ArrayList<String> collectFacts() throws IOException {
        goToTheFirstLine();
        String facts = "";
        int counterOfEmptyLines = 0;
        while (counterOfEmptyLines < EMPTY_LINES_TILL_FACTS) {
            facts = facts = getLine();
            if ((facts.contains("#")) || (facts.isEmpty())) {
                counterOfEmptyLines++;
            }
        }
        facts = getLine();
        ArrayList<String> listOfFacts = new ArrayList();
        for (int i = 0; i < facts.length(); i++) {
            listOfFacts.add(Character.toString(facts.charAt(i)));
        }
        return listOfFacts;
    }

    /**
     * This method parses data Cyras typed input file and makes list of implications.
     * @return
     * @throws IOException
     */
    public ArrayList<String> collectImplications() throws IOException {
        goToTheFirstLine();
        ArrayList<String> implications = new ArrayList();
        String line = "";
        int counterOfEmptyLines = 0;
        while (counterOfEmptyLines < EMPTY_LINES_TILL_IMPLICATIONS) {
            line = getLine();
            if ((line.contains("#")) || (line.isEmpty())) {
                counterOfEmptyLines++;
            }
        }
        do {
            line = getLine();
            implications.add(line);
        } while (!line.isEmpty());
        implications.remove(implications.size() - 1);
        return convertImplications(implications);
    }

    /**
     * This method converts every implication from format "ABC" to format
     * "Rn: A, B -> C"
     * @param listOfImplications
     * @return ArrayList of formated implications
     */
    private ArrayList<String> convertImplications(ArrayList<String> listOfImplications) {
        ArrayList<String> modifiedImplications = new ArrayList();
        int counter = 0;
        for (String implication : listOfImplications) {
            String temp = "";
            temp = "R" + ++counter + ": ";
            for (int i = 0; i < implication.length() - 1; i++) {
                temp = temp + implication.charAt(i) + ", ";
            }
            temp = temp.substring(0, temp.length() - 2);
            temp = temp + " -> " + implication.charAt(implication.length() - 1);
            modifiedImplications.add(temp);
        }
        return modifiedImplications;
    }

    /**
     * Method which finds goal in the input file
     * @return
     * @throws IOException
     */
    public String collectGoal() throws IOException {
        goToTheFirstLine();
        String line = "";
        int counterOfEmptyLines = 0;
        while (counterOfEmptyLines < EMPTY_LINES_TILL_GOAL) {
            line = line = getLine();
            if ((line.contains("#")) || (line.isEmpty())) {
                counterOfEmptyLines++;
            }
        }
        return line = getLine();
    }

    /**
     * This methods reads line and tries to solve problems faced;
     * @return String
     */
    private String getLine() {
        try {
            return this.bufferedReader.readLine();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return null;
    }

    /**
     * Method to reset file cursor;
     */
    private void goToTheFirstLine() {
        try {
            this.bufferedReader.reset();
        } catch (Exception e) {
            System.out.println("Error while reseting file cursor");
        }
    }

}
