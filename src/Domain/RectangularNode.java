/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


public abstract class RectangularNode extends Node
{
    private Rectangle2D.Float _rect;
    protected Image _img;
    
    
    public RectangularNode()
    {
        _rect = new Rectangle2D.Float(0, 0, 1, 1);
        _img = null;
        this._name = "";
        this._description = "";
    }
    
    
    @Override
    public void setPosition(float x, float y)
    {
        _rect.x = x;
        _rect.y = y;
    }
    
    public void setPosition(Point2D.Float position) {
        setPosition(position.x, position.y);
        ArrayList<IOlet> iolets = getIOlets();
        for (IOlet iolet : iolets)
        {
            iolet.setPosition(iolet.getPosition());
        }
    }
    
    @Override
    public Point2D.Float getPosition()
    {
        Point2D.Float pos = new Point2D.Float(_rect.x, _rect.y);
        return pos;
    }
    
    public Image getImg() {
        return _img;
    }

    public void setImg(String src) {
        this._img = Toolkit.getDefaultToolkit().getImage(src);
    }

    @Override
    public boolean include(Point2D.Float point)
    {
        return _rect.contains(point);
    }
    
    public Point2D.Float getCenter()
    {
        Point2D.Float center = new Point2D.Float(
                (float)_rect.getCenterX(),(float)_rect.getCenterY());
        return center;
    }
    
    public void setCenter(Point2D.Float center)
    {
        _rect.x = center.x - (_rect.width / 2);
        _rect.y = center.y - (_rect.height / 2);
    }
    
    public void setDimensions(float height, float width)
    {
        _rect.height = height;
        _rect.width = width;
    }
    
    public Point2D.Float getDimensions()
    {
        return new Point2D.Float(_rect.width, _rect.height);
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
    
    public Object getAttribute(String attribName)
    {
        switch (attribName)
        {
            case "dimensions":
                return getDimensions();
            default:
                throw new IllegalArgumentException(String.format("no method for get %s", attribName));
        }
    }

    
    
}
