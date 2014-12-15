package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MatterList implements java.io.Serializable
{

	private ArrayList<Matter> _matterList;

        public MatterList() {
            this._matterList = new ArrayList<>();
        }
        
	public Integer getCount() {
            return this._matterList.size();
	}
        
        public ArrayList<Matter> getList() {
            return _matterList;
        }
        
        public ArrayList<HashMap<Integer, String>> getMapList() {
            ArrayList<HashMap<Integer, String>> matterListMap = new ArrayList<>();
            for (Matter matter : this._matterList) {
                HashMap<Integer, String> matterMap = new HashMap<Integer, String>();
                matterMap.put(matter.getID(), matter.getName());

                matterListMap.add(matterMap);
            }
            return matterListMap;
        }

        public void addMatterToList(Matter aMatter) {
            for(int i = 0; i<_matterList.size(); i++)
            {
                if (_matterList.get(i).compareMatter(aMatter)) {
                    throw new IllegalArgumentException("La matière fait déjà partie de la liste. Les champs 'Nom' et 'ID' doivent être unique.");
                }
            }
            this._matterList.add(aMatter);
	}

        //efface la matière ayant le même ID number que celui passé en paramètre
	public void remove(int matterID) {
            int i;
            for(i=0; i<_matterList.size(); i++)
            {
                if(_matterList.get(i).getID()==matterID)
                {
                    break;
                }
            }
            if(i<_matterList.size()) {
                _matterList.remove(i);
            }
            else {
            //si on se rend ici, la matière ne fait pas partie de la liste
            throw new IllegalArgumentException("La matière ne fait pas partie de la liste.");
            } 
	}
        
        //retourne une matière selon son index dans la liste (pas nécessairement son numéro d'ID)
        //exception : IllegalArgumentException si l'index est invalide
	public Matter getMatter(int index) {
            if(this.getCount()==0) {
                throw new IllegalArgumentException("Impossible de trouver la matière : la liste des matières est vide.");
            }
            if(index < 0 || index >_matterList.size()) {
                throw new IndexOutOfBoundsException("Index de matière invalide.");   
            }
            return _matterList.get(index);            
	}
        
        //a tester
        public String getMatterName(Integer matterID) {
            for (int i = 0; i<_matterList.size(); i++) {
                if(_matterList.get(i).getID().intValue()==matterID.intValue()) {
                    return _matterList.get(i).getName();
                }
            }
            //si on se rend ici, c'est que la matière ne fait pas partie de la liste
            throw new IllegalArgumentException("La matière ne fait pas partie de la liste.");
        }
        
        public void setMatterName(Integer matterID, String matterName) {
            for (int i = 0; i<_matterList.size(); i++) {
                if(_matterList.get(i).getID().intValue()==matterID.intValue()) {
                    _matterList.get(i).setName(matterName);
                }
            }
        }

	//retourne l'index dans la liste d'une matière (pas le numéro d'ID)
        public int getIndex(String matterName) {
            for(int i = 0; i<_matterList.size(); i++)
            {
                if(_matterList.get(i).getName().compareToIgnoreCase(matterName)==0)
                {
                    return i;
                }
            }
            //si on se rend ici, c'est que la matière ne fait pas partie de la liste
            throw new IllegalArgumentException("La matière ne fait pas partie de la liste.");
	}
        
        public Integer getMatterID(String matterName) {
            for (int i = 0; i<_matterList.size(); i++) {
                if(_matterList.get(i).getName().compareToIgnoreCase(matterName)==0) {
                    return _matterList.get(i).getID();
                }
            }
            //si on se rend ici, c'est que la matière ne fait pas partie de la liste
            throw new IllegalArgumentException("La matière ne fait pas partie de la liste.");
        }
        
        public Integer getMatterID(int listIndex) {
            if (listIndex >= _matterList.size()) {
                throw new IndexOutOfBoundsException("Index de matière invalide."); 
            }
            return _matterList.get(listIndex).getID();
        }
}