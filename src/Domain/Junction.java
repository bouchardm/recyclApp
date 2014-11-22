package Domain;

import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Junction extends Node {
    private ArrayList<Inlet> _inletList;
    private Outlet _outlet;
    private Point2D.Float _position;
    private ArrayList _exit;

    public final static float RADIUS = 0.3f;

    public Junction()
    {
        _position = new Point2D.Float(0, 0);
        _inletList = new ArrayList<>();
        _outlet = new Outlet(this);
    }
    
    public MatterBasket getMatterBasket() {
        return _outlet.getMatterBasket();
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

    public void addInlet()
    {
//        Inlet inlet = new Inlet(this);
        _inletList.add(new Inlet(this)); // c'est Juncion qui devrait créer le nouvel Inlet
    }
    
    public ArrayList<Inlet> getInletList() {
        return this._inletList;
    }

    @Override
    public boolean include(Point2D.Float point)
    {
        return _position.distance(point) <= RADIUS;
    }

    @Override
    public Point2D.Float getCenter()
    {
        return getPosition();
    }
    
    public void setOutlet(Outlet outlet) {
        _outlet = outlet;
    }
    
    public Outlet getOutlet() {
        return _outlet;
    }
    

    @Override
    public void setAttribute(String attribName, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getAttribute(String attribName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<IOlet> getIOlets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //ajoute les quantités du matterBasket au matterBasket à son Outlet
    @Override
    public void processMatterBasket(MatterBasket matterBasket) {
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
    }
    

}