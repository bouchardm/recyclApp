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
public class ConveyorTest {
    
    public ConveyorTest() {
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
     * Test of include method, of class Conveyor.
     */
    @Test
    public void testInclude() {
        System.out.println("include");
        EntryPoint e1 = new EntryPoint();
        ExitPoint e2 = new ExitPoint();
        e2.setPosition(1, 0);
        Conveyor instance = new Conveyor(e1.getOutlet(), e2.getInlet());
        assertTrue(instance.include(new Point2D.Float(0.5f, 0)));
        assertTrue(instance.include(new Point2D.Float(0.5f, 0.1f)));
        assertTrue(instance.include(new Point2D.Float(0.5f, -0.1f)));
        assertTrue(instance.include(new Point2D.Float(1.01f, 0)));
        assertTrue(instance.include(new Point2D.Float(-0.01f, 0)));
        assertFalse(instance.include(new Point2D.Float(0.5f, 0.11f)));
        assertFalse(instance.include(new Point2D.Float(0.5f, -0.11f)));
        assertFalse(instance.include(new Point2D.Float(1.11f, 0)));
        assertFalse(instance.include(new Point2D.Float(-0.11f, 0)));
    }

    /**
     * Test of getStartNode method, of class Conveyor.
     */
    @Test
    public void testGetStartNode() {
        System.out.println("getStartNode");
        EntryPoint e1 = new EntryPoint();
        ExitPoint e2 = new ExitPoint();
        Outlet o = e1.getOutlet();
        Inlet i = e2.getInlet();
        Conveyor instance = new Conveyor(o, i);
        Node expResult = (Node)e1;
        Node result = instance.getStartNode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndNode method, of class Conveyor.
     */
    @Test
    public void testGetEndNode() {
        System.out.println("getEndNode");
        EntryPoint e1 = new EntryPoint();
        ExitPoint e2 = new ExitPoint();
        Outlet o = e1.getOutlet();
        Inlet i = e2.getInlet();
        Conveyor instance = new Conveyor(o, i);
        Node expResult = (Node)e2;
        Node result = instance.getEndNode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMatterBasket method, of class Conveyor.
     */
    @Test
    public void testGetMatterBasket() {
        System.out.println("getMatterBasket");
        Conveyor instance = null;
        MatterBasket expResult = null;
        MatterBasket result = instance.getMatterBasket();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of getPoint1 method, of class Conveyor.
     */
    @Test
    public void testGetPoint1() {
        System.out.println("getPoint1");
        EntryPoint e1 = new EntryPoint();
        e1.setPosition(0.3f, 6.4f);
        ExitPoint e2 = new ExitPoint();
        Conveyor instance = new Conveyor(e1.getOutlet(), e2.getInlet());
        Point2D.Float expResult = new Point2D.Float(0.3f, 6.4f);
        Point2D.Float result = instance.getPoint1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPoint2 method, of class Conveyor.
     */
    @Test
    public void testGetPoint2() {
        System.out.println("getPoint2");
        EntryPoint e1 = new EntryPoint();
        ExitPoint e2 = new ExitPoint();
        e2.setPosition(0.3f, 6.4f);
        Conveyor instance = new Conveyor(e1.getOutlet(), e2.getInlet());
        Point2D.Float expResult = new Point2D.Float(0.3f, 6.4f);
        Point2D.Float result = instance.getPoint2();
        assertEquals(expResult, result);
    }
    
}
