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
public class SortCenter
{
    private Point2D.Float dimensions;
    public SortCenter()
    {
        dimensions = new Point2D.Float(15f, 10f);
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
