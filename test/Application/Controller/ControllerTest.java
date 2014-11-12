/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Application.Controller;

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
public class ControllerTest {
    
    public ControllerTest() {
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
     * Test of showAboutUs method, of class Controller.
     */
    @Test
    public void testShowAboutUs() {
        System.out.println("showAboutUs");
        Controller instance = new Controller();
        instance.showAboutUs();
    }

    /**
     * Test of getSortCenterDimensions method, of class Controller.
     */
    @Test
    public void testGetSortCenterDimensions() {
        System.out.println("getSortCenterDimensions");
        Controller instance = new Controller();
        Point2D expResult = new Point2D.Float(15f, 10f);
        Point2D result = instance.getSortCenterDimensions();
        assertEquals(expResult, result);
    }
    
}
