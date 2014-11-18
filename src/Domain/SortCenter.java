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

/**
 *
 * @author Dany
 */
public class SortCenter extends Element
{
    private ArrayList _conveyorList;
    private ArrayList _entryPointList;
    private ArrayList _exitPointList;
    private ArrayList _sortStationList;
    private ArrayList _transStationList;
    private ArrayList _junctionList;
    private Float _size;
    private MatterList _matterList;

    public SortCenter() {
        _conveyorList = new ArrayList<Conveyor>();
        _entryPointList = new ArrayList<EntryPoint>();
        _exitPointList = new ArrayList<ExitPoint>();
        _sortStationList = new ArrayList<SortStation>();
        _transStationList = new ArrayList<TransStation>();
        _junctionList = new ArrayList<Junction>();
        
        dimensions = new Point2D.Float(15f, 10f);
    }
    
    public void addSortStation(Point2D.Float aPosition, Integer exit) {
        SortStation station = new SortStation();
        station.setPosition(aPosition);
        station.setExit(exit);
        this._sortStationList.add(station);
    }
    
    public ArrayList getEntryPoints()
    {
        return _entryPointList;
    }
    
    public ArrayList getExitPoints()
    {
        return _exitPointList;
    }
    
    public ArrayList getJunctions()
    {
        return _junctionList;
    }
    
    public ArrayList getConveyors()
    {
        return _conveyorList;
    }
    
    public ArrayList getSortStation()
    {
        return _sortStationList;
    }
    
    public ArrayList getTransStation()
    {
        return _transStationList;
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
    
    public ArrayList getSortStationList() {
        return _sortStationList;
    }
    
    public SortStation getSortStationCursorIn(Point2D.Float position) {
        ArrayList sortStationList = this.getSortStationList();
        
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

    public void unselectAll() {
        ArrayList sortStationList = this.getSortStationList();
        
        for (Iterator iterator = sortStationList.iterator(); iterator.hasNext();) {            
            SortStation next = (SortStation)iterator.next();
            
            next.setSelected(false);
        }
        
    }
}
