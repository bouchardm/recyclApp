/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;

/**
 *
 * @author Simon
 */
public class TransStationTest {
    
    public TransStationTest() {
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
     * Test of setTransformMatrix method, of class TransStation.
     */
    @Test
    public void testSetTransformMatrix() {
        System.out.println("setTransformMatrix");
        TransMatrix newTransMatrix = null;
        TransStation instance = new TransStation();
        instance.setTransMatrix(newTransMatrix);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTransMatrix method, of class TransStation.
     */
    @Test
    public void testGetTransMatrix() {
        System.out.println("getTransMatrix");
        TransStation instance = new TransStation();
        TransMatrix expResult = null;
        TransMatrix result = instance.getTransMatrix();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of transformMatterBasket method, of class TransStation.
     */
    @Test
    public void processMatterBasket() {
        System.out.println("transformMatterBasket_success");
        //créer un matterBasket
        Matter mat1 = new Matter("chose1", 1);
        Matter mat2 = new Matter("chose2", 2);
        Matter mat3 = new Matter("chose3", 3);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(mat1.getID(), new Float(100));
        mb.addMatterQuantity(mat2.getID(), new Float(1000));
        mb.addMatterQuantity(mat3.getID(), new Float(500));
         //créer une matrice de tri
        HashMap<Integer, ArrayList<Float>> smatrix = new HashMap<>();
        ArrayList<Float> innerList = new ArrayList<>();
        innerList.add(new Float(0.4));
        innerList.add(new Float(0.5));
        innerList.add(new Float(0.1));
        smatrix.put(1, innerList);
        ArrayList<Float> innerList2 = new ArrayList<>();
        innerList2.add(new Float(0.3));
        innerList2.add(new Float(0.2));
        innerList2.add(new Float(0.5));
        smatrix.put(2, innerList2);
        ArrayList<Float> innerList3 = new ArrayList<>();
        innerList3.add(new Float(0.2));
        innerList3.add(new Float(0.65));
        innerList3.add(new Float(0.15));
        smatrix.put(3, innerList3);
        SortMatrix sorter = new SortMatrix();
        sorter.setSortMatrix(smatrix);
        //créer une matrice de transformation
        HashMap<Integer, Float> transformQty1 = new HashMap<>();
        transformQty1.put(1,new Float(0.95));
        transformQty1.put(2,new Float(0));
        transformQty1.put(3, new Float(0.05));
        HashMap<Integer, Float> transformQty2 = new HashMap<>();
        transformQty2.put(1, new Float(0.1));
        transformQty2.put(2, new Float(0.8));
        transformQty2.put(3, new Float(0.1));
        HashMap<Integer, Float> transformQty3 = new HashMap<>();
        transformQty3.put(1, new Float(0.01));
        transformQty3.put(2, new Float(0.02));
        transformQty3.put(3, new Float(0.97));
        HashMap<Integer, HashMap<Integer, Float>> tMatrix = new HashMap<>();
        tMatrix.put(1, transformQty1);
        tMatrix.put(2, transformQty2);
        tMatrix.put(3, transformQty3);
        TransMatrix transMatrix = new TransMatrix();
        transMatrix.setTransMatrix(tMatrix);
        TransStation tstation = new TransStation();
        tstation.setSortMatrix(sorter);
        tstation.setTransMatrix(transMatrix);
        //ajouter des outlets (3)
        Outlet o1 = new Outlet(tstation);
        Outlet o2 = new Outlet(tstation);
        Outlet o3 = new Outlet(tstation);
        tstation.addOutlet(o1);
        tstation.addOutlet(o2);
        tstation.addOutlet(o3);
        tstation.processMatterBasket(mb);
         //assert que c'est le résultat attendu
        assertTrue((float)o1.getMatterBasket().getMatterQuantity(1)==(float)80);
        assertTrue((float)o1.getMatterBasket().getMatterQuantity(2)<(float)243.05 && 
                (float)o1.getMatterBasket().getMatterQuantity(2)>(float)242.95);
        assertTrue((float)o1.getMatterBasket().getMatterQuantity(3)==(float)118);
        assertTrue((float)o2.getMatterBasket().getMatterQuantity(1)==(float)100);
        assertTrue((float)o2.getMatterBasket().getMatterQuantity(2)==(float)162);
        assertTrue((float)o2.getMatterBasket().getMatterQuantity(3)==(float)383.5);
        assertTrue((float)o3.getMatterBasket().getMatterQuantity(1)==(float)20);
        assertTrue((float)o3.getMatterBasket().getMatterQuantity(2)==(float)405);
        assertTrue((float)o3.getMatterBasket().getMatterQuantity(3)==(float)88.5);
    }
    
     /**
     * Test of transformMatterBasket method, of class TransStation.
     */
    @Test (expected = IllegalArgumentException.class)
    public void transformMatterBasket_fail_missingFromMB() {
        System.out.println("transformMatterBasket_fail_transform matter not in basket");
        //créer un matterBasket
        Matter mat1 = new Matter("chose1", 1);
        Matter mat2 = new Matter("chose2", 2);
//        Matter mat3 = new Matter("chose3", 3);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(mat1.getID(), new Float(100));
        mb.addMatterQuantity(mat2.getID(), new Float(1000));
//        mb.addMatterQuantity(mat3.getID(), new Float(500));
         //créer une matrice de tri
        HashMap<Integer, ArrayList<Float>> smatrix = new HashMap<>();
        ArrayList<Float> innerList = new ArrayList<>();
        innerList.add(new Float(0.4));
        innerList.add(new Float(0.5));
        innerList.add(new Float(0.1));
        smatrix.put(1, innerList);
        ArrayList<Float> innerList2 = new ArrayList<>();
        innerList2.add(new Float(0.3));
        innerList2.add(new Float(0.2));
        innerList2.add(new Float(0.5));
        smatrix.put(2, innerList2);
        ArrayList<Float> innerList3 = new ArrayList<>();
        innerList3.add(new Float(0.2));
        innerList3.add(new Float(0.65));
        innerList3.add(new Float(0.15));
        smatrix.put(3, innerList3);
        SortMatrix sorter = new SortMatrix();
        sorter.setSortMatrix(smatrix);
        //créer une matrice de transformation
        HashMap<Integer, Float> transformQty1 = new HashMap<>();
        transformQty1.put(1,new Float(0.95));
        transformQty1.put(2,new Float(0));
        transformQty1.put(3, new Float(0.05));
        HashMap<Integer, Float> transformQty2 = new HashMap<>();
        transformQty2.put(1, new Float(0.1));
        transformQty2.put(2, new Float(0.8));
        transformQty2.put(3, new Float(0.1));
        HashMap<Integer, Float> transformQty3 = new HashMap<>();
        transformQty3.put(1, new Float(0.01));
        transformQty3.put(2, new Float(0.02));
        transformQty3.put(3, new Float(0.97));
        HashMap<Integer, HashMap<Integer, Float>> tMatrix = new HashMap<>();
        tMatrix.put(1, transformQty1);
        tMatrix.put(2, transformQty2);
        tMatrix.put(3, transformQty3);
        TransMatrix transMatrix = new TransMatrix();
        transMatrix.setTransMatrix(tMatrix);
        TransStation tstation = new TransStation();
        tstation.setSortMatrix(sorter);
        tstation.setTransMatrix(transMatrix);
        //ajouter des outlets (3)
        Outlet o1 = new Outlet(tstation);
        Outlet o2 = new Outlet(tstation);
        Outlet o3 = new Outlet(tstation);
        tstation.addOutlet(o1);
        tstation.addOutlet(o2);
        tstation.addOutlet(o3);
        tstation.processMatterBasket(mb);
    }
    
         /**
     * Test of transformMatterBasket method, of class TransStation.
     */
    @Test (expected = IllegalArgumentException.class)
    public void transformMatterBasket_fail_missingFromMatrix() {
        System.out.println("transformMatterBasket_matter in basket not in matrix");
        //créer un matterBasket
        Matter mat1 = new Matter("chose1", 1);
        Matter mat2 = new Matter("chose2", 2);
        Matter mat3 = new Matter("chose3", 3);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(mat1.getID(), new Float(100));
        mb.addMatterQuantity(mat2.getID(), new Float(1000));
        mb.addMatterQuantity(mat3.getID(), new Float(500));
         //créer une matrice de tri
        HashMap<Integer, ArrayList<Float>> smatrix = new HashMap<>();
        ArrayList<Float> innerList = new ArrayList<>();
        innerList.add(new Float(0.4));
        innerList.add(new Float(0.5));
        innerList.add(new Float(0.1));
        smatrix.put(1, innerList);
        ArrayList<Float> innerList2 = new ArrayList<>();
        innerList2.add(new Float(0.3));
        innerList2.add(new Float(0.2));
        innerList2.add(new Float(0.5));
        smatrix.put(2, innerList2);
//        ArrayList<Float> innerList3 = new ArrayList<>();
//        innerList3.add(new Float(0.2));
//        innerList3.add(new Float(0.65));
//        innerList3.add(new Float(0.15));
//        smatrix.put(3, innerList3);
        SortMatrix sorter = new SortMatrix();
        sorter.setSortMatrix(smatrix);
        //créer une matrice de transformation
        HashMap<Integer, Float> transformQty1 = new HashMap<>();
        transformQty1.put(1,new Float(0.95));
        transformQty1.put(2,new Float(0));
        transformQty1.put(3, new Float(0.05));
        HashMap<Integer, Float> transformQty2 = new HashMap<>();
        transformQty2.put(1, new Float(0.1));
        transformQty2.put(2, new Float(0.8));
        transformQty2.put(3, new Float(0.1));
        HashMap<Integer, Float> transformQty3 = new HashMap<>();
        transformQty3.put(1, new Float(0.01));
        transformQty3.put(2, new Float(0.02));
        transformQty3.put(3, new Float(0.97));
        HashMap<Integer, HashMap<Integer, Float>> tMatrix = new HashMap<>();
        tMatrix.put(1, transformQty1);
        tMatrix.put(2, transformQty2);
        tMatrix.put(3, transformQty3);
        TransMatrix transMatrix = new TransMatrix();
        transMatrix.setTransMatrix(tMatrix);
        TransStation tstation = new TransStation();
        tstation.setSortMatrix(sorter);
        tstation.setTransMatrix(transMatrix);
        //ajouter des outlets (3)
        Outlet o1 = new Outlet(tstation);
        Outlet o2 = new Outlet(tstation);
        Outlet o3 = new Outlet(tstation);
        tstation.addOutlet(o1);
        tstation.addOutlet(o2);
        tstation.addOutlet(o3);
        tstation.processMatterBasket(mb);
    }
    
}
