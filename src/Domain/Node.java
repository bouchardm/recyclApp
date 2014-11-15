package Domain;


import java.awt.List;
import java.awt.Point;
import java.awt.geom.Point2D;

public abstract class Node extends Element
{
	private Integer _iD;
	public Point _point;
        
        public abstract Point2D.Float getPosition();
        public abstract Point2D.Float getCenter();
        public abstract void setPosition(float x, float y);
        
        
}