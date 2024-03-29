package Domain;

import java.awt.Color;
import java.util.ArrayList;


//import Domain.Node;
//import Domain.Conveyor;

public class Inlet extends IOlet
{
	private Float _speedMax;  // Float vs float?
        private boolean _free;

        public Inlet()
        {}
        
        public Inlet(Node parentNode)
        {
            super(parentNode);
            _color = Color.YELLOW;
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

    /**
     * @return the _free
     */
    public boolean isFree() {
        return _free;
    }

    /**
     * @param _free the _free to set
     */
    public void setFree(boolean _free) {
        this._free = _free;
    }
        
        
}