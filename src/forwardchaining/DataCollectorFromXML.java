package forwardchaining;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class should be used for the data collection process of the program
 * which demonstrates how production system methods work from the XML file.
 * @author Dainius Jocas, VU MIF, PS#3, 3rd year
 */
public class DataCollectorFromXML implements DataCollector {

    final static String NAME_OF_FACT_TAG = "fact";
    final static String NAME_OF_GOAL_TAG = "goal";
    final static String NAME_OF_IMPLICATION_TAG = "implication";
    final static String NAME_OF_ANTECEDENT_TAG = "antecedent";
    final static String NAME_OF_CONSEQUENT_TAG = "consequent";
    final static String NAME_OF_IMPLICATIONS_ID_ATTRIBUTE = "id";

    Document dataDocument;

    /**
     * This constructor prepares class for reading xml file.
     * @param fileURI
     */
    public DataCollectorFromXML(String fileURI) {
        DocumentBuilderFactory dbf;
        DocumentBuilder db;
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            dataDocument = db.parse(new File(fileURI));
        } catch (Exception ex) {
            System.out.println("ERROR: Loading of the XML document failed!!!");
        }
    }

    /**
     * This method takes all the tags named like constant "NAME_OF_FACT_TAG" and
     * puts text nodes of these tags to the array list and returns it.
     * @return ArrayList filled with the text nodes of the fact tags.
     * @throws IOException
     */
    public ArrayList collectFacts() throws IOException {
        ArrayList<String> facts = new <String>ArrayList();
        NodeList listOfFacts = dataDocument.getElementsByTagName(NAME_OF_FACT_TAG);
        for (int i = 0; i < listOfFacts.getLength(); i++) {
            facts.add(listOfFacts.item(i).getTextContent());
        }
        return facts;
    }

    /**
     * This method from XML file reads data for constructing implications.
     * One implication at the end should look like string in format:
     *  [implication.id]: [<[ancedent1], [antecedent2]...>] -> [consequent]
     * @return ArrayList of implications
     * @throws IOException
     */
    public ArrayList collectImplications() throws IOException {
        ArrayList<String> implications = new <String>ArrayList();
        String implication = "";
        NodeList listOfImplications = dataDocument.
                getElementsByTagName(NAME_OF_IMPLICATION_TAG);
        for (int i = 0; i < listOfImplications.getLength(); i++) {
            implication = getIDOfImplication(listOfImplications.item(i)) + ": ";
            implication = implication + 
                    getAntecedents(listOfImplications.item(i)) + " -> ";
            implication = implication + 
                    getConsequent(listOfImplications.item(i));
            implications.add(implication);
        }
        return implications;
    }

    /**
     * This method gets text value of the tag "goal".
     * @return String which is usually one char == 'Z'
     * @throws IOException
     */
    public String collectGoal() throws IOException {
        return dataDocument.getElementsByTagName(NAME_OF_GOAL_TAG).item(0).
                getTextContent().trim();
    }

    /**
     * Method that takes if of implication node
     * @param implication
     * @return String which is id of implication
     */
    private String getIDOfImplication(Node implication) {
        return implication.
                getAttributes().
                getNamedItem(NAME_OF_IMPLICATIONS_ID_ATTRIBUTE).
                getNodeValue();
    }

    /**
     * Method that takes all the values of the nodes that are antecedents from
     * the implication
     * @param nodeList
     * @return String in format <[antecedent1], [antecedetn2]...>
     */
    private String getAntecedents(Node implication) {
        String result = "";
        NodeList antecedents = ((Element) implication).
                getElementsByTagName(NAME_OF_ANTECEDENT_TAG);
        for (int i = 0; i < antecedents.getLength(); i++) {
            result = result + antecedents.item(i).getTextContent() + ", ";
        }
        return result.substring(0, result.length() - 2);
    }

    /**
     * Method that gets consequent from implication
     * @param implication
     * @return String which is consequent of the implication, usually one letter
     */
    private String getConsequent(Node implication) {
        return ((Element) implication).
                getElementsByTagName(NAME_OF_CONSEQUENT_TAG).
                item(0).getTextContent();
    }

}
