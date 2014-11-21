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
        TransMatrix tmtest = new TransMatrix();
        tmtest.addMatterToTransMatrix(1);
        tmtest.addMatterToTransMatrix(2);
        assertTrue(tmtest.getMatterCount()==2);
        HashMap<Integer, HashMap<Integer, Float>> tester = tmtest.getTransMatrix();
        assertTrue(tester.containsKey(1));
        assertTrue(tester.containsKey(2));
        assertTrue(tester.get(1).containsKey(1));
        assertTrue(tester.get(1).get(1)==1);
        assertTrue(tester.get(1).containsKey(2));
        assertTrue(tester.get(1).get(2)==0);
        assertTrue(tester.get(2).containsKey(2));
        assertTrue(tester.get(2).get(2)==1);
        assertTrue(tester.get(2).containsKey(1));
        assertTrue(tester.get(2).get(1)==0);
    }

    /**
     * Test of addMatterToTransMatrix method, of class TransMatrix.
     */
    @Test
    public void testAddMatterToTransMatrix_Integer_HashMap_Success() {
        System.out.println("addMatterToTransMatrix_int_hm_success");
        Matter mat1 = new Matter("dog", 1);
        Matter mat2 = new Matter("cat", 2);
        HashMap<Integer, Float> catTransformQuantity = new HashMap<>();
        HashMap<Integer, Float> dogTransformQuantity = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Float>> matterQuantities = new HashMap<>();
        TransMatrix tmtest = new TransMatrix();
        catTransformQuantity.put(1,new Float(.25));
        catTransformQuantity.put(2,new Float(.75));
        dogTransformQuantity.put(1,new Float(.35));
        matterQuantities.put(1, dogTransformQuantity);
        matterQuantities.put(2, catTransformQuantity);
        tmtest.addMatterToTransMatrix(mat1.getID(), dogTransformQuantity);
        tmtest.addMatterToTransMatrix(mat2.getID(), catTransformQuantity);
        assertTrue(tmtest.getMatterCount()==2);
        HashMap<Integer, HashMap<Integer, Float>> tester = tmtest.getTransMatrix();
        assertTrue(tester.containsKey(1));
        assertTrue(tester.containsKey(2));
        assertTrue(tester.get(1).containsKey(1));
        assertTrue((float)tester.get(1).get(1)==(float)0.35);
        assertTrue(tester.get(1).containsKey(2));
        assertTrue(tester.get(1).get(2)==0.0);
        assertTrue(tester.get(2).containsKey(2));
        assertTrue(tester.get(2).get(2)==0.75);
        assertTrue(tester.get(2).containsKey(1));
        assertTrue(tester.get(2).get(1)==0.25);
    }

    /**
     * Test of getMatterCount method, of class TransMatrix.
     * Empty matrix
     */
    @Test
    public void testGetMatterCount_empty() {
        TransMatrix tmtest = new TransMatrix();
        assertTrue(tmtest.getMatterCount()==0);
    }
    
    /**
     * Test of getMatterCount method, of class TransMatrix.
     * With elements
     */
    @Test
    public void testGetMatterCount_wElements() {
        TransMatrix tmtest = new TransMatrix();
        Matter mat1 = new Matter("dog", 1);
        Matter mat2 = new Matter("cat", 2);
        HashMap<Integer, Float> catTransformQuantity = new HashMap<>();
        HashMap<Integer, Float> dogTransformQuantity = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Float>> matterQuantities = new HashMap<>();
        catTransformQuantity.put(1,new Float(.25));
        catTransformQuantity.put(2,new Float(.75));
        dogTransformQuantity.put(1,new Float(.35));
        dogTransformQuantity.put(2, new Float(.65));
        matterQuantities.put(1, dogTransformQuantity);
        matterQuantities.put(2, catTransformQuantity);
        assertTrue(tmtest.getMatterCount()==0);
        tmtest.setTransMatrix(matterQuantities);
        assertTrue(tmtest.getMatterCount()==2);
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
     * Test of setTransMatrix method, of class TransMatrix.
     */
    @Test
    public void testSetTransMatrix() {
        System.out.println("setTransMatrix_success");
        //on crée une transMatrix vide et on la remplace par une pleine après
        TransMatrix tmtest = new TransMatrix();
        Matter mat1 = new Matter("dog", 1);
        Matter mat2 = new Matter("cat", 2);
        HashMap<Integer, Float> catTransformQuantity = new HashMap<>();
        HashMap<Integer, Float> dogTransformQuantity = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Float>> matterQuantities = new HashMap<>();
        catTransformQuantity.put(1,new Float(.25));
        catTransformQuantity.put(2,new Float(.75));
        dogTransformQuantity.put(1,new Float(.35));
        dogTransformQuantity.put(2, new Float(.65));
        matterQuantities.put(1, dogTransformQuantity);
        matterQuantities.put(2, catTransformQuantity);
        assertTrue(tmtest.getMatterCount()==0);
        tmtest.setTransMatrix(matterQuantities);
        assertTrue(tmtest.getMatterCount()==2);
        HashMap<Integer, HashMap<Integer, Float>> tester = tmtest.getTransMatrix();
        assertTrue(tester.containsKey(1));
        assertTrue(tester.containsKey(2));
        assertTrue(tester.get(1).containsKey(1));
        assertTrue((float)tester.get(1).get(1)==(float)0.35);
        assertTrue(tester.get(1).containsKey(2));
        assertTrue((float)tester.get(1).get(2)==(float)0.65);
        assertTrue(tester.get(2).containsKey(2));
        assertTrue(tester.get(2).get(2)==0.75);
        assertTrue(tester.get(2).containsKey(1));
        assertTrue(tester.get(2).get(1)==0.25);
    }

    /**
     * Test of removeAllMatterFromTransMatrix method, of class TransMatrix.
     */
    @Test
    public void testRemoveAllMatterFromTransMatrix() {
        TransMatrix tmtest = new TransMatrix();
        Matter mat1 = new Matter("dog", 1);
        Matter mat2 = new Matter("cat", 2);
        HashMap<Integer, Float> catTransformQuantity = new HashMap<>();
        HashMap<Integer, Float> dogTransformQuantity = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Float>> matterQuantities = new HashMap<>();
        catTransformQuantity.put(1,new Float(.25));
        catTransformQuantity.put(2,new Float(.75));
        dogTransformQuantity.put(1,new Float(.35));
        dogTransformQuantity.put(2, new Float(.65));
        matterQuantities.put(1, dogTransformQuantity);
        matterQuantities.put(2, catTransformQuantity);
        tmtest.setTransMatrix(matterQuantities);
        assertTrue(tmtest.getMatterCount()==2);
        tmtest.removeAllMatterFromTransMatrix();
        assertTrue(tmtest.getMatterCount()==0);
    }
    
    /**
     * Test of removeAllMatterFromTransMatrix method, of class TransMatrix. Case: empty matrix
     */
    @Test
    public void testRemoveAllMatterFromTransMatrix_emptyMatrix() {
        TransMatrix tmtest = new TransMatrix();
        tmtest.removeAllMatterFromTransMatrix();
        assertTrue(tmtest.getMatterCount()==0);
    }


    /**
     * Test of removeMatterFromMatrix method, of class TransMatrix.
     * with elements
     */
    @Test
    public void testRemoveMatterFromMatrix_wElements() {
        TransMatrix tmtest = new TransMatrix();
        Matter mat1 = new Matter("dog", 1);
        Matter mat2 = new Matter("cat", 2);
        HashMap<Integer, Float> catTransformQuantity = new HashMap<>();
        HashMap<Integer, Float> dogTransformQuantity = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Float>> matterQuantities = new HashMap<>();
        catTransformQuantity.put(1,new Float(.25));
        catTransformQuantity.put(2,new Float(.75));
        dogTransformQuantity.put(1,new Float(.35));
        dogTransformQuantity.put(2, new Float(.65));
        matterQuantities.put(1, dogTransformQuantity);
        matterQuantities.put(2, catTransformQuantity);
        tmtest.setTransMatrix(matterQuantities);
        assertTrue(tmtest.getMatterCount()==2);
        tmtest.removeMatterFromMatrix(1);
        HashMap<Integer, HashMap<Integer, Float>> tester = tmtest.getTransMatrix();
        assertTrue(tmtest.getMatterCount()==1);
        assertFalse(tester.containsKey(1));
        assertFalse(tester.get(2).containsKey(1));
    }
    
    /**
     * Test of removeMatterFromMatrix method, of class TransMatrix.
     * matter not in matrix
     */
    @Test
    public void testRemoveMatterFromMatrix_notInMatrix() {
        TransMatrix tmtest = new TransMatrix();
        Matter mat1 = new Matter("dog", 1);
        Matter mat2 = new Matter("cat", 2);
        HashMap<Integer, Float> catTransformQuantity = new HashMap<>();
        HashMap<Integer, Float> dogTransformQuantity = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Float>> matterQuantities = new HashMap<>();
        catTransformQuantity.put(1,new Float(.25));
        catTransformQuantity.put(2,new Float(.75));
        dogTransformQuantity.put(1,new Float(.35));
        dogTransformQuantity.put(2, new Float(.65));
        matterQuantities.put(1, dogTransformQuantity);
        matterQuantities.put(2, catTransformQuantity);
        tmtest.setTransMatrix(matterQuantities);
        assertTrue(tmtest.getMatterCount()==2);
        tmtest.removeMatterFromMatrix(3);
        assertTrue(tmtest.getMatterCount()==2);
    }
    
    /**
     * Test of removeMatterFromMatrix method, of class TransMatrix.
     * empty matrix
     */
    @Test
    public void testRemoveMatterFromMatrix_emptyMatrix() {
        TransMatrix tmtest = new TransMatrix();
        tmtest.removeMatterFromMatrix(1);
        assertTrue(tmtest.getMatterCount()==0);
    }
}
