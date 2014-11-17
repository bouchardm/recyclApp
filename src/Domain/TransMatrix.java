package Domain;


import java.util.HashMap;
import java.util.ArrayList;

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
    
    public void addMatterToTransMatrix(Integer matterID) {
        //todo
    }
    
    public void addMatterToTransMatrix(Integer matterID, ArrayList<Float> matterQuantities) {
        //todo
    }
    
    public int getMatterCount() {
        //todo
    }
    
    public TransMatrix getTransMatrix() {
        //todo
    }
    
    public void removeAllMatterFromTransMatrix() {
        //todo
    }
    
    public void removeMatterFromMatrix() {
        //todo
    }
}