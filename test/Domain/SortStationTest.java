/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
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
public class SortStationTest {
    
    public SortStationTest() {
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
     * Test of getName method, of class SortStation.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        SortStation instance = new SortStation();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class SortStation.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String _name = "";
        SortStation instance = new SortStation();
        instance.setName(_name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class SortStation.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        SortStation instance = new SortStation();
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class SortStation.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String _description = "";
        SortStation instance = new SortStation();
        instance.setDescription(_description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKgHMax method, of class SortStation.
     */
    @Test
    public void testGetKgHMax() {
        System.out.println("getKgHMax");
        SortStation instance = new SortStation();
        float expResult = 0.0F;
        float result = instance.getKgHMax();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setKgHMax method, of class SortStation.
     */
    @Test
    public void testSetKgHMax() {
        System.out.println("setKgHMax");
        float _kgHMax = 0.0F;
        SortStation instance = new SortStation();
        instance.setKgHMax(_kgHMax);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColor method, of class SortStation.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        SortStation instance = new SortStation();
        Color expResult = null;
        Color result = instance.getColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setColor method, of class SortStation.
     */
    @Test
    public void testSetColor() {
        System.out.println("setColor");
        Color _color = null;
        SortStation instance = new SortStation();
        instance.setColor(_color);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSelected method, of class SortStation.
     */
//    @Test
//    public void testIsSelected() {
//        System.out.println("isSelected");
//        SortStation instance = new SortStation();
//        Boolean expResult = null;
//        Boolean result = instance.isSelected();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setSelected method, of class SortStation.
//     */
//    @Test
//    public void testSetSelected() {
//        System.out.println("setSelected");
//        Boolean _selected = null;
//        SortStation instance = new SortStation();
//        instance.setSelected(_selected);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getImg method, of class SortStation.
     */
    @Test
    public void testGetImg() {
        System.out.println("getImg");
        SortStation instance = new SortStation();
        Image expResult = null;
        Image result = instance.getImg();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setImg method, of class SortStation.
     */
    @Test
    public void testSetImg() {
        System.out.println("setImg");
        String src = "";
        SortStation instance = new SortStation();
        instance.setImg(src);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addOutlet method, of class SortStation.
     */
    @Test
    public void testAddOutlet() {
        System.out.println("addOutlet");
        Outlet outlet = null;
        SortStation instance = new SortStation();
        instance.addOutlet(outlet);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeOulet method, of class SortStation.
     */
    @Test
    public void testRemoveOulet() {
        System.out.println("removeOulet");
        int index = 0;
        SortStation instance = new SortStation();
        instance.removeOulet(index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutletCount method, of class SortStation.
     */
    @Test
    public void testGetOutletCount() {
        System.out.println("getOutletCount");
        SortStation instance = new SortStation();
        int expResult = 0;
        int result = instance.getOutletCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortMatterBasketToOutlets method, of class SortStation.
     */
    @Test
    public void testSortMatterBasketToOutlets_success() {
        System.out.println("processMatterBasket_success");
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
        //créer une station
        SortStation station = new SortStation();
        //ajouter des outlets (3)
        Outlet o1 = new Outlet(station);
        Outlet o2 = new Outlet(station);
        Outlet o3 = new Outlet(station);
        station.addOutlet(o1);
        station.addOutlet(o2);
        station.addOutlet(o3);
        //effectuer le sort
        station.setSortMatrix(sorter);
        ///tests de tests
        System.out.println("# outlets: "+station.getOutletCount());
        System.out.println("# matters: "+mb.getNumberOfMatterInBasket());
        ////
        station.sortMatterBasket(mb);
        
        //assert que c'est le résultat attendu
        assertTrue((float)o1.getMatterBasket().getMatterQuantity(1)==(float)40);
        assertTrue((float)o1.getMatterBasket().getMatterQuantity(2)==(float)300);
        assertTrue((float)o1.getMatterBasket().getMatterQuantity(3)==(float)100);
        assertTrue((float)o2.getMatterBasket().getMatterQuantity(1)==(float)50);
        assertTrue((float)o2.getMatterBasket().getMatterQuantity(2)==(float)200);
        assertTrue((float)o2.getMatterBasket().getMatterQuantity(3)==(float)325);
        assertTrue((float)o3.getMatterBasket().getMatterQuantity(1)==(float)10);
        assertTrue((float)o3.getMatterBasket().getMatterQuantity(2)==(float)500);
        assertTrue((float)o3.getMatterBasket().getMatterQuantity(3)==(float)75);
    }
    
    /**
     * Test of sortMatterBasketToOutlets method, of class SortStation.
     * test fails : number of outlets in matrix and station is not the same
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSortMatterBasketToOutlets_failure_notRightNumberOfOutlets() {
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
        //créer une station
        SortStation station = new SortStation();
        //ajouter des outlets (3)
        Outlet o1 = new Outlet(station);
        Outlet o2 = new Outlet(station);
        station.addOutlet(o1);
        station.addOutlet(o2);
        //effectuer le sort
        station.setSortMatrix(sorter);
        station.sortMatterBasket(mb);
    }
    
     /**
     * Test of sortMatterBasketToOutlets method, of class SortStation.
     * test fails : number of outlets in matrix and station is not the same
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSortMatterBasketToOutlets_failure_notRightNumberOfMatter() {
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
        //on ajoute juste 2 matières à la matrice même s'il y en a 3 dans le panier
        SortMatrix sorter = new SortMatrix();
        sorter.setSortMatrix(smatrix);
        //créer une station
        SortStation station = new SortStation();
        //ajouter des outlets (3)
        Outlet o1 = new Outlet(station);
        Outlet o2 = new Outlet(station);
        station.addOutlet(o1);
        station.addOutlet(o2);
        //effectuer le sort
        station.setSortMatrix(sorter);
        station.sortMatterBasket(mb);
    }

    /**
     * Test of setExit method, of class SortStation.
     */
    @Test
    public void testSetExit() {
        System.out.println("setExit");
        int nbExit = 0;
        SortStation instance = new SortStation();
        instance.setExit(nbExit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutletList method, of class SortStation.
     */
    @Test
    public void testGetOutletList() {
        System.out.println("getOutletList");
        SortStation instance = new SortStation();
        ArrayList<Outlet> expResult = null;
        ArrayList<Outlet> result = instance.getOutletList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
