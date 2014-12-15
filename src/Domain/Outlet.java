package Domain;

import java.util.ArrayList;

public class Outlet extends IOlet
{
	//private Conveyor _conveyor;
	//private Node _parentNode;
	private MatterBasket _matterBasket;
        
        public Outlet()
        {
       
        }
        public Outlet(Node parentNode)
        {
            super(parentNode);
            _relativePosition.x = 1;
            _relativePosition.y = (float)Math.random();
            setPosition(_relativePosition);
            _matterBasket = new MatterBasket();
            _errorMessages = new ArrayList<>();
        }
        
        public Outlet(Node parentNode, MatterList matterList)
        {
            super(parentNode);
            _relativePosition.x = 1;
            _relativePosition.y = (float)Math.random();
            setPosition(_relativePosition);
            this._matterBasket = new MatterBasket(matterList);
        }
        
        public MatterBasket getMatterBasket()
        {
            MatterBasket mb = new MatterBasket();
            mb.setQuantities(_matterBasket.getQuantities());
            return mb;
        }
        
        
        public void setMatterBasket( MatterBasket matterBasket)
        {
           _matterBasket.setQuantities(matterBasket.getQuantities());
        }
        
        public float getTotalMatterQuantity() {
            return _matterBasket.getTotalQuantity();
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
            if (_node instanceof Station)
            {
                int index = ((Station)_node).getOutletIndex(this);
                if (index >= 0)
                {
                    identifier = Integer.toString(index + 1);
                }
            }
            return identifier;
        }
        
}