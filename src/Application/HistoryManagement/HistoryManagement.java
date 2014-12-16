package Application.HistoryManagement;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.util.Stack;

public class HistoryManagement {
    
    private Stack<XMLEncoder> _undoStack ;
    private Stack<XMLEncoder> _redoStack ;
    
    public  HistoryManagement()
    {
    _undoStack = new Stack();
    _redoStack = new Stack();
    
    }
    
    public void clearUndoStack()
    {
    _undoStack.clear();
    }
    
    public void clearRedoStack()
    {
    _redoStack.clear();
    }
    
    public void addToRedoStack(XMLEncoder encoder)
    {
    _redoStack.push(encoder);
    
    }
    
        public void addToUndoStack(XMLEncoder encoder)
    {
    _undoStack.push(encoder);
    
    }
    
    
    public XMLEncoder Undo() {
      XMLEncoder encoder = null;
      
      if(!_undoStack.empty()){
      encoder = _undoStack.pop();
      _redoStack.push(encoder);
      }
      
      return encoder;
    }
    
    
    public void Redo() {
            throw new UnsupportedOperationException();
    }

    /**
     * @return the _undoStack
     */
    public Stack<XMLEncoder> getUndoStack() {
        return _undoStack;
    }

    /**
     * @param _undoStack the _undoStack to set
     */
    public void setUndoStack(Stack<XMLEncoder> _undoStack) {
        this._undoStack = _undoStack;
    }

    /**
     * @return the _redoStack
     */
    public Stack<XMLEncoder> getRedoStack() {
        return _redoStack;
    }

    /**
     * @param _redoStack the _redoStack to set
     */
    public void setRedoStack(Stack<XMLEncoder> _redoStack) {
        this._redoStack = _redoStack;
    }
    
    
}