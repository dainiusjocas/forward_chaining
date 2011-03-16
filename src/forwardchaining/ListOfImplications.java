package forwardchaining;

import java.util.ArrayList;

/**
 * This class describes the data structure for storing implications.
 * @author Dainius Jocas, VU MIF, PS#3, 3rd year
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

    /**
     * This method removes implication from the list of implications
     * @param implication
     */
    public void removeImplication(Implication implication) {
        this.listOfImplications.remove(implication);
    }

    /**
     *
     * @param <Implication>
     * @return ArrayList of implications
     */
    public <Implication>ArrayList getListOfImplications() {
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
