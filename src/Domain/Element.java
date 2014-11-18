package Domain;


import java.awt.geom.Point2D;

public abstract class Element
{
    private Integer _iD;
    public abstract boolean include(Point2D.Float point);
    public Element()
    {
        // todo
    }
}