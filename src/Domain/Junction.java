package Domain;

import java.awt.List;
import java.awt.geom.Point2D;

public class Junction extends Node {
	private List _inletList;
	private Outlet _outlet;
        private Point2D.Float _position;
        
        public final static float RADIUS = 0.3f;
        
        
        public Junction()
        {
            _position = new Point2D.Float(0, 0);
        }
        
        @Override
        public Point2D.Float getPosition()
        {
            return _position;
        }
        
        @Override
        public void setPosition(float x, float y)
        {
            _position.x = x;
            _position.y = y;
        }
        
        @Override
        public boolean include(Point2D.Float point)
        {
            return _position.distance(point) <= RADIUS;
        }
}