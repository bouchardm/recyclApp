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
public class IOlet extends Element
{

    protected Node _node;
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
        setPosition(new Point2D.Float(-1, -1));

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
        _relativePosition = pos;
    }
    
    public Ellipse2D.Float getCircle()
    {
        Point2D.Float center = getPosition();
        return new Ellipse2D.Float(center.x-RADIUS, center.y-RADIUS, RADIUS*2, RADIUS*2);
    }

    public Node getNode() {
        return _node;
    }

    public void setNode(Node newNode) {
        _node = newNode;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
