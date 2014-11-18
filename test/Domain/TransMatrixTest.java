/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import java.util.HashMap;
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
public class TransMatrixTest {
    
    
    
    public TransMatrixTest() {
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
     * Test of addMatterToTransMatrix method, of class TransMatrix.
     */
    /*
    cas: succes
    */
    @Test
    public void testAddMatterToTransMatrix_Integer_success() {
        Matter mat1 = new Matter("dog", 1);
        Matter mat2 = new Matter("cat", 2);
        HashMap<Integer, Float> catTransformQuantity = new HashMap<>();
        HashMap<Integer, Float> dogTransformQuantity = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Float>> matterQuantities = new HashMap<>();
        TransMatrix tmtest = new TransMatrix();
        catTransformQuantity.put(1,new Float(.25));
        catTransformQuantity.put(2,new Float(.75));
        dogTransformQuantity.put(1,new Float(.35));
        dogTransformQuantity.put(2,new Float(.65));
        matterQuantities.put(1, dogTransformQuantity);
        matterQuantities.put(2, catTransformQuantity);
        tmtest.addMatterToTransMatrix(1);
        tmtest.addMatterToTransMatrix(2);
        assertTrue(tmtest.getMatterCount()==2);
        //GET TRANS MATRIX AND TEST ITS CONTENTS
    }

    /**
     * Test of addMatterToTransMatrix method, of class TransMatrix.
     */
    @Test
    public void testAddMatterToTransMatrix_Integer_HashMap() {
        System.out.println("addMatterToTransMatrix");
        Integer matterID = null;
        HashMap<Integer, Float> transformQuantities = null;
        TransMatrix instance = new TransMatrix();
        instance.addMatterToTransMatrix(matterID, transformQuantities);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatterCount method, of class TransMatrix.
     */
    @Test
    public void testGetMatterCount() {
        System.out.println("getMatterCount");
        TransMatrix instance = new TransMatrix();
        int expResult = 0;
        int result = instance.getMatterCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTransMatrix method, of class TransMatrix.
     */
    @Test
    public void testGetTransMatrix() {
        TransMatrix tmTest1 = new TransMatrix();
        HashMap<Integer, HashMap<Integer, Float>> matrix = tmTest1.getTransMatrix();
        assertTrue(tmTest1.getMatterCount()==matrix.size());
        HashMap<Integer, Float> quantities = new HashMap<>();
        matrix.put(1, quantities);
        assertFalse(tmTest1.getMatterCount()==matrix.size());
    }

    /**
     * Test of removeAllMatterFromTransMatrix method, of class TransMatrix.
     */
    @Test
    public void testRemoveAllMatterFromTransMatrix() {
        System.out.println("removeAllMatterFromTransMatrix");
        TransMatrix instance = new TransMatrix();
        instance.removeAllMatterFromTransMatrix();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMatterFromMatrix method, of class TransMatrix.
     */
    @Test
    public void testRemoveMatterFromMatrix() {
        System.out.println("removeMatterFromMatrix");
        TransMatrix instance = new TransMatrix();
        instance.removeMatterFromMatrix();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
