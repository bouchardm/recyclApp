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
public class ExitPointTest {

    public ExitPointTest() {
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
     * Test of Get/Set Inlet method, of class ExitPoint.
     */
    @Test
    public void testGetSInlet() {
        System.out.println("Get/Set inlet of ExitPoint");
        Station aStation = new StationImpl();
        Inlet inlet = new Inlet(aStation);
        ExitPoint instance = new ExitPoint();
        instance.setInlet(inlet);
        assertEquals(instance.getInlet(), inlet);

    }

}
