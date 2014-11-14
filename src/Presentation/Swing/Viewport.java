/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.Serializable;
import javax.swing.JPanel;

/**
 *
 * @author Dany
 */
 public class Viewport extends JPanel implements Serializable
{
    private MainFrame mainFrame;
    private SortCenterDrawer _drawer;
    private float zoomFactor = 1f;
    public enum VIEW_MODES { ICONIC, TEXTUAL };
    private VIEW_MODES viewmode = VIEW_MODES.ICONIC;
    
    public enum CREATION_MODES {SORT_STATION, TRANS_STATION, JUNCTION, CONVEYOR_1, CONVEYOR_2, NONE};
    private CREATION_MODES creationMode = CREATION_MODES.NONE;
    
    private boolean showGrid;
    private boolean snapToGrid;
    public static int MARGIN = 50;
    
    public final Grid grid;
    
    
    public Viewport()
    {
        showGrid = false;
        snapToGrid = false;
        grid = new Grid();
        config();
    }
    
    public Viewport(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        grid = new Grid();
        showGrid = false;
        snapToGrid = false;
        config();
    }
    
    private void config()
    {
        setBackground(Color.GRAY);
        setZoomFactor(1.0f);
    }
    
    
    @Override
    protected void paintComponent(Graphics g)
    {
       if (mainFrame != null)
       {
           super.paintComponent(g);
           _drawer = new SortCenterDrawer(mainFrame._recycleAppController, this);
           _drawer.draw(g);
       }
    }
    
    public void displayGrid(boolean showGrid)
    {
        this.showGrid = showGrid;
        repaint();
    }
    
    public void display() {
        repaint();
    }
    
    public void setShowGrid(boolean showGrid)
    {
        this.showGrid = showGrid;
    }
    
    
//    public void toggleDisplayGrid()
//    {
//        showGrid = !showGrid;
//        repaint();
//    }
    
    public void setZoomFactor(float zoomFactor)
    {
        if (zoomFactor < 0.1)
        {
            zoomFactor = 0.1f;
        }
        this.zoomFactor = zoomFactor;
        if (mainFrame != null)
        {
            Point2D.Float dim = mainFrame._recycleAppController.getSortCenterDimensions();
            dim.x = dim.x;
            dim.y = dim.y;
            int x = meterToPix(dim.x) + MARGIN * 2;
            int y = meterToPix(dim.y) + MARGIN * 2;
            setSize(new Dimension(x, y));
            setPreferredSize(new Dimension(x, y));
            
        }
        repaint();
    }
    
    public float getZoomFactor()
    {
        return zoomFactor;
    }
    
    public boolean isShowGrid()
    {
        return showGrid;
    }
    
    public float pixToMeter(int pixels)
    {
        return (((float)(pixels - MARGIN * zoomFactor) / 50) / zoomFactor);
    }
    
    public int meterToPix(float meters)
    {
        return (int)((meters * 50 * zoomFactor) + (MARGIN * zoomFactor));
    }
    
    public Point2D.Float createPointInMeter (int pixelX, int pixelY) {
        return new Point2D.Float(this.pixToMeter(pixelX), this.pixToMeter(pixelY));
    }
    
    public SortCenterDrawer getDrawer() {
        return _drawer;
    }
    
    public CREATION_MODES getCreationMode() {
        return creationMode;
    }

    public void setCreationMode(CREATION_MODES creationMode) {
        this.creationMode = creationMode;
    }
}
