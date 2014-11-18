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
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.imageio.ImageIO;

/**
 *
 * @author Dany
 */
public class SortCenterDrawer
{
    private Controller _controller;
    private Viewport _viewport;
    private Color _selectedContour;

    public SortCenterDrawer(Controller controller, Viewport viewport)
    {
        this._controller = controller;
        this._viewport = viewport;
        _selectedContour = Color.MAGENTA;
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
        if (_controller != null)
        {
            Point2D.Float dim = _controller.getSortCenterDimensions();
            float zoomFactor = _viewport.getZoomFactor();
            int margin = (int)(_viewport.MARGIN * zoomFactor);
            int level = 240;
            int width = (int)(dim.x * 50 * zoomFactor);
            int height = (int)(dim.y * 50 * zoomFactor);
            g.setColor(new Color(level, level, level));
            g.fillRect(margin, margin, width, height);
            g.setColor(Color.WHITE);
            g.drawRect(margin, margin, width, height);
            if (_controller.isFloorSelected())
            {
                g.setColor(_selectedContour);
                g.drawRect(margin, margin, width, height);
            }
        }
    }
    
    private void drawGrid(Graphics g)
    {
        Point2D.Float dim = _controller.getSortCenterDimensions();
        
        Point2D.Float gridDim = _viewport.getGridDimensions();
        Point2D.Float gridOffset = _viewport.getGridOffset();
        
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
        ArrayList sortStationList = this._controller.getProject().getSortCenter().getSortStationList();
        for (int i = sortStationList.size() - 1; i >= 0; i--) {
            this.drawStation(g, (SortStation) sortStationList.get(i));
        }
    }
    
    private void drawStation(Graphics g, SortStation station) {
        Point2D.Float dimension = station.getDimensions();
        Point2D.Float position = station.getPosition();

        int positionMeterX = _viewport.meterToPix(position.x);
        int positionMeterY = _viewport.meterToPix(position.y);
        // TODO: Regarder pourquoi j'ai besoin de faire -1
        int dimensionMeterX = _viewport.meterToPix(dimension.x - 1);
        int dimensionMeterY = _viewport.meterToPix(dimension.y - 1);

        // Dessin de la stations
        if (station.isSelected()) {
            g.setColor(Color.black);
            g.fillRect(positionMeterX - 2, positionMeterY - 2, dimensionMeterX + 4, dimensionMeterY + 4);
        }

        g.setColor(station.getColor());

        g.fillRect(positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY);

        if (station.getImg() != null) {
            g.drawImage(station.getImg(), positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY, _viewport);
        }
        
        g.drawString(station.getName(), positionMeterX, positionMeterY + dimensionMeterY + 20);
    }
    
    private void drawJunctions(Graphics g)
    {
    }
    
    private void drawConveyors(Graphics g)
    {
    }
            
}
