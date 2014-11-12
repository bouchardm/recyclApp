/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import Application.Controller.Controller;
import java.util.Vector;

/**
 *
 * @author Dany
 */
public class Project
{
    private String _filename;
    private String _path;
    private SortCenter _sortCenter;
    private Boolean _isSaved;
    
    // public Vector<ProjectController> _projectController = new Vector<ProjectController>();
    public Controller _recyclAppController;
    public SortCenter _sortCenter2;
    
    public Project()
    {
        newProject();
    }
    
    public void newProject()
    {
        _filename = null;
        _path = null;
        _sortCenter = new SortCenter();
        _isSaved = true;
    }
    
    public void loadProject(String path)
    {
    }
    
    public void saveProject()
    {
        saveAsProject(_path, _filename);
    }
    
    public void saveAsProject(String path, String filename)
    {
        this._path = path;
        this._filename = filename;
        _isSaved = true;
    }
    
    public void exportImage(String path)
    {
    }
    
    public void setUnsaved()
    {
        _isSaved = false;
    }
    
    public boolean isSaved()
    {
        return _isSaved;
    }
    
    public SortCenter getSortCenter()
    {
        return _sortCenter;
    }
    
}
