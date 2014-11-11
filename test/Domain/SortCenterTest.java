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
public class SortCenterTest {
    
    public SortCenterTest() {
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
     * Test of getDimensions method, of class SortCenter.
     */
    @Test
    public void testGetDimensions() {
        System.out.println("getDimensions");
        SortCenter instance = new SortCenter();
        Point2D.Float expResult = new Point2D.Float(10f, 10f);
        Point2D.Float result = instance.getDimensions();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDimensions method, of class SortCenter.
     */
    @Test
    public void testSetDimensions() {
        System.out.println("setDimensions");
        Float x = 20f;
        Float y = 30f;
        Point2D.Float dimensions = new Point2D.Float(x, y);
        SortCenter instance = new SortCenter();
        instance.setDimensions(x, y);
        assertEquals(instance.getDimensions(), dimensions);
    }
    
}
