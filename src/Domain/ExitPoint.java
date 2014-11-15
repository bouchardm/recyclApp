package Domain;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ExitPoint extends Station
{
    private Inlet _inlet;
    
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
}