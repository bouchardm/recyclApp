package Domain;

public class Matter {
	private String _name;
	private Integer _iD;
        
        public Matter()
        {}
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
    

    //si le numéro de ID ou le nom de la matière est identique, c'est la même matière
    public boolean compareMatter(Matter matter2) {
        if(this.getName().compareToIgnoreCase(matter2.getName())==0 || this.getID()==matter2.getID()) {
            return true;
        }
        else return false;
    }

    /**
     * @return the _iD
     */
    public Integer getID() {
        return _iD;
    }

    /**
     * @param _iD the _iD to set
     */
    public void setID(Integer _iD) {
        this._iD = _iD;
    }
}
