package Domain;

import java.awt.Color;
import java.awt.Image;
import java.awt.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcleking
 */
public class SortStation extends Station {

    public SortStation()
    {
        super();
    }
    
    @Override
    public void setAttribute(String attribName, Object value)
    {
        try
        {
            super.setAttribute(attribName, value);
        }
        catch (IllegalArgumentException e)
        {
            switch (attribName)
            {
                default:
                    throw new IllegalArgumentException(String.format("no method for set %s", attribName));
            }
        }
        
    }

    @Override
    public Object getAttribute(String attribName) {
        try
        {
            return super.getAttribute(attribName);
        }
        catch (IllegalArgumentException e)
        {
            switch(attribName) {
                case "dimensions":
                    return getDimensions();
                case "speedMax":
                    return this.getKgHMax();
                case "img":
                    return this.getImg();
                default:
                    throw new IllegalArgumentException(String.format("no method for get %s", attribName));
            }
        }
    }

    @Override
    public ArrayList<IOlet> getIOlets() {
        ArrayList<IOlet> iolets = new ArrayList<>();
        iolets.add(getInlet());
        iolets.addAll(getOutletList());
        return iolets;
    }
    
    @Override
    public void setTransMatrix(TransMatrix tm) {
        throw new IllegalArgumentException("On peut seulement ajouter une matrice de transformation à une station de transformation.");
    }
}
