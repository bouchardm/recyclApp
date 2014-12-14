/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TechnicalServices;

import Application.Controller.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marcleking
 */
public class Utility {
    
    // Fonction provenant de : http://stackoverflow.com/questions/4765469/how-to-retrieve-jtable-data-as-an-array
    public static Object[][] getTableData (JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];
        for (int i = 0 ; i < nRow ; i++)
            for (int j = 0 ; j < nCol ; j++)
                tableData[i][j] = dtm.getValueAt(i,j);
        return tableData;
    }
    
    public static javax.swing.table.DefaultTableModel generateTableFromMatterBasket(HashMap<Integer, Float> listMatter, Controller controller) {
        String[] headers = new String[2];
        headers[0] = "Matière";
        headers[1] = "Quantités (kg/h)";
        
        String[][] matters = new String[listMatter.size()][headers.length];
        
        int i = 0;
        for (Map.Entry<Integer, Float> entrySet : listMatter.entrySet()) {
            Integer key = entrySet.getKey();
            Float value = entrySet.getValue();
            
            matters[i][0] = controller.getMatterName(key);
            matters[i][1] = value.toString();
            
            i++;
        }
        
        DefaultTableModel result = new javax.swing.table.DefaultTableModel(matters, headers);
        
        return result;
    }
    
    public static javax.swing.table.DefaultTableModel generateTableFromMatterList(ArrayList<HashMap<Integer, String>> matterList) {
        String[] headers = new String[2];
        headers[0] = "Identifiant de la matière";
        headers[1] = "Nom de la matière";
        
        String[][] matters = new String[matterList.size()][headers.length];
        
        int i = 0;
        for (Iterator<HashMap<Integer, String>> iterator = matterList.iterator(); iterator.hasNext();) {
            HashMap<Integer, String> matter = iterator.next();
            
            Integer key = matter.entrySet().iterator().next().getKey();
            String value = matter.entrySet().iterator().next().getValue();
            
            matters[i][0] = key.toString();
            matters[i][1] = value;
            
            i++;
        }
        
        DefaultTableModel result = new javax.swing.table.DefaultTableModel(matters, headers);
        return result;
    }
            
}
