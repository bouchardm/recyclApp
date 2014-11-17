package Domain;

import java.awt.Color;
import java.awt.Image;
import java.awt.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SortStation extends Station {
	private Inlet _inlet;
	private List _outputList; // on parle d'outlet pas d'output
	private Float _speedMax;
	private SortMatrix _sortMatrix;
	private Integer _stationID; // déjà hérité de Node._id
	private Point _position; // la position s'obtient par _rect non pas par _position
	public SortMatrix _sortMatrix2; // aucun rapport
	public Element _element; // aucun rapport
	public SortMatrix _sortMatrix3; // aucun rapport
        private Map<Matter,Integer> _exit; // c'est outlet qui contient la proportion des matières et c'est implémenté avec MatterBasket
        
        private Rectangle2D.Float rect; // déjà hérité ... entant que _rect
        private String _name;
        private String _description;
        private float _kgHMax; // duplicata avec _speedMax (quel nom d'attribut est dans le modèle?)
        private Color _color; // aucun rapport dans le domaine
        private Boolean _selected; // la SortStation n'a pas à savoir ça
        private Image _img; // l'image n'a pas affaire dans le domaine
        
        public SortStation()
        {
            this._name = "";
            this._description = "";
            this._color = Color.RED;
            this._kgHMax = 0;
            this._selected = false; 
            this._img = null;
        }

        public void setExit(int nbExit) {
            this._exit = new HashMap<Matter,Integer>();
            
            this._exit.put(new Matter("todo: change", 0), 100);
            
            for (int i = 1; i < nbExit; i++) {
                this._exit.put(new Matter("todo: change", i), 0);
            }
        }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String _description) {
        this._description = _description;
    }

    public Map<Matter, Integer> getExit() {
        return _exit;
    }

    public void setExit(Map<Matter, Integer> _exit) {
        this._exit = _exit;
    }

    
    public float getKgHMax() {
        return _kgHMax;
    }

    public void setKgHMax(float _kgHMax) {
        this._kgHMax = _kgHMax;
    }

    public Color getColor() {
        return _color;
    }

    public void setColor(Color _color) {
        this._color = _color;
    }
    
    public Boolean isSelected() {
        return _selected;
    }

    public void setSelected(Boolean _selected) {
        this._selected = _selected;
    }
    
    public Image getImg() {
        return _img;
    }

    public void setImg(String src) {
        this._img = Toolkit.getDefaultToolkit().getImage(src);
    }
        
}