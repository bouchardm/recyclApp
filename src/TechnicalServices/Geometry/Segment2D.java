package TechnicalServices.Geometry;


import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dany
 */
public abstract class Segment2D extends Line2D.Float
{
    public static class Float extends Segment2D
    {
        public Float(){}
        public Float(float x1, float y1, float x2, float y2)
        {
            setLine(x1, y1, x2, y2);
        }
        
        public Point2D.Float intersectingPoint(Segment2D.Float seg)
        {
        float d = (x1-x2)*(seg.y1-seg.y2) - (y1-y2)*(seg.x1-seg.x2);
        if (d == 0) return null;
        
        float xi = ((seg.x1-seg.x2)*(x1*y2-y1*x2)-(x1-x2)*(seg.x1*seg.y2-seg.y1*seg.x2))/d;
        float yi = ((seg.y1-seg.y2)*(x1*y2-y1*x2)-(y1-y2)*(seg.x1*seg.y2-seg.y1*seg.x2))/d;
        
        Point2D.Float p = new Point2D.Float(xi,yi);
        if (x1 != x2 && !(xi >= Math.min(x1, x2) && xi <= Math.max(x1, x2)))
        {
            return null;
        }
        if (y1 != y2 && !(yi >= Math.min(y1, y2) && yi <= Math.max(y1, y2)))
        {
            return null;
        }
        return p;
        }
    }
}
