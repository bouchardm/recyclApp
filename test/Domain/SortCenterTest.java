/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
    
//    @Test
//    public void addSortStationTest() {
//        SortCenter sc = new SortCenter();
//        Matter m1 = new Matter("m1",1);
//        Matter m2 = new Matter("m2",2);
//        sc.addMatterToMatterList(m1);
//        sc.addMatterToMatterList(m2);
//        SortStation ss = sc.addSortStation(2);
//        //2 "colonnes" dans la matrice = 2 sorties
//        assertTrue(ss.getSortMatrix().getSortMatrix().get(1).size()==2);
//        //les 2 matières sont dans la matrice
//        assertTrue(ss.getSortMatrix().getSortMatrix().containsKey(1));
//        assertTrue(ss.getSortMatrix().getSortMatrix().containsKey(2));
//        assertTrue(ss.getSortMatrix().getSortMatrix().size()==2);        
//    }
    
//    @Test
//    public void addTransStationTest() {
//        SortCenter sc = new SortCenter();
//        Matter m1 = new Matter("m1",1);
//        Matter m2 = new Matter("m2",2);
//        sc.addMatterToMatterList(m1);
//        sc.addMatterToMatterList(m2);
//        System.out.println("Matter "+sc.getMatterList().getMatterName(1)+", "+sc.getMatterList().getMatterName(2));
//        TransStation ts = sc.addTransStation(2);
//        //test sort matrix
//        //2 "colonnes" dans la matrice = 2 sorties
//        assertTrue(ts.getSortMatrix().getSortMatrix().get(1).size()==2);
//        //les 2 matières sont dans la matrice
//        assertTrue(ts.getSortMatrix().getSortMatrix().containsKey(1));
//        assertTrue(ts.getSortMatrix().getSortMatrix().containsKey(2));
//        assertTrue(ts.getSortMatrix().getSortMatrix().size()==2);  
//        //test trans matrix
//        //il y a le même nombre de lignes (matières à transformer) et colonnes (matière en quoi transformer)
//        assertTrue(ts.getTransMatrix().getMatterCount()==ts.getTransMatrix().getTransMatrix().get(1).size());
//        assertTrue(ts.getTransMatrix().getMatterCount()==ts.getTransMatrix().getTransMatrix().get(2).size());
//        //il y a le bon nombre de lignes (et donc de colonnes, vérifié juste ci-dessus)
//        assertTrue(ts.getTransMatrix().getMatterCount()==2);
//        //les bonnes matières sont dans les lignes
//        assertTrue(ts.getTransMatrix().getTransMatrix().containsKey(1));
//        assertTrue(ts.getTransMatrix().getTransMatrix().containsKey(2));
//        //les bonnes matières sont dans les colonnes
//        assertTrue(ts.getTransMatrix().getTransMatrix().get(1).containsKey(1));
//        assertTrue((float)ts.getTransMatrix().getTransMatrix().get(1).get(1)==(float)1);
//        assertTrue(ts.getTransMatrix().getTransMatrix().get(1).containsKey(2));
//        assertTrue(ts.getTransMatrix().getTransMatrix().get(2).containsKey(1));
//        assertTrue(ts.getTransMatrix().getTransMatrix().get(2).containsKey(2));
//        
//    }
    
    @Test
    public void getTotalEntryPointsMatterBasketTest_Valid() {
        System.out.println("TotalEP MB test : valid");
        Matter m1 = new Matter("m1",11);
        Matter m2 = new Matter("m2",12);
        Matter m3 = new Matter("m3",13);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(), new Float(100));
        mb.addMatterQuantity(m2.getID(), new Float(1000));
        mb.addMatterQuantity(m3.getID(), new Float(500));
        MatterBasket mb2 = new MatterBasket();
        mb2.addMatterQuantity(m1.getID(), new Float(200));
        mb2.addMatterQuantity(m2.getID(), new Float(2000));
        mb2.addMatterQuantity(m3.getID(), new Float(1000));
        SortCenter sc = new SortCenter();
        sc.addEntryPoint();
        sc.addEntryPoint();
        sc.getEntryPoints().get(0).setMatterBasket(mb);
        sc.getEntryPoints().get(1).setMatterBasket(mb2);
        MatterBasket totalMB = sc.getTotalEntryPointsMatterBasket();
        float grandTotal = totalMB.getTotalQuantity();
        assertTrue(grandTotal==4800);    
    }
    
    @Test
    public void getTotalEntryPointsMatterBasketTest_NoMatterNoEntryPoints() {
        System.out.println("TotalEP MB test : no matter, no ep");
        SortCenter sc = new SortCenter();
        assertTrue(sc.getTotalEntryPointsMatterBasket().getTotalQuantity()==0);    
    }
    
    @Test
    public void getTotalEntryPointsMatterBasketTest_NoMatter2EntryPoints() {
        System.out.println("TotalEP MB test : no matter, no ep");
        SortCenter sc = new SortCenter();
        sc.addEntryPoint();
        sc.addEntryPoint();
        assertTrue(sc.getTotalEntryPointsMatterBasket().getTotalQuantity()==0);    
    }
    
    @Test
    public void getRecoveryRateForMatterBasketAtElement_Conveyor() {
        System.out.println("Recovery rate for Conveyor");
        Matter m1 = new Matter("m1",11);
        Matter m2 = new Matter("m2",12);
        Matter m3 = new Matter("m3",13);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(), new Float(100));
        mb.addMatterQuantity(m2.getID(), new Float(1000));
        mb.addMatterQuantity(m3.getID(), new Float(500));
        MatterBasket mb2 = new MatterBasket();
        mb2.addMatterQuantity(m1.getID(), new Float(200));
        mb2.addMatterQuantity(m2.getID(), new Float(2000));
        mb2.addMatterQuantity(m3.getID(), new Float(1000));
        SortCenter sc = new SortCenter();
        sc.addEntryPoint();
        sc.addEntryPoint();
        sc.getEntryPoints().get(0).setMatterBasket(mb);
        sc.getEntryPoints().get(1).setMatterBasket(mb2);
        sc.addExitPoint();
        sc.addExitPoint();
        MatterBasket totalMB = sc.getTotalEntryPointsMatterBasket();
        float grandTotal = totalMB.getTotalQuantity();
        assertTrue(grandTotal==4800); 
        Conveyor conv = sc.addConveyor(sc.getEntryPointOutlet(0), sc.getExitPointInlet(0));
        Conveyor conv2 = sc.addConveyor(sc.getEntryPointOutlet(1), sc.getExitPointInlet(1));
        HashMap<Integer,Float> recov = sc.getRecoveryRateForMatterBasketAtElement(conv);
        HashMap<Integer,Float> recov2 = sc.getRecoveryRateForMatterBasketAtElement(conv2);
        BigDecimal inRecovM1B1 = new BigDecimal(recov.get(11));
        BigDecimal actualM1B1 = new BigDecimal((float)100/4800);
        assertTrue(inRecovM1B1.equals(actualM1B1));
        BigDecimal inRecovM2B1 = new BigDecimal(recov.get(12));
        BigDecimal actualM2B1 = new BigDecimal((float)1000/4800);
        assertTrue(inRecovM2B1.equals(actualM2B1));
        BigDecimal inRecovM3B1 = new BigDecimal(recov.get(13));
        BigDecimal actualM3B1 = new BigDecimal((float)500/4800);
        assertTrue(inRecovM3B1.equals(actualM3B1));
        BigDecimal inRecovM1B2 = new BigDecimal(recov2.get(11));
        BigDecimal actualM1B2 = new BigDecimal((float)200/4800);
        assertTrue(inRecovM1B2.equals(actualM1B2));
        BigDecimal inRecovM2B2 = new BigDecimal(recov2.get(12));
        BigDecimal actualM2B2 = new BigDecimal((float)2000/4800);
        assertTrue(inRecovM2B2.equals(actualM2B2));
        BigDecimal inRecovM3B2 = new BigDecimal(recov2.get(13));
        BigDecimal actualM3B2 = new BigDecimal((float)1000/4800);
        assertTrue(inRecovM3B2.equals(actualM3B2)); 
    }
    
    @Test
    public void getRecoveryRateForMatterBasketAtElement_TransStation() {
        SortCenter sc = new SortCenter();
        EntryPoint ep = sc.addEntryPoint();
        TransStation ts = sc.addTransStation(1);
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(), new Float(1000));
        mb.addMatterQuantity(m2.getID(), new Float(1000));
        ep.setMatterBasket(mb);
        HashMap<Integer, Float> transformQty1 = new HashMap<>();
        transformQty1.put(1,new Float(1.00));
        transformQty1.put(2, new Float(0.00));
        HashMap<Integer, Float> transformQty2 = new HashMap<>();
        transformQty2.put(1, new Float(1.0));
        transformQty2.put(2, new Float(0.0));
        HashMap<Integer, HashMap<Integer, Float>> tMatrix = new HashMap<>();
        tMatrix.put(1, transformQty1);
        tMatrix.put(2, transformQty2);
        TransMatrix transMatrix = new TransMatrix();
        transMatrix.setTransMatrix(tMatrix);
        ts.setTransMatrix(transMatrix);
        Conveyor conv = sc.addConveyor(ep.getOutlet(), ts.getInlet());
        HashMap<Integer,Float> recov = sc.getRecoveryRateForMatterBasketAtElement(ts);
        assertTrue(recov.get(1)==1);
        assertTrue(recov.get(2)==0);
    }
    
    @Test
    public void getRecoveryRateForMatterBasketAtElement_ExitPoint() {
        SortCenter sc = new SortCenter();
        EntryPoint ep = sc.addEntryPoint();
        TransStation ts = sc.addTransStation(1);
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(), new Float(1000));
        mb.addMatterQuantity(m2.getID(), new Float(1000));
        ep.setMatterBasket(mb);
        HashMap<Integer, Float> transformQty1 = new HashMap<>();
        transformQty1.put(1,new Float(1.00));
        transformQty1.put(2, new Float(0.00));
        HashMap<Integer, Float> transformQty2 = new HashMap<>();
        transformQty2.put(1, new Float(1.0));
        transformQty2.put(2, new Float(0.0));
        HashMap<Integer, HashMap<Integer, Float>> tMatrix = new HashMap<>();
        tMatrix.put(1, transformQty1);
        tMatrix.put(2, transformQty2);
        TransMatrix transMatrix = new TransMatrix();
        transMatrix.setTransMatrix(tMatrix);
        ts.setTransMatrix(transMatrix);
        Conveyor conv = sc.addConveyor(ep.getOutlet(), ts.getInlet());
        ExitPoint xp = sc.addExitPoint();
        Conveyor conv2 = sc.addConveyor(ts.getOutletList().get(0), xp.getInlet());
        HashMap<Integer,Float> recov = sc.getRecoveryRateForMatterBasketAtElement(xp);
        assertTrue(recov.get(1)==1);
        assertTrue(recov.get(2)==0);
    }
    
    @Test
    public void getRecoveryRateForMatterBasketAtElement_Junction() {
        SortCenter sc = new SortCenter();
        EntryPoint ep = sc.addEntryPoint();
        TransStation ts = sc.addTransStation(1);
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(), new Float(1000));
        mb.addMatterQuantity(m2.getID(), new Float(1000));
        ep.setMatterBasket(mb);
        HashMap<Integer, Float> transformQty1 = new HashMap<>();
        transformQty1.put(1,new Float(1.00));
        transformQty1.put(2, new Float(0.00));
        HashMap<Integer, Float> transformQty2 = new HashMap<>();
        transformQty2.put(1, new Float(1.0));
        transformQty2.put(2, new Float(0.0));
        HashMap<Integer, HashMap<Integer, Float>> tMatrix = new HashMap<>();
        tMatrix.put(1, transformQty1);
        tMatrix.put(2, transformQty2);
        TransMatrix transMatrix = new TransMatrix();
        transMatrix.setTransMatrix(tMatrix);
        ts.setTransMatrix(transMatrix);
        Conveyor conv = sc.addConveyor(ep.getOutlet(), ts.getInlet());
        Junction jc = sc.addJunction();
        Conveyor conv2 = sc.addConveyor(ts.getOutletList().get(0), jc.getInletList().get(0));
        HashMap<Integer,Float> recov = sc.getRecoveryRateForMatterBasketAtElement(jc);
        assertTrue(recov.get(1)==1);
        assertTrue(recov.get(2)==0);
    }
    
    @Test
    public void getRecoveryRateForMatterBasketAtElement_EntryPoints() {
        SortCenter sc = new SortCenter();
        EntryPoint ep = sc.addEntryPoint();
        EntryPoint ep2 = sc.addEntryPoint();
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(), new Float(1000));
        mb.addMatterQuantity(m2.getID(), new Float(1000));
        MatterBasket mb2 = new MatterBasket();
        mb2.addMatterQuantity(m1.getID(), new Float(1000));
        mb2.addMatterQuantity(m2.getID(), new Float(1000));
        ep.setMatterBasket(mb);
        ep2.setMatterBasket(mb2);
        sc.updateDesign();
        HashMap<Integer,Float> recov = sc.getRecoveryRateForMatterBasketAtElement(ep);
        HashMap<Integer, Float> recov2 = sc.getRecoveryRateForMatterBasketAtElement(ep2);
        assertTrue(recov.get(1)==0.25);
        assertTrue(recov.get(2)==0.25);
        assertTrue(recov2.get(1)==0.25);
        assertTrue(recov2.get(2)==0.25);
    }
    
    @Test
    public void getRecoveryRateForMatterBasketAtElement_SortStation() {
        SortCenter sc = new SortCenter();
        EntryPoint ep = sc.addEntryPoint();
        Station st = sc.addSortStation(2);
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(), new Float(1000));
        mb.addMatterQuantity(m2.getID(), new Float(1000));
        ep.setMatterBasket(mb);
        HashMap<Integer, ArrayList<Float>> smatrix = new HashMap<>();
        ArrayList<Float> innerList = new ArrayList<>();
        innerList.add(new Float(0.75));
        innerList.add(new Float(0.25));
        smatrix.put(1, innerList);
        ArrayList<Float> innerList2 = new ArrayList<>();
        innerList2.add(new Float(0.55));
        innerList2.add(new Float(0.45));
        smatrix.put(2, innerList2);
        SortMatrix sorter = new SortMatrix();
        sorter.setSortMatrix(smatrix);
        
        st.setSortMatrix(sorter);
        Conveyor conv = sc.addConveyor(ep.getOutlet(), st.getInlet());
        HashMap<Integer,Float> recov = sc.getRecoveryRateForMatterBasketAtElement(st);
        assertTrue(recov.get(1)==0.5);
        assertTrue(recov.get(2)==0.5);
    }
    
     @Test
    public void getRecoveryRateForMatterBasketAtElement_ConveyorEntryExit_emptyBaskets() {
        System.out.println("Recovery rate for Conveyor: empty MB");
        MatterBasket mb = new MatterBasket();
        MatterBasket mb2 = new MatterBasket();
        SortCenter sc = new SortCenter();
        sc.addEntryPoint();
        sc.addEntryPoint();
        sc.getEntryPoints().get(0).setMatterBasket(mb);
        sc.getEntryPoints().get(1).setMatterBasket(mb2);
        sc.addExitPoint();
        sc.addExitPoint();
        MatterBasket totalMB = sc.getTotalEntryPointsMatterBasket();
        float grandTotal = totalMB.getTotalQuantity();
        assertTrue(grandTotal==0); 
        Conveyor conv = sc.addConveyor(sc.getEntryPointOutlet(0), sc.getExitPointInlet(0));
        Conveyor conv2 = sc.addConveyor(sc.getEntryPointOutlet(1), sc.getExitPointInlet(1));
        //Conveyor
        HashMap<Integer,Float> recov = sc.getRecoveryRateForMatterBasketAtElement(conv);
        HashMap<Integer,Float> recov2 = sc.getRecoveryRateForMatterBasketAtElement(conv2);
        assertTrue(recov.isEmpty() && recov2.isEmpty());
        //EntryPoint
        recov = sc.getRecoveryRateForMatterBasketAtElement(sc.getEntryPoints().get(0));
        recov2 = sc.getRecoveryRateForMatterBasketAtElement(sc.getEntryPoints().get(1));
        assertTrue(recov.isEmpty() && recov2.isEmpty());
        //ExitPoint
        ArrayList<ExitPoint> xplist = sc.getExitPoints();
        ExitPoint xp = xplist.get(0);
        recov = sc.getRecoveryRateForMatterBasketAtElement(xp);
        xp = xplist.get(1);
        recov2 = sc.getRecoveryRateForMatterBasketAtElement(xp);
        assertTrue(recov.isEmpty() && recov2.isEmpty());
    }
    
    @Test
    public void getPurityRateForMatterBasketAtElement_SortStation() {
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(), new Float(1500));
        mb.addMatterQuantity(m2.getID(), new Float(500));
        SortCenter sc = new SortCenter();
        SortStation ss = sc.addSortStation(2);
        HashMap<Integer, ArrayList<Float>> smatrix = new HashMap<>();
        ArrayList<Float> innerList = new ArrayList<>();
        innerList.add(new Float(0.75));
        innerList.add(new Float(0.25));
        smatrix.put(1, innerList);
        ArrayList<Float> innerList2 = new ArrayList<>();
        innerList2.add(new Float(0.55));
        innerList2.add(new Float(0.45));
        smatrix.put(2, innerList2);
        SortMatrix sorter = new SortMatrix();
        sorter.setSortMatrix(smatrix);
        ss.setSortMatrix(sorter);
        ss.processMatterBasket(mb);
        HashMap<Integer,Float> purity = sc.getPurityRateForMatterBasketAtElement(ss);
        assertTrue(purity.get(1)==0.75);
        assertTrue(purity.get(2)==0.25);
    }
    
    @Test
    public void getPurityRateForMatterBasketAtElement_TransStation() {
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(), new Float(1500));
        mb.addMatterQuantity(m2.getID(), new Float(500));
        SortCenter sc = new SortCenter();
        TransStation ts = sc.addTransStation(2);
        HashMap<Integer, ArrayList<Float>> smatrix = new HashMap<>();
        ArrayList<Float> innerList = new ArrayList<>();
        innerList.add(new Float(0.75));
        innerList.add(new Float(0.25));
        smatrix.put(1, innerList);
        ArrayList<Float> innerList2 = new ArrayList<>();
        innerList2.add(new Float(0.55));
        innerList2.add(new Float(0.45));
        smatrix.put(2, innerList2);
        SortMatrix sorter = new SortMatrix();
        sorter.setSortMatrix(smatrix);
        ts.setSortMatrix(sorter);
        HashMap<Integer, Float> transformQty1 = new HashMap<>();
        transformQty1.put(1,new Float(1));
        transformQty1.put(2, new Float(0));
        HashMap<Integer, Float> transformQty2 = new HashMap<>();
        transformQty2.put(1, new Float(1));
        transformQty2.put(2, new Float(0));
        HashMap<Integer, HashMap<Integer, Float>> tMatrix = new HashMap<>();
        tMatrix.put(1, transformQty1);
        tMatrix.put(2, transformQty2);
        TransMatrix transMatrix = new TransMatrix();
        transMatrix.setTransMatrix(tMatrix);
        ts.setTransMatrix(transMatrix);
        
        ts.processMatterBasket(mb);
        HashMap<Integer,Float> purity = sc.getPurityRateForMatterBasketAtElement(ts);
        assertTrue(purity.get(1)==1);
        assertTrue(purity.get(2)==0);
    }
    
    @Test
    public void getPurityRateForMatterBasketAtElement_Junction() {
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(), new Float(1500));
        mb.addMatterQuantity(m2.getID(), new Float(500));
        SortCenter sc = new SortCenter();
        Junction jc = sc.addJunction();
        jc.processMatterBasket(mb);
        HashMap<Integer,Float> purity = sc.getPurityRateForMatterBasketAtElement(jc);
        assertTrue(purity.get(1)==0.75);
        assertTrue(purity.get(2)==0.25);
    }
    
    @Test
    public void getPurityRateForMatterBasketAtElement_EntryPoint() {
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(), new Float(1500));
        mb.addMatterQuantity(m2.getID(), new Float(500));
        SortCenter sc = new SortCenter();
        EntryPoint ep = sc.addEntryPoint();
        ep.processMatterBasket(mb);
        HashMap<Integer,Float> purity = sc.getPurityRateForMatterBasketAtElement(ep);
        assertTrue(purity.get(1)==0.75);
        assertTrue(purity.get(2)==0.25);
    }
    
    @Test
    public void getPurityRateForMatterBasketAtElement_ExitPoint() {
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(), new Float(1500));
        mb.addMatterQuantity(m2.getID(), new Float(500));
        SortCenter sc = new SortCenter();
        ExitPoint xp = sc.addExitPoint();
        xp.processMatterBasket(mb);
        HashMap<Integer,Float> purity = sc.getPurityRateForMatterBasketAtElement(xp);
        assertTrue(purity.get(1)==0.75);
        assertTrue(purity.get(2)==0.25);
    }
    
    @Test
    public void getPurityRateForMatterBasketAtElement_Conveyor() {
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        MatterBasket mb = new MatterBasket();
        mb.addMatterQuantity(m1.getID(), new Float(1500));
        mb.addMatterQuantity(m2.getID(), new Float(500));
        SortCenter sc = new SortCenter();
        EntryPoint ep = sc.addEntryPoint();
        ep.setMatterBasket(mb);
        ExitPoint xp = sc.addExitPoint();
        Conveyor conv = sc.addConveyor(ep.getOutlet(), xp.getInlet());
        HashMap<Integer,Float> purity = sc.getPurityRateForMatterBasketAtElement(conv);
        assertTrue(purity.get(1)==0.75);
        assertTrue(purity.get(2)==0.25);
    }
    
    @Test
    public void getPurityRateForMatterBasketAtElement_EmptyBasket() {
        MatterBasket mb = new MatterBasket();
        SortCenter sc = new SortCenter();
        EntryPoint ep = sc.addEntryPoint();
        ep.setMatterBasket(mb);
        HashMap<Integer,Float> purity = sc.getPurityRateForMatterBasketAtElement(ep);
        assertTrue(purity.isEmpty());
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
        Station tstation = sc.addTransStation(1);
//        Station tstation = sc.getStations().get(0);
        tstation.setSortMatrix(sorter);
        tstation.setTransMatrix(transMatrix);
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
    
//    @Test
//    public void updateDesignTest3() {
//        System.out.println("Update design test 3 : design sans convoyeurs reliés à la sortie (sortie vide).");
//        //construire matterBasket
//        Matter m1 = new Matter("m1",11);
//        Matter m2 = new Matter("m2",12);
//        MatterBasket mb = new MatterBasket();
//        mb.addMatterQuantity(m1.getID(), new Float(100));
//        mb.addMatterQuantity(m2.getID(), new Float(1000));
//        SortCenter sc = new SortCenter();
//        sc.addEntryPoint();
//        sc.addExitPoint();
//       //conveyor end points
//        sc.setEntryPointMatterBasket(0, mb);
//        sc.updateDesign();
//        MatterBasket newMb = sc.getExitPointMatterBasket(0);
//        assertTrue(newMb.getNumberOfMatterInBasket()==0);
//    }
    
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
        mlist.addMatterToList(m1);
        mlist.addMatterToList(m2);
        
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
        sc.addTransStation(1);                           //S0
        Station s0 = sc.getStations().get(0);
        s0.setSortMatrix(sorter);
        s0.setTransMatrix(transMatrix);
        sc.addSortStation(1);                           //S1
        Station s1 = sc.getStations().get(1);
        s1.setSortMatrix(sorter);
        sc.addSortStation(2);                           //S2
        Station s2 = sc.getStations().get(2);
        s2.setSortMatrix(sorter2);
        sc.addTransStation(2);                           //S3
        Station s3 = sc.getStations().get(3);
        s3.setSortMatrix(sorter2);
        s3.setTransMatrix(transMatrix);
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
        //matterBasket de la station 0
        //outlet 0
        MatterBasket mbs00 = sc.getStationOutletList(0).get(0).getMatterBasket();
        assertTrue(mbs00.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs00.getMatterQuantity(1)==(float)765);
        assertTrue((float)mbs00.getMatterQuantity(2)==(float)935);
        //matterBasket de la station 1
        //outlet 0
        MatterBasket mbs10 = sc.getStationOutletList(1).get(0).getMatterBasket();
        assertTrue(mbs10.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs10.getMatterQuantity(1)==(float)1500);
        assertTrue((float)mbs10.getMatterQuantity(2)==(float)1200);
        //matterBasket de la station 2
        //outlet 0
        MatterBasket mbs20 = sc.getStationOutletList(2).get(0).getMatterBasket();
        assertTrue(mbs20.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs20.getMatterQuantity(1)==(float)1132.5);
        assertTrue((float)mbs20.getMatterQuantity(2)==(float)1601.25);
        //outlet 1
        MatterBasket mbs21 = sc.getStationOutletList(2).get(1).getMatterBasket();
        assertTrue(mbs21.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs21.getMatterQuantity(1)==(float)1132.5);
        assertTrue((float)mbs21.getMatterQuantity(2)==(float)533.75);
        //matterBasket de la station 3
        //outlet 0
        MatterBasket mbs30 = sc.getStationOutletList(3).get(0).getMatterBasket();
        assertTrue(mbs30.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs30.getMatterQuantity(1)==(float)564.625);
        assertTrue((float)mbs30.getMatterQuantity(2)==(float)402.75);
        //outlet 1
        MatterBasket mbs31 = sc.getStationOutletList(3).get(1).getMatterBasket();
        assertTrue(mbs31.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs31.getMatterQuantity(1)==(float)564.625);
        assertTrue((float)mbs31.getMatterQuantity(2)==(float)134.25);
        
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
        mlist.addMatterToList(m1);
        mlist.addMatterToList(m2);
        
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
        sc.addTransStation(1);                           //S0
        Station s0 = sc.getStations().get(0);
        s0.setSortMatrix(sorter);
        s0.setTransMatrix(transMatrix);
        sc.addSortStation(1);                           //S1
        Station s1 = sc.getStations().get(1);
        s1.setSortMatrix(sorter);
        sc.addSortStation(2);                           //S2
        Station s2 = sc.getStations().get(2);
        s2.setSortMatrix(sorter2);
        sc.addTransStation(2);                           //S3
        Station s3 = sc.getStations().get(3);
        s3.setSortMatrix(sorter2);
        s3.setTransMatrix(transMatrix);
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
        //matterBasket de la station 0
        //outlet 0
        MatterBasket mbs00 = sc.getStationOutletList(0).get(0).getMatterBasket();
        assertTrue(mbs00.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs00.getMatterQuantity(1)==(float)765);
        assertTrue((float)mbs00.getMatterQuantity(2)==(float)935);
        //matterBasket de la station 1
        //outlet 0
        MatterBasket mbs10 = sc.getStationOutletList(1).get(0).getMatterBasket();
        assertTrue(mbs10.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs10.getMatterQuantity(1)==(float)1500);
        assertTrue((float)mbs10.getMatterQuantity(2)==(float)1200);
        //matterBasket de la station 2
        //outlet 0
        MatterBasket mbs20 = sc.getStationOutletList(2).get(0).getMatterBasket();
        assertTrue(mbs20.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs20.getMatterQuantity(1)==(float)1132.5);
        assertTrue((float)mbs20.getMatterQuantity(2)==(float)1601.25);
        //outlet 1
        MatterBasket mbs21 = sc.getStationOutletList(2).get(1).getMatterBasket();
        assertTrue(mbs21.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs21.getMatterQuantity(1)==(float)1132.5);
        assertTrue((float)mbs21.getMatterQuantity(2)==(float)533.75);
        //matterBasket de la station 3
        //outlet 0
        MatterBasket mbs30 = sc.getStationOutletList(3).get(0).getMatterBasket();
        assertTrue(mbs30.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs30.getMatterQuantity(1)==(float)564.625);
        assertTrue((float)mbs30.getMatterQuantity(2)==(float)402.75);
        //outlet 1
        MatterBasket mbs31 = sc.getStationOutletList(3).get(1).getMatterBasket();
        assertTrue(mbs31.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs31.getMatterQuantity(1)==(float)564.625);
        assertTrue((float)mbs31.getMatterQuantity(2)==(float)134.25);
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
        mlist.addMatterToList(m1);
        mlist.addMatterToList(m2);
        
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
        sc.addTransStation(1);                           //S0
        Station s0 = sc.getStations().get(0);
        s0.setSortMatrix(sorter);
        s0.setTransMatrix(transMatrix);
        sc.addSortStation(1);                           //S1
        Station s1 = sc.getStations().get(1);
        s1.setSortMatrix(sorter);
        sc.addSortStation(2);                           //S2
        Station s2 = sc.getStations().get(2);
        s2.setSortMatrix(sorter2);
        sc.addTransStation(2);                           //S3
        Station s3 = sc.getStations().get(3);
        s3.setSortMatrix(sorter2);
        s3.setTransMatrix(transMatrix);
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
        //matterBasket de la station 0
        //outlet 0
        MatterBasket mbs00 = sc.getStationOutletList(0).get(0).getMatterBasket();
        assertTrue(mbs00.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs00.getMatterQuantity(1)==(float)765);
        assertTrue((float)mbs00.getMatterQuantity(2)==(float)935);
        //matterBasket de la station 1
        //outlet 0
        MatterBasket mbs10 = sc.getStationOutletList(1).get(0).getMatterBasket();
        assertTrue(mbs10.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs10.getMatterQuantity(1)==(float)1500);
        assertTrue((float)mbs10.getMatterQuantity(2)==(float)1200);
        //matterBasket de la station 2
        //outlet 0
        MatterBasket mbs20 = sc.getStationOutletList(2).get(0).getMatterBasket();
        assertTrue(mbs20.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs20.getMatterQuantity(1)==(float)1132.5);
        assertTrue((float)mbs20.getMatterQuantity(2)==(float)1601.25);
        //outlet 1
        MatterBasket mbs21 = sc.getStationOutletList(2).get(1).getMatterBasket();
        assertTrue(mbs21.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs21.getMatterQuantity(1)==(float)1132.5);
        assertTrue((float)mbs21.getMatterQuantity(2)==(float)533.75);
        //matterBasket de la station 3
        //outlet 0
        MatterBasket mbs30 = sc.getStationOutletList(3).get(0).getMatterBasket();
        assertTrue(mbs30.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs30.getMatterQuantity(1)==(float)564.625);
        assertTrue((float)mbs30.getMatterQuantity(2)==(float)402.75);
        //outlet 1
        MatterBasket mbs31 = sc.getStationOutletList(3).get(1).getMatterBasket();
        assertTrue(mbs31.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs31.getMatterQuantity(1)==(float)564.625);
        assertTrue((float)mbs31.getMatterQuantity(2)==(float)134.25);
        
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
    
    @Test
    public void updateDesignTest8() {
        System.out.println("Update design test 8 : 2 entryPoints, 2 transStation, 2 sortStation, 1 junction, 3 exits. Two tests same baskets. Station 3 disconnected after test 1.");
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
        sc.addTransStation(1);                           //S0
        Station s0 = sc.getStations().get(0);
        s0.setSortMatrix(sorter);
        s0.setTransMatrix(transMatrix);
        sc.addSortStation(1);                           //S1
        Station s1 = sc.getStations().get(1);
        s1.setSortMatrix(sorter);
        sc.addSortStation(2);                           //S2
        Station s2 = sc.getStations().get(2);
        s2.setSortMatrix(sorter2);
        sc.addTransStation(2);                           //S3
        Station s3 = sc.getStations().get(3);
        s3.setSortMatrix(sorter2);
        s3.setTransMatrix(transMatrix);
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
        //vérif initiale
        //matterBasket de la station 0
        //outlet 0
        MatterBasket mbs00 = sc.getStationOutletList(0).get(0).getMatterBasket();
        assertTrue(mbs00.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs00.getMatterQuantity(1)==(float)765);
        assertTrue((float)mbs00.getMatterQuantity(2)==(float)935);
        //matterBasket de la station 1
        //outlet 0
        MatterBasket mbs10 = sc.getStationOutletList(1).get(0).getMatterBasket();
        assertTrue(mbs10.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs10.getMatterQuantity(1)==(float)1500);
        assertTrue((float)mbs10.getMatterQuantity(2)==(float)1200);
        //matterBasket de la station 2
        //outlet 0
        MatterBasket mbs20 = sc.getStationOutletList(2).get(0).getMatterBasket();
        assertTrue(mbs20.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs20.getMatterQuantity(1)==(float)1132.5);
        assertTrue((float)mbs20.getMatterQuantity(2)==(float)1601.25);
        //outlet 1
        MatterBasket mbs21 = sc.getStationOutletList(2).get(1).getMatterBasket();
        assertTrue(mbs21.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs21.getMatterQuantity(1)==(float)1132.5);
        assertTrue((float)mbs21.getMatterQuantity(2)==(float)533.75);
        //matterBasket de la station 3
        //outlet 0
        MatterBasket mbs30 = sc.getStationOutletList(3).get(0).getMatterBasket();
        assertTrue(mbs30.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs30.getMatterQuantity(1)==(float)564.625);
        assertTrue((float)mbs30.getMatterQuantity(2)==(float)402.75);
        //outlet 1
        MatterBasket mbs31 = sc.getStationOutletList(3).get(1).getMatterBasket();
        assertTrue(mbs31.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs31.getMatterQuantity(1)==(float)564.625);
        assertTrue((float)mbs31.getMatterQuantity(2)==(float)134.25);
        
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
        
        
        
        
        //enlever convoyeur 6 et revérifier que les stations et nodes déconnectés sont à 0
        sc.getConveyors().remove(6);
        sc.updateDesign();
        //matterBasket de la station 0
        //outlet 0
        mbs00 = sc.getStationOutletList(0).get(0).getMatterBasket();
        assertTrue(mbs00.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs00.getMatterQuantity(1)==(float)765);
        assertTrue((float)mbs00.getMatterQuantity(2)==(float)935);
        //matterBasket de la station 1
        //outlet 0
        mbs10 = sc.getStationOutletList(1).get(0).getMatterBasket();
        assertTrue(mbs10.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs10.getMatterQuantity(1)==(float)1500);
        assertTrue((float)mbs10.getMatterQuantity(2)==(float)1200);
        //matterBasket de la station 2
        //outlet 0
        mbs20 = sc.getStationOutletList(2).get(0).getMatterBasket();
        assertTrue(mbs20.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs20.getMatterQuantity(1)==(float)1132.5);
        assertTrue((float)mbs20.getMatterQuantity(2)==(float)1601.25);
        //outlet 1
        mbs21 = sc.getStationOutletList(2).get(1).getMatterBasket();
        assertTrue(mbs21.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs21.getMatterQuantity(1)==(float)1132.5);
        assertTrue((float)mbs21.getMatterQuantity(2)==(float)533.75);
        //matterBasket de la station 3
        //outlet 0
        mbs30 = sc.getStationOutletList(3).get(0).getMatterBasket();
        assertTrue(mbs30.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs30.getMatterQuantity(1)==(float)0);
        assertTrue((float)mbs30.getMatterQuantity(2)==(float)0);
        //outlet 1
        mbs31 = sc.getStationOutletList(3).get(1).getMatterBasket();
        assertTrue(mbs31.getNumberOfMatterInBasket()==2);
        assertTrue((float)mbs31.getMatterQuantity(1)==(float)0);
        assertTrue((float)mbs31.getMatterQuantity(2)==(float)0);
        
        //vérifier les quantités en sortie
        newMb0 = sc.getExitPointMatterBasket(0);
        newMb1 = sc.getExitPointMatterBasket(1);
        newMb2 = sc.getExitPointMatterBasket(2);
        //matterBasket de la jonction
        juncMb = sc.getJunctionMatterBasket(0);
        //confirmer qu'on a tous les bonnes quantités à la fin
        assertTrue(newMb0.getNumberOfMatterInBasket()==2);
        assertTrue(newMb1.getNumberOfMatterInBasket()==2);
        assertTrue(newMb2.getNumberOfMatterInBasket()==2);
        //vérification à la jonction
        assertTrue((float)juncMb.getMatterQuantity(1)==(float)2265);
        assertTrue((float)juncMb.getMatterQuantity(2)==(float)2135);
        assertTrue((float)newMb0.getMatterQuantity(1)==(float)1132.5);
        assertTrue((float)newMb0.getMatterQuantity(2)==(float)1601.25);
        assertTrue((float)newMb1.getMatterQuantity(1)==(float)0);
        assertTrue((float)newMb1.getMatterQuantity(2)==(float)0);
        assertTrue((float)newMb2.getMatterQuantity(1)==(float)0);
        assertTrue((float)newMb2.getMatterQuantity(2)==(float)0);
    }
    
    //vérifie verifyCycles en ajoutant un convoyeur (c'est addConveyor qui lance verifyCycles)
    //cas succès traités avec les sortCenter valides
    @Test(expected = IllegalArgumentException.class)
    public void verifyCyclesTest_fail_convoyeurIntroduitCycleIndirect() {
        System.out.println("Introduction d'un cycle entre une station et une jonction.");
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
        sc.addTransStation(1);                           //S0
        Station s0 = sc.getStations().get(0);
        s0.setSortMatrix(sorter);
        s0.setTransMatrix(transMatrix);
        sc.addSortStation(1);                           //S1
        Station s1 = sc.getStations().get(1);
        s1.setSortMatrix(sorter);
        sc.addSortStation(3);                           //S2
        Station s2 = sc.getStations().get(2);
        s2.setSortMatrix(sorter2);
        sc.addTransStation(2);                           //S3
        Station s3 = sc.getStations().get(3);
        s3.setSortMatrix(sorter2);
        s3.setTransMatrix(transMatrix);
        //créer la jonction et ses inlets/outlets
        sc.addJunction();
        Junction j1= sc.getJunction(0);
        j1.addInlet();
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
        //c9 -- INTRODUIT UN CYCLE
        sc.addConveyor(sc.getStationOutletList(2).get(2), sc.getJunctionInletList(0).get(2));
        //
        sc.setEntryPointMatterBasket(0, mb1);
        sc.setEntryPointMatterBasket(1, mb2);
        sc.updateDesign();

    }
    
    //vérifie verifyCycles en ajoutant un convoyeur (c'est addConveyor qui lance verifyCycles)
    @Test(expected = IllegalArgumentException.class)
    public void verifyCyclesTest_fail_directCycleBetweenStations() {
        System.out.println("Introduction d'un cycle entre une station et une jonction.");
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
        sc.addTransStation(1);                           //S0
        Station s0 = sc.getStations().get(0);
        s0.setSortMatrix(sorter);
        s0.setTransMatrix(transMatrix);
        sc.addSortStation(1);                           //S1
        Station s1 = sc.getStations().get(1);
        s1.setSortMatrix(sorter);
        sc.addSortStation(3);                           //S2
        Station s2 = sc.getStations().get(2);
        s2.setSortMatrix(sorter2);
        sc.addTransStation(2);                           //S3
        Station s3 = sc.getStations().get(3);
        s3.setSortMatrix(sorter2);
        s3.setTransMatrix(transMatrix);
        //créer la jonction et ses inlets/outlets
        sc.addJunction();
        Junction j1= sc.getJunction(0);
        j1.addInlet();
        j1.addInlet();
        j1.addInlet();
        //créer les convoyeurs
        //c0
        
        //c7
        sc.addConveyor(sc.getStationOutletList(0).get(0), sc.getStations().get(2).getInlet());
        //introduit cycle
        sc.addConveyor(sc.getStationOutletList(2).get(0), sc.getStations().get(0).getInlet());
        
        sc.setEntryPointMatterBasket(0, mb1);
        sc.setEntryPointMatterBasket(1, mb2);
        sc.updateDesign();

    }
}
