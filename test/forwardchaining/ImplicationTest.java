/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forwardchaining;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dj
 */
public class ImplicationTest {

    private Implication instance;
    public ImplicationTest() {
        instance = new Implication("R1: A -> A");
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetDescriptor1() {
        String implication = "R1: A -> A";
        String expectedResult = "R1";
        String result = this.instance.getDescriptor();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDescriptor2() {
        String implication = "R12: A, B, C, D, E -> Z";
        instance = new Implication(implication);
        String expectedResult = "R12";
        String result = this.instance.getDescriptor();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAntecedent1() {
        String implication = "R1: A -> A";
        String expectedResult = "A";
        String result = this.instance.getAntecedent();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAntecedent2() {
        String implication = "R12: A, B, C, D, E -> Z";
        instance = new Implication(implication);
        String expectedResult = "A, B, C, D, E";
        String result = this.instance.getAntecedent();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetConsequence1() {
        String implication = "R1: A -> A";
        String expectedResult = "A";
        String result = this.instance.getConsequence();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetConsequence2() {
        String implication = "R12: A, B, C, D, E -> Z";
        instance = new Implication(implication);
        String expectedResult = "Z";
        String result = this.instance.getConsequence();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsConsequenceProvable1() {
        String implication = "R1: A -> B";
        instance = new Implication(implication);
        Facts facts = new Facts("A, B, C");
        boolean expectedResult = true;
        boolean result = instance.isConsequenceProvable(facts);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsConsequenceProvable2() {
        String implication = "R1: A -> B";
        instance = new Implication(implication);
        Facts facts = new Facts("A");
        boolean expectedResult = true;
        boolean result = instance.isConsequenceProvable(facts);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsConsequenceProvable3() {
        String implication = "R1: A -> B";
        instance = new Implication(implication);
        Facts facts = new Facts("B");
        boolean expectedResult = false;
        boolean result = instance.isConsequenceProvable(facts);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsConsequenceProvable4() {
        String implication = "R1: A, B -> C";
        instance = new Implication(implication);
        Facts facts = new Facts("A, B");
        boolean expectedResult = true;
        boolean result = instance.isConsequenceProvable(facts);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsConsequenceProvable5() {
        String implication = "R1: A, B -> C";
        instance = new Implication(implication);
        Facts facts = new Facts("A, C");
        boolean expectedResult = false;
        boolean result = instance.isConsequenceProvable(facts);
        assertEquals(expectedResult, result);
    }
}