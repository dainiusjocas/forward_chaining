package forwardchaining;

/**
 * Data structure for storing one implication.
 * @author Dainius Jocas, VU MIF, PS#3, 3rd year
 */
public class Implication {
    private String id;
    private String antecedent;
    private String consequent;

    /**
     * This constructor expects to get a string where one string is in format:
     * [descriptor]: [<[antecedent1], [antecedent2], ..> [consequent]
     * @param Implication
     */
    public Implication(String Implication) {
        this.id = this.extractDescriptor(Implication);
        this.antecedent = this.extractAntecedent(Implication);
        this.consequent = this.extractConsequence(Implication);
    }

    /**
     * Method which extracts id part of the implication.
     * @param implication
     * @return string that represent id of implication
     */
    private String extractDescriptor(String implication) {
        return implication.split(":")[0];
    }

    /**
     * Method which extracts antecedent part of the implication.
     * @param implication
     * @return string that represent id of implication
     */
    private String extractAntecedent(String implication) {
        return implication.split("->")[0].split(":")[1].trim();
    }

    /**
     * Method which extracts consequent part of the implication.
     * @param implication
     * @return string that represent id of implication
     */
    private String extractConsequence(String implication) {
        return implication.split("->")[1].trim();
    }

    /**
     * Set of getters of the class
     * @return
     */
    public String getDescriptor() {
        return this.id;
    }
    public String getAntecedent() {
        return this.antecedent;
    }
    public String getConsequent() {
        return this.consequent;
    }

    /**
     * Method to check if we have enough facts to prove implication.
     * @param facts
     * @return
     */
    public boolean isConsequentProvable(Facts facts) {
        String[] splittedAntecedent = this.antecedent.split(",");
        boolean provable = true;
        for (String a : splittedAntecedent) {
            if (!facts.searchForFact(a.trim())) {
                provable = false;
            }
        }
        return provable;
    }

    @Override
    public String toString() {
        return this.id + ": " + this.antecedent + " -> " + this.consequent;
    }
}
