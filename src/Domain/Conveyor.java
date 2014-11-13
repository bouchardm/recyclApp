package Domain;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Conveyor extends Element
{
	private Outlet _startPoint;
	private Inlet _endPoint;
	private Object _iD;
	private MatterBasket _matterBasket;
	public SortCenter _sortCenter;
	public Element _element;
	public Outlet Outlet ;
        private Line2D.Float _line;
        
        private final static float _WIDTH = 0.2f;
        
        public Conveyor(Outlet startPoint, Inlet endPoint)
        {
            this._startPoint = startPoint;
            this._endPoint = endPoint;
        }
        
	public Float GetLength() {
		throw new UnsupportedOperationException();
	}
        
        
        @Override
        public boolean include(Point2D.Float point)
        {
            return _line.ptLineDist(point) <= _WIDTH/2;
        }
}