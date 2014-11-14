/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Swing;

import Application.Controller.Controller;
import Domain.SortStation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Dany
 */
public class SortCenterDrawer
{
    private Controller _recyclAppController;
    private Viewport _viewport;

    public SortCenterDrawer(Controller controller, Viewport viewport)
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
        
        g.setColor(Color.BLACK);
        
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
        this.drawStations(g);
    }
    
    private void drawStations(Graphics g)
    {
        g.setColor(Color.red);
        
        int positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY;
        
        // Boucle des stations
        for (Iterator iterator = this._recyclAppController.getProject().getSortCenter().getSortStationList().iterator(); iterator.hasNext();) {
            SortStation next = (SortStation)iterator.next();
            Point2D.Float dimension = next.getDimensions();
            Point2D.Float position = next.getPosition();
            
            positionMeterX = _viewport.meterToPix(position.x);
            positionMeterY = _viewport.meterToPix(position.y);
            // TODO: Regarder pourquoi j'ai besoin de faire -1
            dimensionMeterX = _viewport.meterToPix(dimension.x - 1);
            dimensionMeterY = _viewport.meterToPix(dimension.y - 1);
            
            // Dessin de la stations
            g.fillRect(positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY);
        }
    }
    
    private void drawJunctions(Graphics g)
    {
    }
    
    private void drawConveyors(Graphics g)
    {
    }
            
}
