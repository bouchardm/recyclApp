package Domain;


import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class Node extends Element
{
	private Point _point;
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
    
    public abstract ArrayList<IOlet> getIOlets();
    
    public abstract void processMatterBasket(MatterBasket matterBasket);
    
    public abstract void setMatterBasketAtOutlets(MatterBasket matterBasket);

    /**
     * @return the _point
     */
    public Point getPoint() {
        return _point;
    }

    /**
     * @param _point the _point to set
     */
    public void setPoint(Point _point) {
        this._point = _point;
    }
        
}