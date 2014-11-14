package Domain;

public class Matter {
	private String _name;
	private Integer _iD;
        
    public Matter(String matterName, int matterId) {
        this._name = matterName;
        this._iD = matterId;
    }
    
    public String getName() {
        return this._name;
    }
    
    public Integer getID() {
        return this._iD;
    }
    
    public boolean compareMatter(Matter matter2) {
        if(this._name.compareToIgnoreCase(matter2._name)==0 || this._iD==matter2._iD) {
            return true;
        }
        else return false;
    }
}
