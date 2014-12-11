package Domain;

import java.util.ArrayList;


//import Domain.Node;
//import Domain.Conveyor;

public class Inlet extends IOlet
{
	private Float _speedMax;  // Float vs float?
        private boolean _free;

        
        public Inlet(Node parentNode)
        {
            super(parentNode);
            _speedMax = null ; // Valeur initiale?
            _free = true;
            _relativePosition = _node.getCenter();
            _relativePosition.x = _relativePosition.x - 1;
            _relativePosition.x = _relativePosition.y - 1;
            setPosition(_relativePosition);
            _errorMessages = new ArrayList<>();
        }
        
       /* public boolean isFree()
        {
            return _conveyor == null;
        } */
        
        public void setSpeedMax(Float speedMax)
        {
        _speedMax = speedMax;
        }
        
        public Float getSpeedMax(){
        
            return _speedMax;
        }
        
        @Override
        public String getIdentifier()
        {
            return "";
        }
        
        
}