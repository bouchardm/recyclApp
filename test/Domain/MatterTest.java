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
 * @author Simon
 */
public class MatterTest {
    
    public MatterTest() {
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

    /*
    Test getName de matter
    */
    @Test
    public void getNameTest() {
        System.out.println("getName");
        Matter testMatter = new Matter("tomate", 1);
        assertEquals("tomate", testMatter.getName());
    }
    
    /*
    Test getID de matter
    */
    @Test
    public void getIDTest() {
        System.out.println("getID");
        Matter testMatter = new Matter("autreMatiere", 3);
        assertTrue(testMatter.getID()==3);
    }
}
