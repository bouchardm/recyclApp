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
public abstract class IOlet
{
    private Node _parentNode;
    private Conveyor _conveyor;
    
    public IOlet(Node parentNode)
    {
        if (parentNode == null)
        {
            throw new IllegalArgumentException("node cannot be null");
        }
        _parentNode = parentNode;
        _conveyor = null;
    }
    
    public boolean IsFree()
    {
        return (_conveyor == null);
    }
    
    public Point2D.Float getPosition()
    {
        return _parentNode.getCenter();
    }
    
    
}
