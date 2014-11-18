/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author keita shimoda
 */
public class InletTest {
    
    public InletTest() {
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
    
      // contructor test
    @Test
    public void testCreateOutlet() {
        System.out.println("Create Outlet");
        Station aStation = new StationImpl();
        Inlet instance = new Inlet(aStation);
        assertEquals(instance.getNode(), aStation);
        assertEquals(instance.IsFree(), true);
    }
    
    /**
     * Test of Set/ Get SpeedMax method, of class Inlet.
     */
    @Test
    public void testGetSetSpeedMax() {
        System.out.println("setSpeedMax");
        Station aStation = new StationImpl();
        Float speedMax = 10.0f;
        Inlet instance = new Inlet(aStation);
        instance.setSpeedMax(speedMax);
        assertEquals(instance.getSpeedMax(), speedMax);
        
    }

    
}
