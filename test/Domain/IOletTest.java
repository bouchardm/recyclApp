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
    
        public class StationImpl extends Station {
    }

    /**
     * Test of IsFree method, of class IOlet.
     */
    @Test
    public void testIsFree() {

        System.out.println("IsFree");
        Station aStation = new StationImpl();
        IOlet instance = new IOlet(aStation);
        boolean expResult = true;
        boolean result = instance.IsFree();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPosition method, of class IOlet.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Station aStation = new StationImpl();
        IOlet instance = new IOlet(aStation);
        Point2D.Float expResult = new Point2D.Float(0.5f, 0.5f);
        Point2D.Float result = instance.getPosition();
        assertEquals(expResult, result);
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
     * Test of Get/Set Conveyor method, of class IOlet.
     */
    @Test
    public void testGetSetConveyor() {
        System.out.println("Get/Set Conveyor");
        Station station1 = new StationImpl();
        Station station2 = new StationImpl();
     //   station1.
  

    }


}
