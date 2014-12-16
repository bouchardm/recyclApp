/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Swing;

import Application.Controller.Controller;
import Domain.IOlet;
import Domain.Conveyor;
import Domain.ConveyorBend;
import Domain.EntryPoint;
import Domain.ExitPoint;
import Domain.Junction;
import Domain.Station;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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
    private Color _contourColor;
    private Color _selectedContourColor;
    private Line2D.Float _connectingArrow;
    private Font _font;

    public SortCenterDrawer(Controller controller, Viewport viewport) {
        this._controller = controller;
        this._viewport = viewport;
        _contourColor = Color.BLACK;
        _selectedContourColor = Color.CYAN;
        _connectingArrow = null;
        _font = new Font("TimesRoman", Font.BOLD, (int)(_viewport.getZoomFactor()*10));
    }
    

    public void draw(Graphics g)
    {
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
            Color color = _controller.getProject().getSortCenter().getColor();
            if (_controller.isFloorSelected())
            {
                color = (Color)_controller.getSelectedElementAttribute("color");
            }
            g.setColor(color);
            g.fillRect(margin, margin, width, height);
            g.setColor(_contourColor);
            
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            
            g.drawImage(_controller.getSortCenterImg(), margin, margin, width, height, _viewport);
            
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

        for (int i=0; i < sortStationList.size(); i++) {
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

        if (station.getErrorMessages().size() > 0) {
            g.setColor(Color.red);
            g.fillRect(positionMeterX - 5, positionMeterY - 5, dimensionMeterX + 10, dimensionMeterY + 10);
        }
        
        // Dessin de la stations
        

        g.setColor(station.getColor());
        

        g.fillRect(positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY);
        
        g.setColor(_contourColor);
        if (_controller.selectedElementIs(station)) {
            g.setColor(_selectedContourColor);
        }
        g.drawRect(positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY);
        
        if (station.getImg() != null)
        {
            g.drawImage(station.getImg(), positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY, _viewport);
        }
        g.setFont(_font);
        g.setColor(contrastColor(station.getColor()));
        g.drawString(station.getName(), positionMeterX + (int)(dimensionMeterX*0.05), positionMeterY + (int)(dimensionMeterY*0.2));

        iolets.addAll(station.getIOlets());

        for (IOlet iol : iolets) {
            drawIOlet(g, iol);
        }
    }

    private void drawIOlet(Graphics g, IOlet iolet) {
        g.setColor(iolet.getColor());
        
        Ellipse2D.Float circle = iolet.getCircle();
        int x = _viewport.meterToPix(circle.x);
        int y = _viewport.meterToPix(circle.y);
        int w = _viewport.meterToPixDim(circle.width);
        int h = _viewport.meterToPixDim(circle.height);
        
        
        g.fillOval(x, y, w, h);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(_contourColor);
        if (_controller.selectedElementIs(iolet))
        {
            g2.setColor(_selectedContourColor);
        }
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(_viewport.meterToPix(circle.x),
                    _viewport.meterToPix(circle.y),
                    _viewport.meterToPixDim(circle.width),
                    _viewport.meterToPixDim(circle.height));
        
        g.setColor(contrastColor(iolet.getColor()));
        g.drawString(iolet.getIdentifier(), (int)(x+w/2.7), (int)(y+h/1.3));
    }

    private void drawJunctions(Graphics g) {
        
        
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
        
        if (junction.getErrorMessages().size() > 0) {
            g.setColor(Color.red);
            g.fillRect(positionMeterX - 5, positionMeterY - 5, dimensionMeterX + 10, dimensionMeterY + 10);
        }
        
        g.setColor(junction.getColor());
        g.fillRect(positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY);
        g.drawImage(img, positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY, _viewport);
        ArrayList<IOlet> iolets = new ArrayList<IOlet>();
        
        
        iolets.addAll(junction.getIOlets());
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setColor(_contourColor);
        if (_controller.selectedElementIs(junction)) {
            
            g2.setColor(_selectedContourColor);
        }
        g2.drawRect(positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY);

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
        Point2D.Float position1 = conveyor.getStartPoint().getPosition();
        Point2D.Float position2 = conveyor.getEndPoint().getPosition();
        
        x1 = _viewport.meterToPix(position1.x);
        y1 = _viewport.meterToPix(position1.y);
        x2 = _viewport.meterToPix(position2.x);
        y2 = _viewport.meterToPix(position2.y);
        
        g.setColor(conveyor.getColor());

        if (conveyor.getErrorMessages().size() > 0) {
            g.setColor(Color.red);
        }
        
        if (_controller.selectedElementIs(conveyor)) {
            g.setColor(_selectedContourColor);
        }
        
        ConveyorBend selectedBend = null;
        int bendX, bendY;
        for (ConveyorBend bend: conveyor.getBends())
        {
            bendX = _viewport.meterToPix(bend.getPosition().x);
            bendY = _viewport.meterToPix(bend.getPosition().y);
            g.drawLine(x1, y1, bendX, bendY);
            drawBend(g, bend);
            if (_controller.selectedElementIs(bend))
            {
                selectedBend = bend;
            }
            x1 = bendX;
            y1 = bendY;
        }
        
        drawArrow(g, x1, y1, x2, y2);
        if (selectedBend != null)
        {
            g.setColor(_selectedContourColor);
            drawBend(g, selectedBend);
        }

    }
    
    private void drawBend(Graphics g, ConveyorBend bend)
    {
        Color oldColor = g.getColor();
        Ellipse2D.Float circle = bend.getCircle();
        
        int x, y, d;
        x = _viewport.meterToPix(circle.x);
        y = _viewport.meterToPix(circle.y);
        d = _viewport.meterToPixDim(circle.width);
        
        g.fillOval(x, y, d, d);
        g.setColor(oldColor);
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
//        g.setStroke(new BasicStroke(3));
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
        
        if (entryPoint.getErrorMessages().size() > 0) {
            g.setColor(Color.red);
            g.fillRect(positionMeterX - 5, positionMeterY - 5, dimensionMeterX + 10, dimensionMeterY + 10);
        }
        
        g.setColor(entryPoint.getColor());
        g.fillRect(positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY);
        g.drawImage(img, positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY, _viewport);
        ArrayList<IOlet> iolets = new ArrayList<IOlet>();
        
        iolets.addAll(entryPoint.getIOlets());
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setColor(_contourColor);
        if (_controller.selectedElementIs(entryPoint)) {
            g2.setColor(_selectedContourColor);
        }
        g2.drawRect(positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY);

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
        
        ClassLoader cl = this.getClass().getClassLoader();
        Image img = Toolkit.getDefaultToolkit().getImage(cl.getResource("image/exit.png"));
        
        g.setColor(exitPoint.getColor());
        g.fillRect(positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY);
        g.drawImage(img, positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY, _viewport);
        ArrayList<IOlet> iolets = new ArrayList<IOlet>();
        
        iolets.addAll(exitPoint.getIOlets());
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setColor(_contourColor);
        if (_controller.selectedElementIs(exitPoint)) {
            
            g2.setColor(_selectedContourColor);
        }
        g2.drawRect(positionMeterX, positionMeterY, dimensionMeterX, dimensionMeterY);

        for (IOlet iol : iolets) {
            drawIOlet(g, iol);
        }
    }
    
    
    private Color contrastColor(Color color)
    {
        int r, g, b;
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();
        
        r = (255 - r) % 256;
        g = (255 - g) % 256;
        b = (255 - b) % 256;
        
        return new Color(r, g, b);
    }
}
