/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Application.Controller.Controller;
import Presentation.Swing.InfoEntryPointFrame;
import Presentation.Swing.InfoExitPointFrame;
import java.awt.Color;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Dany
 */
public class SortCenter extends Element {

    private ArrayList<Conveyor> _conveyorList;
    private ArrayList<EntryPoint> _entryPointList;
    private ArrayList<ExitPoint> _exitPointList;
    private ArrayList<Station> _stationList;
    private ArrayList<Junction> _junctionList;
    private Float _size;
    private MatterList _matterList;
    private Image _img;

    public SortCenter() {
        _conveyorList = new ArrayList<>();
        _entryPointList = new ArrayList<>();
        _exitPointList = new ArrayList<>();
        _stationList = new ArrayList<>();
        _junctionList = new ArrayList<>();
        _matterList = new MatterList();
        dimensions = new Point2D.Float(15f, 10f);
        _img = null;
        int level = 240;
        setColor(new Color(level, level, level));
       
    }

    public SortStation addSortStation(int numberOfOutlets) {
        SortStation station = new SortStation();
        for (int i = 0; i < numberOfOutlets; i++) {
            station.addOutlet();
        }
        station.setSortMatrix(new SortMatrix(this._matterList, station.getOutletList().size()));
        this._stationList.add(station);
        this.updateDesign();
        return station;
    }

    public void deleteStation(Station station) {
        for (int i = 0; i < this._stationList.size(); i++) {
            if (this._stationList.get(i).equals(station)) {
                deleteConveyor(station);
                this._stationList.remove(i);
                this.updateDesign();
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
        
        this.updateDesign();

    }
    
    public BigDecimal getTotalStationUsageRate() {
        float totalQtyInTreatment = 0;
        float totalCapacity = 0;
        BigDecimal usage = new BigDecimal(0);
        for(Station station : this._stationList) {
            totalQtyInTreatment = totalQtyInTreatment + station.getTotalMatterQuantity();
            totalCapacity = totalCapacity + station.getKgHMax();
        }
        if(totalCapacity!=0) {
            usage = new BigDecimal(totalQtyInTreatment/totalCapacity);
        }
        return usage;
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
        this.updateDesign();

    }
    
    public void deleteConveyor(Conveyor conveyor) {
       int index = this._conveyorList.indexOf(conveyor);
        _conveyorList.get(index).removeConveyor();
        this._conveyorList.remove(conveyor);
        this.updateDesign();
    }

    //retourne la quantité totale de matière dans une station
    public float getTotalMatterQuantityAtStation(int stationIndex) {
        return (float) _stationList.get(stationIndex).getAttribute("matterQuantity");
    }

    //retourne la quantité totale de matière à un outlet précis d'une station
    public float getMatterQuantityAtStationOutlet(int stationIndex, int outletAtStationIndex) {
        return _stationList.get(stationIndex).getTotalMatterAtOutlet(outletAtStationIndex);
    }

    public ArrayList<EntryPoint> getEntryPoints() {
        return _entryPointList;
    }

    //retourne l'Outlet d'un point d'entrée à l'index "index" de la liste
    public Outlet getEntryPointOutlet(int index) {
        return _entryPointList.get(index).getOutlet();
    }

    public void deleteEntryPoint(EntryPoint entryPoint) {
        for (int i = 0; i < this._entryPointList.size(); i++) {
            if (this._entryPointList.get(i).equals(entryPoint)) {
                this.deleteConveyor(entryPoint);
                this._entryPointList.remove(i);
                this.updateDesign();
                return;
            }
        }
    }
    
    public void deleteExitPoint(ExitPoint exitPoint)
    {
        this.deleteConveyor(exitPoint);
        _exitPointList.remove(exitPoint);
        this.updateDesign();
    }

    //retourne l'Inlet d'un point de sortie à l'index "index" de la liste
    public Inlet getExitPointInlet(int index) {
        return _exitPointList.get(index).getInlet();
    }

    public ArrayList getExitPoints() {
        return _exitPointList;
    }

    public void addMatterToMatterList(String matterName) {
        try {
            int newMatterID = this.getNextMatterID();
            Matter matter = new Matter(matterName, newMatterID);
            this._matterList.addMatterToList(matter);
            //ajouter la matière aux matrices de transformation
            for(Station station : this.getStations()) {
                if(station instanceof TransStation) {
                    ((TransStation)station).getTransMatrix().addMatterToTransMatrix(matter.getID());
                }
            }
            //ajouter la matière aux paniers de matière aux entryPoints
            for(EntryPoint entryPoint : this._entryPointList) {
                MatterBasket mbAtEP = entryPoint.getMatterBasket();
                mbAtEP.addMatterQuantity(newMatterID, new Float(0));
                entryPoint.setMatterBasket(mbAtEP);
            }
            updateDesign();
        }
        catch(IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        }
    }
    
    public void removeMatterFromMatterList(int matterID) {
        try  {
            this._matterList.remove(matterID);
            for(Station station : this.getStations()) {
                if(station instanceof TransStation) {
                    ((TransStation)station).getTransMatrix().removeMatterFromMatrix(matterID);
                }
            }
            for (EntryPoint entryPoint : this.getEntryPoints()) {
                MatterBasket matterBasket = entryPoint.getMatterBasket();
                matterBasket.removeMatterQuantity(matterID);
                entryPoint.setMatterBasket(matterBasket);
            }
            updateDesign();
        }
        catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        }
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
    
    public int getNextMatterID() {
        int listSize = this._matterList.getCount();
        int nextMatterID=1;
        if(listSize!=0) {
            int lastMatterID = this._matterList.getMatterID(listSize-1);
            nextMatterID = lastMatterID+1;  
        }
        return nextMatterID;
        
    }
            

    public void updateDesign() {
        this.resetJunctionMatterBaskets();
        //on crée une liste maître des nodes du centre de tri
        ArrayList<Node> allNodes = new ArrayList<>(); //NEW
        allNodes.addAll(this._entryPointList);
        allNodes.addAll(this._exitPointList);
        allNodes.addAll(this._junctionList);
        allNodes.addAll(this._stationList);
        //for all EntryPoints
        //on ajoute les entry point a une liste de nodes à traiter
        ArrayList<Node> equipmentToProcess = new ArrayList<>();
        for (Node node : _entryPointList) {
            ((EntryPoint)node).clearErrorMessages();
            //NEW:on force les Entry à faire le processing de leurs paniers
            MatterBasket mbAtEntryPoint = ((EntryPoint)node).getMatterBasket();
            node.processMatterBasket(mbAtEntryPoint);
            equipmentToProcess.add(node);
        }
        //for all Conveyors
        //on ajoute tous les convoyeurs à traiter à un map jumelant le convoyeur
        //et son point de départ <Conveyor, StartNode>
        HashMap<Conveyor, Node> conveyorToProcess = new HashMap<>();
        for (Conveyor conveyor : _conveyorList) {
            //NEW: on enlève les messages d'erreur de tous les convoyeurs
            conveyor.clearErrorMessages();
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
                //NEW: si le basket tiens plus de matières que la capacité du convoyeur, on ajoute
                //un message d'erreur au convoyeur
                if(processingConveyor.getMatterBasket().getTotalQuantity()>processingConveyor.getSpeedMax()) {
                    processingConveyor.addErrorMessage("Ce convoyeur reçoit une quantité de matière plus élevée que sa capacité maximale.");
                }
                equipmentToProcess.add(destination);
            }
        }
        //NEW: maintenant, on doit "resetter" le panier de matières à n'importe quelle node
        //qui n'est pas connectée au réseau (C'est à dire, tout ce qui reste dans allNodes)
//        MatterBasket emptyBasket = new MatterBasket(this._matterList);
        MatterBasket emptyBasket = new MatterBasket();
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
                if (visitedNodes.contains(destination) && destination.getClass() != Junction.class) {
                    throw new IllegalArgumentException("Le réseau contient un cycle.");
                } else {
                    nodesToCheck.add(destination);

                }
            }
        }

    }
    //retourne un panier de matière contenant le grand total de chaque matière
    //en entrée au centre
    public MatterBasket getTotalEntryPointsMatterBasket(){
        MatterBasket totalMB = new MatterBasket();
        for(EntryPoint ep : this._entryPointList) {
            HashMap<Integer,Float> epMatterQuantities = ep.getMatterBasket().getQuantities();
            Iterator<Map.Entry<Integer,Float>> iter = epMatterQuantities.entrySet().iterator();
            while(iter.hasNext()) {
                Map.Entry<Integer, Float> entry = iter.next();
                if(totalMB.contains(entry.getKey())){
                    int currentKey = entry.getKey();
                    float originalQty = totalMB.getMatterQuantity(currentKey);
                    float newQty = originalQty + epMatterQuantities.get(currentKey);
                    totalMB.removeMatterQuantity(currentKey);
                    totalMB.addMatterQuantity(currentKey, newQty);
                }
                else {
                    int currentKey = entry.getKey();
                    float currentQty = entry.getValue();
                    totalMB.addMatterQuantity(currentKey, currentQty);
                }
            }
            
        }
        return totalMB;
    }
    
    /**
     * 
     * @param elem
     * @return un hashmap           clé :       matterID
     *                              valeur :    Taux de récupération
     * Taux récupération : déf:
     * la masse d'un produit en un point est quel % de ce qui est rentré dans l’usine
     */
    public HashMap<Integer,Float> getRecoveryRateForMatterBasketAtElement(Element elem) {
        HashMap<Integer,Float> recoveryRates = new HashMap<>();
        MatterBasket totalMB = this.getTotalEntryPointsMatterBasket();
        HashMap<Integer,Float> elemQties = new HashMap<>();
        //version convoyeur
        if(elem instanceof Conveyor){
            elemQties = ((Conveyor)elem).getMatterBasket().getQuantities();
        }
        //version station
        else if(elem instanceof Station) {
            elemQties = ((Station)elem).getAllMatterQuantitiesAtOutlets();
        }
        //version jonction
        else if(elem instanceof Junction) {
            elemQties = ((Junction)elem).getMatterBasket().getQuantities();
        }
        //version EP
        else if(elem instanceof EntryPoint) {
            elemQties = ((EntryPoint)elem).getMatterBasket().getQuantities();
        }
        //version XP
        else if (elem instanceof ExitPoint) {
            elemQties = ((ExitPoint)elem).getMatterBasket().getQuantities();
        }
        if(!elemQties.isEmpty()) {
            Iterator<Map.Entry<Integer,Float>> iter = elemQties.entrySet().iterator();
            while(iter.hasNext()) {
                Map.Entry<Integer,Float> entry = iter.next();
                float recoveryRateForMatter = entry.getValue()/totalMB.getTotalQuantity();
                int matterIDForRecovery = entry.getKey();
                recoveryRates.put(matterIDForRecovery, recoveryRateForMatter);
            }
        }
        return recoveryRates;
    }
    

    public TransStation addTransStation(int numberOfOutlets) {
        TransStation station = new TransStation();
        for (int i = 0; i < numberOfOutlets; i++)
        {
            station.addOutlet();
        }
        station.setSortMatrix(new SortMatrix(this._matterList, station.getOutletCount()));
        station.setTransMatrix(new TransMatrix(this._matterList));
        this._stationList.add(station);
        this.updateDesign();
        return station;
    }

    public void setSortStationMatrix(int index, SortMatrix sm) {
        this._stationList.get(index).setSortMatrix(sm);
        this.updateDesign();
    }

    public void setTransStationMatrix(int index, TransMatrix tm) {
        if (this._stationList.get(index).getClass() != TransStation.class) {
            throw new IllegalArgumentException("Ce n'est pas une station de transformation.");
        } else {
            this._stationList.get(index).setTransMatrix(tm);
            this.updateDesign();
        }
    }

    public Conveyor addConveyor(Outlet aExit, Inlet aEntrance) {

        Conveyor newConv = new Conveyor(aExit, aEntrance);
        try {
            this._conveyorList.add(newConv);
            verifyCycles(aExit.getNode());
            this.updateDesign();
            return newConv;
        } catch (IllegalArgumentException e) {
            this._conveyorList.remove(newConv);
            newConv.removeConveyor();
            throw new IllegalArgumentException("Ce convoyeur introduit un cycle.");

        }
        

    }

    public Junction addJunction() {
        Junction junction = new Junction(this._matterList);
        this._junctionList.add(junction);
        this.updateDesign();
        return junction;
    }

    public void deleteJunction(Junction junction) {
        for (int i = 0; i < this._junctionList.size(); i++) {
            if (this._junctionList.get(i).equals(junction)) {
                deleteConveyor(junction);
                deleteConveyorFromJunction(junction);
                this._junctionList.remove(i);
                this.updateDesign();
                return;
            }
        }
    }

    //ajoute un nouveau entryPoint à la fin de la liste
    public EntryPoint addEntryPoint() {
        EntryPoint entryPoint = new EntryPoint();
        this._entryPointList.add(entryPoint);
        this.setNewEntryPointMatterBasket(entryPoint);
        
        return entryPoint;
    }
    
    public void setNewEntryPointMatterBasket(EntryPoint entryPoint) {
        MatterBasket mb = new MatterBasket(this._matterList);
        entryPoint.processMatterBasket(mb);
    }
    
    public void setNewEntryPointMatterBasket(EntryPoint entryPoint, MatterBasket matterBasket) {
        entryPoint.processMatterBasket(matterBasket);
    }
    

    //ajoute un nouveau exitPoint à la fin de la liste
    public ExitPoint addExitPoint() {
        
        ExitPoint exitPoint = new ExitPoint();
        
        this._exitPointList.add(exitPoint);
        this.updateDesign();
        return exitPoint;
    }
    
    /**
     * Définition taux de pureté: 
     * la masse de ce produit en ce point est quel % de tout ce qui passe par ce point
     */
    public HashMap<Integer, Float> getPurityRateForMatterBasketAtElement(Element elem) {
        HashMap<Integer, Float> purityRate = new HashMap<>();
        HashMap<Integer, Float> mbAtElement = new HashMap<>();
        float totalAtElement = 0;
        //version convoyeur
        if(elem instanceof Conveyor){
            mbAtElement = ((Conveyor)elem).getMatterBasket().getQuantities();
            totalAtElement = ((Conveyor)elem).getMatterBasket().getTotalQuantity();
        }
        //version station
        else if(elem instanceof Station) {
            mbAtElement = ((Station)elem).getAllMatterQuantitiesAtOutlets();
            Iterator<Map.Entry<Integer,Float>> iter = mbAtElement.entrySet().iterator();
            while(iter.hasNext()) {
                Map.Entry<Integer,Float> entry = iter.next();
                totalAtElement = totalAtElement + entry.getValue();
            }
        }
        //version jonction
        else if(elem instanceof Junction) {
            mbAtElement = ((Junction)elem).getMatterBasket().getQuantities();
            totalAtElement = ((Junction)elem).getMatterBasket().getTotalQuantity();
        }
        //version EP
        else if(elem instanceof EntryPoint) {
            mbAtElement = ((EntryPoint)elem).getMatterBasket().getQuantities();
            totalAtElement = ((EntryPoint)elem).getMatterBasket().getTotalQuantity();
        }
        //version XP
        else if (elem instanceof ExitPoint) {
            mbAtElement = ((ExitPoint)elem).getMatterBasket().getQuantities();
            totalAtElement = ((ExitPoint)elem).getMatterBasket().getTotalQuantity();
        }
        if(!mbAtElement.isEmpty() && totalAtElement !=0) {
            Iterator<Map.Entry<Integer,Float>> iter = mbAtElement.entrySet().iterator();
            while(iter.hasNext()) {
                Map.Entry<Integer,Float> entry = iter.next();
                float purityForMatter = (float)entry.getValue()/totalAtElement;
                int matterID = entry.getKey();
                purityRate.put(matterID, purityForMatter);
            }
        }
        return purityRate;
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
    
    public void setDimensions(Point2D.Float point) {
        dimensions.x = point.x;
        dimensions.y = point.y;
    }
    
    public Image getImg()
    {
        return _img;
    }
    
    public void setImg(String src) {
        this._img = Toolkit.getDefaultToolkit().getImage(src);
    }

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
        _entryPointList.get(index).setMatterBasket(matterBasket);
    }

    //obtient le matterbasket de la sortie à "index"
    public MatterBasket getExitPointMatterBasket(int index) {
        return _exitPointList.get(index).getMatterBasket();
    }

    @Override
    public void setAttribute(String attribName, Object value) {
        try
        {
            super.setAttrib(attribName, value);
        }
        catch (IllegalArgumentException e)
        {
            switch (attribName)
            {
                case "dimensionX":
                    setDimensions((Float)value, dimensions.y);
                    break;
                case "dimensionY":
                    setDimensions(dimensions.x, (Float)value);
                    break;
                default:
                    throw new IllegalArgumentException(String.format("no method for set %s", attribName));
            }
        }
    }

    @Override
    public Object getAttribute(String attribName)
    {
        try
        {
            return super.getAttrib(attribName);
        }
        catch (IllegalArgumentException e)
        {
            switch(attribName) {
                case "dimensions":
                return this.getDimensions();
                case "img":
                    return this.getImg();
                default:
                    throw new IllegalArgumentException(String.format("no method for get %s", attribName));
            }
        }
    }

    //méthode utilitaire avant de faire updateDesign
    public void resetJunctionMatterBaskets() {
        for (Junction currentJunction : _junctionList) {
            MatterBasket emptyMatterBasket = new MatterBasket(this._matterList);
            currentJunction.getOutlet().setMatterBasket(emptyMatterBasket);

        }
    }

}
