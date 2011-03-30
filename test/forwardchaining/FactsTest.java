/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forwardchaining;

import chaining.Facts;
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
public class FactsTest {
    Facts instance;

    public FactsTest() {
        instance = new Facts("A");
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
    public void testSearchForFact1() {
        boolean expectedResult = true;
        boolean result = instance.searchForFact("A");
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSearchForFact2() {
        boolean expectedResult = false;
        boolean result = instance.searchForFact("B");
        assertEquals(expectedResult, result);
    }

}