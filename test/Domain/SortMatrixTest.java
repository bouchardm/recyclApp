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
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Simon
 */
public class SortMatrixTest {
    
    public SortMatrixTest() {
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

    //constructeur sans paramètres
    @Test
    public void SortMatrixConstructor_Success() {
        SortMatrix sm = new SortMatrix();
        assertTrue(sm.getMatterCount()==0);
    }
    
    //constructeur avec paramètres (list matières et liste outlet)
    @Test
    public void SortMatrixConstructor_wParam_Success() {
        Matter tm1 = new Matter("matter1", 1);
        Matter tm2 = new Matter("matter2", 2);
        MatterList tmlist = new MatterList();
        tmlist.Add(tm1);
        tmlist.Add(tm2);
        SortStation st = new SortStation();
        Outlet o1 = new Outlet(st);
        Outlet o2 = new Outlet(st);
        ArrayList<Outlet> outletList = new ArrayList<>();
        outletList.add(o1);
        outletList.add(o2);
        SortMatrix sorter = new SortMatrix(tmlist, outletList);
        //la matrice contient 2 éléments
        assertTrue(sorter.getMatterCount()==2);
        assertTrue(sorter.getSortMatrix().containsKey(1));
        //la quantité à la première sortie est 1 (100%)
        assertTrue(sorter.getSortMatrix().get(1).get(0)==1);
        //la quantité à la deuxième sortie est 0 (0%)
        assertTrue(sorter.getSortMatrix().get(1).get(1)==0);
    }
    
    
    
    
    //addMatterToSortMatrix (matterId et liste)
    //public void addMatterToSortMatrix(Integer matterID, ArrayList<Float> matterPerOutlet)
    @Test
    public void addMatterToSortMatrixTest_withList_success() {
        SortMatrix sm = new SortMatrix();
        Matter m1 = new Matter("m1",1);
        ArrayList<Float> mpo = new ArrayList<>();
        Float q1;
        q1 = new Float (0.25);
        Float q2;
        q2 =new Float(0.75);
        mpo.add(q1);
        mpo.add(q2);
        sm.addMatterToSortMatrix(m1.getID(), mpo);
        assertTrue(sm.getMatterCount()==1);
        assertTrue(sm.getSortMatrix().containsKey(1));
        assertFalse(sm.getSortMatrix().containsKey(22));  
        assertTrue(sm.getSortMatrix().get(1).get(1).floatValue()==0.75);
    }
    
    //addMatterToSortMatrix (matterId)
    @Test
    public void addMatterToSortMatrix_noList_success() {
        SortMatrix sm = new SortMatrix();
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        sm.addMatterToSortMatrix(m1.getID());
        sm.addMatterToSortMatrix(m2.getID());
        assertTrue(sm.getMatterCount()==2);
        assertTrue(sm.getSortMatrix().containsKey(m1.getID()));
        assertTrue(sm.getSortMatrix().get(m2.getID()).get(0)==1);
    }
    
    //removeMatterFromMatrix
    //cas: succes
    @Test
    public void removeMatterFromMatrixTest_success() {
        SortMatrix sm = new SortMatrix();
        Matter m1 = new Matter("m1",1);
        sm.addMatterToSortMatrix(m1.getID());
        assertTrue(sm.getMatterCount()==1);
        sm.removeMatterFromMatrix(m1.getID());
        assertTrue(sm.getMatterCount()==0);
    }
    //cas: la matière n'est pas dans la liste
    @Test(expected = IllegalArgumentException.class)
    public void removeMatterFromMatrixTest_fail_notInMatrix() {
        SortMatrix sm = new SortMatrix();
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        sm.addMatterToSortMatrix(m2.getID());
        sm.removeMatterFromMatrix(m1.getID());  
    }
    //cas: la liste est vide
    @Test (expected = IllegalArgumentException.class)
    public void removeMatterFromMatrixTest_fail_emptyMatrix() {
        SortMatrix sm = new SortMatrix();
        Matter m1 = new Matter("m1",1);
        sm.removeMatterFromMatrix(m1.getID());
    }
    
    //removeAllMatterFromSortMatrix
    //cas: succes, avec matières dans la liste
    @Test
    public void removeAllMatterFromSortMatrixTest_success() {
        SortMatrix sm = new SortMatrix();
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        sm.addMatterToSortMatrix(m1.getID());
        sm.addMatterToSortMatrix(m2.getID());
        sm.removeAllMatterFromSortMatrix();
        assertTrue(sm.getMatterCount()==0);
    }
    //cas: succes, aucune matière dans la liste
    @Test
    public void removeAllMatterFromSortMatrixTest_success_emptyMatrix() {
        SortMatrix sm = new SortMatrix();
        sm.removeAllMatterFromSortMatrix();
        assertTrue(sm.getMatterCount()==0);
    }
    
    //getSortMatrix
    @Test
    public void getSortMatrixTest_deepCopy_success() {
        SortMatrix sm = new SortMatrix();
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        sm.addMatterToSortMatrix(m1.getID());
        sm.addMatterToSortMatrix(m2.getID());
        HashMap<Integer, ArrayList<Float>> sm2 = sm.getSortMatrix();
        assertTrue(sm2.size()==sm.getMatterCount());
        sm2.remove(1);
        //en changeant la copie, il y a maintenant différence entre les deux matrices
        assertFalse(sm2.size()==sm.getMatterCount());
    }
    
    //setSortMatrix - succes
    @Test
    public void setSortMatrix() {
        SortMatrix smtest = new SortMatrix();
        assertTrue(smtest.getMatterCount()==0);
        //créer un matterBasket
        HashMap<Integer, ArrayList<Float>> mb = new HashMap<>();
        ArrayList<Float> innerList = new ArrayList<>();
        innerList.add(new Float(0.4));
        innerList.add(new Float(0.5));
        innerList.add(new Float(0.1));
        mb.put(1, innerList);
        ArrayList<Float> innerList2 = new ArrayList<>();
        innerList2.add(new Float(0.3));
        innerList2.add(new Float(0.2));
        innerList2.add(new Float(0.5));
        mb.put(2, innerList2);
        smtest.setSortMatrix(mb);
        HashMap<Integer, ArrayList<Float>> tester = smtest.getSortMatrix();
        assertTrue(tester.containsKey(1) && tester.containsKey(2));
        assertTrue((float)tester.get(1).get(2)==(float)0.1);
        assertTrue((float)tester.get(2).get(0)==(float)0.3);
        
    }
    
    
    
    //removeOutletFromMatrix
    //cas : succes
    @Test
    public void removeOutletFromMatrix_success() {
        //todo
    }
    //cas : hashmap vide
    //todo
    
    
    
    
    //addOutletToSortMatrix
    //todo
}
