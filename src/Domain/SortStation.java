package Domain;

import java.awt.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SortStation extends Station {
	private Inlet _inlet;
	private List _outputList;
	private Float _speedMax;
	private SortMatrix _sortMatrix;
	private Integer _stationID;
	private Point _position;
	public SortMatrix _sortMatrix2;
	public Element _element;
	public SortMatrix _sortMatrix3;
        private Map<Matter,Integer> _exit;
        
        private Rectangle2D.Float rect;
        
        public SortStation()
        {
        }

        public void setExit(int nbExit) {
            
            this._exit = new HashMap<Matter,Integer>();
            
            this._exit.put(new Matter(), 100);
            
            for (int i = 1; i < nbExit; i++) {
                this._exit.put(new Matter(), 0);
            }
        }
        
}