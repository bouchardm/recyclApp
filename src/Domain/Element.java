package Domain;


import java.awt.geom.Point2D;

public abstract class Element
{
	public abstract boolean include(Point2D.Float point);
}