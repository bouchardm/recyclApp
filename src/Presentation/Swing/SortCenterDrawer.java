/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Swing;

import Application.Controller.Controller;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

/**
 *
 * @author Dany
 */
public class SortCenterDrawer
{
    private Controller controller;
    private Viewport viewport;

    public SortCenterDrawer(Controller controller, Viewport viewport)
    {
        this.controller = controller;
        this.viewport = viewport;
    }
    
    public void draw(Graphics g)
    {
        drawFloor(g);
        if (viewport.isShowGrid())
        {
            drawGrid(g);
        }
        drawStations(g);
        drawJunctions(g);
        drawConveyors(g);
    }
    
    
    private void drawFloor(Graphics g)
    {
        if (controller != null)
        {
            Point2D.Float dim = controller.getSortCenterDimensions();
            float zoomFactor = viewport.getZoomFactor();
            int margin = (int)(viewport.MARGIN * zoomFactor);
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
