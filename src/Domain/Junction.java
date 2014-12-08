package Domain;

import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Junction extends RectangularNode {
    private ArrayList<Inlet> _inletList;
    private Outlet _outlet;
    private Point2D.Float _position;
    private Float _speedMax;

    public final static float RADIUS = 0.3f;

    public Junction()
    {
        _position = new Point2D.Float(0, 0);
        _inletList = new ArrayList<>();
        _speedMax = 10f;
        this.setDimensions(0.5f, 0.5f);
        _outlet = new Outlet(this);
        _outlet.setPosition(new Point2D.Float(getCenter().x+1, 0));
        this.addInlet();
        _errorMessages = new ArrayList<>();
    }
    
    public Junction(MatterList matterList)
    {
        _position = new Point2D.Float(0, 0);
        _inletList = new ArrayList<>();
        _speedMax = 10f;
        this.setDimensions(0.5f, 0.5f);
        _outlet = new Outlet(this, matterList);
        _outlet.setPosition(new Point2D.Float(getCenter().x+1, 0));
        this.addInlet();
        _errorMessages = new ArrayList<>();
    }
    
    public float getSpeedMax() {
        return _speedMax;
    }

    public void setSpeedMax(Float _speedMax) {
        this._speedMax = _speedMax;
    }
    
    public MatterBasket getMatterBasket() {
        return _outlet.getMatterBasket();
    }
    

//    @Override
//    public Point2D.Float getPosition()
//    {
//        return _position;
//    }
//
//    @Override
//    public void setPosition(float x, float y)
//    {
//        _position.x = x;
//        _position.y = y;
//    }

    public void addInlet()
    {
//        Inlet inlet = new Inlet(this);
        _inletList.add(new Inlet(this)); // c'est Juncion qui devrait créer le nouvel Inlet
    }
    
    public ArrayList<Inlet> getInletList() {
        return this._inletList;
    }

//    @Override
//    public boolean include(Point2D.Float point)
//    {
//        return _position.distance(point) <= RADIUS;
//    }
//
//    @Override
//    public Point2D.Float getCenter()
//    {
//        return getPosition();
//    }
    
//    public void setOutlet(Outlet outlet) {
//        _outlet = outlet;
//    }
    
    public Outlet getOutlet() {
        return _outlet;
    }
    

    @Override
    public void setAttribute(String attribName, Object value) {
        try
        {
            super.setAttribute(attribName, value);
        }
        catch (IllegalArgumentException e)
        {
            switch (attribName)
            {
                case "speedMax":
                    this.setSpeedMax((Float) value);
                    break;
                default:
                    throw new IllegalArgumentException(String.format("no method for set %s", attribName));
            }
        }
    }

    @Override
    public Object getAttribute(String attribName) {
        try
        {
            return super.getAttribute(attribName);
        }
        catch (IllegalArgumentException e)
        {
            switch(attribName) {
                case "speedMax":
                    return this.getSpeedMax();
                case "matterQuantities":
                    return this.getOutlet().getMatterBasket().getQuantities();
                case "matterQuantity":
                    return this.getOutlet().getTotalMatterQuantity();
                default:
                    throw new IllegalArgumentException(String.format("no method for get %s", attribName));
            }
        }
    }

    @Override
    public ArrayList<IOlet> getIOlets() {
        ArrayList<IOlet> iolets = new ArrayList<>();
        
        iolets.addAll(getInletList());
        iolets.add(getOutlet());
        
        return iolets;
    }
    
    //ajoute les quantités du matterBasket au matterBasket à son Outlet
    @Override
    public void processMatterBasket(MatterBasket matterBasket) {
        this.clearErrorMessages();
        if(_outlet.getMatterBasket()==null || _outlet.getMatterBasket().getNumberOfMatterInBasket()==0) {
            _outlet.setMatterBasket(matterBasket);
        }
        else {
            HashMap<Integer, Float> mbQuantities = _outlet.getMatterBasket().getQuantities();
            Iterator<Map.Entry<Integer, Float>> mbIter = mbQuantities.entrySet().iterator();
            //le nouveau panier qu'on mettra à la sortie
            MatterBasket newMatterBasket = new MatterBasket();
            while(mbIter.hasNext()) {
                Map.Entry<Integer, Float> currentMatterToProcess = mbIter.next();
                int currentMatterID = currentMatterToProcess.getKey();
                float currentMatterQty = currentMatterToProcess.getValue();
                float newMatterQty = currentMatterQty + matterBasket.getMatterQuantity(currentMatterID);
                newMatterBasket.addMatterQuantity(currentMatterID, newMatterQty);
            }
            _outlet.setMatterBasket(newMatterBasket); 
        }
        if(_outlet.getMatterBasket().getTotalQuantity()>0 && _outlet.hasConveyor()==false) {
            this.addErrorMessage("La jonction reçoit de la matière mais sa sortie n'est pas connectée au réseau.");
        }
    }
    
    @Override
    public void setMatterBasketAtOutlets(MatterBasket matterBasket) {
        this._outlet.setMatterBasket(matterBasket);
    }
    

}