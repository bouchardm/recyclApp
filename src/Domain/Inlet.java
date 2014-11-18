package Domain;


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
        

        
        
}