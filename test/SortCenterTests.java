/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Domain.Junction;
import Domain.Matter;
import Domain.MatterBasket;
import Domain.MatterList;
import Domain.SortCenter;
import Domain.SortMatrix;
import Domain.Station;
import Domain.TransMatrix;
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
public class SortCenterTests {
    
    public SortCenterTests() {
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

     //vérifie verifyCycles en ajoutant un convoyeur (c'est addConveyor qui lance verifyCycles)
    //cas succès traités avec les sortCenter valides
    @Test(expected = IllegalArgumentException.class)
    public void verifyCyclesTest_fail_convoyeurIntroduitCycleIndirect() {
        System.out.println("tout est ok.");
        //construire le premier matterBasket en entrée
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        MatterBasket mb1 = new MatterBasket();
        mb1.addMatterQuantity(m1.getID(), new Float(700));
        mb1.addMatterQuantity(m2.getID(), new Float(1000));
        //construire le deuxième matterBasket en entrée
        MatterBasket mb2 = new MatterBasket();
        mb2.addMatterQuantity(m1.getID(), new Float(1500));
        mb2.addMatterQuantity(m2.getID(), new Float(1200));
        //matterList
        MatterList mlist = new MatterList();
        mlist.addMatterToList(m1);
        mlist.addMatterToList(m2);
        
        //construire centre de tri
        SortCenter sc = new SortCenter();
        sc.addEntryPoint();     //EP0
        sc.addEntryPoint();     //EP1
        //ajout du matterBasket au sortCenter
        sc.setMatterList(mlist);
        //créer une matrice de tri pour les machines à une sortie
        HashMap<Integer, ArrayList<Float>> smatrix = new HashMap<>();
        ArrayList<Float> innerList = new ArrayList<>();
        innerList.add(new Float(1));
        smatrix.put(1, innerList);
        ArrayList<Float> innerList2 = new ArrayList<>();
        innerList2.add(new Float(1));
        smatrix.put(2, innerList2);
        SortMatrix sorter = new SortMatrix();
        sorter.setSortMatrix(smatrix);
        //créer la matrice de tri pour les machines à deux sorties
        HashMap<Integer, ArrayList<Float>> smatrix2 = new HashMap<>();
        ArrayList<Float> innerList21 = new ArrayList<>();
        innerList21.add(new Float(0.5));
        innerList21.add(new Float(0.5));
        smatrix2.put(1, innerList21);
        ArrayList<Float> innerList22 = new ArrayList<>();
        innerList22.add(new Float(0.75));
        innerList22.add(new Float(0.25));
        smatrix2.put(2, innerList22);
        SortMatrix sorter2 = new SortMatrix();
        sorter2.setSortMatrix(smatrix2);
        //créer une matrice de transformation
        HashMap<Integer, Float> transformQty1 = new HashMap<>();
        transformQty1.put(1,new Float(0.95));
        transformQty1.put(2, new Float(0.05));
        HashMap<Integer, Float> transformQty2 = new HashMap<>();
        transformQty2.put(1, new Float(0.1));
        transformQty2.put(2, new Float(0.9));
        HashMap<Integer, HashMap<Integer, Float>> tMatrix = new HashMap<>();
        tMatrix.put(1, transformQty1);
        tMatrix.put(2, transformQty2);
        TransMatrix transMatrix = new TransMatrix();
        transMatrix.setTransMatrix(tMatrix);
        //créer les station
        sc.addTransStation(1);                           //S0
        Station s0 = sc.getStations().get(0);
        s0.setSortMatrix(sorter);
        s0.setTransMatrix(transMatrix);
        sc.addSortStation(1);                           //S1
        Station s1 = sc.getStations().get(1);
        s1.setSortMatrix(sorter);
        sc.addSortStation(3);                           //S2
        //créer les convoyeurs
        //c0
        sc.addConveyor(sc.getEntryPointOutlet(0), sc.getStations().get(0).getInlet());
        //c1
        sc.addConveyor(sc.getEntryPointOutlet(1), sc.getStations().get(1).getInlet());
        

    }
}
