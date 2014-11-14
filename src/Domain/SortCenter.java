/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import Application.Controller.Controller;
//import java.awt.List;
import java.util.ArrayList;
import java.awt.geom.Point2D;
/**
 *
 * @author Dany
 */
public class SortCenter extends Element
{
    
    private ArrayList<EntryPoint> _entryPointList;
    private ArrayList<ExitPoint> _exitPointList;
    private ArrayList<Station> _stationList;
    private ArrayList<Junction> _junctionList;
    private Float _size;
    private MatterList _matterList;
    private ArrayList<Conveyor> _conveyorList;
    public Project _project;
    public MatterBasket _matterBasket;
    public Node _node;
    public MatterList _matterList2;
    public Controller _recyclAppController;
    //pourquoi est-ce que le sortCenter a 2 convoyeurs?
    public Conveyor _conveyor;
    public Conveyor _conveyor2;
    
    public void addSortStation(Point2D aPosition) {
            throw new UnsupportedOperationException();
    }

    public void updateDesign() {
        throw new UnsupportedOperationException();
        //à partir des point d'entrée
        //on va chercher toutes les stations
            //chaque station calcul son nouveau output
            //propager le nouveau output sur le convoyeur
            //la station va chercher toutes les stations connectées
            //ils utilisent le input du convoyeur pour calculer leurs output
            //jusqu'à temps qu'on arrive à la sortie, qui mets à jour son matterBasket
    }

    public void addTransStation(Point2D aPosition) {
            throw new UnsupportedOperationException();
    }

    //precondition: aEntrance et aExit sont libres et valides
    public void addConveyor(Outlet aExit, Inlet aEntrance) {
        //VÉRIFIER QU'IL N'Y A PAS DE BOUCLE!!!!
        //à partir de aEntrance, vérifier tous les stations connectés en sorties
        //récursivement : si on arrive à des exitPoint sans arriver à la station 
        //auquel appartient aExit, tout est ok!
        Conveyor newConveyor = new Conveyor(aExit, aEntrance);
        this._conveyorList.add(newConveyor);
        updateDesign();
    }

    public void addJunction(Point2D aPosition) {
            throw new UnsupportedOperationException();
    }

    public void addEntryPoint(Point2D aPosition) {
            throw new UnsupportedOperationException();
    }

    public void addExitPoint(Point2D aPosition) {
            throw new UnsupportedOperationException();
    }
        
        
    private Point2D.Float dimensions;
    public SortCenter()
    {
        dimensions = new Point2D.Float(15f, 10f);
    }
    
    
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
}
