package Domain;


import Domain.Node;
import Domain.Conveyor;

public class Inlet extends IOlet
{
	private Float _speedMax;
	private Conveyor _conveyor;
	private Node _parentNode;

        
        public Inlet(Node parentNode)
        {
            super(parentNode);
        }
        
        public boolean isFree()
        {
            return _conveyor == null;
        }
}