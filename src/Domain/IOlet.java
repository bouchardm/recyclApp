/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import java.awt.geom.Point2D;

/**
 *
 * @author Dany
 */
public class IOlet
{
    private Node _node;
    private Conveyor _conveyor;
    
    public IOlet(Node parentNode)
    {
        if (parentNode == null)
        {
            throw new IllegalArgumentException("parentNode cannot be null");
        }
        _node = parentNode;
        _conveyor = null;
    }
    
    public boolean IsFree()
    {
        return (_conveyor == null);
    }
    
    public Point2D.Float getPosition()
    {
        return _node.getCenter();
    }
    
    public Node getNode()
    {
        return _node;
    }
    
    public void setConveyor(Conveyor conveyor)
    {
        _conveyor = conveyor;
    }
    
    public Conveyor getConveyor()
    {
        return _conveyor;
    }
}
