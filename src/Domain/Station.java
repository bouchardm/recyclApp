/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import java.awt.Image;



public class Station extends RectangularNode
{
    protected Image _img;
    
    public void setExit(int nbExit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setAttribute(String attribName, Object value)
    {
        try
        {
            super.setAttribute(attribName, value);
        }
        catch (IllegalArgumentException e)
        {
            switch (attribName)
            {
                default:
                    throw new IllegalArgumentException(String.format("no method for set %s", attribName));
            }
        }
        
    }


    @Override
    public Object getAttribute(String attribName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    


    
    
}
