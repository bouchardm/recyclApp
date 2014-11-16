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
import org.junit.internal.runners.statements.ExpectException;

/**
 *
 * @author Dany
 */
public class IOletTest {
    
    public IOletTest() {
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
     * Test of IsFree method, of class IOlet.
     */
    @Test
    public void testIsFree() {
        System.out.println("IsFree");
        IOlet instance = null;
        boolean expResult = false;
        boolean result = instance.IsFree();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosition method, of class IOlet.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        IOlet instance = null;
        Point2D.Float expResult = null;
        Point2D.Float result = instance.getPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNode method, of class IOlet.
     */
    @Test
    public void testGetNode() {
        System.out.println("getNode");
        EntryPoint e = new EntryPoint();
        IOlet instance = new IOlet(e);
        Node expResult = e;
        Node result = instance.getNode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConveyor method, of class IOlet.
     */
    @Test
    public void testSetConveyor() {
        System.out.println("setConveyor");
        Conveyor conveyor = null;
        IOlet instance = null;
        instance.setConveyor(conveyor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConveyor method, of class IOlet.
     */
    @Test
    public void testGetConveyor() {
        System.out.println("getConveyor");
        IOlet instance = null;
        Conveyor expResult = null;
        Conveyor result = instance.getConveyor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
