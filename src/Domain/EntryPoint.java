package Domain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

public class EntryPoint extends RectangularNode
{
    private Outlet _outlet;
    private Object _matterBasket;
    private ArrayList _exit;
    
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