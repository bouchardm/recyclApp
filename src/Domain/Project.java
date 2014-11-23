/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import Application.Controller.Controller;
import java.util.ArrayList;
import java.util.HashMap;
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
        
        this._sortCenter.setMatterList(null);
        
        this.test();
    }
    
    public void test() { // TODO : enlever pour la remise
        Matter m1 = new Matter("m1",1);
        Matter m2 = new Matter("m2",2);
        Matter m3 = new Matter("m3",3);
        Matter m4 = new Matter("m4",4);
        
        MatterList tmlist = new MatterList();
        tmlist.Add(m1);
        tmlist.Add(m2);
        tmlist.Add(m3);
        tmlist.Add(m4);

        this._sortCenter.setMatterList(tmlist);

        //construire le premier matterBasket en entrée

        MatterBasket mb1 = new MatterBasket();
        mb1.addMatterQuantity(m1.getID(), new Float(700));
        mb1.addMatterQuantity(m2.getID(), new Float(1000));
        //construire le deuxième matterBasket en entrée
        MatterBasket mb2 = new MatterBasket();
        mb2.addMatterQuantity(m1.getID(), new Float(1500));
        mb2.addMatterQuantity(m2.getID(), new Float(1200));


        //créer une matrice de tri pour les machines à deux sortie
        HashMap<Integer, ArrayList<Float>> smatrix = new HashMap<>();
        ArrayList<Float> innerList = new ArrayList<>();
        innerList.add(new Float(.5));
        innerList.add(new Float(.5));
        smatrix.put(1, innerList);
        ArrayList<Float> innerList2 = new ArrayList<>();
        innerList2.add(new Float(.5));
        innerList2.add(new Float(.5));
        smatrix.put(2, innerList2);
        SortMatrix sorter = new SortMatrix();
        sorter.setSortMatrix(smatrix);
        
        
        
        //créer la matrice de tri pour les machines à deux sorties
        HashMap<Integer, ArrayList<Float>> smatrix2 = new HashMap<>();
        ArrayList<Float> innerList21 = new ArrayList<>();
        innerList21.add(new Float(0.5));
        innerList21.add(new Float(0.5));
        smatrix2.put(1, innerList21);
        ArrayList<Float> innerList22 = new ArrayList<>();
        innerList22.add(new Float(0.75));
        innerList22.add(new Float(0.25));
        smatrix2.put(2, innerList22);
        SortMatrix sorter2 = new SortMatrix();
        sorter2.setSortMatrix(smatrix2);

        //créer les station

        this._sortCenter.addSortStation(2);                           //S1
        Station s1 = this._sortCenter.getStations().get(0);
        s1.setSortMatrix(sorter);
        
        
        
        this._sortCenter.addTransStation(2);                           //S2
        TransStation s2 = (TransStation) this._sortCenter.getStations().get(1);
        s2.setSortMatrix(sorter2);
        
            
        
        
        // {1={1=0.35, 2=0.0}, 2={1=0.25, 2=0.75}}
        HashMap<Integer, Float> catTransformQuantity = new HashMap<>();
        HashMap<Integer, Float> dogTransformQuantity = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Float>> matterQuantities = new HashMap<>();
        TransMatrix tmtest = new TransMatrix();
        catTransformQuantity.put(3,new Float(.25));
        catTransformQuantity.put(4,new Float(.75));
        dogTransformQuantity.put(3,new Float(.5));
        dogTransformQuantity.put(4,new Float(.5));
        matterQuantities.put(1, dogTransformQuantity);
        matterQuantities.put(2, catTransformQuantity);
        tmtest.addMatterToTransMatrix(m3.getID(), dogTransformQuantity);
        tmtest.addMatterToTransMatrix(m4.getID(), catTransformQuantity);
        
        s2.setTransMatrix(tmtest);
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
