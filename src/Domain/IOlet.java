/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

/**
 *
 * @author Dany
 */
public abstract class IOlet
{
    private Node _parentNode;
    private Conveyor _conveyor;
    
    public IOlet(Node parentNode)
    {
        if (parentNode == null)
        {
            throw new IllegalArgumentException("node cannot be null");
        }
        _parentNode = parentNode;
        _conveyor = null;
    }
    
    public boolean IsFree()
    {
        return (_conveyor == null);
    }
    
    
}
