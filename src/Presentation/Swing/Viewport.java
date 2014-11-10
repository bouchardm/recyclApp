/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Swing;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Dany
 */
public class Viewport extends JPanel implements Serializable
{
    private JFrame mainFrame;
    
    private float zoomFactor = 1f;
    public enum VIEW_MODES { ICONIC, TEXTUAL };
    private VIEW_MODES viewmode = VIEW_MODES.ICONIC;
    
    public enum CREATION_MODES {SORT_STATION, TRANS_STATION, JUNCTION, CONVEYOR, NONE};
    private CREATION_MODES creationMode = CREATION_MODES.NONE;
    
    private boolean showGrid = false;
    private boolean snapToGrid = false;
    
    
    
    public Viewport()
    {
        config();
    }
    
    public Viewport(JFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        config();
        
    }
    
    private void config()
    {
        setBackground(Color.GRAY);
    }
    
    
    @Override
    protected void paintComponent(Graphics g)
    {
       if (mainFrame != null)
       {
           super.paintComponent(g);
           SortCenterDrawer drawer = new SortCenterDrawer(null, this);
           drawer.draw(g);
           // ...
       }
    }
    
    
    public void toggleDisplayGrid()
    {
        showGrid = !showGrid;
        repaint();
    }
    
    public void setZoomFactor(float zoomFactor)
    {
        this.zoomFactor = zoomFactor;
        repaint();
    }
    
    public boolean isShowGrid()
    {
        return showGrid;
    }
}
