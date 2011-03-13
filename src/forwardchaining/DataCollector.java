package forwardchaining;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This interface describes the way that data readers should collect data.
 * @author Dainius Jocas, VU MIF, PS#3, 3rd years
 */
public interface DataCollector {

    String collectFacts() throws IOException;

    ArrayList collectImplications() throws IOException;

    String collectGoal() throws IOException;
}
