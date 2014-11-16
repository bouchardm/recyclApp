/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Swing;

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
public class GridTest {
    
    public GridTest() {
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
     * Test of snap method, of class Grid.
     */
    @Test
    public void testSnap() {
        System.out.println("snap");
        Point2D.Float point = new Point2D.Float(1.2f, 2.4f);
        Grid instance = new Grid();
        Point2D.Float expResult = new Point2D.Float(1, 2);
        assertEquals(expResult, instance.snap(point));
        instance.setDimensions(new Point2D.Float(0.5f, 0.5f));
        expResult = new Point2D.Float(1, 2.5f);
        assertEquals(expResult, instance.snap(point));
    }

    /**
     * Test of getDimensions method, of class Grid.
     */
    @Test
    public void testGetDimensions() {
        System.out.println("getDimensions");
        Grid instance = new Grid();
        Point2D.Float expResult = new Point2D.Float(1, 1);
        Point2D.Float result = instance.getDimensions();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDimensions method, of class Grid.
     */
    @Test
    public void testSetDimensions() {
        System.out.println("setDimensions");
        Point2D.Float dimensions = new Point2D.Float(0.3f, 0.7f);
        Grid instance = new Grid();
        instance.setDimensions(dimensions);
        Point2D.Float result = instance.getDimensions();
        assertEquals(dimensions, result);
    }

    /**
     * Test of getOffset method, of class Grid.
     */
    @Test
    public void testGetOffset() {
        System.out.println("getOffset");
        Grid instance = new Grid();
        Point2D.Float expResult = new Point2D.Float(0, 0);
        Point2D.Float result = instance.getOffset();
        assertEquals(expResult, result);
    }
    
}
