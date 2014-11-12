package Domain;


import java.awt.geom.Point2D;

public abstract class Element extends MatterBasket {
	public SortStation _sortStation;
	public EntryPoint _entryPoint;
	public ExitPoint _exitPoint;
	public Conveyor _conveyor;
	public Junction _junction;

	public abstract boolean include(Point2D.Float point);
}