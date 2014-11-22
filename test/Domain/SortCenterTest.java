/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import java.awt.geom.Point2D;
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
 * @author Dany
 */
public class SortCenterTest {
    
    public SortCenterTest() {
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
     * Test of getDimensions method, of class SortCenter.
     */
    @Test
    public void testGetDimensions() {
        System.out.println("getDimensions");
        SortCenter instance = new SortCenter();
        Point2D.Float expResult = new Point2D.Float(15f, 10f);
        Point2D.Float result = instance.getDimensions();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDimensions method, of class SortCenter.
     */
    @Test
    public void testSetDimensions() {
        System.out.println("setDimensions");
        Float x = 20f;
        Float y = 30f;
        Point2D.Float dimensions = new Point2D.Float(x, y);
        SortCenter instance = new SortCenter();
        instance.setDimensions(x, y);
        assertEquals(instance.getDimensions(), dimensions);
    }
    
    @Test
    public void updateDesignTest() {
        System.out.println("Update design test 1 : one entry point, one exit point, one conveyor (aucun tri, transfo).");
        Matter m1 = new Matter("m1",11);
        Matter m2 = new Matter("m2",12);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(), new Float(100));
        mb.addMatterQuantity(m2.getID(), new Float(1000));
        SortCenter sc = new SortCenter();
        sc.addEntryPoint();
        sc.addExitPoint();
       //conveyor end points
        sc.addConveyor(sc.getEntryPointOutlet(0), sc.getExitPointInlet(0));
        sc.setEntryPointMatterBasket(0, mb);
        sc.updateDesign();
        MatterBasket newMb = sc.getExitPointMatterBasket(0);
        assertTrue((float)newMb.getMatterQuantity(11)==(float)100);
        assertTrue((float)newMb.getMatterQuantity(12)==(float)1000);
    }
    
    @Test
    public void updateDesignTest2() {
        System.out.println("Update design test 2 : one entry point, one exit point, one transformStation, two conveyors.");
        //construire matterBasket
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(), new Float(100));
        mb.addMatterQuantity(m2.getID(), new Float(1000));
        //construire centre de tri
        SortCenter sc = new SortCenter();
        sc.addEntryPoint();
        sc.addExitPoint();
        //construire la station de transformation et ses matrices
        //créer une matrice de tri
        HashMap<Integer, ArrayList<Float>> smatrix = new HashMap<>();
        ArrayList<Float> innerList = new ArrayList<>();
        innerList.add(new Float(1));
        smatrix.put(1, innerList);
        ArrayList<Float> innerList2 = new ArrayList<>();
        innerList2.add(new Float(1));
        smatrix.put(2, innerList2);
        SortMatrix sorter = new SortMatrix();
        sorter.setSortMatrix(smatrix);
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
        //créer la station
        sc.addTransStation();
        Station tstation = sc.getStations().get(0);
        tstation.setSortMatrix(sorter);
        tstation.setTransMatrix(transMatrix);
        tstation.addOutlet();
        //ajouter les convoyeurs
        //convoyeur de EntryPoint à tstation
        sc.addConveyor(sc.getEntryPointOutlet(0), tstation.getInlet());
        //convoyeur de tstation à ExitPoint
        sc.addConveyor(tstation.getOutletList().get(0), sc.getExitPointInlet(0));
        sc.setEntryPointMatterBasket(0, mb);
        sc.updateDesign();
        //vérifier les quantités en sortie
        MatterBasket newMb = sc.getExitPointMatterBasket(0);
        assertTrue((float)newMb.getMatterQuantity(1)==(float)195);
        assertTrue((float)newMb.getMatterQuantity(2)==(float)905);
        
    }
    
    @Test
    public void updateDesignTest3() {
        System.out.println("Update design test 3 : design sans convoyeurs reliés à la sortie (sortie vide).");
        //construire matterBasket
        Matter m1 = new Matter("m1",11);
        Matter m2 = new Matter("m2",12);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(), new Float(100));
        mb.addMatterQuantity(m2.getID(), new Float(1000));
        SortCenter sc = new SortCenter();
        sc.addEntryPoint();
        sc.addExitPoint();
       //conveyor end points
        sc.setEntryPointMatterBasket(0, mb);
        sc.updateDesign();
        MatterBasket newMb = sc.getExitPointMatterBasket(0);
        assertTrue(newMb.getNumberOfMatterInBasket()==0);
    }
    
    @Test
    public void updateDesignTest4() {
        System.out.println("Update design test 4 : no matter in basket.");
        MatterBasket mb = new MatterBasket();
        SortCenter sc = new SortCenter();
        sc.addEntryPoint();
        sc.addExitPoint();
       //conveyor end points
        sc.addConveyor(sc.getEntryPointOutlet(0), sc.getExitPointInlet(0));
        sc.setEntryPointMatterBasket(0, mb);
        sc.updateDesign();
        MatterBasket newMb = sc.getExitPointMatterBasket(0);
        assertTrue(newMb.getNumberOfMatterInBasket()==0);
    }
    
    @Test
    public void updateDesignTest5() {
        System.out.println("Update design test 5 : 2 entryPoints, 2 transStation, 2 sortStation, 1 junction, 3 exits.");
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
        mlist.Add(m1);
        mlist.Add(m2);
        
        //construire centre de tri
        SortCenter sc = new SortCenter();
        sc.addEntryPoint();     //EP0
        sc.addEntryPoint();     //EP1
        sc.addExitPoint();      //XP0
        sc.addExitPoint();      //XP1
        sc.addExitPoint();      //XP2
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
        sc.addTransStation();                           //S0
        Station s0 = sc.getStations().get(0);
        s0.setSortMatrix(sorter);
        s0.setTransMatrix(transMatrix);
        s0.addOutlet();
        sc.addSortStation();                           //S1
        Station s1 = sc.getStations().get(1);
        s1.setSortMatrix(sorter);
        s1.addOutlet();
        sc.addSortStation();                           //S2
        Station s2 = sc.getStations().get(2);
        s2.setSortMatrix(sorter2);
        s2.addOutlet();
        s2.addOutlet();
        sc.addTransStation();                           //S3
        Station s3 = sc.getStations().get(3);
        s3.setSortMatrix(sorter2);
        s3.setTransMatrix(transMatrix);
        s3.addOutlet();
        s3.addOutlet();
        //créer la jonction et ses inlets/outlets
        sc.addJunction();
        Junction j1= sc.getJunction(0);
        j1.addInlet();
        j1.addInlet();
        //créer les convoyeurs
        //c0
        sc.addConveyor(sc.getEntryPointOutlet(0), sc.getStations().get(0).getInlet());
        //c1
        sc.addConveyor(sc.getEntryPointOutlet(1), sc.getStations().get(1).getInlet());
        //c2
        sc.addConveyor(sc.getStationOutletList(0).get(0), sc.getJunctionInletList(0).get(0));
        //c3
        sc.addConveyor(sc.getStationOutletList(1).get(0), sc.getJunctionInletList(0).get(1));
        //c4
        sc.addConveyor(sc.getJunctionOutlet(0), sc.getStations().get(2).getInlet());
        //c5
        sc.addConveyor(sc.getStationOutletList(2).get(0), sc.getExitPointInlet(0));
        //c6
        sc.addConveyor(sc.getStationOutletList(2).get(1), sc.getStations().get(3).getInlet());
        //c7
        sc.addConveyor(sc.getStationOutletList(3).get(0), sc.getExitPointInlet(1));
        //c8
        sc.addConveyor(sc.getStationOutletList(3).get(1), sc.getExitPointInlet(2));
        //
        sc.setEntryPointMatterBasket(0, mb1);
        sc.setEntryPointMatterBasket(1, mb2);
        sc.updateDesign();
        //vérifier les quantités en sortie
        MatterBasket newMb0 = sc.getExitPointMatterBasket(0);
        MatterBasket newMb1 = sc.getExitPointMatterBasket(1);
        MatterBasket newMb2 = sc.getExitPointMatterBasket(2);
        //matterBasket de la jonction
        MatterBasket juncMb = sc.getJunctionMatterBasket(0);
        //confirmer qu'on a tous les bonnes quantités à la fin
        assertTrue(newMb0.getNumberOfMatterInBasket()==2);
        assertTrue(newMb1.getNumberOfMatterInBasket()==2);
        assertTrue(newMb2.getNumberOfMatterInBasket()==2);
        assertTrue((float)newMb0.getMatterQuantity(1)==(float)1132.5);
        assertTrue((float)newMb0.getMatterQuantity(2)==(float)1601.25);
        assertTrue((float)newMb1.getMatterQuantity(1)==(float)564.625);
        assertTrue((float)newMb1.getMatterQuantity(2)==(float)402.75);
        assertTrue((float)newMb2.getMatterQuantity(1)==(float)564.625);
        assertTrue((float)newMb2.getMatterQuantity(2)==(float)134.25);
        //vérification à la jonction
        assertTrue((float)juncMb.getMatterQuantity(1)==(float)2265);
        assertTrue((float)juncMb.getMatterQuantity(2)==(float)2135);
    }
    
    @Test
    public void updateDesignTest6() {
        System.out.println("Update design test 6 : 2 entryPoints, 2 transStation, 2 sortStation, 1 junction, 3 exits. Two tests, new baskets.");
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
        mlist.Add(m1);
        mlist.Add(m2);
        
        //construire centre de tri
        SortCenter sc = new SortCenter();
        sc.addEntryPoint();     //EP0
        sc.addEntryPoint();     //EP1
        sc.addExitPoint();      //XP0
        sc.addExitPoint();      //XP1
        sc.addExitPoint();      //XP2
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
        sc.addTransStation();                           //S0
        Station s0 = sc.getStations().get(0);
        s0.setSortMatrix(sorter);
        s0.setTransMatrix(transMatrix);
        s0.addOutlet();
        sc.addSortStation();                           //S1
        Station s1 = sc.getStations().get(1);
        s1.setSortMatrix(sorter);
        s1.addOutlet();
        sc.addSortStation();                           //S2
        Station s2 = sc.getStations().get(2);
        s2.setSortMatrix(sorter2);
        s2.addOutlet();
        s2.addOutlet();
        sc.addTransStation();                           //S3
        Station s3 = sc.getStations().get(3);
        s3.setSortMatrix(sorter2);
        s3.setTransMatrix(transMatrix);
        s3.addOutlet();
        s3.addOutlet();
        //créer la jonction et ses inlets/outlets
        sc.addJunction();
        Junction j1= sc.getJunction(0);
        j1.addInlet();
        j1.addInlet();
        //ajouter la matterList au sortCenter
        sc.setMatterList(mlist);
        //créer les convoyeurs
        //c0
        sc.addConveyor(sc.getEntryPointOutlet(0), sc.getStations().get(0).getInlet());
        //c1
        sc.addConveyor(sc.getEntryPointOutlet(1), sc.getStations().get(1).getInlet());
        //c2
        sc.addConveyor(sc.getStationOutletList(0).get(0), sc.getJunctionInletList(0).get(0));
        //c3
        sc.addConveyor(sc.getStationOutletList(1).get(0), sc.getJunctionInletList(0).get(1));
        //c4
        sc.addConveyor(sc.getJunctionOutlet(0), sc.getStations().get(2).getInlet());
        //c5
        sc.addConveyor(sc.getStationOutletList(2).get(0), sc.getExitPointInlet(0));
        //c6
        sc.addConveyor(sc.getStationOutletList(2).get(1), sc.getStations().get(3).getInlet());
        //c7
        sc.addConveyor(sc.getStationOutletList(3).get(0), sc.getExitPointInlet(1));
        //c8
        sc.addConveyor(sc.getStationOutletList(3).get(1), sc.getExitPointInlet(2));
        //
        sc.setEntryPointMatterBasket(0, mb1);
        sc.setEntryPointMatterBasket(1, mb2);
        sc.updateDesign();
        
        //vérifier les quantités en sortie
        MatterBasket newMb0 = sc.getExitPointMatterBasket(0);
        MatterBasket newMb1 = sc.getExitPointMatterBasket(1);
        MatterBasket newMb2 = sc.getExitPointMatterBasket(2);
        //matterBasket de la jonction
        MatterBasket juncMb = sc.getJunctionMatterBasket(0);
        //confirmer qu'on a tous les bonnes quantités à la fin
        assertTrue(newMb0.getNumberOfMatterInBasket()==2);
        assertTrue(newMb1.getNumberOfMatterInBasket()==2);
        assertTrue(newMb2.getNumberOfMatterInBasket()==2);
        //vérification à la jonction
        assertTrue((float)juncMb.getMatterQuantity(1)==(float)2265);
        assertTrue((float)juncMb.getMatterQuantity(2)==(float)2135);
        assertTrue((float)newMb0.getMatterQuantity(1)==(float)1132.5);
        
        assertTrue((float)newMb0.getMatterQuantity(2)==(float)1601.25);
        assertTrue((float)newMb1.getMatterQuantity(1)==(float)564.625);
        assertTrue((float)newMb1.getMatterQuantity(2)==(float)402.75);
        assertTrue((float)newMb2.getMatterQuantity(1)==(float)564.625);
        assertTrue((float)newMb2.getMatterQuantity(2)==(float)134.25);
        //Deuxième test
        MatterBasket mb3 = new MatterBasket();
        mb3.addMatterQuantity(m1.getID(), new Float(700));
        mb3.addMatterQuantity(m2.getID(), new Float(1000));
        //construire le deuxième matterBasket en entrée
        MatterBasket mb4 = new MatterBasket();
        mb4.addMatterQuantity(m1.getID(), new Float(1500));
        mb4.addMatterQuantity(m2.getID(), new Float(1200));
        sc.setEntryPointMatterBasket(0, mb3);
        sc.setEntryPointMatterBasket(1, mb4);
        sc.updateDesign();
        //vérification à la jonction
        assertTrue((float)juncMb.getMatterQuantity(1)==(float)2265);
        assertTrue((float)juncMb.getMatterQuantity(2)==(float)2135);
        assertTrue((float)newMb0.getMatterQuantity(1)==(float)1132.5);
        
        assertTrue((float)newMb0.getMatterQuantity(2)==(float)1601.25);
        assertTrue((float)newMb1.getMatterQuantity(1)==(float)564.625);
        assertTrue((float)newMb1.getMatterQuantity(2)==(float)402.75);
        assertTrue((float)newMb2.getMatterQuantity(1)==(float)564.625);
        assertTrue((float)newMb2.getMatterQuantity(2)==(float)134.25);
    }
    
    @Test
    public void updateDesignTest7() {
        System.out.println("Update design test 7 : 2 entryPoints, 2 transStation, 2 sortStation, 1 junction, 3 exits. Two tests same baskets.");
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
        mlist.Add(m1);
        mlist.Add(m2);
        
        //construire centre de tri
        SortCenter sc = new SortCenter();
        sc.addEntryPoint();     //EP0
        sc.addEntryPoint();     //EP1
        sc.addExitPoint();      //XP0
        sc.addExitPoint();      //XP1
        sc.addExitPoint();      //XP2
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
        sc.addTransStation();                           //S0
        Station s0 = sc.getStations().get(0);
        s0.setSortMatrix(sorter);
        s0.setTransMatrix(transMatrix);
        s0.addOutlet();
        sc.addSortStation();                           //S1
        Station s1 = sc.getStations().get(1);
        s1.setSortMatrix(sorter);
        s1.addOutlet();
        sc.addSortStation();                           //S2
        Station s2 = sc.getStations().get(2);
        s2.setSortMatrix(sorter2);
        s2.addOutlet();
        s2.addOutlet();
        sc.addTransStation();                           //S3
        Station s3 = sc.getStations().get(3);
        s3.setSortMatrix(sorter2);
        s3.setTransMatrix(transMatrix);
        s3.addOutlet();
        s3.addOutlet();
        //créer la jonction et ses inlets/outlets
        sc.addJunction();
        Junction j1= sc.getJunction(0);
        j1.addInlet();
        j1.addInlet();
        //ajouter la matterList au sortCenter
        sc.setMatterList(mlist);
        //créer les convoyeurs
        //c0
        sc.addConveyor(sc.getEntryPointOutlet(0), sc.getStations().get(0).getInlet());
        //c1
        sc.addConveyor(sc.getEntryPointOutlet(1), sc.getStations().get(1).getInlet());
        //c2
        sc.addConveyor(sc.getStationOutletList(0).get(0), sc.getJunctionInletList(0).get(0));
        //c3
        sc.addConveyor(sc.getStationOutletList(1).get(0), sc.getJunctionInletList(0).get(1));
        //c4
        sc.addConveyor(sc.getJunctionOutlet(0), sc.getStations().get(2).getInlet());
        //c5
        sc.addConveyor(sc.getStationOutletList(2).get(0), sc.getExitPointInlet(0));
        //c6
        sc.addConveyor(sc.getStationOutletList(2).get(1), sc.getStations().get(3).getInlet());
        //c7
        sc.addConveyor(sc.getStationOutletList(3).get(0), sc.getExitPointInlet(1));
        //c8
        sc.addConveyor(sc.getStationOutletList(3).get(1), sc.getExitPointInlet(2));
        //
        sc.setEntryPointMatterBasket(0, mb1);
        sc.setEntryPointMatterBasket(1, mb2);
        sc.updateDesign();
        sc.updateDesign();
        
        //vérifier les quantités en sortie
        MatterBasket newMb0 = sc.getExitPointMatterBasket(0);
        MatterBasket newMb1 = sc.getExitPointMatterBasket(1);
        MatterBasket newMb2 = sc.getExitPointMatterBasket(2);
        //matterBasket de la jonction
        MatterBasket juncMb = sc.getJunctionMatterBasket(0);
        //confirmer qu'on a tous les bonnes quantités à la fin
        assertTrue(newMb0.getNumberOfMatterInBasket()==2);
        assertTrue(newMb1.getNumberOfMatterInBasket()==2);
        assertTrue(newMb2.getNumberOfMatterInBasket()==2);
        //vérification à la jonction
        assertTrue((float)juncMb.getMatterQuantity(1)==(float)2265);
        assertTrue((float)juncMb.getMatterQuantity(2)==(float)2135);
        assertTrue((float)newMb0.getMatterQuantity(1)==(float)1132.5);
        
        assertTrue((float)newMb0.getMatterQuantity(2)==(float)1601.25);
        assertTrue((float)newMb1.getMatterQuantity(1)==(float)564.625);
        assertTrue((float)newMb1.getMatterQuantity(2)==(float)402.75);
        assertTrue((float)newMb2.getMatterQuantity(1)==(float)564.625);
        assertTrue((float)newMb2.getMatterQuantity(2)==(float)134.25);

        
    }
}
