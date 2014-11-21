package Domain;

import java.util.ArrayList;

public class ExitPoint extends RectangularNode
{
    private Inlet _inlet;
    private MatterBasket _matterBasket;
   // private position 
    
    public ExitPoint()
    {
        _inlet = new Inlet(this);
        _matterBasket = new MatterBasket();
    }
        
    public Inlet getInlet()
    {
        return _inlet;
    }

    public void setInlet(Inlet inlet)
    {
    _inlet = inlet;
    }
    
    public MatterBasket getMatterBasket(){
        return this._matterBasket;
    }
    
    public void setMatterBasket(MatterBasket matterBasket) {
        this._matterBasket = matterBasket;
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
    
    @Override
    public void processMatterBasket(MatterBasket matterBasket) {
        this.setMatterBasket(matterBasket);
    }
}