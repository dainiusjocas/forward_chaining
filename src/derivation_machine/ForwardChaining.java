package derivation_machine;

import derivation_machine.DerivationMachine;
import chaining.DataCollector;
import chaining.Implication;

/**
 * This class is for implementing logical derivations with forward-chaining
 * algorithm.
 * @author Dainius Jocas, VU MIF, PS#3, 3rd year
 */
public class ForwardChaining extends DerivationMachine {

    /**
     * Constructor that only calls of super class.
     * @param dataCollector
     * @throws Exception
     */
    public ForwardChaining(DataCollector dataCollector) throws Exception {
        super(dataCollector);
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
}
