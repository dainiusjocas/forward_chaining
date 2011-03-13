/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forwardchaining;

import java.util.ArrayList;

/**
 * This interface describes the way that data readers should collect data.
 * @author Dainius
 */
public interface DataReader {

    String collectFacts();

    ArrayList collectProductions();

    String collectGoal();
}
