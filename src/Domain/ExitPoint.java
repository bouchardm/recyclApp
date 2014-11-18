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
}