/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Swing;

import Application.Controller.Controller;
import Domain.IOlet;
import Domain.Inlet;
import Domain.Conveyor;
import Domain.EntryPoint;
import Domain.ExitPoint;
import Domain.Junction;
import Domain.Station;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author Dany
 */
public class SortCenterDrawer {

    private Controller _controller;
    private Viewport _viewport;
    private Color _selectedContourColor;
    private Line2D.Float _connectingArrow;

    public SortCenterDrawer(Controller controller, Viewport viewport) {
        this._controller = controller;
        this._viewport = viewport;
        _selectedContourColor = Color.MAGENTA;
        _connectingArrow = null;
    }
    

    public void draw(Graphics g) {
        drawFloor(g);
        if (_viewport.isShowGrid()) {
            drawGrid(g);
        }
        
        drawConveyors(g);
        drawStations(g);
        drawJunctions(g);
        drawEntryPoints(g);
        drawExitPoints(g);
        if (_connectingArrow != null)
        {            
            drawConnectingArrow(g);
        }
    }

    private void drawFloor(Graphics g) {
        if (_controller != null) {
            Point2D.Float dim = _controller.getSortCenterDimensions();
            float zoomFactor = _viewport.getZoomFactor();
            int margin = (int) (_viewport.MARGIN * zoomFactor);
            int level = 240;
            int width = (int) (dim.x * 50 * zoomFactor);
            int height = (int) (dim.y * 50 * zoomFactor);
            g.setColor(new Color(level, level, level));
            g.fillRect(margin, margin, width, height);
            g.setColor(Color.BLACK);
            
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            
            if (_controller.isFloorSelected())
            {
                g2.setColor(_selectedContourColor);
            }
            g2.drawRect(margin, margin, width, height);
        }
    }

    private void drawGrid(Graphics g) {
        Point2D.Float dim = _controller.getSortCenterDimensions();

        Point2D.Float gridDim = _viewport.getGridDimensions();
        Point2D.Float gridOffset = _viewport.getGridOffset();

        float x = gridOffset.x;
        float y = gridOffset.y;

        int pxX;
        int pxY;

        g.setColor(Color.BLACK);

        while (y <= dim.y) {
            while (x <= dim.x) {
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

    private void drawStations(Graphics g) {
        // TODO faire un refactor
        ArrayList sortStationList = this._controller.getProject().getSortCenter().getStations();

        for (int i = sortStationList.size() - 1; i >= 0; i--) {
            this.drawStation(g, (Station) sortStationList.get(i));
        }
    }

    private void drawStation(Graphics g, Station station) {
        Point2D.Float dimension = station.getDimensions();
        Point2D.Float position = station.getPosition();

        int positionMeterX = _viewport.meterToPix(position.x);
        int positionMeterY = _viewport.meterToPix(position.y);
        
        int dimensionMeterX = _viewport.meterToPix(dimension.x - 1);
        int dimensionMeterY = _viewport.meterToPix(dimension.y - 1);

        ArrayList<IOlet> iolets = new ArrayList<>();

        // Dessin de la stations
        if (_controller.selectedElementIs(station)) {
            g.setColor(_selectedContourColor);
            g.fillRect(positionMeterX - 2, positionMeterY - 2, dimensionMeterX + 4, dimensionMeterY + 4);
        }

        g.setColor(station.getColor());

        g.fillRect(positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY);
        
        if (station.getImg() != null) {
            g.drawImage(station.getImg(), positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY, _viewport);
        }

        g.drawString(station.getName(), positionMeterX, positionMeterY + dimensionMeterY + 20);

        if (station instanceof Station) {
            iolets.add(station.getInlet());
        }

        iolets.addAll(station.getIOlets());

        for (IOlet iol : iolets) {
            drawIOlet(g, iol);
        }
    }

    private void drawIOlet(Graphics g, IOlet iolet) {
        g.setColor(Color.BLUE);
        if (iolet instanceof Inlet) {
            g.setColor(Color.YELLOW);
        }
        Ellipse2D.Float circle = iolet.getCircle();
        g.fillOval(_viewport.meterToPix(circle.x),
                _viewport.meterToPix(circle.y),
                _viewport.meterToPixDim(circle.width),
                _viewport.meterToPixDim(circle.height));
        if (_controller.selectedElementIs(iolet)) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            g2.setColor(_selectedContourColor);
            g.drawOval(_viewport.meterToPix(circle.x),
                    _viewport.meterToPix(circle.y),
                    _viewport.meterToPixDim(circle.width),
                    _viewport.meterToPixDim(circle.height));
        }
    }

    private void drawJunctions(Graphics g) {
        
        g.setColor(Color.blue);
        
        ArrayList<Junction> junctionsList = this._controller.getProject().getSortCenter().getJunctions();
        for (Junction junction : junctionsList) {
            this.drawJunction(g, junction);
        }
    }
    
    private void drawJunction(Graphics g, Junction junction) {
        Point2D.Float position = junction.getPosition();
        Point2D.Float dimension = junction.getDimensions();
        
        int positionMeterX = _viewport.meterToPix(position.x);
        int positionMeterY = _viewport.meterToPix(position.y);
        
        int dimensionMeterX = _viewport.meterToPix(dimension.x - 1);
        int dimensionMeterY = _viewport.meterToPix(dimension.y - 1);
        
        ClassLoader cl = this.getClass().getClassLoader();
        Image img = Toolkit.getDefaultToolkit().getImage(cl.getResource("image/junction.png"));
        
        g.drawImage(img, positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY, _viewport);
        ArrayList<IOlet> iolets = new ArrayList<IOlet>();
        
        iolets.addAll(junction.getIOlets());
        
        if (_controller.selectedElementIs(junction)) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            g2.setColor(_selectedContourColor);
            g2.drawRect(positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY);
        }

        for (IOlet iol : iolets) {
            drawIOlet(g, iol);
        }
    }

    private void drawConveyors(Graphics g) {
        ArrayList conveyrList = this._controller.getProject().getSortCenter().getConveyors();

        for (int i = conveyrList.size() - 1; i >= 0; i--) {
            this.drawConveyor(g, (Conveyor) conveyrList.get(i));
        }
    }

    private void drawConveyor(Graphics g, Conveyor conveyor) {
        int x1, x2, y1, y2;
        Point2D.Float position1 = conveyor.getStartOutlet().getPosition();
        Point2D.Float position2 = conveyor.getEndInlet().getPosition();
        
        x1 = _viewport.meterToPix(position1.x);
        y1 = _viewport.meterToPix(position1.y);
        x2 = _viewport.meterToPix(position2.x);
        y2 = _viewport.meterToPix(position2.y);

        g.setColor(Color.BLACK);

        if (_controller.selectedElementIs(conveyor)) {
            g.setColor(_selectedContourColor);
        }
        
        drawArrow(g, x1, y1, x2, y2);

    }
    
    private void drawConnectingArrow(Graphics g)
    {
        int x1, x2, y1, y2;
        x1 = _viewport.meterToPix(_connectingArrow.x1);
        y1 = _viewport.meterToPix(_connectingArrow.y1);
        x2 = _viewport.meterToPix(_connectingArrow.x2);
        y2 = _viewport.meterToPix(_connectingArrow.y2);

        g.setColor(Color.BLACK);
        
        drawArrow(g, x1, y1, x2, y2);
    }

    // Source: http://stackoverflow.com/questions/4112701/drawing-a-line-with-arrow-in-java
    void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        int ARR_SIZE = 10;
        Graphics2D g = (Graphics2D) g1.create();

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);
        len -= 2;
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        g.setStroke(new BasicStroke(3));
        g.drawLine(0, 0, len, 0);
        
              //  g.setStroke(new BasicStroke(10));
              //  g.draw(new Line2D.Float(30, 20, 80, 90));
        g.fillPolygon(new int[]{len, len - ARR_SIZE, len - ARR_SIZE, len},
                new int[]{0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }
    
    public void setConnectingArrow(Line2D.Float line)
    {
        _connectingArrow = line;
    }

    private void drawEntryPoints(Graphics g) {
        ArrayList<EntryPoint> entryPoints = this._controller.getProject().getSortCenter().getEntryPoints();
        
        for (EntryPoint entryPoint : entryPoints) {
            drawEntryPoint(g, entryPoint);
        }
    }

    private void drawEntryPoint(Graphics g, EntryPoint entryPoint) {
        Point2D.Float position = entryPoint.getPosition();
        Point2D.Float dimension = entryPoint.getDimensions();
        
        int positionMeterX = _viewport.meterToPix(position.x);
        int positionMeterY = _viewport.meterToPix(position.y);
        
        int dimensionMeterX = _viewport.meterToPix(dimension.x - 1);
        int dimensionMeterY = _viewport.meterToPix(dimension.y - 1);
        
        ClassLoader cl = this.getClass().getClassLoader();
        Image img = Toolkit.getDefaultToolkit().getImage(cl.getResource("image/entry.png"));
        
        g.drawImage(img, positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY, _viewport);
        ArrayList<IOlet> iolets = new ArrayList<IOlet>();
        
        iolets.addAll(entryPoint.getIOlets());
        
        if (_controller.selectedElementIs(entryPoint)) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            g2.setColor(_selectedContourColor);
            g2.drawRect(positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY);
        }

        for (IOlet iol : iolets) {
            drawIOlet(g, iol);
        }
    }
    
    private void drawExitPoints(Graphics g) {
        ArrayList<ExitPoint> exitPoints = this._controller.getProject().getSortCenter().getExitPoints();
        
        for (ExitPoint exitPoint : exitPoints) {
            drawExitPoint(g, exitPoint);
        }
    }
    
    private void drawExitPoint(Graphics g, ExitPoint exitPoint) {
        Point2D.Float position = exitPoint.getPosition();
        Point2D.Float dimension = exitPoint.getDimensions();
        
        int positionMeterX = _viewport.meterToPix(position.x);
        int positionMeterY = _viewport.meterToPix(position.y);
        
        int dimensionMeterX = _viewport.meterToPix(dimension.x - 1);
        int dimensionMeterY = _viewport.meterToPix(dimension.y - 1);
        
        Image img = Toolkit.getDefaultToolkit().getImage("src/image/exit.png");
        
        g.drawImage(img, positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY, _viewport);
        ArrayList<IOlet> iolets = new ArrayList<IOlet>();
        
        iolets.addAll(exitPoint.getIOlets());
        
        if (_controller.selectedElementIs(exitPoint)) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            g2.setColor(_selectedContourColor);
            g2.drawRect(positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY);
        }

        for (IOlet iol : iolets) {
            drawIOlet(g, iol);
        }
    }
}
