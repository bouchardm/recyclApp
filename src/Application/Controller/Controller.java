package Application.Controller;


import Domain.MatterList;
import Domain.SortMatrix;
import Domain.SortStation;
import Domain.EntryPoint;
import Domain.ExitPoint;
import Domain.Conveyor;
import Domain.Element;
import Domain.Node;
import Domain.Project;
import Domain.SortCenter;
import Presentation.Swing.AboutUs;
import Presentation.Swing.MainFrame;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class Controller {
	private SortMatrix _sortMatrix;
	private Project _project;
	private SortCenter _sortCenter;
	private Conveyor _conveyor;
	private ExitPoint _exitPoint;
	private EntryPoint _entryPoint;
	private Object _matterBasket;
	private Object _transformationMatrix;
	private MatterList _matterList;
        private static int matterIDCounter = 0;
        
        private Element _selectedElement;
        
        public Controller()
        {
            _project = new Project();
            _selectedElement = null;
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
            _selectedElement = null;
            
            List<Element> elements = new ArrayList<Element>();
            
            elements.add(_project.getSortCenter());
            
            elements.addAll(_project.getSortCenter().getJunctions());
            elements.addAll(_project.getSortCenter().getStations());
//            elements.addAll(_project.getSortCenter().getTransStation());
            elements.addAll(_project.getSortCenter().getExitPoints());
            elements.addAll(_project.getSortCenter().getEntryPoints());
            elements.addAll(_project.getSortCenter().getConveyors());
            
            
            for (int i=elements.size()-1; i>-1; i--)
            {
                if (elements.get(i).include(coords))
                {
                    _selectedElement = elements.get(i);
                    return;
                }
            }
        }
        
        public boolean isFloorSelected()
        {
            return _project.getSortCenter().equals(_selectedElement);
        }
                

        public void showAboutUs() {
            AboutUs view = new AboutUs();
            view.setVisible(true);
        }
        
        public boolean selectedElementIs(Element element)
        {
            return _selectedElement != null && _selectedElement.equals(element);
        }
        
        public boolean typeOfElementSelectedIs(Class ElementClass) {
            return _selectedElement != null && _selectedElement.getClass() == ElementClass;
        }
        
        public Map<String, Object> getSelectedElementAttributes() {
            if (this.typeOfElementSelectedIs(SortStation.class)) {
                return this.getStationSelected();
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
        
        public Map<String, Object> getElementAttributes()
        {
            return null;
        }

        public Point2D.Float getSortCenterDimensions()
        {
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
		throw new UnsupportedOperationException();
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

            infoElement.put("name", name);
            infoElement.put("description", description);
            infoElement.put("colod", color);
            infoElement.put("speedMax", speedMax);
            infoElement.put("img", img);

            return infoElement;
        }

	public void EditMatrix() {
		throw new UnsupportedOperationException();
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
            
            _selectedElement = this._project.getSortCenter().addSortStation();
//            ((SortStation)_selectedElement).setExit(value);
            ((SortStation)_selectedElement).setPosition(position);
	}
        
        public void MouveStation(SortStation sortStation, Point2D.Float position) {
            if (!this.getProject().getSortCenter().include(position)) {
                JOptionPane.showMessageDialog(null, "Veuillez indiquez un endroit sur le plan", null, 0);
                return;
            }
            
            sortStation.setPosition(position);
        }

	public void DeleteStation() {
            this.getProject().getSortCenter().getStations().remove(0);
	}

	public void EditStation(String name, String description, Color color, String imgSrc, Float speedMax) {
            
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

	public void AddJunction() {
		throw new UnsupportedOperationException();
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

	public void addConveyor(Object aInlet, Object aOutlet) {
		throw new UnsupportedOperationException();
	}

	public void addJunction(Point aPosition) {
		throw new UnsupportedOperationException();
	}
        
        public Project getProject() {
            return _project;
        }

    
}