package Domain;

import java.awt.Color;
import java.util.ArrayList;

public class ExitPoint extends RectangularNode {

    private Inlet _inlet;
    private MatterBasket _matterBasket;
    private float _speedMax;
    // private position 

    public ExitPoint() {
        _inlet = new Inlet(this);
        _matterBasket = new MatterBasket();
        _errorMessages = new ArrayList<>();
    }

    public Inlet getInlet() {
        return _inlet;
    }

    public void setInlet(Inlet inlet) {
        _inlet = inlet;
    }

    public float getKgHMax() {
        return _speedMax;
    }

    public void setKgHMax(float speedMax) {
        _speedMax = speedMax;
    }

    public MatterBasket getMatterBasket() {
        return this._matterBasket;
    }

    public void setMatterBasket(MatterBasket matterBasket) {
        this._matterBasket.setQuantities(matterBasket.getQuantities());
    }

    @Override
    public void setMatterBasketAtOutlets(MatterBasket matterBasket) {
        this.setMatterBasket(matterBasket);
    }

    @Override
    public void setAttribute(String attribName, Object value) {
        try {
            super.setAttribute(attribName, value);
        } catch (IllegalArgumentException e) {
            switch (attribName) {
                case "img":
                    this.setImg((String) value);
                    break;
                case "name":
                    this.setName((String) value);
                    break;
                case "description":
                    this.setDescription((String) value);
                    break;
                case "speedMax":
                    this.setKgHMax((Float) value);
                    break;
                case "color":
                    this.setColor((Color) value);
                    break;
                default:
                    throw new IllegalArgumentException(String.format("no method for set %s", attribName));
            }
        }
    }

    @Override
    public Object getAttribute(String attribName) {
        try {
            return super.getAttribute(attribName);
        } catch (IllegalArgumentException e) {
            switch (attribName) {
                case "matterBasket":
                    return this.getMatterBasket();
                    

                case "speedMax":
                    return this.getKgHMax();
                default:
                    throw new IllegalArgumentException(String.format("no method for get %s", attribName));
            }
        }
    }

    @Override
    public ArrayList<IOlet> getIOlets() {
        ArrayList<IOlet> iolets = new ArrayList<>();
        iolets.add(_inlet);
        return iolets;
    }

    @Override
    public void processMatterBasket(MatterBasket matterBasket) {
        this.setMatterBasket(matterBasket);
    }
}
