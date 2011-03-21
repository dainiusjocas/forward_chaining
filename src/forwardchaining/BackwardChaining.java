package forwardchaining;

import java.util.ArrayList;

/**
 * This class is for implementing logical derivations with backward-chaining
 * algorithm.
 * @author Dainius Jocas, VU MIF, PS#3, 3rd year
 */
public class BackwardChaining extends DerivationMachine {

    private String currentGoal;
    private Implication currentImplication;
    private ArrayList<String> productions;

    /**
     * Constructor that only calls of super class.
     * @param dataCollector
     * @throws Exception
     */
    public BackwardChaining(DataCollector dataCollector) throws Exception {
        super(dataCollector);
        this.productions = new ArrayList();
        this.currentGoal = this.goal;
    }

    /**
     * Method to check if with current system of implications we can produce new
     * facts
     * @return true if new facts is produced
     */
    /** TODO: fix problem with many facts in consequent */
    @Override
    boolean isNewFactProduced() {
        String cGoal = this.currentGoal;
        this.currentImplication = getImplicationWhereCurrentGoalIsInTheConsequent();
        Implication cImplication = this.currentImplication;
        if (this.currentImplication != null) {
             if (isMemoryToBeChanged(this.currentImplication)) {
                 this.facts.addFact(this.currentGoal);
                 this.productionSystem.add(this.currentImplication.
                         getDescriptor());
                 return true;
             } else {
                 this.currentGoal = this.currentImplication.getAntecedent();
                 if (isNewFactProduced()) {
                     this.facts.addFact(cGoal);
                     this.productionSystem.add(cImplication.getDescriptor());
                 }
             }
        }
        return false;
    }

    /**
     * Method to get implication where current goal is in the consequent
     * @return implication where current goal is as a consequent
     */
    private Implication getImplicationWhereCurrentGoalIsInTheConsequent() {
        for (Object im : this.listOfImplications.getListOfImplications()) {
            Implication implication = (Implication)im;
            if (this.currentGoal.charAt(0) == implication.getConsequent().charAt(0)) {
                return implication;
            }
        }
        return null;
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
}
