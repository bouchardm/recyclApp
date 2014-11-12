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
public class StationTest {
    
    public StationTest() {
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
     * Test of setPosition method, of class Station.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        Station instance = new StationImpl();
        float x = 1.0F;
        float y = 2.0F;
        instance.setPosition(x, y);
        Point2D.Float expResult = new Point2D.Float(1, 2);
        Point2D.Float result = instance.getPosition();
        assertEquals(result, expResult);
    }

    /**
     * Test of getPosition method, of class Station.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Station instance = new StationImpl();
        Point2D.Float expResult = new Point2D.Float(0, 0);
        Point2D.Float result = instance.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of include method, of class Station.
     */
    @Test
    public void testInclude() {
        System.out.println("include");
        Station instance = new StationImpl();
        Point2D.Float point = new Point2D.Float(0, 0);
        assertTrue(instance.include(point));
        point.x = 0.5f;
        point.y = 0.5f;
        assertTrue(instance.include(point));
        point.x = -0.5f;
        point.y = 0.5f;
        assertFalse(instance.include(point));
        point.x = 0.5f;
        point.y = -0.5f;
        assertFalse(instance.include(point));
        point.x = 1.5f;
        point.y = 0.5f;
        assertFalse(instance.include(point));
        point.x = 0.5f;
        point.y = 1.5f;
        assertFalse(instance.include(point));
    }

    public class StationImpl extends Station {
    }
    
}
