package Domain;



import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;


public class SortMatrix {
    /* 
    _sortMatrix: Le premier Integer est le MatterID. Ensuite, on a une liste
    qui contient la matière qui doit se retrouver à chaque sortie, en ordre
    de l'index de la liste. L'index 0 doit être la sortie 1, l'index 1 la sortie 2, etc.
    */
    private HashMap<Integer, ArrayList<Float>> _sortMatrix;

    public SortMatrix() {
        _sortMatrix = new HashMap<>();
    }
    
    //constructeur : recoit matterList et outletList en paramètre
    //crée une matrice qui met toutes les matières à la première sortie dans la liste
    public SortMatrix(MatterList aMatterList, ArrayList<Outlet> outletList) {
        _sortMatrix = new HashMap<>();
        for(int i = 0; i<aMatterList.GetCount(); i++) {
            ArrayList<Float> matrixOutlets = new ArrayList<>();
            for (int j = 0; j<outletList.size(); j++) {
                if(j==0) {
                    Float aQuantity;
                    aQuantity = new Float(1);
                    matrixOutlets.add(aQuantity);
                }
                else {
                    Float aQuantity;
                    aQuantity = new Float(0);
                    matrixOutlets.add(aQuantity);
                }
            }
            _sortMatrix.put(aMatterList.getMatterID(i), matrixOutlets);   
        }
    }

    /*addMatterToSortMatrix(matterID, matterPerOutlet): pour chaque matière qu'on ajoute à la matrice, on ajoute: le numéro ID de la matière
    ainsi qu'une liste contenant l'index de la sortie dans la liste et la quantité qui doit se retourner
    à la sortie.*/
    //precondition : matterId est valide
    public void addMatterToSortMatrix(Integer matterID, ArrayList<Float> matterPerOutlet) {
        _sortMatrix.put(matterID, matterPerOutlet);
    }
    
    /*addToSortMatrix: pour chaque matière qu'on ajoute à la matrice, on ajoute: le numéro ID de la matière
                       On met toute la quantité à sortir dans la première outlet.  */
    //precondition : matterId est valide
    public void addMatterToSortMatrix(Integer matterID) {
        ArrayList<Float> matterPerOutlet = new ArrayList<>();
        Iterator<Map.Entry<Integer, ArrayList<Float>>> smIter = _sortMatrix.entrySet().iterator();
        if(!smIter.hasNext()) {
            matterPerOutlet.add(new Float(1));
            _sortMatrix.put(matterID, matterPerOutlet);
        }
        else {
            Map.Entry<Integer, ArrayList<Float>> currentEntry = smIter.next();
            int outletListSize = currentEntry.getValue().size();
            for (int i = 0; i < outletListSize; i++) {
                if(i==0) {
                    matterPerOutlet.add(new Float(1));
                }
                else {
                    matterPerOutlet.add(new Float(0));
                }
            }
            _sortMatrix.put(matterID, matterPerOutlet);
        }
    }

    //enleve une matiere par son numéro ID
    public void removeMatterFromMatrix(Integer matterID) {
        if((!_sortMatrix.containsKey(matterID)) || _sortMatrix.isEmpty()) {
            throw new IllegalArgumentException("Effacement impossible : la matière n'est pas dans la matrice.");
        }
         _sortMatrix.remove(matterID);
    }
    

    //precondition : l'index passé en paramètre correspond au numéro de la sortie-1
                            //ex: Sortie1 = index 0 ... Sortie2 = index1, etc.
    //precondition : on ne peut pas effacer une sortie dans une matrice qui en a déjà pas
    //PAS TESTÉE
    public void removeOutletFromMatrix(Integer outletListIndex) {
        Iterator<Map.Entry<Integer, ArrayList<Float>>> smIter = _sortMatrix.entrySet().iterator();
        if(outletListIndex==0) {        //si oui
            //pour toutes les matieres, 
                //prendre les quantités de la première sortie et les mettres dans la deuxième
            while(smIter.hasNext()) {
                Map.Entry<Integer, ArrayList<Float>> currentEntry = smIter.next();
                Integer currentMatterId = currentEntry.getKey();        //le ID de la matière courante
                ArrayList<Float> currentOutletQuantities = currentEntry.getValue();  //la liste associée à cette matière
                if (currentOutletQuantities.size()==1) {
                    currentOutletQuantities.remove(0);
                }
                else {
                    float qtyAtFirstOutlet = currentOutletQuantities.get(0);
                    float qtyAtSecondOutlet = currentOutletQuantities.get(1);
                    float newQtyAtFirstOutlet = qtyAtFirstOutlet + qtyAtSecondOutlet;
                    currentOutletQuantities.remove(0);
                    currentOutletQuantities.remove(1);
                    //la nouvelle premiere entrée est la sortie 3 originale... mais elle doit devenir la deuxième
                    currentOutletQuantities.add(0, newQtyAtFirstOutlet);  
                }
            }
        }
        else {                          //si non
            //prendre les quantités de la sortie à effacer et les mettres dans la première
            while(smIter.hasNext()) {
                Map.Entry<Integer, ArrayList<Float>> currentEntry = smIter.next();
                Integer currentMatterId = currentEntry.getKey();
                ArrayList<Float> currentOutletQuantities = currentEntry.getValue();
                if(currentOutletQuantities.size()==1) {
                    currentOutletQuantities.remove(0);
                }
                else {
                    float qtyAtFirstOutlet = currentOutletQuantities.get(0);
                    float qtyAtDeletedOutlet = currentOutletQuantities.get(outletListIndex);
                    float newQtyAtFirstOutlet = qtyAtFirstOutlet + qtyAtDeletedOutlet;
                    currentOutletQuantities.remove(0);
                    currentOutletQuantities.add(0, newQtyAtFirstOutlet);
                    currentOutletQuantities.remove(outletListIndex.intValue());   
                }
            }
        }

    }
    
    //enleve tout les matières de la sortMatrix (reste une matrice vide)
    public void removeAllMatterFromSortMatrix() {        
        Iterator<Map.Entry<Integer, ArrayList<Float>>> smIter = _sortMatrix.entrySet().iterator();
        while(smIter.hasNext()) {
            Map.Entry<Integer, ArrayList<Float>> currentEntry = smIter.next();
            smIter.remove();
        }
    }
    
    //ajoute un outlet à la fin des tableau de quantité. les quantités sont à 0.
    public void addOutletToSortMatrix() {
        Iterator<Map.Entry<Integer, ArrayList<Float>>> smIter = _sortMatrix.entrySet().iterator();
        while(smIter.hasNext()) {
            Map.Entry<Integer, ArrayList<Float>> currentEntry = smIter.next();
            _sortMatrix.get(currentEntry.getKey()).add(new Float(0));
        }
    }
    
    //retourne une copie profonde de _sortMatrix
    public HashMap<Integer, ArrayList<Float>> getSortMatrix()
    {
        HashMap<Integer, ArrayList<Float>> copiedSortMatrix = new HashMap<>();
        for(Integer key : _sortMatrix.keySet()) {
            ArrayList<Float> copiedInnerList = new ArrayList<>();
            for(int j = 0; j<_sortMatrix.get(key).size(); j++) {
                Float quantityAtExit = _sortMatrix.get(key).get(j);
                copiedInnerList.add(quantityAtExit);
            }
            copiedSortMatrix.put(key, copiedInnerList);
        }
        return copiedSortMatrix;
    }
    
    
    public int getMatterCount() {
        return _sortMatrix.size();
    }
        
        
        
        
}