package forwardchaining;

import java.util.ArrayList;

/**
 * This class if for applicating logial derivations. The main method of the
 * class is doForwardChaining, which impolement Forward Chaining principe.
 * @author Dainius Jocas, VU MIF, PS#3, 3rd year
 */
public class DerivationMachine {
    /* Data structures used in application */
    ListOfImplications listOfImplications;
    Facts facts;
    ArrayList <String> productionSystem;
    private String goal;
    DataCollector dataCollector; /* Part of the strategy template */

    /**
     * Constructor which loads data structures of the class from already build
     * data structures.
     * @param listOfImplications object of ListOfImplications
     * @param facts list of facts
     * @param goal
     */
    public DerivationMachine(ListOfImplications listOfImplications, Facts facts,
            String goal) {
        this.listOfImplications = listOfImplications;
        this.facts = facts;
        this.goal = goal;
        this.productionSystem = new ArrayList();
    }

    /**
     * Constructor that loads data structures. This constructor itself asks
     * data collector to collect data.
     * @param dataCollector object that implements initial data collection
     * @throws Exception
     */
    public DerivationMachine(DataCollector dataCollector) throws Exception {
        this.dataCollector = dataCollector;
        this.listOfImplications = new ListOfImplications(this.dataCollector.collectImplications());
        this.facts = new Facts(this.dataCollector.collectFacts());
        this.goal = this.dataCollector.collectGoal();
    }

    /**
     * Method for setting data collector.
     * @param dataCollector object that specifies the way that data can be
     * collected
     */
    public void setDataCollector(DataCollector dataCollector) {
        this.dataCollector = dataCollector;
    }

    /**
     * This method implements forward chaining rule.
     * @return true if goal was reached, otherwise false
     */
    public boolean doForwardChaining() {
        boolean changed = false;
        boolean goalReached = false; /* if true - the work is done */
        int step_index = 0;       /* counter of steps taken */
        if (!(goalReached = isGoalReached()) == true) {
            do {                                                                /* 1 */
                changed = false;
                for (Object im : this.listOfImplications.                       /* 2 */
                        getListOfImplications()) {
                    Implication imp = (Implication)im;
                    if (!(this.productionSystem.contains(imp.getDescriptor()))  /* 3 */
                           && (isMemoryToBeChanged(imp))) {
                        this.facts.addFact(imp.getConsequent());
                        this.productionSystem.add(imp.getDescriptor());         /* 4 */
                        this.listOfImplications.removeImplication(imp);         /* 5 */
                        changed = true;
                        goalReached = isGoalReached();
                        break;                                                  /* 6 */
                    }                                                           /* 7 */
                }                                                               /* 8 */
                if (changed) {                                                  /* log of application*/
                    showStepInfo(++step_index, this.facts.getListOfFacts(),
                        getCurrentProductionSystem());
                }
            } while ((changed == true) && (goalReached == false));              /* 9 */
        } else {
            this.productionSystem.add("No productions used");
        }
        return goalReached;
    }

    public boolean doChaining() {
        boolean isChanged = false;
        boolean isGoalReached = false;
        if (!(isGoalReached = isGoalReached()) == true) {
            if (isGoalInConsequents()) { // if goal is in consequents than there is a sense to try to do chaining
                while (isUnusedRuleLeft()) { // if we have unused facts then we can try to work in order to reach the goal
                    isChanged = isNewFactProduced();
                    if((isGoalReached = isGoalReached()) == true) {
                        break;
                    }
                }
            } else {
                isGoalReached = false;
            }
        } else {
            this.productionSystem.add("No production used");
        }
        return isGoalReached;
    }

    private boolean isNewFactProduced() {
        return false;
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
     * Method that checks if we have goal in the list of implications as a
     * consequent
     * @return true if goal is in the list of consequents, otherwise - false
     */
    private boolean isGoalInConsequents() {
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
     * This method checks if the implication we are working with will change
     * working memory.
     * @param imp
     * @return true if we need to change working memory
     */
    private boolean isMemoryToBeChanged(Implication imp) {
        if (true == imp.isConsequenceProvable(this.facts)) {
            return true;
        }
        return false;
    }

    /**
     * Method that shows current state of the forward chaining.
     * @param step_index
     * @param listOfFacts
     * @param productionSystem
     */
    private void showStepInfo(int step_index, String listOfFacts, String plan) {
        System.out.println("After step nr: " + step_index);
        System.out.println("  List of facts is: " + listOfFacts);
        System.out.println("  Implications used are: " + plan + "\n");
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
}
