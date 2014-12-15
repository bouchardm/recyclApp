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
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Dany
 */
 public class Viewport extends JPanel implements Serializable
{
    private MainFrame _mainFrame;
    private SortCenterDrawer _drawer;
    private float zoomFactor = 1f;
    public enum VIEW_MODES { ICONIC, TEXTUAL };
    private VIEW_MODES viewmode = VIEW_MODES.ICONIC;
    
    public  enum CREATION_MODES {SORT_STATION, TRANS_STATION, JUNCTION, CONVEYOR_1, CONVEYOR_2, NONE, ENTRY, EXIT};
    private CREATION_MODES creationMode = CREATION_MODES.NONE;
    
    private boolean _showGrid;
    private boolean _snapToGrid;
    public static int MARGIN = 50;
    
    private final Grid _grid;
    private Line2D.Float _connectingArrow;
    
    
    public Viewport()
    {
        _showGrid = false;
        _snapToGrid = false;
        _grid = new Grid();
        config();
    }
    
    public Viewport(MainFrame mainFrame)
    {
        this._mainFrame = mainFrame;
        _grid = new Grid();
        _showGrid = false;
        _snapToGrid = false;
        config();
    }
    
    private void config()
    {
        int level = 215;
        setBackground(new Color(level, level, level));
        setZoomFactor(1.0f);
    }
    
    public void setGridDimensions(Point2D.Float dim)
    {
        _grid.setDimensions(dim);
        repaint();
    }
    
    public Point2D.Float getGridDimensions()
    {
        return _grid.getDimensions();
    }
    
    public Point2D.Float getGridOffset()
    {
        return _grid.getOffset();
    }
    
    public Point2D.Float snap(Point2D.Float point)
    {
        return _grid.snap(point);
    }
    
    public void setConnectingArrow(Line2D.Float line)
    {
        _connectingArrow = line;
    }
    
    public Line2D.Float getConnectingArrow()
    {
        return _connectingArrow;
    }
    
    
    @Override
    protected void paintComponent(Graphics g)
    {
       if (_mainFrame != null)
       {
           super.paintComponent(g);
           _drawer = new SortCenterDrawer(_mainFrame._controller, this);
           _drawer.setConnectingArrow(_connectingArrow);
           _drawer.draw(g);
       }
    }
    
    public void exportImage(String path, String extension)
    {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        paint(image.getGraphics());
        try {
            ImageIO.write(image, extension, new File(path));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(_mainFrame,
                String.format("Une erreur est survenue lors de la création du fichier %s\n%s", path, ex.toString()));
        }
    }
    
    public void displayGrid(boolean showGrid)
    {
        this._showGrid = showGrid;
        repaint();
    }
    
    public void snapToGrid(boolean snapToGrid)
    {
        _snapToGrid = snapToGrid;
    }
    
    public void setShowGrid(boolean showGrid)
    {
        this._showGrid = showGrid;
    }
    
    
    public void setZoomFactor(float zoomFactor)
    {
        if (zoomFactor < 0.1)
        {
            zoomFactor = 0.1f;
        }
        this.zoomFactor = zoomFactor;
        if (_mainFrame != null)
        {
            Point2D.Float dim = _mainFrame._controller.getSortCenterDimensions();
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
        return _showGrid;
    }
    
    public boolean isSnapToGrid()
    {
        return _snapToGrid;
    }
    
    public float pixToMeter(int pixels)
    {
        return (((float)(pixels - MARGIN * zoomFactor) / 50) / zoomFactor);
    }
    
    public int meterToPix(float meters)
    {
        return (int)((meters * 50 * zoomFactor) + (MARGIN * zoomFactor));
    }
    
    public int meterToPixDim(float meters)
    {
        return (int)((meters * 50 * zoomFactor) + (zoomFactor));
    }
    
    private void selectFromPoint(Point2D.Float point)
    {
        
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

    public void setCreationMode( CREATION_MODES creationMode) {
        this.creationMode = creationMode;
    }
}
