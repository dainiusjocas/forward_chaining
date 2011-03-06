/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forwardchaining;

/**
 * Data structure for storing implication.
 * @author dj
 */
public class Implication {
    private String descriptor;
    private String antecedent;
    private String consequence;


    public Implication(String Implication) {
        this.descriptor = this.extractDescriptor(Implication);
        this.antecedent = this.extractAntecedent(Implication);
        this.consequence = this.extractConsequence(Implication);
    }

    /**
     * Method which extracts descriptor part of the implication.
     * @param implication
     * @return string that represent descriptor of implication
     */
    private String extractDescriptor(String implication) {
        return implication.split(":")[0];
    }

    /**
     * Method which extracts antecedent part of the implication.
     * @param implication
     * @return string that represent descriptor of implication
     */
    private String extractAntecedent(String implication) {
        return implication.split("->")[0].split(":")[1].trim();
    }

    /**
     * Method which extracts consequence part of the implication.
     * @param implication
     * @return string that represent descriptor of implication
     */
    private String extractConsequence(String implication) {
        return implication.split("->")[1].trim();
    }

    /**
     * Set of getters of the class
     * @return
     */
    public String getDescriptor() {
        return this.descriptor;
    }
    public String getAntecedent() {
        return this.antecedent;
    }
    public String getConsequence() {
        return this.consequence;
    }

    @Override
    public String toString() {
        return this.descriptor + ": " + this.antecedent + " -> " + this.consequence;
    }

    /**
     * Method to check if we have enough facts to prove implication.
     * @param facts
     * @return
     */
    public boolean isConsequenceProvable(Facts facts) {
        String[] splittedAntecedent = this.antecedent.split(",");
        boolean provable = true;
        for (String a : splittedAntecedent) {
            if (!facts.searchForFact(a.trim())) {
                provable = false;
            }
        }
        return provable;
    }
}
