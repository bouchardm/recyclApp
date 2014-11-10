/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Swing;

import Application.Controller.Controller;
import java.awt.Color;
import java.awt.Graphics;

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
//        g.setColor(new Color(0,255,0));
        int x = 10;
        int y = 20;
        int width = 100;
        int height = 100;
        int level = 240;
        g.setColor(new Color(level, level, level));
        g.fillRect(x, y, width, height);
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
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
