package Domain;

public class ExitPoint extends RectangularNode
{
    private Inlet _inlet;
   // private position 
    
    public ExitPoint()
    {
        _inlet = new Inlet(this);
    }
        
    public Inlet getInlet()
    {
        return _inlet;
    }

    public void setInlet(Inlet inlet)
    {
    _inlet = inlet;
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