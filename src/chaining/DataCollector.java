package chaining;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This interface describes the way that data readers should collect data.
 * @author Dainius Jocas, VU MIF, PS#3, 3rd year
 */
public interface DataCollector {

    /**
     * Method for collecting facts for further use in application
     * @return ArrayList of names of facts where one name of the fact is a
     * String
     * @throws IOException
     */
    ArrayList<String> collectFacts() throws IOException;

    /**
     * Method for collecting implications for further use in the application
     * @return ArrayList of implications where one implication is String
     * formatted in that way:
     *   [implication.id]: [<[ancedent1], [antecedent2]...>] -> [consequent]
     * @throws IOException
     */
    ArrayList<String> collectImplications() throws IOException;

    /**
     * Method which collects goal for the application
     * @return a String wgich is a name of the goal
     * @throws IOException
     */
    String collectGoal() throws IOException;
}
