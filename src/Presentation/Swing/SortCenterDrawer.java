/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Swing;

import Application.Controller.RecyclAppController;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

/**
 *
 * @author Dany
 */
public class SortCenterDrawer
{
    private RecyclAppController _recyclAppController;
    private Viewport _viewport;

    public SortCenterDrawer(RecyclAppController controller, Viewport viewport)
    {
        this._recyclAppController = controller;
        this._viewport = viewport;
    }
    
    public void draw(Graphics g)
    {
        drawFloor(g);
        if (_viewport.isShowGrid())
        {
            drawGrid(g);
        }
        drawStations(g);
        drawJunctions(g);
        drawConveyors(g);
    }
    
    
    private void drawFloor(Graphics g)
    {
        if (_recyclAppController != null)
        {
            Point2D.Float dim = _recyclAppController.getSortCenterDimensions();
            float zoomFactor = _viewport.getZoomFactor();
            int margin = (int)(_viewport.MARGIN * zoomFactor);
            int level = 240;
            int width = (int)(dim.x * 50 * zoomFactor);
            int height = (int)(dim.y * 50 * zoomFactor);
            g.setColor(new Color(level, level, level));
            g.fillRect(margin, margin, width, height);
            g.setColor(Color.WHITE);
            g.drawRect(margin, margin, width, height);
        }
    }
    
    private void drawGrid(Graphics g)
    {
        Point2D.Float dim = _recyclAppController.getSortCenterDimensions();
        
        Point2D.Float gridDim = _viewport.grid.getDimensions();
        Point2D.Float gridOffset = _viewport.grid.getOffset();
        
        float x = gridOffset.x;
        float y = gridOffset.y;
        
        int pxX;
        int pxY;
        
        g.setColor(Color.WHITE);
        
        while (y <= dim.y)
        {
            while (x <= dim.x)
            {
                pxX = _viewport.meterToPix(x);
                pxY = _viewport.meterToPix(y);
                
                g.fillRect(pxX - 1, pxY - 1, 3, 3);
                
                x += gridDim.x;
            }
            x = gridOffset.x;
            y += gridDim.y;
        }
    }
    
    private void drawStations(Graphics g)
    {
    }
    
    private void drawJunctions(Graphics g)
    {
    }
    
    private void drawConveyors(Graphics g)
    {
    }
            
}
