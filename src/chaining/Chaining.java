package chaining;

/**
 * This interface is to define interface for all the classes that can make a
 * chaining procedures.
 * @author Dainius Jocas
 */
public interface Chaining {
    
    /**
     * Implementation of this method should do some variation of the chaining
     * algorithm 
     * @param goal 
     * @return boolean if chaining was successful, otherwise - false
     */
    boolean doChaining(String goal);

    /**
     * Implementation of this method should return string representation of the
     * production system
     * @return String
     */
    String getProductionSystem();
}
