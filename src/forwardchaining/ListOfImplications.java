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
public class ListOfImplications {
    private ArrayList <Implication> listOfImplications;

    /**
     * Builder of list of implications.
     * @param listOfImplications
     */
    public ListOfImplications(ArrayList<String> listOfImplications) {
        this.listOfImplications = new ArrayList();
        for (String implication : listOfImplications) {
            this.addImplication(implication);
        }
    }

    public ArrayList getListOfImpliccations() {
        return this.listOfImplications;
    }
    /**
     * Method which adds implication to the list of implications
     * @param implication
     */
    private void addImplication(String implication) {
        this.listOfImplications.add(new Implication(implication));
    }

    /**
     * Method which prints list of implications - implication by implication.
     */
    public void printImplications() {
        for (Implication implication : this.listOfImplications) {
            System.out.println(implication);
        }
    }
}
