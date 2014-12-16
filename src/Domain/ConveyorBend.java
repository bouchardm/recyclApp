/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Dany
 */
public class ConveyorBend extends Element
{

    /**
     * @return the RADIUS
     */
    public static float getRADIUS() {
        return RADIUS;
    }
    private Point2D.Float _position;
    private static final float RADIUS = 0.075f;
    private Conveyor _parentConveyor;
    
    public ConveyorBend(Conveyor conveyor)
    {
        _parentConveyor = conveyor;
    }
    
    public void delete()
    {
        getParentConveyor().removeBend(this);
    }
    
    public Point2D.Float getPosition()
    {
        Point2D.Float pos = new Point2D.Float(getPosition().x, getPosition().y);
        return pos;
    }
    
    public void setPosition(Point2D.Float pos)
    {
        _position = pos;
    }
    
    public Ellipse2D.Float getCircle()
    {
        Point2D.Float center = getPosition();
        return new Ellipse2D.Float(center.x-getRADIUS(), center.y-getRADIUS(), getRADIUS()*2, getRADIUS()*2);
    }
    
    public Color getColor()
    {
        return getParentConveyor().getColor();
    }
    
    @Override
    public boolean include(Point2D.Float point)
    {
        return getPosition().distance(point) <= getRADIUS();
    }
    

    @Override
    public Object getAttribute(String attribName)
    {
        switch(attribName)
        {
            case "position":
                return getPosition();
            case "dimensions":
                return new Point2D.Float(getRADIUS()*2, getRADIUS()*2);
            case "color":
                return getColor();
            default:
                throw new IllegalArgumentException(String.format("no method for get %s", attribName));
        }
    }
    
    @Override
    public void setAttribute(String attribName, Object value)
    {
        switch (attribName)
            {
            case "position":
                setPosition((Point2D.Float)value);
                break;
            default:
                throw new IllegalArgumentException(String.format("no method for set %s", attribName));
            }
    }

    /**
     * @return the _parentConveyor
     */
    public Conveyor getParentConveyor() {
        return _parentConveyor;
    }

    /**
     * @param _parentConveyor the _parentConveyor to set
     */
    public void setParentConveyor(Conveyor _parentConveyor) {
        this._parentConveyor = _parentConveyor;
    }
    
}
