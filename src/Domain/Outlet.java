package Domain;

import java.awt.Color;
import java.util.ArrayList;

public class Outlet extends IOlet
{
	//private Conveyor _conveyor;
	//private Node _parentNode;
	private MatterBasket _matterBasket;
        
        public Outlet()
        {
            _matterBasket = new MatterBasket();
        }
        public Outlet(Node parentNode)
        {
            super(parentNode);
            _color = Color.BLUE;
            _relativePosition.x = 1;
            _relativePosition.y = (float)Math.random();
            setPosition(_relativePosition);
            _matterBasket = new MatterBasket();
            _errorMessages = new ArrayList<>();
        }
        
//        public Outlet(Node parentNode, MatterList matterList)
//        {
//            super(parentNode);
//            _relativePosition.x = 1;
//            _relativePosition.y = (float)Math.random();
//            setPosition(_relativePosition);
//            this._matterBasket = new MatterBasket(matterList);
//        }
        public MatterBasket getMatterBasket()
        {
        return this._matterBasket;
        }
        
        public void setMatterBasket(MatterBasket _matterBasket)
        {
        this._matterBasket = _matterBasket;
        }
        public MatterBasket getMatterBasketQty()
        {
            MatterBasket mb = new MatterBasket();
            mb.setQuantities(_matterBasket.getQuantities());
            return mb;
//            return this._matterBasket;
        }
        
        
        public void setMatterBasketQty( MatterBasket matterBasket)
        {
           _matterBasket.setQuantities(matterBasket.getQuantities());
//           this._matterBasket = _matterBasket;
        }
        
        public float getTotalMatterQuantity() {
            return getMatterBasketQty().getTotalQuantity();
        }
        
        public boolean hasConveyor() {
            if(this.getConveyor()==null){
                return false;
            }
            else{
                return true;
            }
        }
        
        
        @Override
        public String getIdentifier()
        {
            String identifier = "";
            if (getNode() instanceof Station)
            {
                int index = ((Station)getNode()).getOutletIndex(this);
                if (index >= 0)
                {
                    identifier = Integer.toString(index + 1);
                }
            }
            return identifier;
        }
        
}