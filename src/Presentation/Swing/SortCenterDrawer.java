/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Swing;

import Application.Controller.Controller;
import Domain.IOlet;
import Domain.Inlet;
import Domain.Outlet;
import Domain.SortStation;
import Domain.Station;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author Dany
 */
public class SortCenterDrawer
{
    private Controller _controller;
    private Viewport _viewport;
    private Color _selectedContourColor;

    public SortCenterDrawer(Controller controller, Viewport viewport)
    {
        this._controller = controller;
        this._viewport = viewport;
        _selectedContourColor = Color.MAGENTA;
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
            g.setColor(Color.BLACK);
            g.drawRect(margin, margin, width, height);
            if (_controller.isFloorSelected())
            {
                g.setColor(_selectedContourColor);
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
        // TODO faire un refactor
        ArrayList sortStationList = this._controller.getProject().getSortCenter().getStations();

        for (int i = sortStationList.size() - 1; i >= 0; i--)
        {
            this.drawStation(g, (Station) sortStationList.get(i));
        }
    }
    
    private void drawStation(Graphics g, Station station) {
        Point2D.Float dimension = station.getDimensions();
        Point2D.Float position = station.getPosition();

        int positionMeterX = _viewport.meterToPix(position.x);
        int positionMeterY = _viewport.meterToPix(position.y);
        // TODO: Regarder pourquoi j'ai besoin de faire -1
        int dimensionMeterX = _viewport.meterToPix(dimension.x - 1);
        int dimensionMeterY = _viewport.meterToPix(dimension.y - 1);
        
        ArrayList<IOlet> iolets = new ArrayList<>();

        // Dessin de la stations
        if (_controller.selectedElementIs(station))
        {
            g.setColor(_selectedContourColor);
            g.fillRect(positionMeterX - 2, positionMeterY - 2, dimensionMeterX + 4, dimensionMeterY + 4);
        }

        g.setColor(station.getColor());

        g.fillRect(positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY);

        if (station.getImg() != null) {
            g.drawImage(station.getImg(), positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY, _viewport);
        }
        
        g.drawString(station.getName(), positionMeterX, positionMeterY + dimensionMeterY + 20);
        
        if (station instanceof Station)
        {
            iolets.add(station.getInlet());
        }
        
        iolets.addAll(station.getIOlets());
        
        for (IOlet iol: iolets)
        {
            drawIOlet(g, iol);
        }
    }
    
    private void drawIOlet(Graphics g, IOlet iolet)
    {
        g.setColor(Color.BLUE);
        if (iolet instanceof Inlet)
        {
            g.setColor(Color.YELLOW);
        }
        Ellipse2D.Float circle = iolet.getCircle();
        g.fillOval(_viewport.meterToPix(circle.x),
                _viewport.meterToPix(circle.y),
                _viewport.meterToPixDim(circle.width),
                _viewport.meterToPixDim(circle.height));
        if (_controller.selectedElementIs(iolet))
        {
            g.setColor(_selectedContourColor);
            g.drawOval(_viewport.meterToPix(circle.x),
                _viewport.meterToPix(circle.y),
                _viewport.meterToPixDim(circle.width),
                _viewport.meterToPixDim(circle.height));
        }
    }
    
    private void drawJunctions(Graphics g)
    {
    }
    
    private void drawConveyors(Graphics g)
    {
    }
            
}
