package Domain;


import java.awt.Color;
import java.awt.List;
import java.awt.Point;
import java.awt.geom.Point2D;

public abstract class Node extends Element
{
	public Point _point;
        protected Color _color;
        
        public abstract Point2D.Float getPosition();
        public abstract Point2D.Float getCenter();
        public abstract void setPosition(float x, float y);
        
    public Color getColor() {
        return _color;
    }

    public void setColor(Color _color) {
        this._color = _color;
    }
        
}