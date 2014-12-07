package Domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MatterBasket {
	private HashMap<Integer, Float> _quantity;   //string = nom matière, Float = quantité associée

    //constructeur sans paramètres : crée _quantity vide
    public MatterBasket() {
        _quantity = new HashMap<>();
    }
    
    public boolean contains(int matterID) {
        return _quantity.containsKey(matterID);
    }
    
    public HashMap<Integer, Float> getQuantities() {
        HashMap<Integer, Float> copiedQuantities = new HashMap<>();
        Iterator<Map.Entry<Integer, Float>> mbIter = _quantity.entrySet().iterator();
        while(mbIter.hasNext()) {
            Map.Entry<Integer, Float> currentEntry = mbIter.next();
            int matterID = currentEntry.getKey();
            float matterQty = currentEntry.getValue();
            copiedQuantities.put(matterID, matterQty);
        }
        return copiedQuantities;
    }
    
    public void setQuantities(HashMap<Integer, Float> newQuantities) {
        _quantity.clear();
        _quantity = new HashMap<>();
        Iterator<Map.Entry<Integer,Float>> mbIter = newQuantities.entrySet().iterator();
        while (mbIter.hasNext()) {
            Map.Entry<Integer, Float> currentEntry = mbIter.next();
            int matterID = currentEntry.getKey();
            float matterQty = currentEntry.getValue();
            _quantity.put(matterID, matterQty);
        }
    }
    
    //constructeur avec paramètre: 
    //_quantity contient tous les matière de la liste avec une quantité associée de 0
    public MatterBasket( MatterList aMatterList) {
        _quantity = new HashMap<>();
        if(0<aMatterList.getCount()) { 
            for(int i = 0; i<aMatterList.getCount(); i++)
            {
                if(aMatterList.getCount()>0) {
                    _quantity.put(aMatterList.getMatter(i).getID(),new Float(0));
                }
            }
        }
    }
    
    public void setMatterQuantity(Integer matterID, Float aQuantity)
    {
        if(_quantity.isEmpty()) {
            throw new IllegalArgumentException("Le panier est vide!");
        }
        else if(!this._quantity.containsKey(matterID)) {
            throw new IllegalArgumentException("La matière n'est pas dans le panier.");
        }
        this._quantity.remove(matterID);
        this._quantity.put(matterID, aQuantity);
    }
    
    public void addMatterQuantity(Integer matterID, Float aQuantity) {
        if(this._quantity.containsKey(matterID)) {
            throw new IllegalArgumentException("La matière est déjà dans le panier.");
        }
        this._quantity.put(matterID, aQuantity);
    }
    
    public float getMatterQuantity(Integer matterID) {
        if(_quantity.isEmpty()) {
            throw new IllegalArgumentException("Le panier est vide!");
        }
        else if(!this._quantity.containsKey(matterID)) {
            throw new IllegalArgumentException("La matière n'est pas dans le panier.");
        }
        return this._quantity.get(matterID);
    }
    
    //enleve une matiere du matter basket
    public void removeMatterQuantity(Integer matterID)
    {
        if(_quantity.isEmpty()) {
            throw new IllegalArgumentException("Le panier est vide!");
        }
        else if(!this._quantity.containsKey(matterID)) {
            throw new IllegalArgumentException("Erreur: la matière demandée n'est pas dans le panier. Aucune action effectuée.");
        }
        this._quantity.remove(matterID);
    }
    
    //renvoie la quantité total de matière dans le matterBasket
    public float getTotalQuantity()
    {
        if(_quantity.isEmpty()) {
            return 0;
        }
        else {
            float total = 0;
            for (Integer key : _quantity.keySet()) {
                total = total + _quantity.get(key);
            }
            return total;
        }
    }
    
    public int getNumberOfMatterInBasket() {
        return _quantity.size();
    }
}
