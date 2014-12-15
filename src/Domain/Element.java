package Domain;


import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class Element implements java.io.Serializable
{
    private Integer _iD;
    protected String _name;
    protected String _description;
    protected Color _color;
    protected ArrayList<String> _errorMessages;
    
    public abstract boolean include(Point2D.Float point);
    public abstract Object getAttribute(String attribName);
    public abstract void setAttribute(String attribName, Object value);
    public Element()
    {
        // todo
    }
    
    public ArrayList<String> getErrorMessages(){
        return this._errorMessages;
    }
    
    public void addErrorMessage(String errorMessage){
        this._errorMessages.add(errorMessage);
    }
    
    public void clearErrorMessages() {
        this._errorMessages.clear();
    }

    
    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }
    
    public Color getColor()
    {
        return _color;
    }
    
    public void setColor(Color color)
    {
        this._color = color;
    }
    
    public String getDescription()
    {
        return _description;
    }
    
    public void setDescription(String description)
    {
        _description = description;
    }
    
    public Object getAttrib(String attribName)
    {
        switch (attribName)
        {
            case "name":
                return this.getName();
            case "description":
                return this.getDescription();
            case "color":
                return this.getColor();
            default:
                throw new IllegalArgumentException(String.format("no method for get %s", attribName));
        }
    }
    
    protected void setAttrib(String attribName, Object value)
    {
        switch (attribName)
        {
            case "name":
                this.setName((String) value);
                break;
            case "description":
                this.setDescription((String) value);
                break;
            case "color":
                this.setColor((Color) value);
                break;
            default:
                throw new IllegalArgumentException(String.format("no method for set %s", attribName));
        }
    }
}