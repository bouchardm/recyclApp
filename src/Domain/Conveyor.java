package Domain;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;



public class Conveyor extends Element
{
        private static final float DEFAULTCAPACITY = 10; // 10 kg/h
	private Outlet _startPoint;
	private Inlet _endPoint;
	private Object _iD;
	private MatterBasket _matterBasket;
        private Float _maxCapatity;
        private Line2D.Float _line;
        
        private final static float _WIDTH = 0.2f;
        
        public Conveyor(Outlet startPoint, Inlet endPoint)
        {
            if (startPoint == null || endPoint == null)
            {
                throw new IllegalArgumentException("startPoint and endPoint cannot be null");
            }
            _line = new Line2D.Float();
            this._startPoint = startPoint;
            this._endPoint = endPoint;
            _maxCapatity = DEFAULTCAPACITY;
            
            updatePoints();
        }
        
        
        @Override
        public boolean include(Point2D.Float point)
        {
            return ((float)_line.ptSegDist(point)) <= _WIDTH/2;
        }
        
        private void updatePoints()
        {
            Point2D.Float p1 = _startPoint.getNode().getCenter();
            Point2D.Float p2 = _endPoint.getNode().getCenter();
            _line.setLine(p1.x, p1.y, p2.x, p2.y);
        }
        
        
        public Node getStartNode()
        {
            return _startPoint.getNode();
        }
        
        public Node getEndNode()
        {
            return _endPoint.getNode();
        }
        
        public MatterBasket getMatterBasket()
        {
            return _startPoint.getMatterBasket();
        }
        
        public Point2D.Float getPoint1()
        {
            updatePoints();
            return (Point2D.Float)_line.getP1();
        }
        
        
        public Point2D.Float getPoint2()
        {
            updatePoints();
            return (Point2D.Float)_line.getP2();
        }
        
}