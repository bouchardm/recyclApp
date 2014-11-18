package Domain;

public class EntryPoint extends Station {

    private Outlet _outlet;
    private MatterBasket _matterBasket;
   

    public EntryPoint() {
        super();
        _outlet = new Outlet(this);
    }

    public void setMatterBasket(MatterBasket matterBasket) {
        _matterBasket = matterBasket;
    }

    public MatterBasket getMatterBasket() {
        return _matterBasket;
    }

    public Outlet getOutlet() {
        return _outlet;
    }
    
    public void setOutlet(Outlet outlet){
        _outlet = outlet;
    }



}
