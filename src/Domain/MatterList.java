package Domain;

import java.util.ArrayList;

public class MatterList {
	//public MatterBasket _matterBasket;
	//public MatterBasket _matterBasket2;
	public ArrayList<Matter> _matterList;
	//public SortCenter _sortCenter;
	//public Matter _matter2;

        public MatterList() {
            this._matterList = new ArrayList<>();
        }
        
	public Integer GetCount() {
            return this._matterList.size();
	}

        public void Add(Matter aMatter) {
            for(int i = 0; i<_matterList.size(); i++)
            {
                if (_matterList.get(i).compareMatter(aMatter)) {
                    throw new IllegalArgumentException("La matière fait déjà partie de la liste. Les champs 'Nom' et 'ID' doivent être unique.");
                }
            }
            this._matterList.add(aMatter);
	}

        //efface la matière ayant le même ID number que celui passé en paramètre
	public void Remove(int matterID) {
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
	public Matter GetMatter(int index) {
            if(this.GetCount()==0) {
                throw new IllegalArgumentException("Impossible de trouver la matière : la liste des matières est vide.");
            }
            if(index < 0 || index >_matterList.size()) {
                throw new IndexOutOfBoundsException("Index de matière invalide.");   
            }
            return _matterList.get(index);            
	}

	//retourne l'index dans la liste d'une matière (pas le numéro d'ID)
        public Integer GetIndex(String matterName) {
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
}