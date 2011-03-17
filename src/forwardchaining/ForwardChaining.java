package forwardchaining;

/**
 *
 * @author Dainius
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
