/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import Application.Controller.RecyclAppController;
import java.awt.List;
import java.awt.geom.Point2D;

/**
 *
 * @author Dany
 */
public class SortCenter extends Element
{
    
    private List _entryPointList;
    private List _exitPointList;
    private List _stationList;
    private List _junctionList;
    private Float _size;
    private MatterList _matterList;
    private List _conveyorList;
    public Project _project;
    public MatterBasket _matterBasket;
    public Node _node;
    public MatterList _matterList2;
    public RecyclAppController _recyclAppController;
    public Conveyor _conveyor;
    public Conveyor _conveyor2;

    public void addSortStation(Point2D aPosition) {
            throw new UnsupportedOperationException();
    }

    public void updateDesign() {
            throw new UnsupportedOperationException();
    }

    public void addTransStation(Point2D aPosition) {
            throw new UnsupportedOperationException();
    }

    public void addConveyor(Inlet aEntrance, Outlet aExit) {
            throw new UnsupportedOperationException();
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
