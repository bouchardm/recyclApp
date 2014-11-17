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


public class OutletTest {
    
    public OutletTest() {
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
     * Test of getMatterBasket method, of class Outlet.
     */
//    @Test
//    public void testGetMatterBasket() {
//        System.out.println("getMatterBasket");
//        Outlet instance = null;
//        MatterBasket expResult = null;
//        MatterBasket result = instance.getMatterBasket();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
        
    // contructor test
    @Test
    public void createOutletTest()
    {
    System.out.println("createOutletTest");
    Station aStation = new StationImpl();
    Outlet instance = new Outlet(aStation);
    assertEquals( instance.getNode(), aStation);
    assertEquals( instance.IsFree(), true);
        
    }


    
    
}
