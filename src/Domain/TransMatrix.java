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
    
    //getter : doit retourner une copie profonde de transMatrix
    public HashMap<Integer, HashMap<Integer, Float>> getTransMatrix() {
        HashMap<Integer, HashMap<Integer, Float>> copiedMatrix = new HashMap<>();
        Iterator<Map.Entry<Integer, HashMap<Integer, Float>>> tmIter = getTransformMatrix().entrySet().iterator();
        while(tmIter.hasNext()) {
            Map.Entry<Integer, HashMap<Integer, Float>> currentMatterEntry = tmIter.next();
            int currentMatterID = currentMatterEntry.getKey();
            HashMap<Integer, Float> currentMatterTransMap = currentMatterEntry.getValue();
            Iterator<Map.Entry<Integer, Float>> innerIter = currentMatterTransMap.entrySet().iterator();
            HashMap<Integer, Float> copiedQuantities = new HashMap<>();
            while(innerIter.hasNext()) {
                Map.Entry<Integer, Float> innerEntry = innerIter.next();
                int transMatterID = innerEntry.getKey();
                float transMatterQty = innerEntry.getValue();
                copiedQuantities.put(transMatterID, transMatterQty);
            }
            copiedMatrix.put(currentMatterID, copiedQuantities);
        }
        return copiedMatrix;
    }
    
    //setter : crée une copie profonde de la matrice en entrée et la conserve dans _transformMatrix
    public void setTransMatrix(HashMap<Integer, HashMap<Integer, Float>> newTransMatrix) {
        //on veut mettre une copie dans _transMatrix. On commence par clairer transformMatrix
        this.removeAllMatterFromTransMatrix();
        Iterator<Map.Entry<Integer, HashMap<Integer, Float>>> tmIter = newTransMatrix.entrySet().iterator();
        while (tmIter.hasNext()) {
            Map.Entry<Integer, HashMap<Integer, Float>> currentEntry = tmIter.next();
            Integer matterID = currentEntry.getKey();
            HashMap<Integer, Float> quantitiesToCopy = currentEntry.getValue();
            HashMap<Integer, Float> copiedQuantities = new HashMap<>();
            Iterator<Map.Entry<Integer, Float>> innerIter = quantitiesToCopy.entrySet().iterator();
            while(innerIter.hasNext()) {
                Map.Entry<Integer, Float> innerEntry = innerIter.next();
                int innerKey = innerEntry.getKey();
                float innerValue = innerEntry.getValue();
                copiedQuantities.put(innerKey, innerValue);
            }
            getTransformMatrix().put(matterID, copiedQuantities);
        }
    }
    
    //constructeur avec paramètres
    //par défaut, crée une matrice qui ne fait aucune transformation (ex: 100% de matière A reste matière A)
    public TransMatrix(MatterList matterList) {
        _transformMatrix = new HashMap<>();
        
        for(Matter inputMatter : matterList.getList()) {
            HashMap<Integer, Float> transformQuantities = new HashMap<>();
            for(Matter outputMatter : matterList.getList()) {
                float transformIntoQty;
                if (inputMatter.getID().intValue()==outputMatter.getID()) {
                    transformIntoQty = 1;
                }
                else {
                    transformIntoQty = 0;
                }
                transformQuantities.put(outputMatter.getID(), transformIntoQty);
            }
            _transformMatrix.put(inputMatter.getID(), transformQuantities);
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
            Iterator<Map.Entry<Integer, HashMap<Integer, Float>>> tmIter = getTransformMatrix().entrySet().iterator();
            while (tmIter.hasNext()) {
                Map.Entry<Integer, HashMap<Integer, Float>> currentEntry = tmIter.next();
                Integer currentMatterID = currentEntry.getKey();
                //on ajoute 0 pour toutes les matter ID
                transformQty.put(currentMatterID, new Float(0));
                getTransformMatrix().get(currentMatterID).put(matterID, new Float(0));
            }
        }
        //on mets transformation à 100% de la matière à elle même
        transformQty.put(matterID, new Float(1));
        getTransformMatrix().put(matterID, transformQty); 
    }
    
    //ajoute la nouvelle matière et les quantités dans lequel elle se transforme dans le transformMatrix
    //ajoute une entrée pour toutes les autres matières indiquant qu'ils se transforment à 0%
    //en cette nouvelle matière
    //le HashMap en entrée (transformQuantities) doit contenir une valeur de transformation pour
    //toutes les matières du matterList incluant la matière elle même
    public void addMatterToTransMatrix(Integer matterID, HashMap<Integer, Float> transformQuantities) {
        //boucle dans le hashmap. pour chaque clé, on ajoute au hashMap interne l'entry (matterID, 0).
        Iterator<Map.Entry<Integer, HashMap<Integer, Float>>> tmIter = getTransformMatrix().entrySet().iterator();
        while(tmIter.hasNext()) {
            HashMap<Integer, Float> newTransformQuantities = new HashMap<>(); //ca sera les nouvelles quantités par matiere
            Map.Entry<Integer, HashMap<Integer, Float>> currentEntry = tmIter.next();
            Integer currentID = currentEntry.getKey();
            //on va chercher les valeurs courantes du hashmap interne pour le recopier dans un nouveau
            //hashmap avec les nouvelles valeurs
            HashMap<Integer, Float> matterTransformQuantities = currentEntry.getValue();
            Iterator<Map.Entry<Integer, Float>> innerIter = matterTransformQuantities.entrySet().iterator();
            newTransformQuantities.put(matterID, new Float(0));
            while(innerIter.hasNext()) {
                Map.Entry<Integer, Float> currentInnerEntry = innerIter.next();
                Integer innerKey = currentInnerEntry.getKey();
                Float innerQty = currentInnerEntry.getValue();
                newTransformQuantities.put(innerKey, innerQty);
            }
            getTransformMatrix().remove(currentID);
            //rajouter les nouvelles quantités pour chaque matière à _transformMatrix
            getTransformMatrix().put(currentID, newTransformQuantities);
        }
        
        //ajout au transformMatrix : (matterID, transformQuantities)
        getTransformMatrix().put(matterID, transformQuantities);
        
    }
    
    public int getMatterCount() {
        return getTransformMatrix().size();
    }
    
    //vide la transMatrix
    public void removeAllMatterFromTransMatrix() {
        getTransformMatrix().clear();
    }
    
    //ne fait rien si la matière est déjà pas incluse dans la matrice
    public void removeMatterFromMatrix(Integer matterID) {
        //on commence par enlever l'entrée principale de la matrice associée au matterID
        getTransformMatrix().remove(matterID);
        //ensuite on enlève les références à ce matter ID dans les hashMap internes
        Iterator<Map.Entry<Integer, HashMap<Integer, Float>>> tmIter = getTransformMatrix().entrySet().iterator();
        while(tmIter.hasNext()) {
            Map.Entry<Integer, HashMap<Integer, Float>> currentEntry = tmIter.next();
//            currentEntry.getValue().remove(matterID);
            int currentMatterID = currentEntry.getKey();
            float transformIntoDeletedMatterPercentage = currentEntry.getValue().get(matterID);
            float newQty = currentEntry.getValue().get(currentMatterID) + transformIntoDeletedMatterPercentage;
            currentEntry.getValue().remove(matterID);
            currentEntry.getValue().remove(currentMatterID);
            currentEntry.getValue().put(currentMatterID, newQty);
        }
    }

    /**
     * @return the _transformMatrix
     */
    public HashMap<Integer, HashMap<Integer, Float>> getTransformMatrix() {
        return _transformMatrix;
    }

    /**
     * @param _transformMatrix the _transformMatrix to set
     */
    public void setTransformMatrix(HashMap<Integer, HashMap<Integer, Float>> _transformMatrix) {
        this._transformMatrix = _transformMatrix;
    }
}
