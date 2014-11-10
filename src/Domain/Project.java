/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

/**
 *
 * @author Dany
 */
public class Project
{
    private String filename;
    private String path;
    private SortCenter sortCenter;
    private Boolean isSaved;
    
    public Project()
    {
        newProject();
    }
    
    public void newProject()
    {
        filename = null;
        path = null;
        sortCenter = new SortCenter();
        isSaved = true;
    }
    
    public void loadProject(String path)
    {
    }
    
    public void saveProject()
    {
        saveAsProject(path, filename);
    }
    
    public void saveAsProject(String path, String filename)
    {
        this.path = path;
        this.filename = filename;
        isSaved = true;
    }
    
    public void exportImage(String path)
    {
    }
    
    public void setUnsaved()
    {
        isSaved = false;
    }
    
    public SortCenter getSortCenter()
    {
        return sortCenter;
    }
    
}
