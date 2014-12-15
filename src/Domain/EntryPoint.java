package Domain;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

public class EntryPoint extends RectangularNode
{
    private Outlet _outlet;
    private String _name;

    public EntryPoint() {
        super();
        _outlet = new Outlet(this);
        _errorMessages = new ArrayList<>();
        _color = Color.WHITE;
    }


    public void setMatterBasket(MatterBasket matterBasket) {
        this._outlet.setMatterBasket(matterBasket);
        if(this._outlet.hasConveyor()==false && matterBasket.getTotalQuantity()>0) {
            this.addErrorMessage("Le point d'entrée reçoit de la matière mais n'est pas connecté au réseau.");
        }
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
                case "matterBasket":
                    return this.getMatterBasket();
                default:
                    throw new IllegalArgumentException(String.format("no method for get %s", attribName));
            }
        }
    }

    @Override
    public ArrayList<IOlet> getIOlets() {
        ArrayList<IOlet> iolets = new ArrayList<>();
        
        iolets.add(this.getOutlet());
        
        return iolets;
    }
    
    //Généralement, appeller avec entryPoint.processMatterBasket(entryPoint.getMatterBasket());
    @Override
    public void processMatterBasket(MatterBasket matterBasket) {
        this._outlet.setMatterBasket(matterBasket);
        if(this._outlet.hasConveyor()==false && matterBasket.getTotalQuantity()>0) {
            this.addErrorMessage("Le point d'entrée reçoit de la matière mais n'est pas connecté au réseau.");
        }
    }
    
    @Override
    public void setMatterBasketAtOutlets(MatterBasket matterBasket) {
        this.setMatterBasket(matterBasket);
    }
    
}
