package Domain;

import java.awt.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

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
        
        private Rectangle2D.Float rect;
        
        public SortStation()
        {
        }
}