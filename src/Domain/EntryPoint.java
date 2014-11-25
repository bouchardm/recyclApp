package Domain;

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
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        
        MatterList tmlist = new MatterList();
        tmlist.Add(m1);
        tmlist.Add(m2);
        MatterBasket mb = new MatterBasket(tmlist);
        mb.setMatterQuantity(1, new Float(700));
        mb.setMatterQuantity(2, new Float(1000));
        this.processMatterBasket(mb);
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
    }
    
    @Override
    public void setMatterBasketAtOutlets(MatterBasket matterBasket) {
        this.setMatterBasket(matterBasket);
    }
    
}
