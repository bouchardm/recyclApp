/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import Application.Controller.Controller;
import java.awt.List;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dany
 */
public class SortCenter extends Element
{
    private ArrayList<Conveyor> _conveyorList;
    private ArrayList<EntryPoint> _entryPointList;
    private ArrayList<ExitPoint> _exitPointList;
    private ArrayList<Station> _stationList;
    private ArrayList<Junction> _junctionList;
    private Float _size;
    private MatterList _matterList;

    public SortCenter() {
        _conveyorList = new ArrayList<>();
        _entryPointList = new ArrayList<>();
        _exitPointList = new ArrayList<>();
        _stationList = new ArrayList<>();
        _junctionList = new ArrayList<>();
        dimensions = new Point2D.Float(15f, 10f);
    }
    
    public SortStation addSortStation() {
        SortStation station = new SortStation();
        this._stationList.add(station);
        return station;
    }
    
    public ArrayList getEntryPoints()
    {
        return _entryPointList;
    }
    
    //retourne l'Outlet d'un point d'entrée à l'index "index" de la liste
    public Outlet getEntryPointOutlet(int index)
    {
        return _entryPointList.get(index).getOutlet();
    }
    
    //retourne l'Inlet d'un point de sortie à l'index "index" de la liste
    public Inlet getExitPointInlet(int index) {
        return _exitPointList.get(index).getInlet();
    }
    
    public ArrayList getExitPoints()
    {
        return _exitPointList;
    }
    
    public ArrayList getJunctions()
    {
        return _junctionList;
    }
    
    //retourne la jonction à l'indice "index" de la liste
    public Junction getJunction(int index) {
        return _junctionList.get(index);
    }
    
    public ArrayList getConveyors()
    {
        return _conveyorList;
    }
    
    //retourne le matterBasket de la jonction à l'indice "index"
    public MatterBasket getJunctionMatterBasket(int index) {
        return this._junctionList.get(index).getMatterBasket();
    }
    
    public ArrayList<Station> getStations()
    {
        return _stationList;
    }
    
    public MatterList getMatterList() {
        return _matterList;
    }

    public void setMatterList(MatterList _matterList) {
        this._matterList = _matterList;
    }
    
    //retourne la liste d'Outlet de la station à l'indice "index" de la liste
    public ArrayList<Outlet> getStationOutletList(int index){
        return this._stationList.get(index).getOutletList();
    }
    
    //retourne la liste d'Inlet d'une jonction à l'indice "index" de la liste
    public ArrayList<Inlet> getJunctionInletList(int index) {
        return this._junctionList.get(index).getInletList();
    }
    
    //retourne l'Outlet d'une jonction à l'indice "index" de la liste
    public Outlet getJunctionOutlet(int index) {
        return this._junctionList.get(index).getOutlet();
    }
    

    public void updateDesign() {
        //for all EntryPoints
        //on ajoute les entry point a une liste de nodes à traiter
        ArrayList<Node> equipmentToProcess = new ArrayList<>();
        for(Node node : _entryPointList) {
            equipmentToProcess.add(node);
        }
        //for all Conveyors
        //on ajoute tous les convoyeurs à traiter à un map jumelant le convoyeur
            //et son point de départ <Conveyor, StartNode>
        HashMap<Conveyor, Node> conveyorToProcess = new HashMap<>();
        for(Conveyor conveyor : _conveyorList) {
            Node conveyorStartNode = conveyor.getStartNode();
            conveyorToProcess.put(conveyor, conveyorStartNode);
        }
        while(!equipmentToProcess.isEmpty()) {
            Node currentNode = equipmentToProcess.get(0);
            equipmentToProcess.remove(0);
            //prendre tous les convoyeurs qui ont le node courant comme point de départ
            //et les mettres dans une liste & les enlever de la liste à traiter
            Iterator<Map.Entry<Conveyor, Node>> convIter = conveyorToProcess.entrySet().iterator();
            ArrayList<Conveyor> currentConveyors = new ArrayList<>();
            while(convIter.hasNext()) {
                Map.Entry<Conveyor, Node> currentConveyorEntry = convIter.next();
                Conveyor currentConveyor = currentConveyorEntry.getKey();
                Node currentConveyorOrigin = currentConveyorEntry.getValue();
                if(currentConveyor.getStartNode()==currentNode) {
                    currentConveyors.add(currentConveyor);
                    convIter.remove();
                }
            }
            //pour chaque convoyeur à traiter,
            for(int convCtr = 0; convCtr<currentConveyors.size(); convCtr++) {
                //trouver la destination
                Conveyor processingConveyor = currentConveyors.get(convCtr);
                Node destination = processingConveyor.getEndNode();
                //faire la mise à jour du matterBasket à la destination
                //(comme on fait toujours le traitement à la destination, le
                // entryPoint n'est jamais traité. Son matterBasket doit être
                // mis à jour avant)
                System.out.println(destination.getClass());
                destination.processMatterBasket(processingConveyor.getMatterBasket());
                System.out.println("here");
                equipmentToProcess.add(destination);
            }
                
            
        }

            

            
    }

    public TransStation addTransStation() {
        TransStation station = new TransStation();
        this._stationList.add(station);
        return station;
    }
    
    public void setSortStationMatrix(int index, SortMatrix sm) {
        this._stationList.get(index).setSortMatrix(sm);
    }
    
    public void setTransStationMatrix(int index, TransMatrix tm) {
        if(this._stationList.get(index).getClass() != TransStation.class) {
            throw new IllegalArgumentException("Ce n'est pas une station de transformation.");
        }
        else {
            this._stationList.get(index).setTransMatrix(tm);
        }
    }

    public void addConveyor(Outlet aExit, Inlet aEntrance) {
        this._conveyorList.add(new Conveyor(aExit, aEntrance));
    }

    public void addJunction() {
        this._junctionList.add(new Junction());
    }

    //ajoute un nouveau entryPoint à la fin de la liste
    public void addEntryPoint() {
        this._entryPointList.add(new EntryPoint());
    }

    //ajoute un nouveau exitPoint à la fin de la liste
    public void addExitPoint() {
        this._exitPointList.add(new ExitPoint());
    }
    
    private Point2D.Float dimensions;
    
    @Override
    public boolean include(Point2D.Float point)
    {
        return (0 <= point.x && point.x <= dimensions.x) && 
                (0 <= point.y && point.y <= dimensions.y);
    }
    
    
    public Point2D.Float getDimensions()
    {
        return dimensions;
    }
    
    public void setDimensions(Float x, Float y)
    {
        dimensions.x = x;
        dimensions.y = y;
    }
//      on a déjà getStations() plus haut qui fait la même chose
//    public ArrayList getSortStationList() {
//        return _sortStationList;
//    }
    
    public SortStation getStationCursorIn(Point2D.Float position) {
        ArrayList sortStationList = this.getStations();
        
        for (Iterator iterator = sortStationList.iterator(); iterator.hasNext();) {            
            SortStation next = (SortStation)iterator.next();

            if (next.include(position)) {
                // Change la position de l'element déplacer dans la list
                int i = sortStationList.indexOf(next);
                if (i > 0) {
                    Collections.swap(sortStationList, i, i-1);
                }
                return next;
            }
        }
        
        return null;
    }
    
    //change le matter basket du point d'entrée "index" pour le matterbasket en entrée
    public void setEntryPointMatterBasket(int index, MatterBasket matterBasket) {
        _entryPointList.get(index).setMatterBasket(matterBasket);
    }
    
    //obtient le matterbasket de la sortie à "index"
    public MatterBasket getExitPointMatterBasket(int index) {
        return _exitPointList.get(index).getMatterBasket();
    }

    @Override
    public void setAttribute(String attribName, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getAttribute(String attribName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
