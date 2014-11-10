/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Controller;

import Presentation.Swing.AboutUs;

/**
 *
 * @author Marcleking
 */
public class MainController {
    
    public void showAboutUs() {
        AboutUs view = new AboutUs();
        view.setVisible(true);
    }
    
    
}
