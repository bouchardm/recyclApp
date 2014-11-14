package Domain;

import java.util.HashMap;

public class MatterBasket {
	private HashMap<String, Float> _quantity;   //string = nom matière, Float = quantité associée
	//private Object _matterlList;
	//private MatterList _matterList;        //SR: est-ce vraiment nécessaire???
	//public Outlet _outlet;
	//public SortCenter _sortCenter;
	//public Outlet _outlet2;
	//public MatterList _matterList2;

    //constructeur sans paramètres : crée _quantity vide
    public MatterBasket() {
        _quantity = new HashMap<>();
        //_matterList = new MatterList();
    }
    
    //constructeur avec paramètre: 
    //_quantity contient tous les matière de la liste avec une quantité associée de 0
    //_matterList est une copie profonde de aMatterList
    public MatterBasket( MatterList aMatterList) {
        _quantity = new HashMap<>();
        //_matterList = new MatterList();
        for(int i = 0; i<aMatterList.GetCount(); i++)
        {
            if(aMatterList.GetCount()>0) {
            //_matterList.Add(aMatterList.GetMatter(i));
            _quantity.put(aMatterList.GetMatter(i).getName(),new Float(0));
            }
        }
    }
    
    public void setMatterQuantity(String matterName, Float aQuantity) {
        if(_quantity.isEmpty()) {
            throw new IllegalArgumentException("Le panier est vide!");
        }
        else if(!this._quantity.containsKey(matterName)) {
            throw new IllegalArgumentException("La matière n'est pas dans le panier.");
        }
        this._quantity.remove(matterName);
        this._quantity.put(matterName, aQuantity);
    }
    
    public void addMatter(String matterName, Float aQuantity) {
        if(this._quantity.containsKey(matterName)) {
            throw new IllegalArgumentException("La matière est déjà dans le panier.");
        }
        this._quantity.put(matterName, aQuantity);
 
        
    }
    
    public float getMatterQuantity(String matterName) {
        if(_quantity.isEmpty()) {
            throw new IllegalArgumentException("Le panier est vide!");
        }
        else if(!this._quantity.containsKey(matterName)) {
            throw new IllegalArgumentException("La matière n'est pas dans le panier.");
        }
        return this._quantity.get(matterName);
    }
    
    
}