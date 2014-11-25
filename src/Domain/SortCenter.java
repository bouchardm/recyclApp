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
//import jdk.nashorn.internal.objects.NativeArray;

/**
 *
 * @author Dany
 */
public class SortCenter extends Element {

    private ArrayList<Conveyor> _conveyorList;
    private ArrayList<EntryPoint> _entrytPointList;
    private ArrayList<ExitPoint> _exitPointList;
    private ArrayList<Station> _stationList;
    private ArrayList<Junction> _junctionList;
    private Float _size;
    private MatterList _matterList;

    public SortCenter() {
        _conveyorList = new ArrayList<>();
        _entrytPointList = new ArrayList<>();
        _exitPointList = new ArrayList<>();
        _stationList = new ArrayList<>();
        _junctionList = new ArrayList<>();
        _matterList = new MatterList();
        dimensions = new Point2D.Float(15f, 10f);
    }

    public SortStation addSortStation(int numberOfOutlets) {
        SortStation station = new SortStation();
        for (int i = 0; i < numberOfOutlets; i++) {
            station.addOutlet();
        }
        station.setSortMatrix(new SortMatrix(this._matterList, station.getOutletList().size()));
        this._stationList.add(station);
        return station;
    }

    public void deleteStation(Station station) {
        for (int i = 0; i < this._stationList.size(); i++) {
            if (this._stationList.get(i).equals(station)) {
                deleteConveyor(station);
                this._stationList.remove(i);
                return;
            }
        }
    }

    public void deleteConveyor(Node station) {

        ArrayList<IOlet> iolets = station.getIOlets();
        for (int i = 0; i < iolets.size(); i++) {
            Conveyor aConveyor = iolets.get(i).getConveyor();

            for (int j = 0; j < this._conveyorList.size(); j++) {
                if (_conveyorList.get(j).equals(aConveyor)) {
                    _conveyorList.get(j).removeConveyor();
                    _conveyorList.remove(j);
                    break;
                }

            }
        }

    }

//     DeleteConvyeor from a junction
    public void deleteConveyorFromJunction(Junction junction) {

        ArrayList<IOlet> iolets = junction.getIOlets();
        for (int i = 0; i < iolets.size(); i++) {

            ArrayList<Conveyor> aConveyorList = iolets.get(i).getConveyorList();
            for (int j = 0; j < aConveyorList.size(); j++) {

                Conveyor aConveyor = aConveyorList.get(j);
                for (int k = 0; k < this._conveyorList.size(); k++) {
                    if (_conveyorList.get(k).equals(aConveyor)) {
                        _conveyorList.get(k).removeConveyor();
                        _conveyorList.remove(k);
//                        break;
                    }

                }
            }
        }

    }
    
    public void deleteConveyor(Conveyor conveyor) {
        this._conveyorList.remove(conveyor);
    }

    //retourne la quantité totale de matière dans une station
    public float getTotalMatterQuantityAtStation(int stationIndex) {
        return (float) _stationList.get(stationIndex).getAttribute("matterQuantity");
    }

    //retourne la quantité totale de matière à un outlet précis d'une station
    public float getMatterQuantityAtStationOutlet(int stationIndex, int outletAtStationIndex) {
        return _stationList.get(stationIndex).getTotalMatterAtOutlet(outletAtStationIndex);
    }

    public ArrayList getEntryPoints() {
        return _entrytPointList;
    }

    //retourne l'Outlet d'un point d'entrée à l'index "index" de la liste
    public Outlet getEntryPointOutlet(int index) {
        return _entrytPointList.get(index).getOutlet();
    }

    public void deleteEntryPoint(EntryPoint entryPoint) {
        for (int i = 0; i < this._entrytPointList.size(); i++) {
            if (this._entrytPointList.get(i).equals(entryPoint)) {
                this.deleteConveyor(entryPoint);
                this._entrytPointList.remove(i);
                return;
            }
        }
    }
    
    public void deleteExitPoint(ExitPoint exitPoint)
    {
        this.deleteConveyor(exitPoint);
        _exitPointList.remove(exitPoint);
    }

    //retourne l'Inlet d'un point de sortie à l'index "index" de la liste
    public Inlet getExitPointInlet(int index) {
        return _exitPointList.get(index).getInlet();
    }

    public ArrayList getExitPoints() {
        return _exitPointList;
    }

    public void addMatterToMatterList(Matter matter) {
        this._matterList.Add(matter);
    }

    public ArrayList getJunctions() {
        return _junctionList;
    }

    //retourne la jonction à l'indice "index" de la liste
    public Junction getJunction(int index) {
        return _junctionList.get(index);
    }

    public ArrayList getConveyors() {
        return _conveyorList;
    }

    //retourne le matterBasket de la jonction à l'indice "index"
    public MatterBasket getJunctionMatterBasket(int index) {
        return this._junctionList.get(index).getMatterBasket();
    }

    public ArrayList<Station> getStations() {
        return _stationList;
    }

    public MatterList getMatterList() {
        return _matterList;
    }

    public void setMatterList(MatterList _matterList) {
        this._matterList = _matterList;
    }

    //retourne la liste d'Outlet de la station à l'indice "index" de la liste
    public ArrayList<Outlet> getStationOutletList(int index) {
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
        this.resetJunctionMatterBaskets();
        //on crée une liste maître des nodes du centre de tri
        ArrayList<Node> allNodes = new ArrayList<>(); //NEW
        allNodes.addAll(this._entrytPointList);
        allNodes.addAll(this._exitPointList);
        allNodes.addAll(this._junctionList);
        allNodes.addAll(this._stationList);
        //for all EntryPoints
        //on ajoute les entry point a une liste de nodes à traiter
        ArrayList<Node> equipmentToProcess = new ArrayList<>();
        for (Node node : _entrytPointList) {
            equipmentToProcess.add(node);
        }
        //for all Conveyors
        //on ajoute tous les convoyeurs à traiter à un map jumelant le convoyeur
        //et son point de départ <Conveyor, StartNode>
        HashMap<Conveyor, Node> conveyorToProcess = new HashMap<>();
        for (Conveyor conveyor : _conveyorList) {
            Node conveyorStartNode = conveyor.getStartNode();
            conveyorToProcess.put(conveyor, conveyorStartNode);
        }
        while (!equipmentToProcess.isEmpty()) {
            Node currentNode = equipmentToProcess.get(0);
            equipmentToProcess.remove(0);
            allNodes.remove(currentNode); //NEW
            //prendre tous les convoyeurs qui ont le node courant comme point de départ
            //et les mettres dans une liste & les enlever de la liste à traiter
            Iterator<Map.Entry<Conveyor, Node>> convIter = conveyorToProcess.entrySet().iterator();
            ArrayList<Conveyor> currentConveyors = new ArrayList<>();
            while (convIter.hasNext()) {
                Map.Entry<Conveyor, Node> currentConveyorEntry = convIter.next();
                Conveyor currentConveyor = currentConveyorEntry.getKey();
                Node currentConveyorOrigin = currentConveyorEntry.getValue();
                if (currentConveyor.getStartNode() == currentNode) {
                    currentConveyors.add(currentConveyor);
                    convIter.remove();
                }
            }
            //pour chaque convoyeur à traiter,
            for (int convCtr = 0; convCtr < currentConveyors.size(); convCtr++) {
                //trouver la destination
                Conveyor processingConveyor = currentConveyors.get(convCtr);
                Node destination = processingConveyor.getEndNode();
                //faire la mise à jour du matterBasket à la destination
                //(comme on fait toujours le traitement à la destination, le
                // entryPoint n'est jamais traité. Son matterBasket doit être
                // mis à jour avant)
                destination.processMatterBasket(processingConveyor.getMatterBasket());
                equipmentToProcess.add(destination);
            }
        }
        //NEW: maintenant, on doit "resetter" le panier de matières à n'importe quelle node
        //qui n'est pas connectée au réseau (C'est à dire, tout ce qui reste dans allNodes)
        MatterBasket emptyBasket = new MatterBasket(this._matterList);
        for (Node unconnectedNode : allNodes) {
            unconnectedNode.setMatterBasketAtOutlets(emptyBasket);
        }
    }

    //vérifie le réseau pour voir si l'introduction de ce convoyeur introduit un cycle
    public void verifyCycles(Node startNode) {
        ArrayList<Node> visitedNodes = new ArrayList<>();
        ArrayList<Node> nodesToCheck = new ArrayList<>();
        nodesToCheck.add(startNode);
        while (!nodesToCheck.isEmpty()) {
            Node currentNode = nodesToCheck.get(0);
            nodesToCheck.remove(currentNode);
            visitedNodes.add(currentNode);
            ArrayList<Conveyor> conveyorsToCheck = new ArrayList<>();
            for (Conveyor convIter : this._conveyorList) {
                if (convIter.getStartNode() == currentNode) {
                    conveyorsToCheck.add(convIter);
                }
            }
            for (Conveyor convCheck : conveyorsToCheck) {
                Node destination = convCheck.getEndNode();
                if (visitedNodes.contains(destination)) {
                    throw new IllegalArgumentException("Le réseau contient un cycle.");
                } else {
                    nodesToCheck.add(destination);

                }
            }
        }

    }

    public TransStation addTransStation(int numberOfOutlets) {
        TransStation station = new TransStation();
        for (int i = 0; i < numberOfOutlets; i++) {
            station.addOutlet();
        }
        station.setSortMatrix(new SortMatrix(this._matterList, station.getOutletCount()));
        station.setTransMatrix(new TransMatrix(this._matterList));
        this._stationList.add(station);
        return station;
    }

    public void setSortStationMatrix(int index, SortMatrix sm) {
        this._stationList.get(index).setSortMatrix(sm);
    }

    public void setTransStationMatrix(int index, TransMatrix tm) {
        if (this._stationList.get(index).getClass() != TransStation.class) {
            throw new IllegalArgumentException("Ce n'est pas une station de transformation.");
        } else {
            this._stationList.get(index).setTransMatrix(tm);
        }
    }

    public void addConveyor(Outlet aExit, Inlet aEntrance) {

        Conveyor newConv = new Conveyor(aExit, aEntrance);
        try {

            this._conveyorList.add(newConv);
            verifyCycles(aExit.getNode());
        } catch (IllegalArgumentException e) {
            this._conveyorList.remove(newConv);
            newConv.removeConveyor();
            throw new IllegalArgumentException("Ce convoyeur introduit un cycle.");

        }

    }

    public Junction addJunction() {
        Junction junction = new Junction();
        this._junctionList.add(junction);
        return junction;
    }

    public void deleteJunction(Junction junction) {
        for (int i = 0; i < this._junctionList.size(); i++) {
            if (this._junctionList.get(i).equals(junction)) {
                deleteConveyor(junction);
                deleteConveyorFromJunction(junction);
                this._junctionList.remove(i);
                return;
            }
        }
    }

    //ajoute un nouveau entryPoint à la fin de la liste
    public EntryPoint addEntryPoint() {
        EntryPoint entryPoint = new EntryPoint();
        this._entrytPointList.add(entryPoint);
        return entryPoint;
    }

    //ajoute un nouveau exitPoint à la fin de la liste
    public ExitPoint addExitPoint() {
        ExitPoint exitPoint = new ExitPoint();
        this._exitPointList.add(exitPoint);
        return exitPoint;
    }

    private Point2D.Float dimensions;

    @Override
    public boolean include(Point2D.Float point) {
        return (0 <= point.x && point.x <= dimensions.x)
                && (0 <= point.y && point.y <= dimensions.y);
    }

    public Point2D.Float getDimensions() {
        return dimensions;
    }

    public void setDimensions(Float x, Float y) {
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
            SortStation next = (SortStation) iterator.next();

            if (next.include(position)) {
                // Change la position de l'element déplacer dans la list
                int i = sortStationList.indexOf(next);
                if (i > 0) {
                    Collections.swap(sortStationList, i, i - 1);
                }
                return next;
            }
        }

        return null;
    }

    //change le matter basket du point d'entrée "index" pour le matterbasket en entrée
    public void setEntryPointMatterBasket(int index, MatterBasket matterBasket) {
        _entrytPointList.get(index).setMatterBasket(matterBasket);
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

    //méthode utilitaire avant de faire updateDesign
    public void resetJunctionMatterBaskets() {
        for (Junction currentJunction : _junctionList) {
            MatterBasket emptyMatterBasket = new MatterBasket(this._matterList);
            currentJunction.getOutlet().setMatterBasket(emptyMatterBasket);

        }
    }

}
