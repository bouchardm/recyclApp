package Application.Controller;


import Domain.MatterList;
import Domain.SortMatrix;
import Domain.SortStation;
import Domain.EntryPoint;
import Domain.ExitPoint;
import Domain.Conveyor;
import Domain.Element;
import Domain.Inlet;
import Domain.Outlet;
import Domain.Project;
import Domain.SortCenter;
import Presentation.Swing.AboutUs;
import Presentation.Swing.MainFrame;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Controller {
	private SortMatrix _sortMatrix;
	private Project _project;
	private SortCenter _sortCenter;
	private Conveyor _conveyor;
	private SortStation _station;
	private ExitPoint _exitPoint;
	private EntryPoint _entryPoint;
	private Object _matterBasket;
	private Object _transformationMatrix;
	private MatterList _matterList;
        private static int matterIDCounter = 0;
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

	public void AddConvoyer(Outlet aExit, Inlet aEntrance) {
            this._sortCenter.addConveyor(aExit, aEntrance);
            this._sortCenter.updateDesign();
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

	public void AddStation() {
		throw new UnsupportedOperationException();
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

	public void AddMatter() {
		throw new UnsupportedOperationException();
            // new matter avec id = matterIDCounter
            // matterIDCounter ++
            // faudra ajouter à matterList
                //vérifier: matterBasket aussi?  je crois que ça sera plutôt
                //en propageant le panier d'une station à une autre
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
}