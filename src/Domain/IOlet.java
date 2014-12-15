/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import TechnicalServices.Geometry.Segment2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author Dany
 */
public abstract class IOlet extends Element
{

    Node _node;
    private Conveyor _conveyor;
    private ArrayList<Conveyor> _conveyorList;
    private Ellipse2D.Float _circle;
    private static float RADIUS = 0.15f;
    Point2D.Float _relativePosition;

    public IOlet()
    {
         _node = null;
        _conveyor = null;
        _circle = new Ellipse2D.Float();
        _relativePosition = new Point2D.Float(-1, -1);
        _conveyorList = new ArrayList<>();
    
    }
    
    public IOlet(Node parentNode) {
        if (parentNode == null) {
            throw new IllegalArgumentException("parentNode cannot be null");
        }
        _node = parentNode;
        _conveyor = null;
        _circle = new Ellipse2D.Float();
        _relativePosition = new Point2D.Float(-1, -1);
        _conveyorList = new ArrayList<>();
    }

    public boolean IsFree() {
        return (getConveyor() == null);
    }

    public Point2D.Float getPosition()
    {
        return new Point2D.Float(getNode().getCenter().x+getRelativePosition().x,
                            getNode().getCenter().y+getRelativePosition().y);
    }
    
    public void setPosition(Point2D.Float pos)
    {
        if (getNode() instanceof RectangularNode)
        {
            Point2D.Float vector = new Point2D.Float(pos.x - getNode().getCenter().x,
                                        pos.y - getNode().getCenter().y);
            Point2D.Float rectDim = ((RectangularNode)getNode()).getDimensions();
            float unitLength = (float)Math.sqrt(Math.pow(vector.x, 2) + Math.pow(vector.y, 2));
            float newLength = (float)(rectDim.x + rectDim.y);
            vector.x =  newLength * vector.x / unitLength;
            vector.y =  newLength * vector.y / unitLength;
            
            if (vector.x != vector.x || vector.y != vector.y) // check if NaN
            {
                return;
            }
            
            Segment2D.Float intersectSeg = new Segment2D.Float(0, 0, vector.x, vector.y);
            Segment2D.Float line = new Segment2D.Float();
            Point2D.Float point = null;
            if (vector.x >= 0)
            {
                line.setLine(rectDim.x/2+RADIUS, rectDim.y/2+RADIUS, rectDim.x/2+RADIUS, -rectDim.y/2-RADIUS);
                point = line.intersectingPoint(intersectSeg);
            }
            if (point == null && vector.y >= 0)
            {
                line.setLine(-rectDim.x/2-RADIUS, rectDim.y/2+RADIUS, rectDim.x/2+RADIUS, rectDim.y/2+RADIUS);
                point = line.intersectingPoint(intersectSeg);
            }
            if (point == null && vector.y <= 0)
            {
                line.setLine(-rectDim.x/2-RADIUS, -rectDim.y/2-RADIUS, rectDim.x/2+RADIUS, -rectDim.y/2-RADIUS);
                point = line.intersectingPoint(intersectSeg);
            }
            if (point == null && vector.x <= 0)
            {
                line.setLine(-rectDim.x/2-RADIUS, rectDim.y/2+RADIUS, -rectDim.x/2-RADIUS, -rectDim.y/2-RADIUS);
                point = line.intersectingPoint(intersectSeg);
            }
            
            _relativePosition.x = point.x;
            _relativePosition.y = point.y;
            
            if (getConveyor() != null)
            {
                getConveyor().updatePoints();
            }
        }
    }
    
    public abstract String getIdentifier();
    
    public void updatePosition()
    {
        setPosition(getRelativePosition());
    }
    
    public Ellipse2D.Float getCircle()
    {
        Point2D.Float center = getPosition();
        return new Ellipse2D.Float(center.x-RADIUS, center.y-RADIUS, RADIUS*2, RADIUS*2);
    }

    public Node getNode() {
        return _node;
    }

    public void setConveyor(Conveyor conveyor) {
        _conveyor = conveyor;
    }

    public Conveyor getConveyor() {
        return _conveyor;
    }

    public void removeConveyor() {
        setConveyor(null);
    }

    @Override
    public boolean include(Point2D.Float point)
    {
        return getCircle().contains(point);
    }

    @Override
    public void setAttribute(String attribName, Object value) {

        switch (attribName)
        {
            case "position":
                setPosition((Point2D.Float)value);
                break;
            default:
                throw new IllegalArgumentException(String.format("no method for set %s", attribName));
        }
    }

    @Override
    public Object getAttribute(String attribName) {
        switch(attribName) {
            case "position":
                return getPosition();
            case "dimensions":
                return new Point2D.Float(RADIUS*2, RADIUS*2);
            default:
                throw new IllegalArgumentException(String.format("no method for get %s", attribName));
        }
    }
    
       public void addConveyor(Conveyor conveyor)
        {
            getConveyorList().add(conveyor);
        }
       
       public ArrayList<Conveyor> getConveyorList()
       {
       return _conveyorList;
       }

    /**
     * @return the _relativePosition
     */
    public Point2D.Float getRelativePosition() {
        return _relativePosition;
    }

    /**
     * @param _relativePosition the _relativePosition to set
     */
    public void setRelativePosition(Point2D.Float _relativePosition) {
        this._relativePosition = _relativePosition;
    }

    /**
     * @param _node the _node to set
     */
    public void setNode(Node _node) {
        this._node = _node;
    }

    /**
     * @param _conveyorList the _conveyorList to set
     */
    public void setConveyorList(ArrayList<Conveyor> _conveyorList) {
        this._conveyorList = _conveyorList;
    }

    /**
     * @param _circle the _circle to set
     */
    public void setCircle(Ellipse2D.Float _circle) {
        this._circle = _circle;
    }
}
