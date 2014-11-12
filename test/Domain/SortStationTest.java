/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import java.awt.geom.Point2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dany
 */
public class SortStationTest {
    
    public SortStationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPosition method, of class SortStation.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        SortStation instance = new SortStation();
        Point2D.Float expResult = new Point2D.Float(0, 0);
        Point2D.Float result = instance.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPosition method, of class SortStation.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        float x = 0.0F;
        float y = 0.0F;
        SortStation instance = new SortStation();
        instance.setPosition(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of include method, of class SortStation.
     */
    @Test
    public void testInclude() {
        System.out.println("include");
        Point2D.Float point = null;
        SortStation instance = new SortStation();
        boolean expResult = false;
        boolean result = instance.include(point);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
