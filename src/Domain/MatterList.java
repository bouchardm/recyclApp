package Domain;

import java.util.ArrayList;
import java.util.HashMap;


public class MatterList 
{

	private ArrayList<Matter> _matterList;

        public MatterList() {
            this._matterList = new ArrayList<>();
        }
        
	public Integer getCount() {
            return this.getMatterList().size();
	}
        
        public ArrayList<Matter> getList() {
            return getMatterList();
        }
        
        public ArrayList<HashMap<Integer, String>> getMapList() {
            ArrayList<HashMap<Integer, String>> matterListMap = new ArrayList<>();
            for (Matter matter : this.getMatterList()) {
                HashMap<Integer, String> matterMap = new HashMap<Integer, String>();
                matterMap.put(matter.getID(), matter.getName());

                matterListMap.add(matterMap);
            }
            return matterListMap;
        }

        public void addMatterToList(Matter aMatter) {
            for(int i = 0; i<getMatterList().size(); i++)
            {
                if (getMatterList().get(i).compareMatter(aMatter)) {
                    throw new IllegalArgumentException("La matière fait déjà partie de la liste. Les champs 'Nom' et 'ID' doivent être unique.");
                }
            }
            this.getMatterList().add(aMatter);
	}

        //efface la matière ayant le même ID number que celui passé en paramètre
	public void remove(int matterID) {
            int i;
            for(i=0; i<getMatterList().size(); i++)
            {
                if(getMatterList().get(i).getID()==matterID)
                {
                    break;
                }
            }
            if(i<getMatterList().size()) {
                getMatterList().remove(i);
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
            if(index < 0 || index >getMatterList().size()) {
                throw new IndexOutOfBoundsException("Index de matière invalide.");   
            }
            return getMatterList().get(index);            
	}
        
        //a tester
        public String getMatterName(Integer matterID) {
            for (int i = 0; i<getMatterList().size(); i++) {
                if(getMatterList().get(i).getID().intValue()==matterID.intValue()) {
                    return getMatterList().get(i).getName();
                }
            }
            //si on se rend ici, c'est que la matière ne fait pas partie de la liste
            throw new IllegalArgumentException("La matière ne fait pas partie de la liste.");
        }
        
        public void setMatterName(Integer matterID, String matterName) {
            for (int i = 0; i<getMatterList().size(); i++) {
                if(getMatterList().get(i).getID().intValue()==matterID.intValue()) {
                    getMatterList().get(i).setName(matterName);
                }
            }
        }

	//retourne l'index dans la liste d'une matière (pas le numéro d'ID)
        public int getIndex(String matterName) {
            for(int i = 0; i<getMatterList().size(); i++)
            {
                if(getMatterList().get(i).getName().compareToIgnoreCase(matterName)==0)
                {
                    return i;
                }
            }
            //si on se rend ici, c'est que la matière ne fait pas partie de la liste
            throw new IllegalArgumentException("La matière ne fait pas partie de la liste.");
	}
        
        public Integer getMatterID(String matterName) {
            for (int i = 0; i<getMatterList().size(); i++) {
                if(getMatterList().get(i).getName().compareToIgnoreCase(matterName)==0) {
                    return getMatterList().get(i).getID();
                }
            }
            //si on se rend ici, c'est que la matière ne fait pas partie de la liste
            throw new IllegalArgumentException("La matière ne fait pas partie de la liste.");
        }
        
        public Integer getMatterID(int listIndex) {
            if (listIndex >= getMatterList().size()) {
                throw new IndexOutOfBoundsException("Index de matière invalide."); 
            }
            return getMatterList().get(listIndex).getID();
        }

    /**
     * @return the _matterList
     */
    public ArrayList<Matter> getMatterList() {
        return _matterList;
    }

    /**
     * @param _matterList the _matterList to set
     */
    public void setMatterList(ArrayList<Matter> _matterList) {
        this._matterList = _matterList;
    }
}