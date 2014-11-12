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
public class JunctionTest {
    
    public JunctionTest() {
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
     * Test of getPosition method, of class Junction.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Junction instance = new Junction();
        Point2D.Float expResult = new Point2D.Float(0, 0);
        Point2D.Float result = instance.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPosition method, of class Junction.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        float x = 0.0F;
        float y = 0.0F;
        Point2D.Float expResult = new Point2D.Float(x, y);
        Point2D.Float result;
        Junction instance = new Junction();
        instance.setPosition(x, y);
        result = instance.getPosition();
        assertEquals(expResult, result);
        expResult.x = x = 1.0f;
        expResult.y = y = 2.0f;
        instance.setPosition(x, y);
        result = instance.getPosition();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of include method, of class Junction.
     */
    @Test
    public void testInclude() {
        System.out.println("include");
        Junction instance = new Junction();
        assertTrue(instance.include(new Point2D.Float(0, 0)));
        assertTrue(instance.include(new Point2D.Float(0, 0.3f)));
        assertTrue(instance.include(new Point2D.Float(0, 0.3f)));
        assertTrue(instance.include(new Point2D.Float(0.3f, 0)));
        assertTrue(instance.include(new Point2D.Float(-0.3f, 0)));
        assertTrue(instance.include(new Point2D.Float(0.2f, 0.2f)));
        assertFalse(instance.include(new Point2D.Float(0.4f, 0)));
        assertFalse(instance.include(new Point2D.Float(-0.4f, 0)));
        assertFalse(instance.include(new Point2D.Float(0, 0.4f)));
        assertFalse(instance.include(new Point2D.Float(0, -0.4f)));
        assertFalse(instance.include(new Point2D.Float(0.25f, 0.25f)));
    }
    
}
