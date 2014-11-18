package Domain;


import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


public class TransMatrix {
    //l'attribut transformMatrix : HashMap<MatterID, <Array de qté par matière, dans le même ordre que le matterList utilisé pour le construire>>
    
    private HashMap<Integer, HashMap<Integer, Float>> _transformMatrix;
        
    //constructeur sans paramètres
    public TransMatrix() {
        _transformMatrix = new HashMap<>();
    }
    
    //constructeur avec paramètres
    //par défaut, crée une matrice qui ne fait aucune transformation (ex: 100% de matière A reste matière A)
    public TransMatrix(MatterList matterList) {
        _transformMatrix = new HashMap<>();
        HashMap<Integer, Float> transformQuantities = new HashMap<>();
        for(int i = 0; i < matterList.GetCount(); i++) {
            for(int j = 0; j< matterList.GetCount(); j++) {
                float transformIntoQty;
                if (i==j) {
                    transformIntoQty = 1;
                }
                else {
                    transformIntoQty = 0;
                }
                transformQuantities.put(matterList.getMatterID(j), transformIntoQty);
            }
            _transformMatrix.put(matterList.getMatterID(i), transformQuantities);
        }
    }
    
    //ajoute une matière aux deux hashmaps sans transformation
    //ex: si on ajoute la matière B, la matière B sera transformé à 100% en matière B
    //toutes les autres matières seront transformé à 0% en matière B
    public void addMatterToTransMatrix(Integer matterID) {
        //créer map pour la nouvelle matière        
        HashMap<Integer, Float> transformQty = new HashMap<>();
        //si la matrice est vide, on ne fait qu'ajouter la matière et elle se transforme en elle même
        if(!_transformMatrix.isEmpty()) {
            Iterator<Map.Entry<Integer, HashMap<Integer, Float>>> tmIter = _transformMatrix.entrySet().iterator();
            while (tmIter.hasNext()) {
                Map.Entry<Integer, HashMap<Integer, Float>> currentEntry = tmIter.next();
                Integer currentMatterID = currentEntry.getKey();
                //on ajoute 0 pour toutes les matter ID
                transformQty.put(currentMatterID, new Float(0));
            }
        }
        //on mets transformation à 100% de la matière à elle même
        transformQty.put(matterID, new Float(1));
        _transformMatrix.put(matterID, transformQty); 
    }
    
    //ajoute la nouvelle matière et les quantités dans lequel elle se transforme dans le transformMatrix
    //ajoute une entrée pour toutes les autres matières indiquant qu'ils se transforment à 0%
    //en cette nouvelle matière
    //le HashMap en entrée (transformQuantities) doit contenir une valeur de transformation pour
    //toutes les matières du matterList incluant la matière elle même
    public void addMatterToTransMatrix(Integer matterID, HashMap<Integer, Float> transformQuantities) {
        //boucle dans le hashmap. pour chaque clé, on ajoute au hashMap interne l'entry (matterID, 0).
        Iterator<Map.Entry<Integer, HashMap<Integer, Float>>> tmIter = _transformMatrix.entrySet().iterator();
        while(tmIter.hasNext()) {
            HashMap<Integer, Float> newTransformQuantities = new HashMap<>();
            Map.Entry<Integer, HashMap<Integer, Float>> currentEntry = tmIter.next();
            Integer currentID = currentEntry.getKey();
            //on va chercher les valeurs courantes du hashmap interne pour le recopier dans un nouveau
            //hashmap avec les nouvelles valeurs
            HashMap<Integer, Float> matterTransformQuantities = currentEntry.getValue();
            Iterator<Map.Entry<Integer, Float>> innerIter = matterTransformQuantities.entrySet().iterator();
            while(innerIter.hasNext()) {
                Map.Entry<Integer, Float> currentInnerEntry = innerIter.next();
                Integer innerKey = currentInnerEntry.getKey();
                Float innerQty = currentInnerEntry.getValue();
                newTransformQuantities.put(innerKey, innerQty);
                //effacer l'entry courante (du inner hashmap)
                //!!!!!!!!!ICI!!!!!!!
            }
            //rajouter les nouvelles quantités pour chaque matière à _transformMatrix
        }
        //ajout au transformMatrix : (matterID, transformQuantities)
        
    }
    
    public int getMatterCount() {
        return _transformMatrix.size();
    }
    
    //doit retourner une copie profonde de transMatrix
    public HashMap<Integer, HashMap<Integer, Float>> getTransMatrix() {
        HashMap<Integer, HashMap<Integer, Float>> copiedMatrix = new HashMap<>();
        //todo
        return copiedMatrix;
    }
    
    public void removeAllMatterFromTransMatrix() {
        //todo
    }
    
    public void removeMatterFromMatrix() {
        //todo
    }
}
