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
 * @author Dany
 */
public class ProjectTest {
    
    public ProjectTest() {
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
     * Test of newProject method, of class Project.
     */
    @Test
    public void testNewProject() {
        System.out.println("newProject");
        Project instance = new Project();
        instance.newProject();
        SortCenter sc1 = instance.getSortCenter();
        instance.newProject();
        assertNotSame(sc1, instance.getSortCenter());
    }

    /**
     * Test of loadProject method, of class Project.
     */
    @Test
    public void testLoadProject() {
        System.out.println("loadProject");
        String path = "";
        Project instance = new Project();
        instance.loadProject(path);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveProject method, of class Project.
     */
    @Test
    public void testSaveProject() {
        System.out.println("saveProject");
        Project instance = new Project();
        instance.saveProject();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveAsProject method, of class Project.
     */
    @Test
    public void testSaveAsProject() {
        System.out.println("saveAsProject");
        String path = "";
        String filename = "";
        Project instance = new Project();
        instance.saveAsProject(path, filename);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exportImage method, of class Project.
     */
    @Test
    public void testExportImage() {
        System.out.println("exportImage");
        String path = "";
        Project instance = new Project();
        instance.exportImage(path);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUnsaved method, of class Project.
     */
    @Test
    public void testSetUnsaved() {
        System.out.println("setUnsaved");
        Project instance = new Project();
        instance.setUnsaved();
        assertFalse(instance.isSaved());
    }
    
}
