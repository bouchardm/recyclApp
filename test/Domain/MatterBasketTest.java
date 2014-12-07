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
public class MatterBasketTest {
    
    public MatterBasketTest() {
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
    Test:   getMatterQuantity : retourne la bonne quantité
            addMatter : ajout avec succès aussi testé ici
    */
    @Test
    public void getMatterQuantity_Success() {
        System.out.println("getMatterQuantity: success.");
        Matter tm1 = new Matter("tomate",11);
        MatterList tml1 = new MatterList();
        tml1.addMatterToList(tm1);
        MatterBasket mb1 = new MatterBasket(tml1);
        assertTrue(mb1.getMatterQuantity(11)==0);  
    }
    /*
    Test:   getMatterQuantity : exception IllegalArgumentException si pas dans le panier (panier pas vide)
    */
    @Test(expected = IllegalArgumentException.class)
    public void getMatterQuantity_Fails_IllegalArgumentException(){
        System.out.println("getMatterQuantity: fails : matter not in basket.");
        Matter tm1 = new Matter("tomate",11);
        MatterList tml1 = new MatterList();
        tml1.addMatterToList(tm1);
        MatterBasket mb1 = new MatterBasket(tml1);
        mb1.getMatterQuantity(15);
    }
    /*
    Test:   getMatterQuantity : exception IllegalArgumentException si pas dans le panier (panier vide)
    */
    @Test(expected = IllegalArgumentException.class)
    public void getMatterQuantity_FailsEmptyBasket_IllegalArgumentException(){
        System.out.println("getMatterQuantity: fails : matter not in empty basket.");
        MatterBasket mb1 = new MatterBasket();
        mb1.getMatterQuantity(15);
    }
    
    /*
    Test:   addMatter échoue : matière déjà dans panier, éléments ajoutés 1 à 1
    */
    @Test(expected = IllegalArgumentException.class)
    public void addMatter_InvalidMatter_MatterAlreadyIncluded_ThrowInvalidArgumentException() {
        System.out.println("getMatterQuantity: fails. Matter already included in basket");
        MatterBasket mb1 = new MatterBasket();
        Integer matterID = new Integer(16);
        Float aQuantity = new Float(150);
        mb1.addMatterQuantity(matterID, aQuantity);
        mb1.addMatterQuantity(matterID, aQuantity);
    }
    /*
    Test:   addMatter échoue : matière déjà dans panier, éléments déjà ajouté par une liste
    */
    @Test(expected = IllegalArgumentException.class)
    public void addMatter_InvalidMatter_MatterAlreadyIncludedList_ThrowInvalidArgumentException() {
        System.out.println("getMatterQuantity: fails. Matter already included in basket");
        Matter m1 = new Matter("m1", 14);
        Matter m2 = new Matter("m2", 17);
        MatterList mList = new MatterList();
        mList.addMatterToList(m1);
        mList.addMatterToList(m2);
        Float aQuantity = new Float(150);
        MatterBasket mb = new MatterBasket(mList);
        mb.addMatterQuantity(17, aQuantity);
    }
    
    /*
    Test:   SetMatterQuantity : succes
    */
    @Test
    public void setMatterQuantityTest_Valid() {
        System.out.println("setMatterQuantity: valid");
        Matter m1 = new Matter("m1",14);
        Float aQuantity = new Float(150);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(),aQuantity);
        Float aQuantity2 = new Float(250);
        mb.setMatterQuantity(m1.getID(), aQuantity2);
        assertTrue(mb.getMatterQuantity(m1.getID())==aQuantity2);
    }
    
    /*
    Test:   SetMatterQuantity : échoue. Liste pas vide, mais item pas dans liste
    */
    @Test (expected = IllegalArgumentException.class)
    public void setMatterQuantityTest_invalid_ItemNotInList_throwsIllegalArgumentException(){
        System.out.println("setMatterQuantity: invalid, item not in basket");
        Matter m1 = new Matter("m1",14);
        Float aQuantity = new Float(150);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(),aQuantity);
        Float aQuantity2 = new Float(250);
        mb.setMatterQuantity(15, aQuantity2);
    }
            
    /*
    Test:   SetMatterQuantity : échoue. Liste vide, aucune modif possible
    */
    @Test(expected = IllegalArgumentException.class)
    public void setMatterQuantityTest_invalid_ListEmpty_throwsIllegalArgumentException(){
        System.out.println("setMatterQuantity: invalid, item not in empty basket");
        MatterBasket mb = new MatterBasket();
        Float aQuantity2 = new Float(250);
        mb.setMatterQuantity(13, aQuantity2);
    }
    

    /*
    Test: removeMatterQuantity : succès, matière enlevé
    */
    @Test(expected = IllegalArgumentException.class)
    public void removeMatterQuantity_success_matterRemoved() {
        System.out.println("removeMatterQuantity: success");
        Matter m1 = new Matter("tomate", 22);
        MatterBasket mb = new MatterBasket();
        Float aQuantity = new Float(200);
        mb.addMatterQuantity(m1.getID(), aQuantity);
        mb.removeMatterQuantity(22);
        mb.getMatterQuantity(22);
    }
    
    /*
    Test: removeMatterQuantity : échec, matière n'est pas dans le panier
    */
    @Test(expected = IllegalArgumentException.class)
    public void removeMatterQuantity_notInBasket_IllegalArgumentException() {
        System.out.println("removeMatterQuantity: failure, not in basket");
        Matter m1 = new Matter("tomate", 22);
        MatterBasket mb = new MatterBasket();
        Float aQuantity = new Float(200);
        mb.addMatterQuantity(m1.getID(), aQuantity);
        mb.removeMatterQuantity(23);
    }
    
    /*
    Test: removeMatterQuantity : échec, basket vide
    */
    @Test(expected = IllegalArgumentException.class)
    public void removeMatterQuantity_emptyBasket_IllegalArgumentException() {
        System.out.println("removeMatterQuantity: failure, empty basket");
        MatterBasket mb = new MatterBasket();
        mb.removeMatterQuantity(23);
    }
    
    /*
    Test: getTotalQuantity : succès, retourne bon total (panier avec éléments)
    */
    @Test
    public void getTotalQuantity_success_basketWithItems() {
        System.out.println("getTotalQuantity: success");
        Matter m1 = new Matter("tomate", 22);
        Matter m2 = new Matter("autre", 55);
        MatterBasket mb = new MatterBasket();
        Float aQuantity = new Float(200);
        Float anotherQuantity = new Float(500);
        mb.addMatterQuantity(m1.getID(), aQuantity);
        mb.addMatterQuantity(m2.getID(), anotherQuantity);
        assertTrue(mb.getTotalQuantity()==700);
    }
    
    /*
    Test: getTotalQuantity : succès, retourne bon total (panier vide)
    */
    @Test
    public void getTotalQuantity_success_emptyBasket() {
        System.out.println("getTotalQuantity: success (empty basket)");
        MatterBasket mb = new MatterBasket();
        assertTrue(mb.getTotalQuantity()==0);
    }
}
