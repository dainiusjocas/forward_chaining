package chaining;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is implementation of the Backward-Chaining
 * @author Dainius Jocas
 */
public class BackwardChaining implements Chaining{
    private String facts;
    private ArrayList<Implication> implications = new ArrayList();
    private String goal;
    private ArrayList<String> productions = new ArrayList();
    private String path = "";

    /**
     * Constructor
     * @param dataCollector
     */
    public BackwardChaining(DataCollector dataCollector) throws IOException {
        this.goal = dataCollector.collectGoal();
        this.facts = convertListOfFactsToString(dataCollector.collectFacts());
        //ArrayList<String> tempListOfImplications = convertImplications(dataCollector.collectImplications());
        for (String implication : dataCollector.collectImplications()) {
            this.implications.add(new Implication(implication));
        }
    }

    /**
     * This method converts list of facts to the String representation
     * @param listOfFacts
     * @return String in format: "ABC" where every letter is a fact
     */
    private String convertListOfFactsToString(ArrayList<String> listOfFacts) {
        String factsInString = "";
        for (String fact : listOfFacts) {
            factsInString = factsInString + fact;
        }
        return factsInString;
    }

    /**
     * Method that checks if goal is derivable in production system.
     * @param goal
     * @return return true if goal is derivable, otherwise - false
     */
/* 1 Funkcijos deklaracija */
    public boolean doChaining(String goal) {
        System.out.println("Searching for: " + goal);
/* 2 if H yra_tarp_faktų then return true */
        if (isGoalInFacts(goal)) {
            System.out.println(goal + " is in facts.");
            return true;
        }
/* 3 if H nėra_tarp_sukcedentų then return false */
        if (!isGoalSomewhereAsConsequent(goal)) {
            System.out.println("DEADEND!");
            removeLastStep();
            return false;
        }
/* 4 if atbulinis_isvedimas_pakliuvo_į_ciklą then return false */
        if (isChainingInTheLoop(goal)) {
            System.out.println("LOOP!!!");
            return false;
        }
        boolean result = true;
/* 5 for kiekvienai_implikacijai_kur_H_yra_sukcedentas do */
        for (Implication implication : implications) {
            if (implication.getConsequent().contains(goal)) {
                addNewGoal(goal);
                printDerivationPath();
/* 6 if visiems_antecedentams */
                for (String newGoal : getAntecedentAsList(implication)) {
/* 7 true == atbulinis_išvedimas(antecedent_narys)  then return true */
                    result = result && doChaining(newGoal);
                }
                if (result) {
                    removeLastStep();
                    System.out.println(goal + " goes to list of facts!");
                    addNewFact(goal);
                    addImplicationToProductionSystem(implication);
                    return true;
                } else {
                    result = true;
                }
            }
        }
/* 8 return false*/
        return false;
    }

    /**
     * Method that checks if the goal is in facts
     * @param goal that is being searched
     * @return true if goal is in facts, otherwise - false
     */
    public boolean isGoalInFacts(String goal) {
        if (this.facts.contains(goal)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method that checks if there is a implications which has such a consequent
     * @param consequent
     * @return true if there is such an implication with a specific consequent,
     * otherwise - false
     */
    boolean isGoalSomewhereAsConsequent(String consequent) {
        for (Implication implication : this.implications) {
            if (implication.getConsequent().contains(consequent)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that ads a fact to the list of facts
     * @param fact
     */
    private void addNewFact(String fact) {
        this.facts = this.facts + fact;
    }

    /**
     * Method that add implication to the production system
     * @param implication
     */
    void addImplicationToProductionSystem(Implication implication) {
        this.productions.add(implication.getDescriptor());
    }

    /**
     * Method that checks if chaining is in loop
     * @param goal
     * @return true if chaining is in loop, otherwise - false.
     */
    boolean isChainingInTheLoop(String goal) {
        return this.path.contains(goal);
    }

    /**
     * This method prints derivation path.
     */
    void printDerivationPath() {
        String derivationPath = "";
        for (int i = this.path.length() - 1; i >= 0; i--) {
            derivationPath = derivationPath + " <- " + this.path.charAt(i);
        }
        System.out.println("Derivation path: " + derivationPath);
    }
    /**
     * This method ads new goal to the list of goal. "New" means that there are
     * no such goal in derivation path
     * @param newGoal
     */
    void addNewGoal(String newGoal) {
        if (!this.path.contains(newGoal)) {
            this.path = this.path + newGoal;
        }
    }

    /**
     * Method that removes the goal that was searched last.
     */
    void removeLastStep() {
        if (this.path.length() > 0) {
            this.path = this.path.substring(0, this.path.length() - 1);
        }
    }

    /**
     * Method that prints current state of the program to terminal
     */
    void showState() {
        System.out.println("Current list of facts: " + getFacts());
        System.out.println("Current production system: " + getProductionSystem());
    }

    /**
     * Method that prints implications with indentation
     */
    void printImplications() {
        for (Implication implication : this.implications) {
            System.out.println("  " + implication);
        }
    }

    /**
     * Method that gets String representation of facts
     * @return
     */
    String getFacts() {
        String representationOfFacts = "{";
        for (int i = 0; i < this.facts.length(); i++) {
            representationOfFacts = representationOfFacts + this.facts.charAt(i) + ", ";
        }
        return representationOfFacts.substring(0, representationOfFacts.length() - 2) + "}";
    }

    /**
     * Getter of goal
     * @return
     */
    String getGoal() {
        return this.goal;
    }

    /**
     * Method that gets String representation of production system
     * @return
     */
    public String getProductionSystem() {
        String productionSystem = "Production System: {";
        if (this.productions.size() > 0) {
            for (String production : this.productions) {
                productionSystem = productionSystem + production.
                        substring(0, production.length()) + ", ";
            }
            return productionSystem.substring(0, productionSystem.length() - 2) + "}";
        }
        return "{NO PRODUCTIONS USED. Goal was in facts.}";
    }

    /**
     * This method collects and returns all the members of antecedent as a
     * ArrayList
     * @return ArrayList<String> with members of antecedent
     */
    private ArrayList<String> getAntecedentAsList(Implication implication) {
        ArrayList listOfMembersOfAntecedent = new ArrayList();
        String antecedent = getAntecedentAsString(implication);
        for (int i = 0; i < antecedent.length(); i++) {
            listOfMembersOfAntecedent.add(Character.toString(antecedent.charAt(i)));
        }
        return listOfMembersOfAntecedent;
    }

    private String getAntecedentAsString(Implication implication) {
        String antecedentInString = "";
        for (int i = 0; i < implication.getAntecedent().length(); i++) {
            if ((64 < implication.getAntecedent().charAt(i)) &&
                    (91 > implication.getAntecedent().charAt(i))) {
                antecedentInString = antecedentInString + implication.getAntecedent().charAt(i);
            }
        }
        return antecedentInString;
    }
}
