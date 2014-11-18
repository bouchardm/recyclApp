package Domain;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class ExitPoint extends Station
{
    private Inlet _inlet;
    private ArrayList _exit;
    
    public ExitPoint()
    {
        _inlet = new Inlet(this);
    }

    public void operation() {
            throw new UnsupportedOperationException();
    }
        
    public Inlet getInlet()
    {
        return _inlet;
    }
    
    @Override
    public void setExit(int nbExit)
    {
        this._exit = new ArrayList<Integer>();
    } 
    
}