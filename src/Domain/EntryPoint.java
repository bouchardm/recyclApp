package Domain;

import java.awt.Point;

public class EntryPoint extends Station
{
    private Outlet _outlet;
    private Object _matterBasket;
    
    public EntryPoint()
    {
        super();
        _outlet = new Outlet(this);
    }

        
        
    public Outlet getOutlet()
    {
        return _outlet;
    }
        
        
}