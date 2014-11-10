/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Controller;

import Domain.Project;
import Presentation.Swing.AboutUs;
import java.awt.geom.Point2D;

/**
 *
 * @author Marcleking
 */
public class Controller
{
    private final Project project;
    
    public Controller()
    {
        project = new Project();
    }
    
    public void showAboutUs() {
        AboutUs view = new AboutUs();
        view.setVisible(true);
    }
    
    
    // ************ SortCenter ***************
    
    public Point2D.Float getSortCenterDimensions()
    {
        return project.getSortCenter().getDimensions();
    }
    
    
}
