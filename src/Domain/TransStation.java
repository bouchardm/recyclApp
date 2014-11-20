package Domain;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class TransStation extends Station {
	private TransMatrix _transformMatrix;
	
    @Override
    public void setTransMatrix(TransMatrix newTransMatrix) {
        _transformMatrix = newTransMatrix;
    }

    public TransMatrix getTransMatrix() {
        return _transformMatrix;
    }
        
    public TransStation() {
        super();
        _transformMatrix = new TransMatrix();
    }
    
    //transforme le contenu du matterBasket et appel ensuite la méthode SortMatterBasket pour le trier aux outlets
    //precondition 1: il doit y avoir autant de matière dans le matterBasket que dans la transMatrix
    //precondition 2: chaque matiere doit avoir une quantité de transformation pour chaque matiere du matterBasket
    @Override
    public void processMatterBasket(MatterBasket matterBasket) {
        //tester precondition 1
        if(matterBasket.getNumberOfMatterInBasket()!=this._transformMatrix.getMatterCount()) {
            throw new IllegalArgumentException("La quantité de matières dans le panier et dans la matrice n'est pas pareil.");
        }
        //on va chercher une copie de la matrice de transformation
        HashMap<Integer, HashMap<Integer, Float>> transMatrix = _transformMatrix.getTransMatrix();
        Iterator<Map.Entry<Integer, HashMap<Integer, Float>>> tmIter = transMatrix.entrySet().iterator();
        //on iter dans les "rangées" de la matrice
        HashMap<Integer, Float> newQuantities = new HashMap<>();
        while(tmIter.hasNext()) {
            Map.Entry<Integer, HashMap<Integer, Float>> currentEntry = tmIter.next();
            int currentMatterId = currentEntry.getKey();
            HashMap<Integer, Float> currentMatterTransQuantities = currentEntry.getValue();
            Iterator<Map.Entry<Integer, Float>> innerIter = currentMatterTransQuantities.entrySet().iterator();
            while(innerIter.hasNext()) {
                Map.Entry<Integer, Float> innerEntry = innerIter.next();
                int transformToMatterID = innerEntry.getKey();
                float transformQtyFactor = innerEntry.getValue();
                //tester precondition 2
                //on essaie le traitement suivant. on attrapera les erreurs si une matière  de la matrice
                //n'est pas dans le basket
                try {
                    //on doit trouver les pourcentage de transformation par matière.
                    //dans newQuantities, on mettra à chaque itération les nouvelles quantités de chaque matière
                    //une vérif (if) doit être faite
                    if(newQuantities.containsKey(transformToMatterID)) {
                        //si matterID est in newQuantities, on fait l'addition et on mets la nouvelle valeur
                        float interimQty = newQuantities.get(transformToMatterID);
                        float newMatterQty = interimQty + (transformQtyFactor*matterBasket.getMatterQuantity(currentMatterId));
                        newQuantities.remove(transformToMatterID);
                        newQuantities.put(transformToMatterID, newMatterQty);
                    }
                    else {
                        //sinon, on fait l'ajout tout simplement
                        float newMatterQty = transformQtyFactor*matterBasket.getMatterQuantity(currentMatterId);
                        newQuantities.put(transformToMatterID, newMatterQty);
                    }                
                }
                catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Une matière de la matrice de transformation n'est "
                            + "pas présente dans le panier de matières.");
                }
            }
                    
                    
            
            //on change les quantités du matterBasket pour réfléter les nouvelles quantités
        }
        matterBasket.setQuantities(newQuantities);
        super.processMatterBasket(matterBasket);
    }

    @Override
    public ArrayList<IOlet> getIOlets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}