/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forwardchaining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is for reading input file with following rules.
 * @author dj
 */
public class DataCollector {
    public static String TERMINAL_WORD_FOR_PRODUCTIONS = "productions:";
    public static String TERMINAL_WORD_FOR_FACTS = "facts:";
    public static String TERMINAL_WORD_FOR_GOAL = "goal:";
    public static char TERMINAL_SYMBOL_FOR_COMMENT = '#';


    private BufferedReader bufferedReader;

    /**
     * This constructor receives URI of the file with data for program,
     * initializes file reader, and marks the beginning of the file for better
     * parsing of the file.
     * @param fileURI - URI of the file
     */
    public DataCollector(String fileURI) {
        fileURI = "src/InputData/input1.txt"; /* TODO: delete this line */
        try {
            bufferedReader = new BufferedReader(new FileReader(new File
                    (fileURI)));
            bufferedReader.mark(1);
        } catch (FileNotFoundException e) {
            System.out.println("The file You specified doesn't exist!");
        } catch (IOException e) {
            System.out.println("The file You specified is not valid!");
        } catch (Exception e) {
            System.out.println("Some general error occurred.");
        }
    }

    /**
     * Gets lines where are facts for the forward chaining.
     * @throws IOException
     */
    public String collectFacts() throws IOException {
        this.bufferedReader.reset();
        String temp = "";
        String facts = "";
        do {
            temp = this.bufferedReader.readLine();
        } while (!temp.toLowerCase().contains(DataCollector.
                TERMINAL_WORD_FOR_FACTS));
        temp = getFirstNotEmptyLine();
        while ((temp != null) && (!temp.isEmpty()) &&
                        (temp.charAt(0) != DataCollector.
                        TERMINAL_SYMBOL_FOR_COMMENT)) {
            facts = facts + temp;
            temp = this.bufferedReader.readLine();
        }
        return facts;
    }

    public ArrayList collectProductions() throws IOException {
        this.bufferedReader.reset();
        String temp = "";
        ArrayList listOfImplications = new ArrayList();
        do {
            temp = this.bufferedReader.readLine();
        } while (!temp.toLowerCase().contains(DataCollector.
                TERMINAL_WORD_FOR_PRODUCTIONS));
        temp = getFirstNotEmptyLine();
        while ((temp != null) && (!temp.isEmpty()) &&
                        (temp.charAt(0) != DataCollector.
                        TERMINAL_SYMBOL_FOR_COMMENT)) {
            listOfImplications.add(temp.trim());
            temp = this.bufferedReader.readLine();
        }
        return listOfImplications;
    }

    /**
     * Method which finds goal in the input file
     * @return
     * @throws IOException
     */
    public String collectGoal() throws IOException {
        this.bufferedReader.reset();
        String temp = "";
        String goal = "";
        do {
            temp = this.bufferedReader.readLine();
        } while (!temp.toLowerCase().contains(DataCollector.
                TERMINAL_WORD_FOR_GOAL));
        goal = getFirstNotEmptyLine();
        return goal;
    }

    /**
     * Method which skips all the empty lines in the file and returns first not
     * empty line.
     */
    private String getFirstNotEmptyLine() throws IOException {
        String temporaryLine;
        do {
            temporaryLine = this.bufferedReader.readLine();
        } while ((temporaryLine.isEmpty()) || 
                (temporaryLine.charAt(0) == DataCollector.
                TERMINAL_SYMBOL_FOR_COMMENT));
        return temporaryLine;
    }
}
