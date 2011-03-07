package forwardchaining;

import java.util.ArrayList;

/**
 * This class is used to describe data structure for storing facts
 * @author Dainius Jocas, VU MIF, PS#3, 3rd year
 */
public class Facts {
    ArrayList<String> facts;

    /**
     * Constructor loads and fills data structures
     * @param facts
     */
    public Facts(String facts) {
        this.facts = new ArrayList();
        this.extractFacts(facts);
    }

    /**
     * This method extracts facts from the string representation of the facts.
     * @param facts
     */
    private void extractFacts(String facts) {
        String[] moo = facts.split(",");
        for (String s: moo) {
            this.facts.add(s.trim());
        }
    }

    /**
     * Method which checks if set of facts, has a fact given by paraeter.
     * @param Z
     * @return true or false
     */
    public boolean searchForFact(String Z) {
        if (this.facts.contains(Z)) {
            return true;
        }
        return false;
    }

    /**
     * This method can add one more fact to the set of the facts.
     * @param fact
     */
    public void addFact(String fact) {
        if (!searchForFact(fact)) {
            this.facts.add(fact);
        }
    }

    /**
     * This method makes a string representation of all the facts.
     * @return
     */
    public String getListOfFacts() {
        String stringOfFacts = "";
        for (String s : this.facts) {
            stringOfFacts += s + ", ";
        }
        return stringOfFacts.substring(0, stringOfFacts.length() - 2);
    }

}
