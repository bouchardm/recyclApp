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
            _dimensions = new Point2D.Float(0.5f, 0.5f);
            _offset = new Point2D.Float(0, 0);
        }
        
        public Point2D.Float snap(Point2D.Float point)
        {
            Point2D.Float newPoint = new Point2D.Float();
            int i = Math.round((point.y - _offset.y) / _dimensions.y);
            int j = Math.round((point.x - _offset.x) / _dimensions.x);
            newPoint.y = _offset.y + (_dimensions.y * i);
            newPoint.x = _offset.x + (_dimensions.x * j);
            return newPoint;
        }
        
        public Point2D.Float getDimensions()
        {
            return _dimensions;
        }
        
        public void setDimensions(Point2D.Float dimensions)
        {
            if (dimensions.x <= 0 || dimensions.y <= 0)
            {
                throw new IllegalArgumentException("La dimension doit être positive");
            }
            _dimensions.x = dimensions.x;
            _dimensions.y = dimensions.y;
        }
        
        public Point2D.Float getOffset()
        {
            return _offset;
        }
        
//        public void setOffset(Point2D.Float offset)
//        {
//            if (offset.x <= 0 || offset.y <= 0)
//            {
//                throw new IllegalArgumentException("Le dé doit être positive");
//            }
//            _offset.x = offset.x;
//            _offset.y = offset.y;
//        }
}