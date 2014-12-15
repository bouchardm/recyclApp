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

    // contructor test
    @Test
    public void testCreateOutlet() {
        System.out.println("Create Outlet");
        Station aStation = new StationImpl();
        Outlet instance = new Outlet(aStation);
        assertEquals(instance.getNode(), aStation);
        assertEquals(instance.IsFree(), true);
    }

        // Test get/set matterbasket
    @Test
    public void testGetSetMatterBasket() {
        System.out.println("getMatterBasket");
        Station aStation = new StationImpl();
        Outlet instance = new Outlet(aStation);
        MatterBasket matterBasket = new MatterBasket();
        instance.setMatterBasketQty(matterBasket);
        assertEquals(matterBasket, instance.getMatterBasketQty());
    
    }
}
