package Presentation.Swing;

import java.awt.geom.Point2D;

public class Grid {
	private String _status; // ? C'est quoi le statut
	private String _dimension;
        private Point2D.Float _dimensions;
        private Point2D.Float _offset;
        
        public Grid()
        {
            _status = null;
            _dimensions = new Point2D.Float(1, 1);
            _offset = new Point2D.Float(0, 0);
        }
        
        public Point2D.Float snap(Point2D.Float point)
        {
            return null;
        }
        
        public Point2D.Float getDimensions()
        {
            return _dimensions;
        }
        
        public Point2D.Float getOffset()
        {
            return _offset;
        }
}