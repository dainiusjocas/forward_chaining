package forwardchaining;

import java.util.ArrayList;

/**
 *
 * @author Dainius
 */
public class BackwardChaining extends DerivationMachine {

    private String currentGoal = "";
    private String currentImplication = "";
    private ArrayList<String> productions;

    /**
     * Constructor that only calls of super class.
     * @param dataCollector
     * @throws Exception
     */
    public BackwardChaining(DataCollector dataCollector) throws Exception {
        super(dataCollector);
        this.productions = new ArrayList();
    }

    @Override
    boolean isNewFactProduced() {
        throw new UnsupportedOperationException("Not supported yet.");
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
