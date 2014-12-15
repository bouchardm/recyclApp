/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Dany
 */
public class Project {

    private String _filename;
    private String _path;
    private SortCenter _sortCenter;
    private Boolean _isSaved;

    public Project() {
        newProject();
    }

    public void newProject() {
        setFilename(null);
        setPath(null);
        setSortCenter(new SortCenter());
        setIsSaved((Boolean) true);

        this.getSortCenter().addEntryPoint();

    }

    public void loadProject(String path) {
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)))) {

            ArrayList<EntryPoint> entryPointList = new ArrayList<>();
//            int i = 0;
//            while (i < 2) {
//                entryPointList.add((EntryPoint) decoder.readObject());
//                i++;
//            }
//            entryPointList.add((EntryPoint) decoder.readObject());        
            // this.getSortCenter().setEntrypoints(entryPointList);

            SortCenter sortcenter = (SortCenter) decoder.readObject();
            this.setSortCenter(sortcenter);

        } catch (final Exception e) {

        }
    }

    public void saveProject(String path) {
        XMLEncoder encoder = null;

        try {

            //Get all entrypoint
//            ArrayList<EntryPoint> entryPointList = getSortCenter().getEntryPoints();
//            ArrayList<ExitPoint> exitPointList = getSortCenter().getExitPoints();
//            ArrayList<Conveyor> conveyorList = getSortCenter().getConveyors();
//            ArrayList<Junction> junctionList = getSortCenter().getJunctions();
//            MatterList matterList = getSortCenter().getMatterList();
//
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
//
//            for (EntryPoint entryPoint : entryPointList) {
//                encoder.writeObject(entryPoint);
//            }
//            for (ExitPoint exitPoint : exitPointList) {
//                encoder.writeObject(exitPoint);
//            }
//            for (Conveyor conveyor : conveyorList) {
//                encoder.writeObject(conveyor);
//            }
//            for (Junction junction : junctionList) {
//                encoder.writeObject(junction);
//            }
//
//                encoder.writeObject(matterList);

            encoder.writeObject(this.getSortCenter());

            encoder.flush();

        } catch (final java.io.IOException e) {
        } finally {

            if (encoder != null) {

                encoder.close();
            }
        }
    }

    public void saveAsProject(String path, String filename) {
        this.setPath(path);
        this.setFilename(filename);
        setIsSaved((Boolean) true);
    }

    public void exportImage(String path) {
    }

    public void setUnsaved() {
        setIsSaved((Boolean) false);
    }

    public boolean isSaved() {
        return getIsSaved();
    }

    public SortCenter getSortCenter() {
        return _sortCenter;
    }

    /**
     * @return the _filename
     */
    public String getFilename() {
        return _filename;
    }

    /**
     * @param _filename the _filename to set
     */
    public void setFilename(String _filename) {
        this._filename = _filename;
    }

    /**
     * @return the _path
     */
    public String getPath() {
        return _path;
    }

    /**
     * @param _path the _path to set
     */
    public void setPath(String _path) {
        this._path = _path;
    }

    /**
     * @param _sortCenter the _sortCenter to set
     */
    public void setSortCenter(SortCenter _sortCenter) {
        this._sortCenter = _sortCenter;
    }

    /**
     * @return the _isSaved
     */
    public Boolean getIsSaved() {
        return _isSaved;
    }

    /**
     * @param _isSaved the _isSaved to set
     */
    public void setIsSaved(Boolean _isSaved) {
        this._isSaved = _isSaved;
    }

}
