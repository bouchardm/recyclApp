/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import Presentation.Swing.Viewport;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public abstract class Station extends RectangularNode
{
    private Image _img;
    private Inlet _inlet;
    private ArrayList<Outlet> _outletList;
    private SortMatrix _sortMatrix;
    private BigDecimal _usageRate;

    private String _name;
    private String _description;
    private float _speedMax;
    
    public Station()
    {
        this._name = "Station";
        this._description = "";
        this._color = Color.orange;
        this._speedMax = 0;
//        this._selected = false; 
        this._img = null;
        _inlet = new Inlet(this);
        
        _outletList = new ArrayList<>();
        _sortMatrix = new SortMatrix();
        _errorMessages = new ArrayList<>();
        _usageRate = new BigDecimal(0);
    }
    
    public abstract void setTransMatrix(TransMatrix tm);
    
    //precondition 1: le nombre de matières dans matterBasket et la matrice doivent être identiques
    //precondition 2: le nombre de sorties du sortStation doit être pareil au nombres de sorties dans la matrice
    @Override
    public void processMatterBasket(MatterBasket matterBasket) {
        //on commence par éliminer les messages d'erreurs courant s'il y en a pour la station
        this.clearErrorMessages();
        //precondition 1
        if (matterBasket.getQuantities().size()!=this.getSortMatrix().getMatterCount()) {
            throw new IllegalArgumentException("Le nombre de matières dans la liste de matière et la matrice de tri ne concorde pas.");
        }
        //precondition2
        HashMap<Integer, ArrayList<Float>> preconditionTest = this.getSortMatrix().getSortMatrix();
        Iterator<Map.Entry<Integer, ArrayList<Float>>> ptIter = preconditionTest.entrySet().iterator();
        while (ptIter.hasNext()) {
            Map.Entry<Integer, ArrayList<Float>> currentTest = ptIter.next();
            if(currentTest.getValue().size()!=this.getOutletList().size()){
                throw new IllegalArgumentException("La station n'a pas le même nombre de sorties que le nombre dans la matrice de tri.");
            }
        }
        //on va chercher la matrice de tri
        HashMap<Integer, ArrayList<Float>> sortMatrix = this.getSortMatrix().getSortMatrix();
        for(int i=0; i<this.getOutletList().size(); i++) {
            //l'outlet qu'on traite
            Outlet currentOutlet = this.getOutletList().get(i);
            //on commence par enlever tous ses messages d'erreurs
            currentOutlet.clearErrorMessages();
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
            
            currentOutlet.setMatterBasketQty(sortedBasketForOutlet);  
            if(!currentOutlet.hasConveyor() && sortedBasketForOutlet.getTotalQuantity()>0) {
                this.addErrorMessage("La sortie "+(i+1)+" reçoit de la matière mais n'est pas connecté à un convoyeur.");
            } 
        }
        if(this.getSpeedMax()!=0) {
            this.setUsageRate(new BigDecimal((float)this.getTotalMatterQuantity()/this.getSpeedMax()));
        }
        if(this.getUsageRate().compareTo(new BigDecimal(1))>0 || (this.getTotalMatterQuantity()>0 && 0==this.getSpeedMax())) {
            this.addErrorMessage("La station "+this.getName()+" reçoit une quantité de matière supérieure à sa capacité.");
        }
    }
    
    
    public void setSortMatrix(SortMatrix sorter) {
        _sortMatrix = sorter;
    }
    
    public SortMatrix getSortMatrix() {
        return _sortMatrix;
    }
    
    public void addMatterToStationSortMatrix(Integer matterID) {
        _sortMatrix.addMatterToSortMatrix(matterID);
    }
    
    @Override
    public void setAttribute(String attribName, Object value)
    {   
        try
        {
            super.setAttribute(attribName, value);
        }
        catch (IllegalArgumentException e)
        {
            switch (attribName)
            {
                case "img":
                    this.setImg((String) value);
                    break;
                case "name":
                    this.setName((String) value);
                    break;
                case "description":
                    this.setDescription((String) value);
                    break;
                case "speedMax":
                    this.setKgHMax((Float) value);
                    break;
                case "color":
                    this.setColor((Color) value);
                    break;
                case "sortMatrix":
                    SortMatrix sortMatrix = new SortMatrix();
                    sortMatrix.setSortMatrix((HashMap<Integer, ArrayList<Float>>) value);
                    this.setSortMatrix(sortMatrix);
                    break;
                case "dimensionX":
                    this.setDimensions(this.getDimensions().y, (Float) value);
                    break;
                case "dimensionY":
                    this.setDimensions((Float) value, this.getDimensions().x);
                    break;
                default:
                    throw new IllegalArgumentException(String.format("no method for set %s", attribName));
            }
        }
        
    }
    
    public int getOutletIndex(Outlet outlet)
    {
        for (int i=0; i<getOutletList().size(); i++)
        {
            if (outlet.equals(getOutletList().get(i)))
            {
                return i;
            }
        }
        return -1;
    }


    @Override
    public Object getAttribute(String attribName) {
        try
        {
            return super.getAttribute(attribName);
        }
        catch (IllegalArgumentException e)
        {
            switch(attribName) {
                case "name":
                    return this.getName();
                case "description":
                    return this.getDescription();
                case "color":
                    return this.getColor();
                case "speedMax":
                    return this.getKgHMax();
                case "img":
                    return this.getImg();
                case "outletList":
                    return this.getOutletList();
                case "sortMatrix":
                    return this.getSortMatrix().getSortMatrix();
                case "matterQuantity":
                    float matterQuantity = 0;
                    for (Outlet outlet : getOutletList()) {
                        matterQuantity = matterQuantity + outlet.getMatterBasketQty().getTotalQuantity();
                    }
                    return matterQuantity;
                case "matterQuantities":
                    return this.getAllMatterQuantitiesAtOutlets();
                case "dimensionX":
                    return this.getDimensions().x;
                case "dimensionY":
                    return this.getDimensions().y;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
    
    public float getTotalMatterAtOutlet(int outletIndex) {
        return getOutletList().get(outletIndex).getTotalMatterQuantity();
    }
    
    public float getTotalMatterQuantity() {
        float totalQty = 0;
        for (int i = 0; i<this.getOutletList().size(); i++) {
            totalQty = totalQty + getOutletList().get(i).getTotalMatterQuantity();
        }
        return totalQty;
    }
    
     
    @Override
    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public void setDescription(String _description) {
        this._description = _description;
    }

    public float getKgHMax() {
        return getSpeedMax();
    }

    public void setKgHMax(float _kgHMax) {
        this.setSpeedMax(_kgHMax);
    }
    @Override
    public Color getColor() {
        return _color;
    }

    @Override
    public void setColor(Color _color) {
        this._color = _color;
    }
    
    @Override
    public Image getImg() {
        return _img;
    }

    @Override
    public void setImg(String src) {
        this.setImg(Toolkit.getDefaultToolkit().getImage(src));
    }
      
    public void addOutlet()
    {
        // add at the end of the list  
        getOutletList().add(new Outlet(this));
    }
    
    public void removeOulet(int index)
    {
        getOutletList().remove(index);
    }
    
    public int getOutletCount()
    {
        return getOutletList().size();
    }
    
    public Inlet getInlet()
    {
        return _inlet;
    }
    
    public ArrayList<Outlet> getOutletList() {
        return _outletList;
    }
    
    @Override
    public void setMatterBasketAtOutlets(MatterBasket matterBasket) {
        for(Outlet outlet : this.getOutletList()) {
            outlet.setMatterBasketQty(matterBasket);
        }
    }
    
    public HashMap<Integer, Float> getAllMatterQuantitiesAtOutlets() {
        ArrayList<Outlet> outletList = this.getOutletList();
        HashMap<Integer, Float> totalMB = new HashMap<>();

        for(Outlet outlet : outletList) {
            HashMap<Integer, Float> currentMB = outlet.getMatterBasketQty().getQuantities();
            for(Map.Entry<Integer, Float> iter : currentMB.entrySet()) {
                if(totalMB.containsKey(iter.getKey())) {
                    float newQty = (float)iter.getValue()+totalMB.get(iter.getKey());
                    totalMB.remove(iter.getKey());
                    totalMB.put(iter.getKey(), newQty);
                } else {
                    totalMB.put(iter.getKey(), iter.getValue());
                }
            }
        }
        return totalMB;
    }

    /**
     * @param _img the _img to set
     */
    public void setImg(Image _img) {
        this._img = _img;
    }

    /**
     * @param _inlet the _inlet to set
     */
    public void setInlet(Inlet _inlet) {
        this._inlet = _inlet;
    }

    /**
     * @param _outletList the _outletList to set
     */
    public void setOutletList(ArrayList<Outlet> _outletList) {
        this._outletList = _outletList;
    }

    /**
     * @return the _usageRate
     */
    public BigDecimal getUsageRate() {
        return _usageRate;
    }

    /**
     * @param _usageRate the _usageRate to set
     */
    public void setUsageRate(BigDecimal _usageRate) {
        this._usageRate = _usageRate;
    }

    /**
     * @return the _speedMax
     */
    public float getSpeedMax() {
        return _speedMax;
    }

    /**
     * @param _speedMax the _speedMax to set
     */
    public void setSpeedMax(float _speedMax) {
        this._speedMax = _speedMax;
    }
}
