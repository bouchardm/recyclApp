package Domain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

public class EntryPoint extends RectangularNode
{
    private Outlet _outlet;

    public EntryPoint() {
        super();
        _outlet = new Outlet(this);
    }

    public void setMatterBasket(MatterBasket matterBasket) {
        this._outlet.setMatterBasket(matterBasket);
    }

    public MatterBasket getMatterBasket() {
        return this._outlet.getMatterBasket();
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

    @Override
    public ArrayList<IOlet> getIOlets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Généralement, appeller avec entryPoint.processMatterBasket(entryPoint.getMatterBasket());
    @Override
    public void processMatterBasket(MatterBasket matterBasket) {
        this._outlet.setMatterBasket(matterBasket);
    }
    
}
