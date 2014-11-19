package Domain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

public class EntryPoint extends RectangularNode
{
    private Outlet _outlet;
    private MatterBasket _matterBasket;
   

    public EntryPoint() {
        super();
        _outlet = new Outlet(this);
    }

    public void setMatterBasket(MatterBasket matterBasket) {
        _matterBasket = matterBasket;
    }

    public MatterBasket getMatterBasket() {
        return _matterBasket;
    }

    public Outlet getOutlet() {
        return _outlet;
    }
    
    public void setOutlet(Outlet outlet){
        _outlet = outlet;
    }

    @Override
    public void setAttribute(String attribName, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getAttribute(String attribName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
