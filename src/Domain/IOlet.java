/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import TechnicalServices.Geometry.Segment2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Dany
 */
public class IOlet extends Element
{

    protected final Node _node;
    private Conveyor _conveyor;
    private Ellipse2D.Float _circle;
    private static float RADIUS = 0.1f;
    protected Point2D.Float _relativePosition;

    public IOlet(Node parentNode) {
        if (parentNode == null) {
            throw new IllegalArgumentException("parentNode cannot be null");
        }
        _node = parentNode;
        _conveyor = null;
        _circle = new Ellipse2D.Float();
        _relativePosition = new Point2D.Float(-1, -1);
    }

    public boolean IsFree() {
        return (_conveyor == null);
    }

    public Point2D.Float getPosition()
    {
        return new Point2D.Float(_node.getCenter().x+_relativePosition.x,
                            _node.getCenter().y+_relativePosition.y);
    }
    
    public void setPosition(Point2D.Float pos)
    {
        if (_node instanceof RectangularNode)
        {
            Point2D.Float vector = new Point2D.Float(pos.x - _node.getCenter().x,
                                        pos.y - _node.getCenter().y);
            Point2D.Float rectDim = ((RectangularNode)_node).getDimensions();
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
                line.setLine(rectDim.x/2, rectDim.y/2, rectDim.x/2, -rectDim.y/2);
                point = line.intersectingPoint(intersectSeg);
            }
            if (point == null && vector.y >= 0)
            {
                line.setLine(-rectDim.x/2, rectDim.y/2, rectDim.x/2, rectDim.y/2);
                point = line.intersectingPoint(intersectSeg);
            }
            if (point == null && vector.y <= 0)
            {
                line.setLine(-rectDim.x/2, -rectDim.y/2, rectDim.x/2, -rectDim.y/2);
                point = line.intersectingPoint(intersectSeg);
            }
            if (point == null && vector.x <= 0)
            {
                line.setLine(-rectDim.x/2, rectDim.y/2, -rectDim.x/2, -rectDim.y/2);
                point = line.intersectingPoint(intersectSeg);
            }
            _relativePosition.x = point.x * 1.2f;
            _relativePosition.y = point.y * 1.2f;
        }
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
        _conveyor = null;
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
            case "dimensions":
                return new Point2D.Float(RADIUS*2, RADIUS*2);
            default:
                throw new IllegalArgumentException(String.format("no method for get %s", attribName));
        }
    }
}
