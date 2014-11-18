/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import java.awt.Image;
import java.util.ArrayList;


public class Station extends RectangularNode
{
    protected Image _img;
    public void setExit(int nbExit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    private Inlet _inlet;
    private ArrayList<Outlet> _outlets;
 
//        _inlet = new Inlet(this);
//        _outlets = new ArrayList<>();
//        setExit(nbOutlet);
        

    
        @Override
    public void setExit(int nbExit)
    {
        for (int i = 0; i < nbExit ; i++)
        {
            Outlet outlet = new Outlet(this);
            _outlets.add(outlet);
        }
    }
    }
    
}
