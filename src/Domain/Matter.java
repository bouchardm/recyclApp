package Domain;

public class Matter {
	private String _name;
	private Integer _iD;
        
    public Matter(String matterName, int matterId) {
        this._name = matterName;
        this._iD = matterId;
    }
    
    //obtient le nom de la matière
    public String getName() {
        return this._name;
    }
    
    public void setName(String name) {
        this._name = name;
    }
    
    //obtient le numéro ID de la matière
    public Integer getID() {
        return this._iD;
    }
    
    public void setID(Integer _iD)
    {
    this._iD = _iD;
    }
    //si le numéro de ID ou le nom de la matière est identique, c'est la même matière
    public boolean compareMatter(Matter matter2) {
        if(this._name.compareToIgnoreCase(matter2._name)==0 || this._iD==matter2._iD) {
            return true;
        }
        else return false;
    }
}
