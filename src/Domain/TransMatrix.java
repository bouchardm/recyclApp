package Domain;


import java.util.HashMap;
import java.util.ArrayList;

public class TransMatrix {
    //l'attribut transformMatrix : HashMap<MatterID, <Array de qté par matière, dans le même ordre que le matterList utilisé pour le construire>>
    
    private HashMap<Integer, ArrayList<Float>> _transformMatrix;
        
    public TransMatrix() {
        _transformMatrix = new HashMap<>();
        
    }
}