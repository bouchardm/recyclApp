package Domain;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.text.Segment;

public class Conveyor extends Element {

    private static final float DEFAULTCAPACITY = 10; // 10 kg/h

    /**
     * @return the _WIDTH
     */
    public static float getWIDTH() {
        return _WIDTH;
    }
    private Outlet _startPoint;
    private Inlet _endPoint;
//    private Object _iD;
//    private MatterBasket _matterBasket;
    private Float _speedMax;
    private Color _color;
    private Line2D.Float _line;
    private final static float _WIDTH = 0.5f;
    private ArrayList<ConveyorBend> _bends;
    private int _bendIndexInsertion = 0;
    
    public Conveyor(Outlet startPoint, Inlet endPoint)
    {
        if (startPoint == null || endPoint == null) 
        {
            throw new IllegalArgumentException("startPoint and endPoint cannot be null");
        }
        _bends = new ArrayList<>();
        _errorMessages = new ArrayList<>();
        _line = new Line2D.Float();
        this._startPoint = startPoint;
        this._endPoint = endPoint;
        _startPoint.setConveyor(this);

        if (endPoint.getNode().getClass() != Junction.class) {
            _endPoint.setConveyor(this);
        } else {
            _endPoint.addConveyor(this);
        }
        _line = new Line2D.Float();
        this._startPoint = startPoint;
        this._endPoint = endPoint;           
        _startPoint.setConveyor(this);

        if (endPoint.getNode().getClass() != Junction.class)
            _endPoint.setConveyor(this);
        else
            _endPoint.addConveyor(this);

        _speedMax = DEFAULTCAPACITY;
        this._color = Color.BLACK;

        updatePoints();
    }
    
    public ConveyorBend addBend()
    {
        ConveyorBend bend = new ConveyorBend(this);
        _bends.add(_bendIndexInsertion, bend);
        return bend;
    }
    
    public void removeBend(ConveyorBend bend)
    {
        _bends.remove(bend);
    }
    
    public ArrayList<ConveyorBend> getBends()
    {
        return _bends;
    }

    /**
     * @return the _line
     */
    public Line2D.Float getLine() {
        return _line;
    }

    /**
     * @param _line the _line to set
     */
    public void setLine(Line2D.Float _line) {
        this._line = _line;
    }

   

    @Override
    public boolean include(Point2D.Float point)
    {
        updatePoints();
        Line2D.Float line = new Line2D.Float(_line.x1, _line.y1, _line.x2, _line.y2);
        _bendIndexInsertion = 0;
        for (ConveyorBend bend: _bends)
        {
            line.x2 = bend.getPosition().x;
            line.y2 = bend.getPosition().y;
            if (((float)line.ptSegDist(point)) <= getWIDTH()/2)
            {
                return true;
            }
            line.x1 = line.x2;
            line.y1 = line.y2;
            
            _bendIndexInsertion++;
        }
        line.x2 = _line.x2;
        line.y2 = _line.y2;
        return ((float)line.ptSegDist(point)) <= getWIDTH()/2;
    }

    public void updatePoints()
    {
        Point2D.Float p1 = _startPoint.getPosition();
        Point2D.Float p2 = _endPoint.getPosition();
        getLine().setLine(p1.x, p1.y, p2.x, p2.y);
    }

    public Outlet getStartPoint() {
        return this._startPoint;
    }
    public void setStartPoint(Outlet _startPoint)
    {
     this._startPoint  =  _startPoint;
    }
    public Inlet getEndPoint() {
        return this._endPoint;
    }
    
    public void setEndPoint(Inlet _endPoint)
    {
    this._endPoint = _endPoint;
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
        return _startPoint.getMatterBasketQty();
    }

    public Point2D.Float getPoint1()
    {
        updatePoints();
        return (Point2D.Float)getLine().getP1();
    }


    public Point2D.Float getPoint2()
    {
        updatePoints();
        return (Point2D.Float)getLine().getP2();
    }

    public void removeConveyor() {
       _startPoint.removeConveyor();
       _endPoint.removeConveyor();
        _startPoint = null;

        _endPoint = null;
    }

    @Override
    public void setAttribute(String attribName, Object value) {
        switch (attribName)
        {
            case "speedMax":
                this.setSpeedMax((Float) value);
                break;
            case "color":
                this._color = (Color) value;
                break;
            default:
                throw new IllegalArgumentException(String.format("no method for set %s", attribName));
        }
    }

    @Override
    public Object getAttribute(String attribName) {
        switch(attribName) {
            case "speedMax":
                return this.getSpeedMax();
            case "matterQuantities":
                return this.getStartPoint().getMatterBasketQty().getQuantities();
            case "color":
                return this._color;
            default:
                throw new IllegalArgumentException(String.format("no method for get %s", attribName));
        }
    }

    public Float getSpeedMax() {
        return _speedMax;
    }

    public void setSpeedMax(Float _speedMax) {
        this._speedMax = _speedMax;
    }

    @Override
    public void setColor(Color _color) {
        this._color = _color;
    }
    
    @Override
    public Color getColor() {
        return this._color;
    }
        
}
