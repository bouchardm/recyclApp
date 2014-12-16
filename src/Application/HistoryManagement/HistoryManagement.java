package Application.HistoryManagement;

import Domain.SortCenter;
import java.util.Stack;

public class HistoryManagement {

    private Stack<SortCenter> _undoStack;
    private Stack<SortCenter> _redoStack;
    private SortCenter currentSortCenter;

    public HistoryManagement() {
        _undoStack = new Stack();
        _redoStack = new Stack();
        
    }
    
    public void setCurrentState (SortCenter sortCenter)
    {
    currentSortCenter = sortCenter;
    }
    public HistoryManagement(SortCenter sortCenter) {
        _undoStack = new Stack();
        _redoStack = new Stack();
        currentSortCenter = sortCenter;
        
    }

    public void clearUndoStack() {
        _undoStack.clear();
    }

    public void clearRedoStack() {
        _redoStack.clear();
    }

    public void addToRedoStack(SortCenter sortCenter) {
        _redoStack.push(sortCenter);

    }

    public void addToUndoStack(SortCenter sortCenter) {
        _undoStack.push(sortCenter);

    }

    public SortCenter Undo() {
        SortCenter sortCenter = null;
 
        if (!_undoStack.empty()) {
            
            _redoStack.push(currentSortCenter);
            sortCenter = _undoStack.pop();         
            currentSortCenter = null;
             currentSortCenter = sortCenter;

        }

        return sortCenter;
    }

    public SortCenter Redo() {

        SortCenter sortCenter = null;
        
        if (!_redoStack.empty()) {
            
            _undoStack.push(currentSortCenter);
            sortCenter = _redoStack.pop();
            currentSortCenter = null;
           currentSortCenter = sortCenter;
         
        }
        return sortCenter;
    }
    public Stack<SortCenter> getUndoStack() {
        return _undoStack;
    }

    /**
     * @param _undoStack the _undoStack to set
     */
    public void setUndoStack(Stack<SortCenter> _undoStack) {
        this._undoStack = _undoStack;
    }

    /**
     * @return the _redoStack
     */
    public Stack<SortCenter> getRedoStack() {
        return _redoStack;
    }

    /**
     * @param _redoStack the _redoStack to set
     */
    public void setRedoStack(Stack<SortCenter> _redoStack) {
        this._redoStack = _redoStack;
    }

}
