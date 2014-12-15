package Application.Controller;

import Domain.SortStation;
import Domain.EntryPoint;
import Domain.ExitPoint;
import Domain.Conveyor;
import Domain.Element;
import Domain.IOlet;
import Domain.Junction;
import Domain.MatterBasket;
import Domain.Node;
import Domain.Project;
import Domain.Inlet;
import Domain.Outlet;
import Domain.Station;
import Domain.TransStation;
import Presentation.Swing.AboutUs;
import Presentation.Swing.matterFrame;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

public class Controller {

    private Project _project;
    private static int matterIDCounter = 0;
    private Outlet _outlet;
    private Inlet _inlet;
    private Element _selectedElement;

    public Controller() {
        _project = new Project();
    }

    public boolean selectedElementIsFloor() {
        if (_selectedElement != null) {
            return _selectedElement.equals(_project.getSortCenter());
        }
        return false;
    }

    public void selectElement(Point2D.Float coords) {
        _selectedElement = _project.getSortCenter();

        List<Element> elements = new ArrayList<>();

        elements.add(_project.getSortCenter());
        elements.addAll(_project.getSortCenter().getConveyors());
        elements.addAll(_project.getSortCenter().getStations());
        elements.addAll(_project.getSortCenter().getJunctions());
        elements.addAll(_project.getSortCenter().getEntryPoints());
        elements.addAll(_project.getSortCenter().getExitPoints());

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

    public Object getOutletAttribute(String attribName) {
        if (_outlet != null) {
            return _outlet.getAttribute(attribName);
        }
        return null;
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

    public ArrayList<String> getSelectedElementErrors() {
        return _selectedElement.getErrorMessages();
    }

    public Map<String, Object> getSelectedElementAttributes() {
        if (this.typeOfElementSelectedIs(SortStation.class)) {
            return this.getStationSelected();
        } else if (this.typeOfElementSelectedIs(TransStation.class)) {
            return this.getTransStationSelected();
        } else if (this.typeOfElementSelectedIs(Junction.class)) {
            return this.getJunctionSelected();
        } else if (this.typeOfElementSelectedIs(Conveyor.class)) {
            return this.getConveyorSelected();
        } else if (this.typeOfElementSelectedIs(EntryPoint.class)) {
            return this.getEntryPointSelected();
        } else if (this.typeOfElementSelectedIs(ExitPoint.class)) {
            return this.getExitPointSelected();
        }
        return null;
    }

    public void setSelectedElementAttribute(String attribName, Object value) {
        if (_selectedElement != null) {
            _selectedElement.setAttribute(attribName, value);
        }
    }

    public Point2D.Float getSortCenterDimensions() {
        return _project.getSortCenter().getDimensions();
    }

    public void SaveProject(String path) {

        XMLEncoder encoder = null;

        try {

            //Get all entrypoint
            ArrayList<EntryPoint> entryPointList = getProject().getSortCenter().getEntryPoints();
            ArrayList<ExitPoint> exitPointList = getProject().getSortCenter().getExitPoints();
            ArrayList<Conveyor> conveyorList = getProject().getSortCenter().getConveyors();
            ArrayList<Junction> junctionList = getProject().getSortCenter().getJunctions();

            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));

            for (EntryPoint entryPoint : entryPointList) {
                encoder.writeObject(entryPoint);
            }
//            for (ExitPoint exitPoint : exitPointList) {
//                encoder.writeObject(exitPoint);
//            }
            for (Conveyor conveyor : conveyorList) {
                encoder.writeObject(conveyor);
            }
            for (Junction junction : junctionList) {
                encoder.writeObject(junction);
            }
//            encoder.writeObject(exitPointList);
//            encoder.writeObject(conveyorList);
//            encoder.writeObject(junctionList);

            encoder.flush();

        } catch (final java.io.IOException e) {
        } finally {

            if (encoder != null) {

                encoder.close();
            }
        }
    }

    public void LoadProject(String path) {

        XMLDecoder decoder = null;
        try {
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));

            ArrayList<EntryPoint> entryPointList = new ArrayList<>();
           int i = 0;
            while (i < 2) 
            {
                entryPointList.add((EntryPoint) decoder.readObject());
                i++;
            }
            this.getProject().getSortCenter().setEntrypoints(entryPointList);

        } 
        catch (final Exception e) 
        {

        }
        finally 
        {

            if (decoder != null) 
            {
decoder.close();
            }
        }
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

    public void EditConveyor(Float speedMax, Color color) {
        if (speedMax != null) {
            this.setSelectedElementAttribute("speedMax", speedMax);
        }

        if (color != null) {
            this.setSelectedElementAttribute("color", color);
        }

        this._project.getSortCenter().updateDesign();
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
        Float dimensionX = (Float) this._selectedElement.getAttribute("dimensionX");
        Float dimensionY = (Float) this._selectedElement.getAttribute("dimensionY");
        HashMap<Integer, Float> matterQuantities = (HashMap<Integer, Float>) this._selectedElement.getAttribute("matterQuantities");

        infoElement.put("name", name);
        infoElement.put("description", description);
        infoElement.put("name", name);
        infoElement.put("description", description);
        infoElement.put("color", color);
        infoElement.put("speedMax", speedMax);
        infoElement.put("img", img);
        infoElement.put("outletList", outletList);
        infoElement.put("sortMatrix", sortMatrix);
        infoElement.put("dimensionX", dimensionX);
        infoElement.put("dimensionY", dimensionY);
        infoElement.put("matterQuantities", matterQuantities);
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
        Float dimensionX = (Float) this._selectedElement.getAttribute("dimensionX");
        Float dimensionY = (Float) this._selectedElement.getAttribute("dimensionY");
        HashMap<Integer, Float> matterQuantities = (HashMap<Integer, Float>) this._selectedElement.getAttribute("matterQuantities");

        infoElement.put("name", name);
        infoElement.put("description", description);
        infoElement.put("color", color);
        infoElement.put("speedMax", speedMax);
        infoElement.put("img", img);
        infoElement.put("outletList", outletList);
        infoElement.put("sortMatrix", sortMatrix);
        infoElement.put("transMatrix", transMatrix);
        infoElement.put("dimensionX", dimensionX);
        infoElement.put("dimensionY", dimensionY);
        infoElement.put("matterQuantities", matterQuantities);
        return infoElement;
    }

    private Map<String, Object> getJunctionSelected() {
        Map<String, Object> infoElement = new HashMap();

        Float speedMax = (Float) this._selectedElement.getAttribute("speedMax");
        HashMap<Integer, Float> matterQuantities = (HashMap<Integer, Float>) this._selectedElement.getAttribute("matterQuantities");

        infoElement.put("speedMax", speedMax);
        infoElement.put("matterQuantities", matterQuantities);

        return infoElement;
    }

    private Map<String, Object> getConveyorSelected() {
        Map<String, Object> infoElement = new HashMap();

        Float speedMax = (Float) this._selectedElement.getAttribute("speedMax");
        HashMap<Integer, Float> matterQuantities = (HashMap<Integer, Float>) this._selectedElement.getAttribute("matterQuantities");
        Color color = (Color) this._selectedElement.getAttribute("color");

        infoElement.put("speedMax", speedMax);
        infoElement.put("matterQuantities", matterQuantities);
        infoElement.put("color", color);

        return infoElement;
    }

    private Map<String, Object> getEntryPointSelected() {
        Map<String, Object> infoElement = new HashMap();

        MatterBasket matterBasket = (MatterBasket) this._selectedElement.getAttribute("matterBasket");

        infoElement.put("matterBasket", matterBasket);

        return infoElement;
    }

    private Map<String, Object> getExitPointSelected() {
        Map<String, Object> infoElement = new HashMap();

        MatterBasket matterBasket = (MatterBasket) this._selectedElement.getAttribute("matterBasket");

        infoElement.put("matterBasket", matterBasket);

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
        ((TransStation) _selectedElement).setPosition(position);
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

    public void moveStation(Point2D.Float position) {
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

    public void EditStation(String name, String description, Color color, String imgSrc, Float speedMax, HashMap<Integer, ArrayList<Float>> sorter, Float dimensionX, Float dimensionY) {

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
            ((Station) _selectedElement).getSortMatrix().setSortMatrix(sorter);
            this._project.getSortCenter().updateDesign();
        }

        if (dimensionX != null) {
            this.setSelectedElementAttribute("dimensionX", dimensionX);
        }

        if (dimensionY != null) {
            this.setSelectedElementAttribute("dimensionY", dimensionY);
        }
        this._project.getSortCenter().updateDesign();
    }

    public void EditStation(String name, String description, Color color, String imgSrc, Float speedMax, HashMap<Integer, ArrayList<Float>> sorter, Float dimensionX, Float dimensionY, HashMap<Integer, HashMap<Integer, Float>> transMatrix) {
        if (transMatrix != null) {
            this.setSelectedElementAttribute("transMatrix", transMatrix);
        }
        this.EditStation(name, description, color, imgSrc, speedMax, sorter, dimensionX, dimensionY);
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

    public void AddEntryPoint(Point2D.Float position) {
        if (!this.getProject().getSortCenter().include(position)) {
            JOptionPane.showMessageDialog(null, "Veuillez indiquez un endroit sur le plan", null, 0);
            return;
        }

        _selectedElement = this._project.getSortCenter().addEntryPoint();
        _selectedElement.setAttribute("position", position);
    }

    public void editEntryPoint(ArrayList<HashMap<Integer, Float>> newMatterBasket) {

        MatterBasket matterBasket = new MatterBasket();

        for (HashMap<Integer, Float> matter : newMatterBasket) {
            matterBasket.addMatterQuantity(matter.entrySet().iterator().next().getKey(), matter.entrySet().iterator().next().getValue());
        }

        ((EntryPoint) this._selectedElement).setMatterBasket(matterBasket);
    }

    public void AddExitPoint(Point2D.Float position) {

        if (!this.getProject().getSortCenter().include(position)) {
            JOptionPane.showMessageDialog(null, "Veuillez indiquez un endroit sur le plan", null, 0);
            return;
        }

        _selectedElement = getProject().getSortCenter().addExitPoint();

        _selectedElement.setAttribute("position", position);

    }

    public void addJunction(Point2D.Float position) {
        if (!this.getProject().getSortCenter().include(position)) {
            JOptionPane.showMessageDialog(null, "Veuillez indiquez un endroit sur le plan", null, 0);
            return;
        }

        _selectedElement = this.getProject().getSortCenter().addJunction();
        _selectedElement.setAttribute("position", position);
    }

    public void deleteEntryPoint() // Pourquoi un delete EntryPoint ... Element plutôt
    {
        this.getProject().getSortCenter().deleteEntryPoint((EntryPoint) _selectedElement);
    }

    public void deleteExitPoint() {
        this.getProject().getSortCenter().deleteExitPoint((ExitPoint) _selectedElement);
    }

    public void EditEntryPoint() {
        throw new UnsupportedOperationException();
    }

    public void RemoveJunction() {
        throw new UnsupportedOperationException();
    }

    public void editJunction(Float speedMax) {
        if (speedMax != null) {
            this.setSelectedElementAttribute("speedMax", speedMax);
        }
    }

    public void deleteJunction() {
        this.getProject().getSortCenter().deleteJunction((Junction) _selectedElement);
    }

    public void deleteConveyor() {
        this.getProject().getSortCenter().deleteConveyor((Conveyor) _selectedElement);
    }

    public void showMatterFrame() {
        matterFrame matterFrame = new matterFrame(this);
        matterFrame.setVisible(true);
    }

    public void addMatter() {
        String matterName = JOptionPane.showInputDialog(null, "Quel est le nom de la matière?", "Matières", 0);
        if (matterName != null) {
            if (!matterName.isEmpty()) {
                try {
                    this._project.getSortCenter().addMatterToMatterList(matterName);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Veuillez indiquez un nom de matière qui n'existe pas déjà.", null, 0);
                }
            }
        }
    }

    public ArrayList<HashMap<Integer, String>> getMatterList() {
        ArrayList<HashMap<Integer, String>> matterList = this.getProject().getSortCenter().getMatterList().getMapList();
        return matterList;
    }

    public void removeMatter(int matterId) {
        this._project.getSortCenter().removeMatterFromMatterList(matterId);
    }

    public void editMatter(HashMap<Integer, String> matter) {
        int key = matter.entrySet().iterator().next().getKey();
        String name = matter.entrySet().iterator().next().getValue();

        this._project.getSortCenter().getMatterList().setMatterName(key, name);
    }

    public HashMap<Integer, Float> getPurityRateForMatterBasketAtSlectedElement() {

        return this._project.getSortCenter().getPurityRateForMatterBasketAtElement(this._selectedElement);
    }

    public HashMap<Integer, Float> getRecoveryRateForMatterBasketAtSelectedElement() {
        return this._project.getSortCenter().getRecoveryRateForMatterBasketAtElement(this._selectedElement);
    }

    public void addConveyor() {

        try {
            this.getProject().getSortCenter().addConveyor(_outlet, _inlet);
//            this._project.getSortCenter().updateDesign();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), null, 0);
        }
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
