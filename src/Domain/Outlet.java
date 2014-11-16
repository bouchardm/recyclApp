package Domain;


import Domain.Node;
import Domain.Conveyor;

public class Outlet extends IOlet
{
	private Conveyor _conveyor;
	private Node _parentNode;
	private MatterBasket _matterBasket;
        
        public Outlet(Node parentNode)
        {
            super((Node)parentNode);
        }
        
        public MatterBasket getMatterBasket()
        {
            return _matterBasket;
        }
}