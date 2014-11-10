/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Controller;

import Presentation.Swing.AboutUs;
import java.awt.geom.Point2D;

/**
 *
 * @author Marcleking
 */
public class Controller {
    
    public void showAboutUs() {
        AboutUs view = new AboutUs();
        view.setVisible(true);
    }
    
    
    public Point2D getSortCenterDimensions()
    {
        return null;
    }
    
    
}
