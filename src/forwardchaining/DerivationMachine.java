/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forwardchaining;

import java.util.ArrayList;

/**
 *
 * @author dj
 */
public class DerivationMachine {
    ArrayList <Implication> listOfImplications;
    Facts facts;
    ArrayList <String> plan;
    private String goal;


    public DerivationMachine(ListOfImplications listOfImplications, Facts facts,
            String goal) {
        this.listOfImplications = listOfImplications.getListOfImpliccations();
        this.facts = facts;
        this.goal = goal;
        this.plan = new <String>ArrayList();
    }

    public boolean doForwardChaining() {
        boolean changed = false;
        boolean haveGoal = false;
        int step_index = 0;
        showData(); /* outputs the initial data */
        do {
            changed = false;
            if (isGoalReached() == true) {
                haveGoal = true;
            } else {
                for (Implication im : this.listOfImplications) {
                    if (!(this.plan.contains(im.getDescriptor())) && (true == im.isConsequenceProvable(this.facts))) {
                        this.facts.addFact(im.getConsequence());
                        changed = true;
                        this.plan.add(im.getDescriptor());
                        break;
                    }
                }
                step_index++;
                showStepInfo(step_index, this.facts.getListOfFacts(),
                        getCurrentPlan());
            }
        } while ((changed == true) && (haveGoal == false));
        if (haveGoal) {
            System.out.println("GOAL IS REACHED!\n" + "Final list of facts is: "
                    + this.facts.getListOfFacts());
        } else {
            System.out.println("The goal is not reached.");
        }
        return haveGoal;
    }
    
    private void showStepInfo(int step_index, String listOfFacts, String plan) {
        System.out.println("After step nr: " + step_index);
        System.out.println("List of facts is: " + listOfFacts);
        System.out.println("Implications used are: " + plan + "\n");
    }

    /**
     * Method that shows the present state of the data.
     */
    private void showData() {
        System.out.println("List of facts: " + this.facts.getListOfFacts());
        System.out.println("Goal: " + this.goal);
        System.out.println("Implications:");
        for (Implication im : this.listOfImplications) {
            System.out.println("  " + im.toString());
        }
        System.out.println();
    }

    private boolean isGoalReached() {
        return this.facts.searchForFact(goal);
    }

    private String getCurrentPlan() {
        String currentPlan = "{";
        for (String s : this.plan) {
            currentPlan = currentPlan + s + "; ";
        }
        return currentPlan.substring(0, currentPlan.length() - 2) + "}";
    }
}
