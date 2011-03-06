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
public class ForwardChaining {
    ArrayList result;
    ListOfImplications listOfImplications;
    Facts facts;
    private String goal;


    public ForwardChaining(ListOfImplications listOfImplications, Facts facts,
            String goal) {
        this.listOfImplications = listOfImplications;
        this.facts = facts;
        this.goal = goal;
    }
}
