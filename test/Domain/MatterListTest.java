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
public class MatterListTest {
    
    public MatterListTest() {
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
    Test GetCount
    */
    @Test
    public void GetCountTest() {
        System.out.println("GetCount");
        Matter testMatter = new Matter("tomate", 1);
        Matter testMatter2 = new Matter("patate", 2);
        MatterList testList = new MatterList();
        testList.Add(testMatter);
        testList.Add(testMatter2);
        assertTrue(testList.GetCount()==2);
    }
    
    /*
    Test Add : succès
    */
    @Test
    public void AddTest() {
        System.out.println("AddTest");
        Matter testMatter = new Matter("tomate", 1);
        MatterList testList = new MatterList();
        testList.Add(testMatter);
        assertTrue(testList.GetMatter(0).getName().compareTo("tomate")==0);
        assertTrue(testList.GetMatter(0).getID()==1);
    }
    /*
    Test Add : fail - Matière déjà dans la liste - ID
    */
    @Test(expected = IllegalArgumentException.class)
    public void AddTest_matterIdExists_ThrowsIllegalArgumentException() {
        System.out.println("Add fails: ID exists already");
        Matter m1 = new Matter("pere noel",3);
        Matter m2 = new Matter("autre chose", 3);
        MatterList testList = new MatterList();
        testList.Add(m1);
        testList.Add(m2);
    }
    /*
    Test Add : fail - Matière déjà dans la liste - Nom
    */
    @Test(expected = IllegalArgumentException.class)
    public void AddTest_matterNameExists_ThrowsIllegalArgumentException() {
        System.out.println("Add fails: name exists already");
        Matter m1 = new Matter("pere noel",3);
        Matter m2 = new Matter("pere noel",55);
        MatterList testList = new MatterList();
        testList.Add(m1);
        testList.Add(m2);
    }
    
    /*
    Test GetMatter : doit planter (on tente d'aller chercher le nom d'une matiere supprimée) 
    */
    @Test(expected = IllegalArgumentException.class)
    public void GetMatter_ThrowsIllegalArgumentException() {
        System.out.println("GetMatter: fails (get deleted matter)");
        Matter testMatter = new Matter("tomate", 1);
        MatterList testList = new MatterList();
        testList.Add(testMatter);
        testList.Remove(1);
        testList.GetMatter(0);
    }
    
    /*
    Test Remove by ID
    */
    @Test
    public void RemoveTest() {
        System.out.println("Remove test: success");
        Matter testMatter = new Matter("tomate",1);
        MatterList testList = new MatterList();
        testList.Add(testMatter);
        testList.Remove(1);
        assertTrue(testList.GetCount()==0);
    }
    
    /*
    Test Remove : échoue lorsque aucune matière avec cet ID est dans la liste
    */
    @Test(expected = IllegalArgumentException.class)
    public void RemoveTest_invalidID_ThrowsIllegalArgumentException() {
        System.out.println("Remove test: fails (remove matter : ID not in list)");
        Matter testMatter = new Matter("tomate",2);
        MatterList testList = new MatterList();
        testList.Add(testMatter);
        testList.Remove(0);
    }
    
    /*
    Test Remove : échoue lorsque la liste est vide
    */
    @Test(expected = IllegalArgumentException.class)
    public void RemoveTest_invalidName_ThrowsIllegalArgumentException() {
        System.out.println("Remove test: fails (remove matter when list is empty)");
        MatterList testList = new MatterList();
        testList.Remove(0);
    }
    /*
    Test GetMatter : succès à la position initiale
    */
    @Test
    public void GetMatterTest_Success() {
        System.out.println("GetMatter test: success at original position");
        Matter tm1 = new Matter("tomate",10);
        MatterList testList = new MatterList();
        testList.Add(tm1);
        assertTrue(testList.GetMatter(0).compareMatter(tm1));
    }
    
    /*
    Test GetMatter : succès à position changée
    */
    @Test
    public void GetMatterTest_Success_positionChanged() {
        System.out.println("GetMatter test: success at changed position");
        Matter tm1 = new Matter("tomate",10);
        Matter tm2 = new Matter("autre", 55);
        MatterList testList = new MatterList();
        testList.Add(tm1);
        testList.Add(tm2);
        testList.Remove(10);
        assertTrue(testList.GetMatter(0).compareMatter(tm2));
    }
    /*
    Test GetMatter : échoue : index invalide
    */
    @Test(expected = IndexOutOfBoundsException.class)
    public void GetMatterTest_InvalidID_ThrowsIllegalArgumentException() {
        System.out.println("GetMatter test: fails. Invalid index");
        Matter tm1 = new Matter("tomate",10);
        MatterList testList = new MatterList();
        testList.Add(tm1);
        testList.GetMatter(2);
    }
    
    /*
    Test GetMatter : échoue : liste vide
    */
    @Test(expected = IllegalArgumentException.class)
    public void GetMatterTest_EmptyList_ThrowsIllegalArgumentException() {
        System.out.println("GetMatter test: fails. Empty list");
        MatterList testList = new MatterList();
        testList.GetMatter(0);
    }
    
    /*
    Test GetIndex : Succès
    */
    @Test
    public void GetIndexTest_Success() {
        System.out.println("GetIndex test: success");
        Matter tm1 = new Matter("tomate",22);
        MatterList testList = new MatterList();
        testList.Add(tm1);
        assertTrue(testList.GetIndex("tomate")==0);
    }
    
    /*
    Test GetIndex : échoue : Nom invalide
    */
    @Test(expected = IllegalArgumentException.class)
    public void GetIndexTest_Fail_InvalidName() {
        System.out.println("GetIndex failure: invalid name");
        Matter tm1 = new Matter("tomate",22);
        MatterList testList = new MatterList();
        testList.Add(tm1);
        testList.GetIndex("zebre");
    }
    
    /*
    Test GetIndex : échoue, liste vide
    */
    @Test(expected = IllegalArgumentException.class)
    public void GetIndexTest_Fail_EmptyList() {
        System.out.println("GetIndex failure: empty list");
        MatterList testList = new MatterList();
        testList.GetIndex("zebre");
    }
}
