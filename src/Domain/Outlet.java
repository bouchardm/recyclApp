package Domain;

public class Outlet extends IOlet
{
	//private Conveyor _conveyor;
	//private Node _parentNode;
	private MatterBasket _matterBasket;
        
        public Outlet(Node parentNode)
        {
            super(parentNode);
            _relativePosition.x = 1;
            _relativePosition.y = (float)Math.random();
            setPosition(_relativePosition);
            _matterBasket = new MatterBasket();
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
        
}