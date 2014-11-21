package Domain;


public class Outlet extends IOlet
{
	//private Conveyor _conveyor;
	//private Node _parentNode;
	private MatterBasket _matterBasket;
        
        public Outlet(Node parentNode)
        {
            super(parentNode);
            
        }
        
        public MatterBasket getMatterBasket()
        {
            return _matterBasket;
        }
        
        
        public void setMatterBasket( MatterBasket matterBasket)
        {
           _matterBasket = matterBasket;
        }
        
}