package Domain;

import java.util.HashMap;


public class MatterBasket {
	private HashMap<Integer, Float> _quantity;   //string = nom matière, Float = quantité associée

    //constructeur sans paramètres : crée _quantity vide
    public MatterBasket() {
        _quantity = new HashMap<>();
    }
    
    //constructeur avec paramètre: 
    //_quantity contient tous les matière de la liste avec une quantité associée de 0
    public MatterBasket( MatterList aMatterList) {
        _quantity = new HashMap<>();
        if(aMatterList.GetCount()>0) { 
            for(int i = 0; i<aMatterList.GetCount(); i++)
            {
                if(aMatterList.GetCount()>0) {
                    _quantity.put(aMatterList.GetMatter(i).getID(),new Float(0));

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
}
