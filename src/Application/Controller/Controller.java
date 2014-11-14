package Application.Controller;


import Domain.MatterList;
import Domain.SortMatrix;
import Domain.SortStation;
import Domain.EntryPoint;
import Domain.ExitPoint;
import Domain.Conveyor;
import Domain.Element;
import Domain.Project;
import Domain.SortCenter;
import Presentation.Swing.AboutUs;
import Presentation.Swing.MainFrame;
import com.sun.xml.internal.fastinfoset.stax.events.Util;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controller {
	private SortMatrix _sortMatrix;
	private Project _project;
	private SortCenter _sortCenter;
	private Conveyor _conveyor;
//	private SortStation _station;
	private ExitPoint _exitPoint;
	private EntryPoint _entryPoint;
	private Object _matterBasket;
	private Object _transformationMatrix;
	private MatterList _matterList;
	public MainFrame _mainFrame;
        
        private Element _selectedElement;
        
        public Controller()
        {
            _project = new Project();
            _selectedElement = null;
        }
        
        public void selectElement(Point2D.Float coords)
        {
            List<Element> elements = new ArrayList<Element>();
            
            elements.add(_project.getSortCenter());
            
            _selectedElement = null;
            
            if (elements.size() != 0)
            {
                _selectedElement = elements.get(elements.size()-1);
            }
        }

        public void showAboutUs() {
            AboutUs view = new AboutUs();
            view.setVisible(true);
        }


        // ************ SortCenter ***************

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

	public void EditMatrix() {
		throw new UnsupportedOperationException();
	}

	public void AddStation(Point2D.Float position) {
            
            JFrame frame = new JFrame("Nombre de sortie");
            
            int value;
            
            try {
                value = Integer.parseInt(JOptionPane.showInputDialog(frame, "Quel est le nombre de sortie?", null, 0));            
            } catch (NumberFormatException e) {
                value = -1;
            }
            if (value > 0) {
                this._project.getSortCenter().addSortStation(position);
            }
	}

	public void DeleteStation() {
		throw new UnsupportedOperationException();
	}

	public void EditStation() {
		throw new UnsupportedOperationException();
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

	public void AddMatterl() {
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