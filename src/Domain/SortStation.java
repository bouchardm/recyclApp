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
    private ArrayList<Outlet> _outletList;
    private SortMatrix _sortMatrix;
    private Map<Matter,Integer> _exit; // c'est outlet qui contient la proportion des matières et c'est implémenté avec MatterBasket

    private String _name;
    private String _description;
    private float _speedMax;

    private Image _img;

    private Boolean _selected; // la SortStation n'a pas à savoir ça

    public SortStation()
    {
        this._name = "";
        this._description = "";
        this._color = Color.RED;
        this._speedMax = 0;
        this._selected = false; 
        this._img = null;
        _outletList = new ArrayList<>();
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

    public float getKgHMax() {
        return _speedMax;
    }

    public void setKgHMax(float _kgHMax) {
        this._speedMax = _kgHMax;
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
      
    public void addOutlet(Outlet outlet){
        // add at the end of the list  
        _outletList.add(outlet);
    }
    
    public void removeOulet(int index)
    {
    _outletList.remove(index);
    }
    
    public int getOutletCount(){
        return _outletList.size();
    }
    
    //le nombre de matières dans matterBasket et la matrice doivent être identiques
    //le nombre de sorties du sortStation doit être pareil au nombres de sorties dans la matrice
    public void sortMatterBasketToOutlets(MatterBasket matterBasket) {
        //on va chercher la matrice de tri
        HashMap<Integer, ArrayList<Float>> sortMatrix = _sortMatrix.getSortMatrix();
        for(int i=0; i<this._outletList.size(); i++) {
            //créer un nouveau basket pour la sortie en question
            MatterBasket sortedBasketForOutlet = new MatterBasket();
            //extraire de matterBasket les matières à traiter
            HashMap<Integer, Float> basketQuantities = matterBasket.getQuantities();
            Iterator<Map.Entry<Integer, Float>> basketIter = basketQuantities.entrySet().iterator();
            //on itère dans le basket. 
            while(basketIter.hasNext()) {
                Map.Entry<Integer, Float> currentEntry = basketIter.next();
                //la nouvelle quantité de matière pour le nouveau matterBasket = % dans la matrice * la quantité dans le panier
               
                int currentMatterID = currentEntry.getKey();
                float percentageForOutlet = sortMatrix.get(currentMatterID).get(i);
                float newQtyForMatterBasket = matterBasket.getMatterQuantity(currentEntry.getKey()) * percentageForOutlet;
                sortedBasketForOutlet.addMatterQuantity(currentMatterID, newQtyForMatterBasket);
            }
            //setMatterBasket de la sortie
            _outletList.get(i).setMatterBasket(sortedBasketForOutlet);  
        }
    }
    
    public void setSortMatrix(SortMatrix sorter) {
        _sortMatrix = sorter;
    }
    
    public SortMatrix getSortMatrix() {
        SortMatrix sm = new SortMatrix();
        sm.setSortMatrix(_sortMatrix.getSortMatrix());
        return sm;
    }
    
    //ATTENTION: on ne devrait pas tenter de "setter" le nombre de outlets
    //en tant que tel. On devrait plutôt ajouter ou retirer de la liste les
    //outlets concernés
    public void setExit(int nbExit) {
        this._exit = new HashMap<Matter,Integer>();

        this._exit.put(new Matter("todo: change", 0), 100);

        for (int i = 1; i < nbExit; i++) {
            this._exit.put(new Matter("todo: change", i), 0);
        }
    }
    
//        public void setExit(Map<Matter, Integer> _exit) {
//        this._exit = _exit;
//    }
    
    public ArrayList<Outlet> getOutletList() {
        return _outletList;
    }
}