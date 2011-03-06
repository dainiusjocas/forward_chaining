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
public class Facts {
    ArrayList<String> facts;

    public Facts(String facts) {
        this.facts = new <String>ArrayList();
        this.extractFacts(facts);
    }

    private void extractFacts(String facts) {
        String[] moo = facts.split(",");
        for (String s: moo) {
            this.facts.add(s.trim());
        }
        //this.facts.addAll(Arrays.asList(moo));
    }

    public boolean searchForFact(String Z) {
        if (this.facts.contains(Z)) {
            return true;
        }
        return false;
    }

   public void addFact(String fact) {
       this.facts.add(fact);
   }

   public void printFacts() {
       System.out.println("List of facts:");
       for (String s : this.facts) {
           System.out.println(s);
       }
   }

}
