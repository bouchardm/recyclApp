/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Dany
 */
public abstract class Station extends Node
{
    private Rectangle2D.Float _rect;
    
    public Station()
    {
        _rect = new Rectangle2D.Float(0, 0, 1, 1);
    }
    
    
    @Override
    public void setPosition(float x, float y)
    {
        _rect.x = x;
        _rect.y = y;
    }
    
    @Override
    public Point2D.Float getPosition()
    {
        return new Point2D.Float(_rect.x, _rect.y);
    }

    @Override
    public boolean include(Point2D.Float point)
    {
        return _rect.contains(point);
    }

    
    
}
