package chaining;

import java.util.ArrayList;

/**
 * This class is implementation of chaining algorithm in forward-chaining fashion
 * @author Dainius Jocas
 */
public class ForwardChaining implements Chaining {
    /* Data structures used in application */
    ListOfImplications listOfImplications;
    Facts facts;
    ArrayList <String> productionSystem;
    String goal;
    DataCollector dataCollector; /* Part of the strategy template */

    /**
     * Constructor that loads data structures. This constructor itself asks
     * data collector to collect data.
     * @param dataCollector object that implements initial data collection
     * @throws Exception
     */
    public ForwardChaining(DataCollector dataCollector) throws Exception {
        this.dataCollector = dataCollector;
        this.listOfImplications = new ListOfImplications(this.dataCollector.collectImplications());
        this.facts = new Facts(this.dataCollector.collectFacts());
        this.goal = this.dataCollector.collectGoal();
        this.productionSystem = new ArrayList();
    }

    /**
     * Method for chaining. This method uses some other methods of this class
     * but because they are abstract they need to be implemented in derived
     * classes.
     * @return true if chaining was successful, otherwise - false
     */
    public boolean doChaining(String goal) {
        boolean isChanged = true;
        boolean isGoalReached = false;                                   /* 1 */
        if (!(isGoalReached = isGoalReached()) == true) {
            if (isGoalInConsequents()) {                                 /* 2 */
                while (isUnusedRuleLeft() && isChanged) {                /* 3 */
                    isChanged = isNewFactProduced();                     /* recursion */
                    if((isGoalReached = isGoalReached()) == true) {
                        break;
                    }
                }
            } else {
                isGoalReached = false;
            }
        } else {
            this.productionSystem.add("No production used");             /* 5 */
        }
        return isGoalReached;                                            /* 4 */
    }

    /**
     * Method that checks if the forward chaining have already finished its work
     * @return
     */
    private boolean isGoalReached() {
        return this.facts.searchForFact(goal);
    }

    /**
     * Gets string representation if the current productionSystem - list of the descriptors
     * used in forward chaining
     * @return
     */
    public String getCurrentProductionSystem() {
        String currentPlan = "{";
        for (String s : this.productionSystem) {
            currentPlan = currentPlan + s + "; ";
        }
        return currentPlan.substring(0, currentPlan.length() - 2) + "}";
    }

    /**
     * Gets string representation of the current facts
     * @return
     */
    public String getListOfFacts() {
        return this.facts.getListOfFacts();
    }

    /**
     * Method that checks if there are unused facts
     * @return true if the list of implications is not empty, otherwise - false
     */
    private boolean isUnusedRuleLeft() {
        if (!this.listOfImplications.getListOfImplications().isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if the implication we are working with will change
     * working memory.
     * @param imp
     * @return true if we need to change working memory
     */
    protected boolean isMemoryToBeChanged(Implication imp) {
        if (true == imp.isConsequentProvable(this.facts)) {
            return true;
        }
        return false;
    }

    /**
     * Method that checks if we have goal in the list of implications as a
     * consequent
     * @return true if goal is in the list of consequents, otherwise - false
     */
    protected boolean isGoalInConsequents() {
        boolean goalIsInConsequents = false;
        for (Object im : this.listOfImplications.getListOfImplications()) {
            Implication implication = (Implication)im;
            if (this.goal == null ? implication.getConsequent() == null : this.
                    goal.equals(implication.getConsequent())) {
                goalIsInConsequents = true;
                break;
            }
        }
        return goalIsInConsequents;
    }

    /**
     * This method checks if new fact can be produced. And search of new facts
     * is organized in a way of forward chaining algorithm.
     * @return true if new fact was produced, otherwise - false
     */
    boolean isNewFactProduced() {
        for (Object im : this.listOfImplications.
                getListOfImplications()) {
            Implication imp = (Implication)im;
            if (!(this.productionSystem.contains(imp.getDescriptor()))
                   && (isMemoryToBeChanged(imp))) {
                this.facts.addFact(imp.getConsequent());
                this.productionSystem.add(imp.getDescriptor());
                this.listOfImplications.removeImplication(imp);
                return true;
            }
        }
        return false;
    }

/**
     * Method that shows the present state of the data.
     */
    public void showData() {
        System.out.println("Initial data:");
        System.out.println("  List of facts: " + this.facts.getListOfFacts());
        System.out.println("  Goal: " + this.goal);
        System.out.println("  Implications:");
        for (Object im : this.listOfImplications.getListOfImplications()) {
            Implication imp = (Implication)im;
            System.out.println("    " + imp.toString());
        }
        System.out.println();
    }

    public String getProductionSystem() {
        if (this.productionSystem.size() != 0) {
            String currentPlan = "{";
            for (String s : this.productionSystem) {
                currentPlan = currentPlan + s + "; ";
            }
            return currentPlan.substring(0, currentPlan.length() - 2) + "}";
        }
        return "{NO PRODUCTIONS USED. Goal was in facts.}";
    }
}
