package forwardchaining;

import java.util.ArrayList;

/**
 * This class if for applicating logial derivations. The main method of the
 * class is doForwardChaining, which impolement Forward Chaining principe.
 * @author Dainius Jocas, VU MIF, PS#3, 3rd year
 */
public class DerivationMachine {
    ArrayList <Implication> listOfImplications;
    Facts facts;
    ArrayList <String> plan;
    private String goal;

    /**
     * Constructor whicl loads data structures of the class.
     * @param listOfImplications
     * @param facts
     * @param goal
     */
    public DerivationMachine(ListOfImplications listOfImplications, Facts facts,
            String goal) {
        this.listOfImplications = listOfImplications.getListOfImplications();
        this.facts = facts;
        this.goal = goal;
        this.plan = new ArrayList();
    }

    /**
     * This method implements forward chaining rule.
     * @return
     */
    public boolean doForwardChaining() {
        boolean changed = false;
        boolean haveGoal = false; /* if true - the work is done */
        int step_index = 0;       /* counter of steps taken */
        do {
            changed = false;
            if (isGoalReached() == true) {
                haveGoal = true;
            } else {
                for (Implication im : this.listOfImplications) {
                    if (!(this.plan.contains(im.getDescriptor())) &&
                            (true == im.isConsequenceProvable(this.facts))) {
                        this.facts.addFact(im.getConsequence());
                        changed = true;
                        this.plan.add(im.getDescriptor());
                        break;
                    }
                }
                step_index++;
                if (changed) {
                    showStepInfo(step_index, this.facts.getListOfFacts(),
                        getCurrentPlan());
                }
            }
        } while ((changed == true) && (haveGoal == false));
        if (0 == step_index) {
            System.out.println("No implications applied");
        }
        return haveGoal;
    }

    /**
     * Method that shows current state of the forward chaining.
     * @param step_index
     * @param listOfFacts
     * @param plan
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
        for (Implication im : this.listOfImplications) {
            System.out.println("    " + im.toString());
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
     * Gets string representation if the current plan - list of the descriptors
     * used in forward chaining
     * @return
     */
    public String getCurrentPlan() {
        String currentPlan = "{";
        for (String s : this.plan) {
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
