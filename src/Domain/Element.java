package Domain;


import java.awt.geom.Point2D;

public abstract class Element
{
    private Integer _iD;
    public abstract boolean include(Point2D.Float point);
    public abstract void setAttribute(String attribName, Object value);
    public abstract Object getAttribute(String attribName);
    public Element()
    {
        // todo
    }
}