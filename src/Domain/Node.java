package Domain;


import java.awt.List;
import java.awt.Point;
import java.awt.geom.Point2D;

public abstract class Node extends Element
{
	private Point2D.Float _position;
	private Integer _iD;
	private List _inletList;
	private List _outletList;
	public Point _point;
	public EntryPoint _entryPoint;
	public SortCenter _sortCenter;
	public Outlet _outLet;
        
        public abstract Point2D.Float getPosition();
        public abstract void setPosition(float x, float y);
}