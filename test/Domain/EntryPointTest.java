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
public class EntryPointTest {
    
    public EntryPointTest() {
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
     * Test of Get/Set MatterBasket method, of class EntryPoint.
     */
    @Test
    public void testGetSetMatterBasket() {
        System.out.println("Get/Set MatterBasket");
        MatterBasket matterBasket = new MatterBasket();
        EntryPoint instance = new EntryPoint();
        instance.setMatterBasket(matterBasket);
        assertEquals(matterBasket, instance.getMatterBasket());
    

    }

    /**
     * Test of Get/Set Outlet method, of class EntryPoint.
     */
    @Test
    public void testGetOutlet() {
        System.out.println("Get/Set Outlet");
        EntryPoint instance = new EntryPoint();
        Station aStation = new StationImpl();
        Outlet outlet = new Outlet(aStation);
        instance.setOutlet(outlet);
        assertEquals(instance.getOutlet(), outlet);

    }

  
    
}
