package Application.Controller;

import Domain.MatterList;
import Domain.SortMatrix;
import Domain.SortStation;
import Domain.EntryPoint;
import Domain.ExitPoint;
import Domain.Conveyor;
import Domain.Element;
import Domain.IOlet;
import Domain.Junction;
import Domain.Matter;
import Domain.MatterBasket;
import Domain.Node;
import Domain.Outlet;
import Domain.Project;
import Domain.SortCenter;
import Domain.Inlet;
import Domain.Outlet;
import Domain.Station;
import Domain.TransMatrix;
import Domain.TransStation;
import Presentation.Swing.AboutUs;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class Controller {
    private SortMatrix _sortMatrix;
    private Project _project;
    private SortCenter _sortCenter;
    private Conveyor _conveyor;
    private SortStation _sortStation;
    private static int matterIDCounter = 0;
    private Outlet _outlet;
    private Inlet _inlet;
    private Element _selectedElement;
        
    public Controller()
    {
        _project = new Project();
    }
        
        public boolean selectedElementIsFloor()
        {
            if (_selectedElement != null) {
                return _selectedElement.equals(_project.getSortCenter());
            }
            return false;
        }
        
        public void selectElement(Point2D.Float coords)
        {
            _selectedElement = _project.getSortCenter();

        List<Element> elements = new ArrayList<Element>();

        elements.add(_project.getSortCenter());
 elements.addAll(_project.getSortCenter().getConveyors());
        elements.addAll(_project.getSortCenter().getJunctions());
        elements.addAll(_project.getSortCenter().getStations());
//            elements.addAll(_project.getSortCenter().getTransStation());
        elements.addAll(_project.getSortCenter().getExitPoints());
        elements.addAll(_project.getSortCenter().getEntryPoints());
       

        ArrayList<IOlet> iolets;

        for (int i = elements.size() - 1; i > -1; i--) {
            if (elements.get(i) instanceof Node) {
                iolets = ((Node) elements.get(i)).getIOlets();

                for (IOlet iol : iolets) {
                    if (iol.include(coords)) {
                        _selectedElement = iol;
                        return;
                    }
                }
            }
            if (elements.get(i).include(coords)) {
                _selectedElement = elements.get(i);
                return;
            }
        }
    }

    public boolean isFloorSelected() {
        return _project.getSortCenter().equals(_selectedElement);
    }

    public void showAboutUs() {
        AboutUs view = new AboutUs();
        view.setVisible(true);
    }

    public boolean selectedElementIs(Element element) {
        return _selectedElement != null && _selectedElement.equals(element);
    }

    public boolean typeOfElementSelectedIs(Class ElementClass) {
        return _selectedElement != null && _selectedElement.getClass() == ElementClass;
    }

    public Object getSelectedElementAttribute(String attribName) {
        return _selectedElement.getAttribute(attribName);
            
    }

    public Map<String, Object> getSelectedElementAttributes() {
        if (this.typeOfElementSelectedIs(SortStation.class)) {
            return this.getStationSelected();
        } else if (this.typeOfElementSelectedIs(TransStation.class)) {
            return this.getTransStationSelected();
        }
        return null;
    }
    
    public void setSelectedElementAttribute(String attribName, Object value)
    {
        if (_selectedElement != null) {
            _selectedElement.setAttribute(attribName, value);
        }
    }

    // ************ SortCenter ***************
    public Map<String, Object> getElementAttributes() {
        return null;
    }

    public Point2D.Float getSortCenterDimensions() {
        return _project.getSortCenter().getDimensions();
    }

    public void SaveProject() {
        throw new UnsupportedOperationException();
    }

    public void LoadProject() {
        throw new UnsupportedOperationException();
    }

    public void CreateNewProject() {
        throw new UnsupportedOperationException();
    }
        
        public String getMatterName(int id) {
            return this._project.getSortCenter().getMatterList().getMatterName(id);
        }
        
        public int getMatterId(String matterName) {
            return this._project.getSortCenter().getMatterList().getMatterID(matterName);
        }

    public void ExportImage() {
        throw new UnsupportedOperationException();
    }

    public void CreateSortCenter() {
        throw new UnsupportedOperationException();
    }

    public void ValidateSortCenter() {
        throw new UnsupportedOperationException();
    }

    public void SetSizeSortCenter() {
        throw new UnsupportedOperationException();
    }

    public void AddConvoyer() {

    }

    public void RemoveConveyor() {
        throw new UnsupportedOperationException();
    }

    public void EditConveyor() {
        throw new UnsupportedOperationException();
    }

    public void AddMatrix() {
        throw new UnsupportedOperationException();
    }

    public void RemoveMatrix() {
        throw new UnsupportedOperationException();
    }

    private Map<String, Object> getStationSelected() {
        Map<String, Object> infoElement = new HashMap();

        String name = (String) this._selectedElement.getAttribute("name");
        String description = (String) this._selectedElement.getAttribute("description");
        Color color = (Color) this._selectedElement.getAttribute("color");
        Float speedMax = (Float) this._selectedElement.getAttribute("speedMax");
        Image img = (Image) this._selectedElement.getAttribute("img");
        ArrayList outletList = (ArrayList<Outlet>) this._selectedElement.getAttribute("outletList");
        HashMap<Integer, ArrayList<Float>> sortMatrix = (HashMap<Integer, ArrayList<Float>>) this._selectedElement.getAttribute("sortMatrix");
            
        infoElement.put("name", name);
        infoElement.put("description", description);
        infoElement.put("name", name);
        infoElement.put("description", description);
        infoElement.put("color", color);
        infoElement.put("speedMax", speedMax);
        infoElement.put("img", img);
        infoElement.put("outletList", outletList);
        infoElement.put("sortMatrix", sortMatrix);
        return infoElement;
    }
        
    private Map<String, Object> getTransStationSelected() {
        Map<String, Object> infoElement = new HashMap();
        
        String name = (String) this._selectedElement.getAttribute("name");
        String description = (String) this._selectedElement.getAttribute("description");
        Color color = (Color) this._selectedElement.getAttribute("color");
        Float speedMax = (Float) this._selectedElement.getAttribute("speedMax");
        Image img = (Image) this._selectedElement.getAttribute("img");
        ArrayList outletList = (ArrayList<Outlet>) this._selectedElement.getAttribute("outletList");
        HashMap<Integer, ArrayList<Float>> sortMatrix = (HashMap<Integer, ArrayList<Float>>) this._selectedElement.getAttribute("sortMatrix");
        HashMap<Integer, HashMap<Integer, Float>> transMatrix = (HashMap<Integer, HashMap<Integer, Float>>) this._selectedElement.getAttribute("transMatrix");
        
        infoElement.put("name", name);
        infoElement.put("description", description);
        infoElement.put("color", color);
        infoElement.put("speedMax", speedMax);
        infoElement.put("img", img);
        infoElement.put("outletList", outletList);
        infoElement.put("sortMatrix", sortMatrix);
//        infoElement.put("matterQuantity", stationMatterQuantity.toString());
        infoElement.put("transMatrix", transMatrix);
        return infoElement;
    }

    public void EditMatrix() {
        throw new UnsupportedOperationException();
    }

    public void AddTransStation(Point2D.Float position) {
        if (!this.getProject().getSortCenter().include(position)) {
            JOptionPane.showMessageDialog(null, "Veuillez indiquez un endroit sur le plan", null, 0);
            return;
        }

        int value;
        try {
            value = Integer.parseInt(JOptionPane.showInputDialog(null, "Quel est le nombre de sortie?", null, 0));
        } catch (NumberFormatException e) {
            value = -1;
        }

        if (value < 0) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir un entier positif.", null, 0);
            return;
        }

        if (value > 1000) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre de sortie réaliste.", null, 0);
            return;
        }

        _selectedElement = this._project.getSortCenter().addTransStation(value);
        ((TransStation)_selectedElement).setPosition(position);
    }
        
        
    public void AddStation(Point2D.Float position) {
        if (!this.getProject().getSortCenter().include(position)) {
            JOptionPane.showMessageDialog(null, "Veuillez indiquez un endroit sur le plan", null, 0);
            return;
        }

        int value;
        try {
            value = Integer.parseInt(JOptionPane.showInputDialog(null, "Quel est le nombre de sortie?", null, 0));
        } catch (NumberFormatException e) {
            value = -1;
        }
        
        if (value < 0) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir un entier positif.", null, 0);
            return;
        }

        if (value > 1000) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre de sortie réaliste.", null, 0);
            return;
        }

        _selectedElement = this._project.getSortCenter().addSortStation(value);
        ((SortStation) _selectedElement).setPosition(position);
    }

    public void MoveStation(Point2D.Float position) {
        if (position.x < 0) {
            position.x = 0;
        } else if (position.x + ((Point2D.Float) getSelectedElementAttribute("dimensions")).x > getSortCenterDimensions().x) {
            position.x = getSortCenterDimensions().x - ((Point2D.Float) getSelectedElementAttribute("dimensions")).x;
        }
        if (position.y < 0) {
            position.y = 0;
        } else if (position.y + ((Point2D.Float) getSelectedElementAttribute("dimensions")).y > getSortCenterDimensions().y) {
            position.y = getSortCenterDimensions().y - ((Point2D.Float) getSelectedElementAttribute("dimensions")).y;
        }
        if (!this.getProject().getSortCenter().include(position)) {
            JOptionPane.showMessageDialog(null, "Veuillez indiquez un endroit sur le plan", null, 0);
            return;
        }
        setSelectedElementAttribute("position", position);
    }
    
    public void DeleteStation() {
        this.getProject().getSortCenter().deleteStation((Station) this._selectedElement);
    }

    public void EditStation(String name, String description, Color color, String imgSrc, Float speedMax, HashMap<Integer, ArrayList<Float>> sorter) {

        if (name != null) {
            this.setSelectedElementAttribute("name", name);
        }

        if (description != null) {
            this.setSelectedElementAttribute("description", description);
        }

        if (color != null) {
            this.setSelectedElementAttribute("color", color);
        }

        if (imgSrc != null) {
            this.setSelectedElementAttribute("img", imgSrc);
        }

        if (speedMax != null) {
            this.setSelectedElementAttribute("speedMax", speedMax);
        }

        if (sorter != null) {
            ((Station)_selectedElement).getSortMatrix().setSortMatrix(sorter);
        }
    }

    public void EditStation(String name, String description, Color color, String imgSrc, Float speedMax, HashMap<Integer, ArrayList<Float>> sorter, HashMap<Integer, HashMap<Integer, Float>> transMatrix) {
        if (transMatrix != null) {
            this.setSelectedElementAttribute("transMatrix", transMatrix);
        }
        this.EditStation(name, description, color, imgSrc, speedMax, sorter);
    }

    public void AddExitPoint() {
        throw new UnsupportedOperationException();
    }

    public void RemoveExitPoint() {
        throw new UnsupportedOperationException();
    }

    public void EditExitPoint() {
        throw new UnsupportedOperationException();
    }

    public void AddEntryPoint() {
        throw new UnsupportedOperationException();
    }

    public void RemoveEntryPoint() {
        throw new UnsupportedOperationException();
    }

    public void EditEntryPoint() {
        throw new UnsupportedOperationException();
    }

    public void AddJunction(Point2D.Float position) {
        this.getProject().getSortCenter().addJunction();
    }

    public void RemoveJunction() {
        throw new UnsupportedOperationException();
    }

    public void EditJunction() {
        throw new UnsupportedOperationException();
    }

    public void AddMatter() {
        throw new UnsupportedOperationException();
    }

    public void RemoveMatter() {
        throw new UnsupportedOperationException();
    }

    public void EditMatter() {
        throw new UnsupportedOperationException();
    }

    public void addStation(Point aPosition) {
        throw new UnsupportedOperationException();
    }

    public void addConveyor() {

//        Inlet inlet = this.getInlet();
//        Outlet outlet = this.getOutlet();
        try {
            this.getProject().getSortCenter().addConveyor(_outlet, _inlet);
            JOptionPane.showMessageDialog(null, "Succès!", null, 0);
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
        }
    }    

    public void addJunction(Point aPosition) {
        throw new UnsupportedOperationException();
    }

    public Project getProject() {
        return _project;
    }

    public void setOutlet() {

        _outlet = (Outlet) _selectedElement;

    }

    public void setInlet() {
        _inlet = (Inlet) _selectedElement;

    }

    public Outlet getOutlet() {
        return _outlet;

    }

    public Inlet getInlet() {
        return _inlet;

    }

}
